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

import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class Hr extends EmptyElement<Hr> implements
	Attributes.Enum.Align<Hr,Hr.Align>,
	Attributes.Boolean.Noshade<Hr>,
	Attributes.Integer.SizeHtml4Only<Hr>,
	Attributes.Dimension.Width<Hr>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.Mouse.Events<Hr>
{

	public Hr(Html html) {
		super(html);
	}

	@Override
	protected Hr open() throws IOException {
		html.out.write("<hr");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_hr_align.asp">HTML hr align Attribute</a>.
	 *
	 * @deprecated  The align attribute of &lt;hr&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements StringSupplier<RuntimeException> {

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

		private final java.lang.String value;

		private Align(java.lang.String value) {
			this.value = value;
		}

		@Override
		public java.lang.String toString() {
			return value;
		}

		@Override
		public java.lang.String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}
}
