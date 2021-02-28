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
package com.aoindustries.html;

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
import java.util.Locale;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Extend RawTextElement: https://html.spec.whatwg.org/#raw-text-elements
public class SCRIPT<PC extends ScriptSupportingContent<PC>> extends Element<SCRIPT<PC>, PC> implements
	com.aoindustries.html.attributes.Boolean.Async<SCRIPT<PC>>,
	com.aoindustries.html.attributes.Enum.Charset<SCRIPT<PC>, com.aoindustries.html.attributes.Enum.Charset.Value>,
	com.aoindustries.html.attributes.Boolean.Defer<SCRIPT<PC>>,
	com.aoindustries.html.attributes.Url.Src<SCRIPT<PC>>,
	// TODO: type
	// TODO: xmlSpace
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	com.aoindustries.html.attributes.Text.ClassNoHtml4<SCRIPT<PC>>,
	com.aoindustries.html.attributes.Text.IdNoHtml4<SCRIPT<PC>>,
	com.aoindustries.html.attributes.Text.StyleNoHtml4<SCRIPT<PC>>,
	com.aoindustries.html.attributes.Text.TitleNoHtml4<SCRIPT<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <script>: Attributes.Event.AlmostGlobal<SCRIPT<PC>>
	com.aoindustries.html.attributes.event.window.Onerror<SCRIPT<PC>>,
	com.aoindustries.html.attributes.event.window.Onload<SCRIPT<PC>>
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

	public SCRIPT(Document document, PC pc) {
		super(document, pc);
		this.type = null;
	}

	public SCRIPT(Document document, PC pc, String type) {
		super(document, pc);
		type = Strings.trimNullIfEmpty(type);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	public SCRIPT(Document document, PC pc, Type type) {
		super(document, pc);
		this.type = (type == null) ? null : type.getContentType();
	}

	@Override
	protected SCRIPT<PC> writeOpen() throws IOException {
		document.out.write("<script");
		SCRIPT<PC> s = type();
		assert s == this;
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 *
	 * @see Doctype#scriptType(java.lang.Appendable)
	 */
	@SuppressWarnings("deprecation")
	protected SCRIPT<PC> type() throws IOException {
		// TODO: Check didBody here and other attributes, perhaps in some central attribute registry that detects duplicate attributes, too
		if(
			type == null
			|| type.equals(ContentType.JAVASCRIPT)
			|| type.equals(ContentType.JAVASCRIPT_OLD)
		) {
			document.doctype.scriptType(document.out);
		} else {
			document.out.write(" type=\"");
			encodeTextInXhtmlAttribute(type, document.out);
			document.out.write('"');
		}
		return this;
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

	protected void startBody() throws IOException {
		if(!didBody) {
			document.out.write('>');
			if(doCdata()) document.out.write("//<![CDATA[");
			document.incDepth().nli();
			didBody = true;
		}
	}

	// TODO: Return a "Body" / "ScriptBody" that only allows additional out or closing the tag.
	// TODO:     Setting attributes after startBody() would create invalid HTML.
	// TODO:     Similar for "text", too.
	// TODO: Interface for "out" with default methods? (Another for "text", too)
	@SuppressWarnings("UseSpecificCatch")
	public SCRIPT<PC> out(Object script) throws IOException {
		while(script instanceof IOSupplierE<?, ?>) {
			try {
				script = ((IOSupplierE<?, ?>)script).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(script instanceof ScriptWriter) {
			try {
				return out((ScriptWriter<?>)script);
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		script = Coercion.nullIfEmpty(script);
		if(script != null) {
			startBody();
			// Allow text markup from translations
			MediaType mediaType = getMediaType();
			MarkupCoercion.write(
				script,
				mediaType.getMarkupType(),
				true,
				getMediaEncoder(mediaType),
				false,
				document.out
			);
		}
		return this;
	}

	public <Ex extends Throwable> SCRIPT<PC> out(IOSupplierE<?, Ex> script) throws IOException, Ex {
		return out((script == null) ? null : script.get());
	}

	// TODO: Consolidate with AttributeWriter?
	@FunctionalInterface
	public static interface ScriptWriter<Ex extends Throwable> {
		void writeScript(DocumentMediaWriter script) throws IOException, Ex;
	}

	public <Ex extends Throwable> SCRIPT<PC> out(ScriptWriter<Ex> script) throws IOException, Ex {
		if(script != null) {
			MediaEncoder encoder = getMediaEncoder(getMediaType());
			startBody();
			script.writeScript(
				new DocumentMediaWriter(
					document,
					encoder,
					new NoCloseWriter(document.out)
				)
			);
		}
		return this;
	}

	/**
	 * Writes the script, automatically closing the script via
	 * {@link #__()} on {@link DocumentMediaWriter#close()}.
	 * This is well suited for use in a try-with-resources block.
	 */
	// TODO: __() method to end text?  Call it "ContentWriter"?
	public DocumentMediaWriter out__() throws IOException {
		MediaEncoder encoder = getMediaEncoder(getMediaType());
		startBody();
		return new DocumentMediaWriter(document, encoder) {
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
		if(!didBody) {
			document.out.write("></script>");
		} else {
			document.decDepth().nli();
			if(doCdata()) document.out.write("//]]>");
			document.out.write("</script>");
		}
		return pc;
	}
}
