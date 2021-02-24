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
 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Thead<PC extends TableContent<PC>> extends
	NormalElement<Thead<PC>, PC, Thead.TheadContent<PC>, Thead.TheadCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Thead<PC>>
{

	public static class TheadContent<PC extends TableContent<PC>> extends
		NormalContent<PC, TheadContent<PC>> implements
		TbodyTheadTfootContent<TheadContent<PC>> {

		protected TheadContent(Thead<PC> element) {
			super(element);
		}
	}

	public static class TheadCloseableContent<PC extends TableContent<PC>> extends
		CloseableNormalContent<PC, TheadCloseableContent<PC>> implements
		TbodyTheadTfootContent<TheadCloseableContent<PC>> {

		protected TheadCloseableContent(Thead<PC> element) {
			super(element);
		}
	}

	public Thead(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Thead<PC> writeOpen() throws IOException {
		document.out.write("<thead");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></thead>" : "</thead>");
	}

	@Override
	protected TheadContent<PC> newC() {
		return new TheadContent<>(this);
	}

	@Override
	protected TheadCloseableContent<PC> newCC() {
		return new TheadCloseableContent<>(this);
	}
}
