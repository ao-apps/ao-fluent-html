/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-fluent-html.
 *
 * ao-fluent-html is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-fluent-html is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-fluent-html.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.html.any;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import com.aoindustries.io.ContentType;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Coercion;
import com.aoindustries.lang.Strings;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.i18n.MarkupCoercion;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Locale;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
// TODO: Extend RawTextElement: https://html.spec.whatwg.org/multipage/syntax.html#raw-text-elements
abstract public class AnySCRIPT<
	D  extends AnyDocument<D>,
	PC extends AnyScriptSupportingContent<D, PC>,
	E  extends AnySCRIPT<D, PC, E>
> extends Element<D, PC, E> implements
	com.aoindustries.html.any.attributes.Boolean.Async<E>,
	com.aoindustries.html.any.attributes.Enum.Charset<E, com.aoindustries.html.any.attributes.Enum.Charset.Value>,
	com.aoindustries.html.any.attributes.Boolean.Defer<E>,
	com.aoindustries.html.any.attributes.Url.Src<E>,
	// TODO: type
	// TODO: xmlSpace
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	com.aoindustries.html.any.attributes.Text.ClassNoHtml4<E>,
	com.aoindustries.html.any.attributes.Text.IdNoHtml4<E>,
	com.aoindustries.html.any.attributes.Text.StyleNoHtml4<E>,
	com.aoindustries.html.any.attributes.Text.TitleNoHtml4<E>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <script>: AlmostGlobalAttributes<E>
	com.aoindustries.html.any.attributes.event.window.Onerror<E>,
	com.aoindustries.html.any.attributes.event.window.Onload<E>
{

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public enum Type {
		/**
		 * The default type for (X)HTML 5.
		 */
		APPLICATION_JAVASCRIPT(ContentType.JAVASCRIPT),

		/**
		 * The default type for XHTML 1.0 / HTML 4.
		 *
		 * @deprecated  Use {@link #APPLICATION_JAVASCRIPT} in HTML 5.
		 */
		@Deprecated
		TEXT_JAVASCRIPT(ContentType.JAVASCRIPT_OLD),

		/**
		 * A JSON script.
		 */
		APPLICATION_JSON(ContentType.JSON),

		/**
		 * A JSON linked data script.
		 */
		APPLICATION_JD_JSON(ContentType.LD_JSON),

		APPLICATION_ECMASCRIPT(ContentType.ECMASCRIPT);

		private final String contentType;

		private Type(String contentType) {
			this.contentType = contentType;
		}

		@Override
		public String toString() {
			return contentType;
		}

		public String getContentType() {
			return contentType;
		}

		private static boolean assertAllLowerCaseAndTrimmed() {
			for(Type type : values()) {
				if(!type.contentType.equals(type.contentType.toLowerCase(Locale.ROOT))) throw new AssertionError("Content types must be lowercase as looked-up later");
				if(!type.contentType.equals(type.contentType.trim())) throw new AssertionError("Content types must be trimmed as looked-up later");
			}
			return true;
		}
		static {
			assert assertAllLowerCaseAndTrimmed();
		}
	}

	private final String type;

	protected AnySCRIPT(D document, PC pc) {
		super(document, pc);
		this.type = null;
	}

	protected AnySCRIPT(D document, PC pc, String type) {
		super(document, pc);
		type = Strings.trimNullIfEmpty(type);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	protected AnySCRIPT(D document, PC pc, Type type) {
		super(document, pc);
		this.type = (type == null) ? null : type.getContentType();
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<script", false);
		E s = type();
		assert s == this;
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 *
	 * @see Doctype#scriptType(java.lang.Appendable)
	 */
	@SuppressWarnings("deprecation")
	protected E type() throws IOException {
		Writer out = document.getUnsafe(null);
		// TODO: Check didBody here and other attributes, perhaps in some central attribute registry that detects duplicate attributes, too
		if(
			type == null
			|| type.equals(ContentType.JAVASCRIPT)
			|| type.equals(ContentType.JAVASCRIPT_OLD)
		) {
			String typeAttr = document.doctype.getScriptType();
			int len = typeAttr.length();
			if(len > 0) {
				if(document.getAtnl()) {
					assert typeAttr.charAt(0) == ' ';
					document.autoIndent(out, 1);
					out.write(typeAttr, 1, len - 1);
					document.clearAtnl();
				} else {
					out.write(typeAttr);
				}
			}
		} else {
			if(document.getAtnl()) {
				document.autoIndent(out, 1);
				out.write("type=\"");
				document.clearAtnl();
			} else {
				out.write(" type=\"");
			}
			encodeTextInXhtmlAttribute(type, out);
			out.append('"');
		}
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	protected MediaType getMediaType() throws UnsupportedEncodingException {
		return type == null ? MediaType.JAVASCRIPT : MediaType.getMediaTypeForContentType(type);
	}

	protected MediaEncoder getMediaEncoder(MediaType mediaType) throws UnsupportedEncodingException {
		return MediaEncoder.getInstance(document.encodingContext, mediaType, MediaType.XHTML);
	}

	@SuppressWarnings("deprecation")
	protected boolean doCdata() {
		return
			document.serialization == Serialization.XML
			&& (
				type == null
				|| type.equals(ContentType.JAVASCRIPT)
				|| type.equals(ContentType.JAVASCRIPT_OLD)
				|| type.equals(ContentType.ECMASCRIPT)
				|| type.equals(ContentType.ECMASCRIPT_OLD)
			);
	}

	private boolean didBody;

	protected void startBody(Writer out) throws IOException {
		if(!didBody) {
			document
				.autoIndent(out)
				.unsafe(out, doCdata() ? (">//<![CDATA[" + NL) : (">" + NL), true)
				.incDepth();
			didBody = true;
		}
	}

	// TODO: Return a "Body" / "ScriptBody" that only allows additional out or closing the tag.
	// TODO:     Setting attributes after startBody() would create invalid HTML.
	// TODO:     Similar for "text", too.
	// TODO: Interface for "out" with default methods? (Another for "text", too)
	@SuppressWarnings("UseSpecificCatch")
	public E out(Object script) throws IOException {
		while(script instanceof IOSupplierE<?, ?>) {
			try {
				script = ((IOSupplierE<?, ?>)script).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(script instanceof ScriptWriter) {
			try {
				@SuppressWarnings("unchecked") ScriptWriter<D, ?> writer = (ScriptWriter<D, ?>)script;
				return out(writer);
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		script = Coercion.nullIfEmpty(script);
		if(script != null) {
			Writer out = document.getUnsafe(null);
			startBody(out);
			// Allow text markup from translations
			MediaType mediaType = getMediaType();
			MarkupCoercion.write(
				script,
				mediaType.getMarkupType(),
				true,
				getMediaEncoder(mediaType),
				false,
				out
			);
			document.clearAtnl(); // Unknown, safe to assume not at newline
		}
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	public <Ex extends Throwable> E out(IOSupplierE<?, Ex> script) throws IOException, Ex {
		return out((script == null) ? null : script.get());
	}

	/**
	 * @param  <D>   This document type
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	// TODO: Consolidate with AttributeWriter?
	@FunctionalInterface
	public static interface ScriptWriter<
		D  extends AnyDocument<D>,
		Ex extends Throwable
	> {
		void writeScript(DocumentMediaWriter<D> script) throws IOException, Ex;
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	public <Ex extends Throwable> E out(ScriptWriter<D, Ex> script) throws IOException, Ex {
		if(script != null) {
			MediaEncoder encoder = getMediaEncoder(getMediaType());
			Writer out = document.getUnsafe(null);
			startBody(out);
			script.writeScript(
				new DocumentMediaWriter<>(
					document,
					encoder,
					new NoCloseWriter(out)
				)
			);
			document.clearAtnl(); // Unknown, safe to assume not at newline
		}
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * Writes the script, automatically closing the script via
	 * {@link #__()} on {@link DocumentMediaWriter#close()}.
	 * This is well suited for use in a try-with-resources block.
	 */
	// TODO: __() method to end text?  Call it "ContentWriter"?
	public DocumentMediaWriter<D> out__() throws IOException {
		MediaEncoder encoder = getMediaEncoder(getMediaType());
		Writer out = document.getUnsafe(null);
		startBody(out);
		return new DocumentMediaWriter<D>(document, encoder, out) {
			@Override
			public void close() throws IOException {
				__();
			}
		};
	}

	/**
	 * Closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public PC __() throws IOException {
		Writer out = document.getUnsafe(null);
		if(!didBody) {
			document.autoIndent(out).unsafe(out, "></script>", false);
		} else {
			document.decDepth().nli(out).unsafe(out, doCdata() ? "//]]></script>" : "</script>", false);
		}
		document.autoNl(out);
		return pc;
	}
}
