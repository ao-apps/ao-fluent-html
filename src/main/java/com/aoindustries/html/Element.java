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
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.util.WrappedException;
import java.io.IOException;

/**
 * @author  AO Industries, Inc.
 */
// TODO: Rename Html to Document, and make an element <html>?
abstract public class Element<E extends Element<E>> implements
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Global<E>
{

	protected final Html html;

	public Element(Html html) {
		this.html = html;
	}

	abstract protected E open() throws IOException;

	// TODO: Make shared attributeImpl methods, with things like a given value encoder and boolean if skip when null
	@SuppressWarnings("unchecked")
	public E attribute(String name, Object value) throws IOException {
		if(value != null) {
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
			// TODO: Validate attribute name? https://dev.w3.org/html5/html-author/#attributes
			html.out.write(' ');
			html.out.write(name);
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
			@Override
			public void close() throws IOException {
				html.out.write('"');
			}
		};
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

	public E attribute(String name, AttributeWriter value) throws IOException {
		return attributeE(name, value);
	}

	// TODO: Auto-closeable attribute writers for streaming implementations
}
