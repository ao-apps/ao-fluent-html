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
 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Tr<PC extends TbodyTheadTfootContent<PC>> extends
	NormalElement<Tr<PC>, PC, Tr.TrContent<PC>, Tr.TrCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Tr<PC>>
{

	public static class TrContent<PC extends TbodyTheadTfootContent<PC>> extends
		NormalContent<PC, TrContent<PC>> implements
		com.aoindustries.html.TrContent<TrContent<PC>> {

		protected TrContent(Tr<PC> element) {
			super(element);
		}
	}

	public static class TrCloseableContent<PC extends TbodyTheadTfootContent<PC>> extends
		CloseableNormalContent<PC, TrCloseableContent<PC>> implements
		com.aoindustries.html.TrContent<TrCloseableContent<PC>> {

		protected TrCloseableContent(Tr<PC> element) {
			super(element);
		}
	}

	public Tr(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Tr<PC> writeOpen() throws IOException {
		document.out.write("<tr");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</tr>");
	}

	@Override
	protected TrContent<PC> newC() {
		return new TrContent<>(this);
	}

	@Override
	protected TrCloseableContent<PC> newCC() {
		return new TrCloseableContent<>(this);
	}
}
