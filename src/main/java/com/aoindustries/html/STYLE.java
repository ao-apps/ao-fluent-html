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
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.io.ContentType;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Coercion;
import com.aoindustries.lang.Strings;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-style-element">4.2.6 The style element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Extend RawTextElement: https://html.spec.whatwg.org/multipage/syntax.html#raw-text-elements
public class STYLE<PC extends MetadataContent<PC>> extends Element<STYLE<PC>, PC> implements
	com.aoindustries.html.attributes.Text.Media<STYLE<PC>>,
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	com.aoindustries.html.attributes.Text.ClassNoHtml4<STYLE<PC>>,
	com.aoindustries.html.attributes.Text.IdNoHtml4<STYLE<PC>>,
	com.aoindustries.html.attributes.Text.StyleNoHtml4<STYLE<PC>>,
	com.aoindustries.html.attributes.Text.TitleNoHtml4<STYLE<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <style>: Attributes.Event.AlmostGlobal<STYLE<PC>>
	com.aoindustries.html.attributes.event.window.Onerror<LINK<PC>>, // Only listed at https://www.w3schools.com/tags/ref_attributes.asp
	com.aoindustries.html.attributes.event.window.Onload<LINK<PC>>
{

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public enum Type {
		/**
		 * The default type.
		 */
		TEXT_CSS(ContentType.CSS);

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

	public STYLE(Document document, PC pc) {
		super(document, pc);
		this.type = null;
	}

	public STYLE(Document document, PC pc, String type) {
		super(document, pc);
		type = Strings.trimNullIfEmpty(type);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	public STYLE(Document document, PC pc, Type type) {
		super(document, pc);
		this.type = (type == null) ? null : type.getContentType();
	}

	@Override
	protected STYLE<PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<style", false);
		return type();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.
	 *
	 * @see Doctype#styleType(java.lang.Appendable)
	 */
	protected STYLE<PC> type() throws IOException {
		Writer out = document.getUnsafe(null);
		if(
			type == null
			|| type.equals(ContentType.CSS)
		) {
			String typeAttr = document.doctype.getStyleType();
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
		return this;
	}

	protected MediaType getMediaType() throws IOException {
		return MediaType.TEXT; // TODO: Version for CSS (with automatic URL rewriting?)
	}

	protected MediaEncoder getMediaEncoder(MediaType mediaType) throws IOException {
		// TODO: This is in a CDATA context, is this the correct way?  Probably not, but how to protect close CDATA ]]>?
		// TODO: Make CSS a fully-supported MediaType, then so this similar to SCRIPT.java
		return textInXhtmlEncoder;
	}

	protected boolean doCdata() {
		return document.serialization == Serialization.XML;
	}

	private boolean didBody;

	protected void startBody(Writer out) throws IOException {
		if(!didBody) {
			document
				.autoIndent(out)
				.unsafe(out, doCdata() ? (">/*<![CDATA[*/" + NL) : (">" + NL), true)
				.incDepth();
			didBody = true;
		}
	}

	// TODO: Out parameter with MediaType, that automatically picks the encoder
	// TODO: Separate "Write" for direct writing (no encoding)?
	@SuppressWarnings("UseSpecificCatch")
	public STYLE<PC> out(Object style) throws IOException {
		while(style instanceof IOSupplierE<?, ?>) {
			try {
				style = ((IOSupplierE<?, ?>)style).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(style instanceof StyleWriter) {
			try {
				return out((StyleWriter<?>)style);
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		style = Coercion.nullIfEmpty(style);
		if(style != null) {
			Writer out = document.getUnsafe(null);
			startBody(out);
			// Allow text markup from translations
			MediaType mediaType = getMediaType();
			MarkupCoercion.write(
				style,
				// TODO: Find and fix other uses of MarkupType.JAVASCRIPT that should be CSS
				MarkupType.CSS, // TODO: Once CSS is a full-on media type: mediaType.getMarkupType(),
				true,
				getMediaEncoder(mediaType),
				false,
				out
			);
			document.clearAtnl(); // Unknown, safe to assume not at newline
		}
		return this;
	}

	public <Ex extends Throwable> STYLE<PC> out(IOSupplierE<?, Ex> style) throws IOException, Ex {
		return out((style == null) ? null : style.get());
	}

	// TODO: Consolidate with AttributeWriter?
	@FunctionalInterface
	public static interface StyleWriter<Ex extends Throwable> {
		void writeStyle(DocumentMediaWriter style) throws IOException, Ex;
	}

	public <Ex extends Throwable> STYLE<PC> out(StyleWriter<Ex> style) throws IOException, Ex {
		if(style != null) {
			MediaEncoder encoder = getMediaEncoder(getMediaType());
			Writer out = document.getUnsafe(null);
			startBody(out);
			style.writeStyle(
				new DocumentMediaWriter(
					document,
					encoder,
					new NoCloseWriter(out)
				)
			);
			document.clearAtnl(); // Unknown, safe to assume not at newline
		}
		return this;
	}

	/**
	 * Writes the style, automatically closing the style via
	 * {@link #__()} on {@link DocumentMediaWriter#close()}.
	 * This is well suited for use in a try-with-resources block.
	 */
	// TODO: __() method to end text?  Call it "ContentWriter"?
	public DocumentMediaWriter out__() throws IOException {
		MediaEncoder encoder = getMediaEncoder(getMediaType());
		Writer out = document.getUnsafe(null);
		startBody(out);
		return new DocumentMediaWriter(document, encoder, out) {
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
			document.autoIndent(out).unsafe(out, "></style>", false);
		} else {
			document.decDepth().nli(out).unsafe(out, doCdata() ? "/*]]>*/</style>" : "</style>", false);
		}
		document.autoNl(out);
		return pc;
	}
}
