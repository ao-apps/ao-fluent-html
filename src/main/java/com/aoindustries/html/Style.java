/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020  AO Industries, Inc.
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

import com.aoindustries.encoding.Coercion;
import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.encoding.Serialization;
import com.aoindustries.encoding.Supplier;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.exception.WrappedException;
import com.aoindustries.io.ContentType;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.lang.Strings;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.Locale;

/**
 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Style extends Element<Style> implements
	Attributes.Text.Media<Style>,
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Text.ClassNoHtml4<Style>,
	Attributes.Text.IdNoHtml4<Style>,
	Attributes.Text.StyleNoHtml4<Style>,
	Attributes.Text.TitleNoHtml4<Style>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <style>: Attributes.Event.AlmostGlobal<Style>
	Attributes.Event.Window.Onerror<Link>, // Only listed at https://www.w3schools.com/tags/ref_attributes.asp
	Attributes.Event.Window.Onload<Link>
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

	public Style(Html html) {
		super(html);
		this.type = null;
	}

	public Style(Html html, String type) {
		super(html);
		type = Strings.trimNullIfEmpty(type);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	public Style(Html html, Type type) {
		super(html);
		this.type = (type == null) ? null : type.getContentType();
	}

	@Override
	protected Style open() throws IOException {
		html.out.write("<style");
		return type();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.
	 *
	 * @see Doctype#styleType(java.lang.Appendable)
	 */
	protected Style type() throws IOException {
		if(
			type == null
			|| type.equals(ContentType.CSS)
		) {
			html.doctype.styleType(html.out);
		} else {
			html.out.write(" type=\"");
			encodeTextInXhtmlAttribute(type, html.out);
			html.out.write('"');
		}
		return this;
	}

	protected MediaType getMediaType() throws IOException {
		return MediaType.TEXT; // TODO: Version for CSS (with automatic URL rewriting?)
	}

	protected MediaEncoder getMediaEncoder(MediaType mediaType) throws IOException {
		// TODO: This is in a CDATA context, is this the correct way?  Probably not, but how to protect close CDATA ]]>?
		return textInXhtmlEncoder;
	}

	protected boolean doCdata() {
		return html.serialization == Serialization.XML;
	}

	private boolean didBody;

	protected void startBody() throws IOException {
		if(!didBody) {
			html.out.write('>');
			if(doCdata()) html.out.write("/*<![CDATA[*/");
			didBody = true;
		}
	}

	// TODO: Out parameter with MediaType, that automatically picks the encoder
	// TODO: Separate "Write" for direct writing (no encoding)?
	@SuppressWarnings("UseSpecificCatch")
	public Style out(Object style) throws IOException {
		while(style instanceof Supplier<?,?>) {
			try {
				style = ((Supplier<?,?>)style).get();
			} catch(Error | RuntimeException | IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		if(style instanceof StyleWriter) {
			try {
				return out((StyleWriter<?>)style);
			} catch(Error | RuntimeException | IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		style = Coercion.nullIfEmpty(style);
		if(style != null) {
			startBody();
			// Allow text markup from translations
			Coercion.write(
				style,
				// TODO: Compatible, but better to make an explicit value for MarkupType.CSS: mediaType.getMarkupType()
				MarkupType.JAVASCRIPT,
				getMediaEncoder(getMediaType()),
				false,
				html.out
			);
		}
		return this;
	}

	public <Ex extends Throwable> Style out(Supplier<?,Ex> style) throws IOException, Ex {
		return out((style == null) ? null : style.get());
	}

	// TODO: Consolidate with AttributeWriter?
	@FunctionalInterface
	public static interface StyleWriter<Ex extends Throwable> {
		void writeStyle(MediaWriter style) throws IOException, Ex;
	}

	public <Ex extends Throwable> Style out(StyleWriter<Ex> style) throws IOException, Ex {
		if(style != null) {
			MediaEncoder encoder = getMediaEncoder(getMediaType());
			startBody();
			style.writeStyle(
				new MediaWriter(
					html.encodingContext,
					encoder,
					new NoCloseWriter(html.out)
				)
			);
		}
		return this;
	}

	/**
	 * Writes the style, automatically closing the style via
	 * {@link #__()} on {@link MediaWriter#close()}.
	 * This is well suited for use in a try-with-resources block.
	 */
	public MediaWriter out__() throws IOException {
		MediaEncoder encoder = getMediaEncoder(getMediaType());
		startBody();
		return new MediaWriter(html.encodingContext, encoder, html.out) {
			@Override
			public void close() throws IOException {
				__();
			}
		};
	}

	public Html __() throws IOException {
		if(!didBody) {
			html.out.write("></style>");
		} else {
			if(doCdata()) html.out.write("/*]]>*/");
			html.out.write("</style>");
		}
		return html;
	}
}
