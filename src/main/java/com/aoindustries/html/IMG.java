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
import java.io.Writer;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/embedded-content.html#the-img-element">4.8.3 The img element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class IMG<
	D  extends AnyDocument<D>,
	PC extends Union_Embedded_Interactive<D, PC>
> extends VoidElement<D, PC, IMG<D, PC>> implements
	com.aoindustries.html.attributes.Enum.Align<IMG<D, PC>, IMG.Align>,
	com.aoindustries.html.attributes.Text.Alt<IMG<D, PC>>,
	// TODO: border
	// TODO: crossorigin
	com.aoindustries.html.attributes.Integer.Height<IMG<D, PC>>,
	// TODO: hspace
	com.aoindustries.html.attributes.Boolean.Ismap<IMG<D, PC>>,
	// TODO: longdesc
	// TODO: sizes
	com.aoindustries.html.attributes.Url.Src<IMG<D, PC>>,
	// TODO: srcset
	com.aoindustries.html.attributes.String.Usemap<IMG<D, PC>>,
	// TODO: vspace
	com.aoindustries.html.attributes.Integer.Width<IMG<D, PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<IMG<D, PC>>,
	com.aoindustries.html.attributes.event.media.Onabort<IMG<D, PC>>,
	com.aoindustries.html.attributes.event.window.Onerror<IMG<D, PC>>,
	com.aoindustries.html.attributes.event.window.Onload<IMG<D, PC>>
	// TODO: More events
{

	public IMG(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected IMG<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<img", false);
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_img_align.asp">HTML img align Attribute</a>.
	 *
	 * @deprecated  The align attribute of &lt;img&gt; is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	public enum Align implements Function<AnyDocument<?>, String> {

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
		public String apply(AnyDocument<?> document) {
			return value;
		}
	}
}
