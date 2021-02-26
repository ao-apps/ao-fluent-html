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
public class THEAD<PC extends TABLE_content<PC>> extends
	Normal<THEAD<PC>, PC, THEAD__<PC>, THEAD_c<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<THEAD<PC>>
{

	public THEAD(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected THEAD<PC> writeOpen() throws IOException {
		document.out.write("<thead");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></thead>" : "</thead>");
	}

	@Override
	protected THEAD__<PC> new__() {
		return new THEAD__<>(this);
	}

	@Override
	protected THEAD_c<PC> new_c() {
		return new THEAD_c<>(this);
	}
}
