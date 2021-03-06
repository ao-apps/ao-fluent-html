/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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
 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Transparent, but there must be no interactive content descendent, a element descendent, or descendent with
//       the tabindex attribute specified.
public class A<PC extends Union_Interactive_Phrasing<PC>> extends
	TransparentElement<A<PC>, PC> implements
	com.aoindustries.html.attributes.Url.Href<A<PC>>,
	com.aoindustries.html.attributes.Enum.Target<A<PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
	// TODO: download
	// TODO: ping
	com.aoindustries.html.attributes.Enum.Rel<A<PC>, A.Rel>,
	com.aoindustries.html.attributes.String.Hreflang<A<PC>>,
	// TODO: type
	// TODO: referrerpolicy
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<A<PC>>
{

	public A(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected A<PC> writeOpen() throws IOException {
		document.out.write("<a");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></a>" : "</a>");
	}

	/**
	 * Ends attributes, writes a text body, then closes this element.
	 * <p>
	 * Since {@link TextContent} is not a part of {@link Union_Interactive_Phrasing},
	 * strictly speaking text is not allowed in all possible content models.
	 * However, since it is such a common operation, we've added it here.
	 * </p>
	 *
	 * @return  The parent content model this element is within
	 *
	 * @see  Document#text(java.lang.Object)
	 */
	public PC __(Object text) throws IOException {
		if(text != null) {
			document.out.append('>');
			document.incDepth();
			document.text(text);
			document.decDepth();
			writeClose(false);
		} else {
			writeClose(true);
		}
		return pc;
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Link_types">Link types - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/links.html#attr-hyperlink-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_a_rel.asp">HTML a rel Attribute</a>.</li>
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
}
