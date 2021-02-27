/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020, 2021  AO Industries, Inc.
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
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-col-element">4.9.4 The col element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/col">&lt;col&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_col.asp">HTML col tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class COL<PC extends COLGROUP_content<PC>> extends VoidElement<COL<PC>, PC> implements
	com.aoindustries.html.attributes.Enum.Align<COL<PC>, COL.Align>,
	// TODO: bgcolor (deprecated)
	// TODO: char (deprecated)
	// TODO: charoff (deprecated)
	com.aoindustries.html.attributes.Integer.Span<COL<PC>>,
	com.aoindustries.html.attributes.Enum.Valign<COL<PC>, COL.Valign>,
	com.aoindustries.html.attributes.Dimension.WidthHtml4Only<COL<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<COL<PC>>
{

	public COL(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected COL<PC> writeOpen() throws IOException {
		document.out.write("<col");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_col_align.asp">HTML col align Attribute</a>.
	 *
	 * @deprecated  The align attribute of &lt;col&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements Function<Document, String> {

		/**
		 * Left-align content
		 */
		LEFT("left"),

		/**
		 * Right-align content
		 */
		RIGHT("right"),

		/**
		 * Center-align content
		 */
		CENTER("center"),

		/**
		 * Stretches the lines so that each line has equal width (like in newspapers and magazines)
		 */
		JUSTIFY("justify"),

		/**
		 * Align the content to a specific character
		 */
		CHAR("char");

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

	/**
	 * See <a href="https://www.w3schools.com/tags/att_col_valign.asp">HTML col valign Attribute</a>.
	 *
	 * @deprecated  The valign attribute of &lt;col&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Valign implements Function<Document, String> {

		/**
		 * Top-align content
		 */
		TOP("top"),

		/**
		 * Center-align content
		 */
		MIDDLE("middle"),

		/**
		 * Bottom-align content
		 */
		BOTTOM("bottom"),

		/**
		 * The baseline is the "imaginary line" which most letters "sit" on, in a line of text.
		 */
		BASELINE("baseline");

		private final String value;

		private Valign(String value) {
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
