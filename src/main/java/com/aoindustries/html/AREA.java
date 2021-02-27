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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class AREA<PC extends PhrasingContent<PC>> extends VoidElement<AREA<PC>, PC> implements
	com.aoindustries.html.attributes.Text.Alt<AREA<PC>>,
	com.aoindustries.html.attributes.Dimension.Coords<AREA<PC>>,
	// TODO: download
	com.aoindustries.html.attributes.Url.Href<AREA<PC>>,
	com.aoindustries.html.attributes.String.Hreflang<AREA<PC>>,
	com.aoindustries.html.attributes.Text.Media<AREA<PC>>,
	// TODO: name? (MDN only)
	// TODO: nohref
	// TODO: ping
	// TODO: referrerpolicy
	com.aoindustries.html.attributes.Enum.Rel<AREA<PC>, AREA.Rel>,
	com.aoindustries.html.attributes.Enum.Shape<AREA<PC>, AREA.Shape>,
	// TODO: target
	// TODO: type (deprecated since definition is in conflict and doesn't do anything?)
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	com.aoindustries.html.attributes.Integer.TabindexHtml4<AREA<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<AREA<PC>>
{

	public AREA(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected AREA<PC> writeOpen() throws IOException {
		document.out.write("<area");
		return this;
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Link_types">Link types - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/links.html#attr-hyperlink-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_area_rel.asp">HTML area rel Attribute</a>.</li>
	 * </ul>
	 */
	public enum Rel implements Function<Document, String> {
		ALTERNATE("alternate"),
		/**
		 * @deprecated
		 */
		@Deprecated
		ARCHIVES("archives"), // MDN only
		AUTHOR("author"), // w3schools, MDN only
		BOOKMARK("bookmark"),
		EXTERNAL("external"),
		/**
		 * @deprecated
		 */
		@Deprecated
		FIRST("first"), // MDN only
		HELP("help"), // w3schools, MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		INDEX("index"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		LAST("last"), // MDN only
		LICENSE("license"), // w3schools, MDN only
		NEXT("next"),
		NOFOLLOW("nofollow"),
		NOOPENER("noopener"),
		NOREFERRER("noreferrer"),
		// TODO: opener?
		PREV("prev"), // w3schools, MDN only
		SEARCH("search"),
		/**
		 * @deprecated
		 */
		@Deprecated
		SIDEBAR("sidebar"), // MDN only
		TAG("tag"),
		/**
		 * @deprecated
		 */
		@Deprecated
		UP("up"); // MDN only

		private final String value;
		// TODO: Verify values by doctype

		private Rel(String value) {
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
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-shape">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_area_shape.asp">HTML area shape Attribute</a>.</li>
	 * </ul>
	 */
	public enum Shape implements Function<Document, String> {
		DEFAULT("default"),
		RECT("rect"),
		CIRCLE("circle"),
		POLY("poly");

		private final String value;

		private Shape(String value) {
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
