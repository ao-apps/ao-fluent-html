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
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaException;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Option extends Element<Option> {

	public Option(Html html) {
		super(html);
	}

	@Override
	protected Option open() throws IOException {
		html.out.write("<option");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_disabled.asp">HTML option disabled Attribute</a>.
	 */
	public Option disabled(boolean disabled) throws IOException {
		if(disabled) {
			if(html.serialization == Serialization.SGML) {
				html.out.write(" disabled");
			} else {
				assert html.serialization == Serialization.XML;
				html.out.write(" disabled=\"disabled\"");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_label.asp">HTML option label Attribute</a>.
	 *
	 * @deprecated  Although still part of the HTML specification, there is a
	 *              <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=40545">20-year old Firefox bug</a>
	 *              that the label attribute is not supported.  We are deprecating
	 *              this method to make it clear it should probably not be used, as the
	 *              effect of label can be attained through the value attribute and
	 *              tag body anyway.
	 */
	@Deprecated
	public Option label(Object label) throws IOException {
		if(label != null) {
			html.out.write(" label=\"");
			Coercion.write(label, MarkupType.TEXT, textInXhtmlAttributeEncoder, false, html.out);
			html.out.write('"');
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_selected.asp">HTML option selected Attribute</a>.
	 */
	public Option selected(boolean selected) throws IOException {
		if(selected) {
			if(html.serialization == Serialization.SGML) {
				html.out.write(" selected");
			} else {
				assert html.serialization == Serialization.XML;
				html.out.write(" selected=\"selected\"");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_value.asp">HTML option value Attribute</a>.
	 */
	public Option value(Object value) throws IOException {
		if(value != null) {
			html.out.write(" value=\"");
			Coercion.write(value, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return this;
	}

	/**
	 * Writes the text body and closes the tag.
	 */
	public Html innerText(Object text) throws IOException {
		html.out.write('>');
		// TODO: Only allow markup when the value has been set (auto-set value from text like ao-taglib?)
		// Allow text markup from translations
		Coercion.write(text, MarkupType.TEXT, textInXhtmlEncoder, false, html.out);
		html.out.write("</option>");
		return html;
	}

	// TODO: indent variant, indent all lines in a filter?
	public MediaWriter innerText() throws IOException {
		try {
			html.out.write('>');
			return new MediaWriter(
				MediaEncoder.getInstance(
					null,
					MediaType.TEXT,
					MediaType.XHTML
				),
				html.out
			) {
				@Override
				public void close() throws IOException {
					html.out.write("</option>");
				}
			};
		} catch(MediaException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Closes to form an empty option.
	 */
	public Html __() throws IOException {
		html.out.write("></option>");
		return html;
	}
}
