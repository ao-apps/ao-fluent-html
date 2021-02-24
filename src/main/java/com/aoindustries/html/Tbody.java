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
 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Tbody<PC extends TableContent<PC>> extends
	NormalElement<Tbody<PC>, PC, Tbody.TbodyContent<PC>, Tbody.TbodyCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Tbody<PC>>
{

	public static class TbodyContent<PC extends TableContent<PC>> extends
		NormalContent<PC> implements
		TbodyTheadTfootContent<TbodyContent<PC>> {

		protected TbodyContent(Tbody<PC> element) {
			super(element);
		}
	}

	public static class TbodyCloseableContent<PC extends TableContent<PC>> extends
		CloseableNormalContent<PC> implements
		TbodyTheadTfootContent<TbodyCloseableContent<PC>> {

		protected TbodyCloseableContent(Tbody<PC> element) {
			super(element);
		}
	}

	public Tbody(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Tbody<PC> writeOpen() throws IOException {
		document.out.write("<tbody");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</tbody>");
	}

	@Override
	protected TbodyContent<PC> newC() {
		return new TbodyContent<>(this);
	}

	@Override
	protected TbodyCloseableContent<PC> newCC() {
		return new TbodyCloseableContent<>(this);
	}
}
