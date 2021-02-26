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
 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class TABLE<PC extends PalpableContent<PC>> extends
	Normal<TABLE<PC>, PC, TABLE__<PC>, TABLE_c<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<TABLE<PC>>
{

	public TABLE(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected TABLE<PC> writeOpen() throws IOException {
		document.out.write("<table");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></table>" : "</table>");
	}

	@Override
	protected TABLE__<PC> new__() {
		return new TABLE__<>(this);
	}

	@Override
	protected TABLE_c<PC> new_c() {
		return new TABLE_c<>(this);
	}
}
