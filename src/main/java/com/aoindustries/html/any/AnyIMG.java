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
package com.aoindustries.html.any;

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
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
abstract public class AnyIMG<
	D  extends AnyDocument<D>,
	PC extends AnyUnion_Embedded_Interactive<D, PC>,
	E  extends AnyIMG<D, PC, E>
> extends Void<D, PC, E> implements
	com.aoindustries.html.any.attributes.Enum.Align<E, AnyIMG.Align>,
	com.aoindustries.html.any.attributes.Text.Alt<E>,
	// TODO: border
	// TODO: crossorigin
	com.aoindustries.html.any.attributes.Integer.Height<E>,
	// TODO: hspace
	com.aoindustries.html.any.attributes.Boolean.Ismap<E>,
	// TODO: longdesc
	// TODO: sizes
	com.aoindustries.html.any.attributes.Url.Src<E>,
	// TODO: srcset
	com.aoindustries.html.any.attributes.String.Usemap<E>,
	// TODO: vspace
	com.aoindustries.html.any.attributes.Integer.Width<E>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<E>,
	com.aoindustries.html.any.attributes.event.media.Onabort<E>,
	com.aoindustries.html.any.attributes.event.window.Onerror<E>,
	com.aoindustries.html.any.attributes.event.window.Onload<E>
	// TODO: More events
{

	protected AnyIMG(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<img", false);
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
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
