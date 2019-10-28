/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019  AO Industries, Inc.
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
import static com.aoindustries.encoding.JavaScriptInXhtmlAttributeEncoder.javaScriptInXhtmlAttributeEncoder;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.util.WrappedException;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * @author  AO Industries, Inc.
 */
// TODO: Rename Html to Document, and make an element <html>?
abstract public class Element<E extends Element<E>> {

	protected final Html html;

	public Element(Html html) {
		this.html = html;
	}

	abstract protected E open() throws IOException;

	// TODO: Make shared attributeImpl methods, with things like a given value encoder and boolean if skip when null
	// TODO: Don't add attribute when value is null
	@SuppressWarnings("unchecked")
	public E attribute(String name, Object value) throws IOException {
		if(value instanceof AttributeWriter) return attribute(name, (AttributeWriter)value);
		if(value instanceof AttributeWriterE) {
			try {
				return (E)attributeE(name, (AttributeWriterE)value);
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		// TODO: Validate attribute name?
		html.out.write(' ');
		html.out.write(name);
		if(value == null) {
			if(html.serialization == Serialization.XML) html.out.write("=\"\"");
		} else {
			html.out.write("=\"");
			Coercion.write(value, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return (E)this;
	}

	public MediaWriter attribute(String name) throws IOException {
		// TODO: Validate attribute name?
		html.out.write(' ');
		html.out.write(name);
		html.out.write("=\"");
		return new MediaWriter(textInXhtmlAttributeEncoder, html.out) {
			// Java 1.8: Lambda
			@Override
			public void close() throws IOException {
				html.out.write('"');
			}
		};
	}

	// Java 1.8: @Functional
	public static interface AttributeWriterE<Ex extends Throwable> {
		void writeAttribute(MediaWriter value) throws IOException, Ex;
	}

	@SuppressWarnings("unchecked")
	public <Ex extends Throwable> E attributeE(String name, AttributeWriterE<Ex> value) throws IOException, Ex {
		if(value == null) {
			return attribute(name, null);
		} else {
			try (MediaWriter out = attribute(name)) {
				value.writeAttribute(out);
			}
		}
		return (E)this;
	}

	// Java 1.8: @Functional
	public static interface AttributeWriter extends AttributeWriterE<RuntimeException> {
		@Override
		void writeAttribute(MediaWriter value) throws IOException;
	}

	public E attribute(String name, AttributeWriter value) throws IOException {
		return attributeE(name, value);
	}

	// TODO: Auto-closeable attribute writers for streaming implementations

	// <editor-fold desc="Global Attributes">
	@SuppressWarnings("unchecked")
	public E clazz(Object clazz) throws IOException {
		if(clazz != null) {
			html.out.write(" class=\"");
			Coercion.write(clazz, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return (E)this;
	}

	@SuppressWarnings("unchecked")
	public E id(Object id) throws IOException {
		if(id != null) {
			html.out.write(" id=\"");
			Coercion.write(id, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return (E)this;
	}

	@SuppressWarnings("unchecked")
	public E style(Object style) throws IOException {
		if(style != null) {
			html.out.write(" style=\"");
			Coercion.write(style, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return (E)this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
	 */
	@SuppressWarnings("unchecked")
	public E tabindex(Integer tabindex) throws IOException {
		if(html.doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				accessor,
				"Element.invalidGlobalAttributeForDoctype",
				html.doctype,
				"tabindex"
			);
		}
		if(tabindex != null) {
			html.out.write(" tabindex=\"");
			html.out.write(tabindex.toString());
			html.out.write('"');
		}
		return (E)this;
	}

	@SuppressWarnings("unchecked")
	public E title(Object title) throws IOException {
		if(title != null) {
			html.out.write(" title=\"");
			// Allow text markup from translations
			Coercion.write(title, MarkupType.TEXT, textInXhtmlAttributeEncoder, false, html.out);
			html.out.write('"');
		}
		return (E)this;
	}
	// </editor-fold>

	// <editor-fold desc="Global Event Attributes">
	// https://www.w3schools.com/tags/ref_eventattributes.asp

	// <editor-fold desc="Mouse Events">
	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
	 */
	@SuppressWarnings("unchecked")
	public E onclick(Object onclick) throws IOException {
		if(onclick != null) {
			if(onclick instanceof AttributeWriter) return onclick((AttributeWriter)onclick);
			if(onclick instanceof AttributeWriterE) {
				try {
					return onclickE((AttributeWriterE<?>)onclick);
				} catch(Error|RuntimeException|IOException e) {
					throw e;
				} catch(Throwable t) {
					throw new WrappedException(t);
				}
			}
			html.out.write(" onclick=\"");
			// TODO: Find more places where we can do javascript markups (ao-taglib...)
			Coercion.write(onclick, MarkupType.JAVASCRIPT, javaScriptInXhtmlAttributeEncoder, false, html.out);
			html.out.write('"');
		}
		return (E)this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
	 */
	public MediaWriter onclick() throws IOException {
		html.out.write(" onclick=\"");
		return new MediaWriter(javaScriptInXhtmlAttributeEncoder, html.out) {
			// Java 1.8: Lambda
			@Override
			public void close() throws IOException {
				html.out.write('"');
			}
		};
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
	 */
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable> E onclickE(AttributeWriterE<Ex> onclick) throws IOException, Ex {
		if(onclick != null) {
			try (MediaWriter out = onclick()) {
				onclick.writeAttribute(out);
			}
		}
		return (E)this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
	 */
	public E onclick(AttributeWriter onclick) throws IOException {
		return onclickE(onclick);
	}

	// </editor-fold>

	// </editor-fold>
}
