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
 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class I<PC extends UnionContent.Palpable_Phrasing<PC>> extends
	NormalTextElement<I<PC>, PC, I.IContent<PC>, I.ICloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<I<PC>>
{

	public static class IContent<PC extends UnionContent.Palpable_Phrasing<PC>> extends
		NormalTextContent<PC, IContent<PC>> implements
		PhrasingContent<IContent<PC>> {

		protected IContent(I<PC> element) {
			super(element);
		}
	}

	public static class ICloseableContent<PC extends UnionContent.Palpable_Phrasing<PC>> extends
		CloseableNormalTextContent<PC, ICloseableContent<PC>> implements
		PhrasingContent<ICloseableContent<PC>> {

		protected ICloseableContent(I<PC> element) {
			super(element);
		}
	}

	public I(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected I<PC> writeOpen() throws IOException {
		document.out.write("<i");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</i>");
	}

	@Override
	protected IContent<PC> newC() {
		return new IContent<>(this);
	}

	@Override
	protected ICloseableContent<PC> newCC() {
		return new ICloseableContent<>(this);
	}
}
