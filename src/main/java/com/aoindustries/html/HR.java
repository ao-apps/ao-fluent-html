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

import java.io.IOException;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-hr-element">4.4.2 The hr element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class HR<PC extends FlowContent<PC>> extends VoidElement<HR<PC>, PC> implements
	com.aoindustries.html.attributes.Enum.Align<HR<PC>, HR.Align>,
	com.aoindustries.html.attributes.Boolean.Noshade<HR<PC>>,
	com.aoindustries.html.attributes.Integer.SizeHtml4Only<HR<PC>>,
	com.aoindustries.html.attributes.Dimension.Width<HR<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<HR<PC>>
{

	public HR(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected HR<PC> writeOpen() throws IOException {
		document.out.write("<hr");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_hr_align.asp">HTML hr align Attribute</a>.
	 *
	 * @deprecated  The align attribute of &lt;hr&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements Function<Document, String> {

		/**
		 * Left-aligns the horizontal line
		 */
		LEFT("left"),

		/**
		 * Center-aligns the horizontal line (this is default)
		 */
		CENTER("center"),

		/**
		 * Right-aligns the horizontal line
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
