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
 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Td<PC extends TrContent<PC>> extends
	NormalTextElement<Td<PC>, PC, Td.TdContent<PC>, Td.TdCloseableContent<PC>> implements
	Attributes.Integer.Colspan<Td<PC>>,
	Attributes.Integer.Rowspan<Td<PC>>,
	// TODO: headers
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Td<PC>>
{

	public static class TdContent<PC extends TrContent<PC>> extends
		NormalTextContent<PC, TdContent<PC>> implements
		FlowContent<TdContent<PC>> {

		protected TdContent(Td<PC> element) {
			super(element);
		}
	}

	public static class TdCloseableContent<PC extends TrContent<PC>> extends
		CloseableNormalTextContent<PC, TdCloseableContent<PC>> implements
		FlowContent<TdCloseableContent<PC>> {

		protected TdCloseableContent(Td<PC> element) {
			super(element);
		}
	}

	public Td(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Td<PC> writeOpen() throws IOException {
		document.out.write("<td");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</td>");
	}

	@Override
	protected TdContent<PC> newC() {
		return new TdContent<>(this);
	}

	@Override
	protected TdCloseableContent<PC> newCC() {
		return new TdCloseableContent<>(this);
	}
}
