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
public class H1<PC extends HeadingContent<PC>> extends
	NormalTextElement<H1<PC>, PC, H1.H1Content<PC>, H1.H1CloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<H1<PC>>
{

	public static class H1Content<PC extends HeadingContent<PC>> extends
		NormalTextContent<PC, H1Content<PC>> implements
		PhrasingContent<H1Content<PC>> {

		protected H1Content(H1<PC> element) {
			super(element);
		}
	}

	public static class H1CloseableContent<PC extends HeadingContent<PC>> extends
		CloseableNormalTextContent<PC, H1CloseableContent<PC>> implements
		PhrasingContent<H1CloseableContent<PC>> {

		protected H1CloseableContent(H1<PC> element) {
			super(element);
		}
	}

	public H1(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected H1<PC> writeOpen() throws IOException {
		document.out.write("<h1");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></h1>" : "</h1>");
	}

	@Override
	protected H1Content<PC> newC() {
		return new H1Content<>(this);
	}

	@Override
	protected H1CloseableContent<PC> newCC() {
		return new H1CloseableContent<>(this);
	}
}
