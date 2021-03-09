/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class TABLE<PC extends PalpableContent<PC>> extends
	Normal<TABLE<PC>, PC, TABLE__<PC>, TABLE_c<PC>> implements
	com.aoindustries.html.attributes.Enum.Align<TABLE<PC>, TABLE.Align>,
	// TODO: bgcolor (deprecated)
	com.aoindustries.html.attributes.Integer.Border<TABLE<PC>>,
	com.aoindustries.html.attributes.Dimension.Cellpadding<TABLE<PC>>,
	com.aoindustries.html.attributes.Dimension.Cellspacing<TABLE<PC>>,
	// TODO: frame (deprecated)
	// TODO: rules (deprecated)
	// TODO: summary (deprecated)
	com.aoindustries.html.attributes.Dimension.WidthHtml4Only<TABLE<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<TABLE<PC>>
{

	public TABLE(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected TABLE<PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<table", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></table>", false);
		} else {
			document.autoNli(out).unsafe(out, "</table>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected TABLE__<PC> new__() {
		return new TABLE__<>(this);
	}

	@Override
	protected TABLE_c<PC> new_c() {
		return new TABLE_c<>(this);
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table#attr-align">&lt;table&gt;: The Table element / align</a>.
	 *
	 * @deprecated  The align attribute of &lt;table&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements Function<Document, String> {

		/**
		 * the table is displayed on the left side of the document
		 */
		LEFT("left"),

		/**
		 * the table is displayed in the center of the document
		 */
		CENTER("center"),

		/**
		 * the table is displayed on the right side of the document
		 */
		RIGHT("right");

		private final String value;

		private Align(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public String apply(Document document) {
			return value;
		}
	}
}
