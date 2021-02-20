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

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.Serialization;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/col">&lt;col&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_col.asp">HTML col tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class Col<PC extends Content> extends VoidElement<Col<PC>, PC> implements
	Attributes.Enum.Align<Col<PC>, Col.Align>,
	// TODO: bgcolor (deprecated)
	// TODO: char (deprecated)
	// TODO: charoff (deprecated)
	Attributes.Integer.Span<Col<PC>>,
	Attributes.Enum.Valign<Col<PC>, Col.Valign>,
	Attributes.Dimension.WidthHtml4Only<Col<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Col<PC>>
{

	public Col(Document document) {
		super(document);
	}

	@Override
	protected Col<PC> open() throws IOException {
		document.out.write("<col");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_col_align.asp">HTML col align Attribute</a>.
	 *
	 * @deprecated  The align attribute of &lt;col&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements Attributes.Enum.EnumSupplier {

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
		public String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_col_valign.asp">HTML col valign Attribute</a>.
	 *
	 * @deprecated  The valign attribute of &lt;col&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Valign implements Attributes.Enum.EnumSupplier {

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
		public String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}
}
