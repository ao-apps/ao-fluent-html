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

/**
 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class H3<PC extends HeadingContent<PC>> extends
	NormalTextElement<H3<PC>, PC, H3.H3Content<PC>, H3.H3CloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<H3<PC>>
{

	public static class H3Content<PC extends HeadingContent<PC>> extends
		NormalTextContent<PC, H3Content<PC>> implements
		PhrasingContent<H3Content<PC>> {

		protected H3Content(H3<PC> element) {
			super(element);
		}
	}

	public static class H3CloseableContent<PC extends HeadingContent<PC>> extends
		CloseableNormalTextContent<PC, H3CloseableContent<PC>> implements
		PhrasingContent<H3CloseableContent<PC>> {

		protected H3CloseableContent(H3<PC> element) {
			super(element);
		}
	}

	public H3(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected H3<PC> writeOpen() throws IOException {
		document.out.write("<h3");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</h3>");
	}

	@Override
	protected H3Content<PC> newC() {
		return new H3Content<>(this);
	}

	@Override
	protected H3CloseableContent<PC> newCC() {
		return new H3CloseableContent<>(this);
	}
}
