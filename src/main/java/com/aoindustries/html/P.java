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
 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class P<PC extends PalpableContent<PC>> extends
	NormalTextElement<P<PC>, PC, P.PContent<PC>, P.PCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<P<PC>>
{

	public static class PContent<PC extends PalpableContent<PC>> extends
		NormalTextContent<PC, PContent<PC>> implements
		PhrasingContent<PContent<PC>> {

		protected PContent(P<PC> element) {
			super(element);
		}
	}

	public static class PCloseableContent<PC extends PalpableContent<PC>> extends
		CloseableNormalTextContent<PC, PCloseableContent<PC>> implements
		PhrasingContent<PCloseableContent<PC>> {

		protected PCloseableContent(P<PC> element) {
			super(element);
		}
	}

	public P(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected P<PC> writeOpen() throws IOException {
		document.out.write("<p");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</p>");
	}

	@Override
	protected PContent<PC> newC() {
		return new PContent<>(this);
	}

	@Override
	protected PCloseableContent<PC> newCC() {
		return new PCloseableContent<>(this);
	}
}
