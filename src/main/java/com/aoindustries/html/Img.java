/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020  AO Industries, Inc.
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
 * See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class Img extends EmptyElement<Img> implements
	Attributes.Enum.Align<Img,Img.Align>,
	Attributes.Text.Alt<Img>,
	// TODO: border
	// TODO: crossorigin
	Attributes.Integer.Height<Img>,
	// TODO: hspace
	Attributes.Boolean.Ismap<Img>,
	// TODO: longdesc
	// TODO: sizes
	Attributes.Url.Src<Img>,
	// TODO: srcset
	Attributes.String.Usemap<Img>,
	// TODO: vspace
	Attributes.Integer.Width<Img>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Img>,
	Attributes.Event.Media.Onabort<Img>,
	Attributes.Event.Window.Onerror<Img>,
	Attributes.Event.Window.Onload<Img>
	// TODO: More events
{

	public Img(Html html) {
		super(html);
	}

	@Override
	protected Img open() throws IOException {
		html.out.write("<img");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_img_align.asp">HTML img align Attribute</a>.
	 *
	 * @deprecated  The align attribute of &lt;img&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements Attributes.Enum.EnumSupplier {

		/**
		 * Align the image to the left
		 */
		LEFT("left"),

		/**
		 * Align the image to the right
		 */
		RIGHT("right"),

		/**
		 * Align the image in the middle
		 */
		MIDDLE("middle"),

		/**
		 * Align the image at the top
		 */
		TOP("top"),

		/**
		 * Align the image at the bottom
		 */
		BOTTOM("bottom");

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
}
