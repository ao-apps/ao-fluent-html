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
public class H4<PC extends HeadingContent<PC>> extends
	NormalTextElement<H4<PC>, PC, H4.H4Content<PC>, H4.H4CloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<H4<PC>>
{

	public static class H4Content<PC extends HeadingContent<PC>> extends
		NormalTextContent<PC, H4Content<PC>> implements
		PhrasingContent<H4Content<PC>> {

		protected H4Content(H4<PC> element) {
			super(element);
		}
	}

	public static class H4CloseableContent<PC extends HeadingContent<PC>> extends
		CloseableNormalTextContent<PC, H4CloseableContent<PC>> implements
		PhrasingContent<H4CloseableContent<PC>> {

		protected H4CloseableContent(H4<PC> element) {
			super(element);
		}
	}

	public H4(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected H4<PC> writeOpen() throws IOException {
		document.out.write("<h4");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></h4>" : "</h4>");
	}

	@Override
	protected H4Content<PC> newC() {
		return new H4Content<>(this);
	}

	@Override
	protected H4CloseableContent<PC> newCC() {
		return new H4CloseableContent<>(this);
	}
}
