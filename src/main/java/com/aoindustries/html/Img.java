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
 * <li>See <a href="https://html.spec.whatwg.org/#the-img-element">4.8.3 The img element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class Img<PC extends Content> extends VoidElement<Img<PC>, PC> implements
	Attributes.Enum.Align<Img<PC>, Img.Align>,
	Attributes.Text.Alt<Img<PC>>,
	// TODO: border
	// TODO: crossorigin
	Attributes.Integer.Height<Img<PC>>,
	// TODO: hspace
	Attributes.Boolean.Ismap<Img<PC>>,
	// TODO: longdesc
	// TODO: sizes
	Attributes.Url.Src<Img<PC>>,
	// TODO: srcset
	Attributes.String.Usemap<Img<PC>>,
	// TODO: vspace
	Attributes.Integer.Width<Img<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Img<PC>>,
	Attributes.Event.Media.Onabort<Img<PC>>,
	Attributes.Event.Window.Onerror<Img<PC>>,
	Attributes.Event.Window.Onload<Img<PC>>
	// TODO: More events
{

	public Img(Document document) {
		super(document);
	}

	@Override
	protected Img<PC> open() throws IOException {
		document.out.write("<img");
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
