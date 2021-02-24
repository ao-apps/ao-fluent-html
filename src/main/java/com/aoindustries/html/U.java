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
 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class U<PC extends UnionContent.Palpable_Phrasing<PC>> extends
	NormalTextElement<U<PC>, PC, U.UContent<PC>, U.UCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<U<PC>>
{

	public static class UContent<PC extends UnionContent.Palpable_Phrasing<PC>> extends
		NormalTextContent<PC, UContent<PC>> implements
		PhrasingContent<UContent<PC>> {

		protected UContent(U<PC> element) {
			super(element);
		}
	}

	public static class UCloseableContent<PC extends UnionContent.Palpable_Phrasing<PC>> extends
		CloseableNormalTextContent<PC, UCloseableContent<PC>> implements
		PhrasingContent<UCloseableContent<PC>> {

		protected UCloseableContent(U<PC> element) {
			super(element);
		}
	}

	public U(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected U<PC> writeOpen() throws IOException {
		document.out.write("<u");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</u>");
	}

	@Override
	protected UContent<PC> newC() {
		return new UContent<>(this);
	}

	@Override
	protected UCloseableContent<PC> newCC() {
		return new UCloseableContent<>(this);
	}
}
