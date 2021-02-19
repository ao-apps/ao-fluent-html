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
 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Area extends EmptyElement<Area> implements
	Attributes.Text.Alt<Area>,
	Attributes.Dimension.Coords<Area>,
	// TODO: download
	Attributes.Url.Href<Area>,
	Attributes.String.Hreflang<Area>,
	Attributes.Text.Media<Area>,
	// TODO: name? (MDN only)
	// TODO: nohref
	// TODO: ping
	// TODO: referrerpolicy
	Attributes.Enum.Rel<Area,Area.Rel>,
	Attributes.Enum.Shape<Area,Area.Shape>,
	// TODO: target
	// TODO: type (deprecated since definition is in conflict and doesn't do anything?)
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Integer.TabindexHtml4<Area>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Area>
{

	public Area(Document document) {
		super(document);
	}

	@Override
	protected Area open() throws IOException {
		document.out.write("<area");
		return this;
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Link_types">Link types - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://html.spec.whatwg.org/multipage/links.html#attr-hyperlink-rel">HTML Standard</a>.
	 * See <a href="https://www.w3schools.com/tags/att_area_rel.asp">HTML area rel Attribute</a>.
	 */
	public enum Rel implements Attributes.Enum.EnumSupplier {
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
		public String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-shape">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/att_area_shape.asp">HTML area shape Attribute</a>.
	 */
	public enum Shape implements Attributes.Enum.EnumSupplier {
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
		public String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}
}
