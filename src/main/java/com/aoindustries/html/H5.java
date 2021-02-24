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
public class H5<PC extends HeadingContent<PC>> extends
	NormalTextElement<H5<PC>, PC, H5.H5Content<PC>, H5.H5CloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<H5<PC>>
{

	public static class H5Content<PC extends HeadingContent<PC>> extends
		NormalTextContent<PC, H5Content<PC>> implements
		PhrasingContent<H5Content<PC>> {

		protected H5Content(H5<PC> element) {
			super(element);
		}
	}

	public static class H5CloseableContent<PC extends HeadingContent<PC>> extends
		CloseableNormalTextContent<PC, H5CloseableContent<PC>> implements
		PhrasingContent<H5CloseableContent<PC>> {

		protected H5CloseableContent(H5<PC> element) {
			super(element);
		}
	}

	public H5(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected H5<PC> writeOpen() throws IOException {
		document.out.write("<h5");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></h5>" : "</h5>");
	}

	@Override
	protected H5Content<PC> newC() {
		return new H5Content<>(this);
	}

	@Override
	protected H5CloseableContent<PC> newCC() {
		return new H5CloseableContent<>(this);
	}
}
