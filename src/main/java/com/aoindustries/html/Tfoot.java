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
 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Tfoot<PC extends TableContent<PC>> extends
	NormalElement<Tfoot<PC>, PC, Tfoot.TfootContent<PC>, Tfoot.TfootCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Tfoot<PC>>
{

	public static class TfootContent<PC extends TableContent<PC>> extends
		NormalContent<PC, TfootContent<PC>> implements
		TbodyTheadTfootContent<TfootContent<PC>> {

		protected TfootContent(Tfoot<PC> element) {
			super(element);
		}
	}

	public static class TfootCloseableContent<PC extends TableContent<PC>> extends
		CloseableNormalContent<PC, TfootCloseableContent<PC>> implements
		TbodyTheadTfootContent<TfootCloseableContent<PC>> {

		protected TfootCloseableContent(Tfoot<PC> element) {
			super(element);
		}
	}

	public Tfoot(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Tfoot<PC> writeOpen() throws IOException {
		document.out.write("<tfoot");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></tfoot>" : "</tfoot>");
	}

	@Override
	protected TfootContent<PC> newC() {
		return new TfootContent<>(this);
	}

	@Override
	protected TfootCloseableContent<PC> newCC() {
		return new TfootCloseableContent<>(this);
	}
}
