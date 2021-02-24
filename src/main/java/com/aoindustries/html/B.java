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
 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class B<PC extends UnionContent.Palpable_Phrasing<PC>> extends
	NormalTextElement<B<PC>, PC, B.BContent<PC>, B.BCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<B<PC>>
{

	public static class BContent<PC extends UnionContent.Palpable_Phrasing<PC>> extends
		NormalTextContent<PC, BContent<PC>> implements
		PhrasingContent<BContent<PC>> {

		protected BContent(B<PC> element) {
			super(element);
		}
	}

	public static class BCloseableContent<PC extends UnionContent.Palpable_Phrasing<PC>> extends
		CloseableNormalTextContent<PC, BCloseableContent<PC>> implements
		PhrasingContent<BCloseableContent<PC>> {

		protected BCloseableContent(B<PC> element) {
			super(element);
		}
	}

	public B(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected B<PC> writeOpen() throws IOException {
		document.out.write("<b");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</b>");
	}

	@Override
	protected BContent<PC> newC() {
		return new BContent<>(this);
	}

	@Override
	protected BCloseableContent<PC> newCC() {
		return new BCloseableContent<>(this);
	}
}
