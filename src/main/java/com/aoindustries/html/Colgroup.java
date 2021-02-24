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
 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Colgroup<PC extends TableContent<PC>> extends
	NormalElement<Colgroup<PC>, PC, Colgroup.ColgroupContent<PC>, Colgroup.ColgroupCloseableContent<PC>> implements
	Attributes.Integer.Span<Colgroup<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Colgroup<PC>>
{

	public static class ColgroupContent<PC extends TableContent<PC>> extends
		NormalContent<PC> implements
		com.aoindustries.html.ColgroupContent<ColgroupContent<PC>> {

		protected ColgroupContent(Colgroup<PC> element) {
			super(element);
		}
	}

	public static class ColgroupCloseableContent<PC extends TableContent<PC>> extends
		CloseableNormalContent<PC> implements
		com.aoindustries.html.ColgroupContent<ColgroupCloseableContent<PC>> {

		protected ColgroupCloseableContent(Colgroup<PC> element) {
			super(element);
		}
	}

	public Colgroup(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Colgroup<PC> writeOpen() throws IOException {
		document.out.write("<colgroup");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</colgroup>");
	}

	@Override
	protected ColgroupContent<PC> newC() {
		return new ColgroupContent<>(this);
	}

	@Override
	protected ColgroupCloseableContent<PC> newCC() {
		return new ColgroupCloseableContent<>(this);
	}
}
