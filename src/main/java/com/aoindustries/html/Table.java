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
public class Table<PC extends PalpableContent<PC>> extends
	NormalElement<Table<PC>, PC, Table.TableContent<PC>, Table.TableCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Table<PC>>
{

	public static class TableContent<PC extends PalpableContent<PC>> extends
		NormalContent<PC> implements
		com.aoindustries.html.TableContent<TableContent<PC>> {

		protected TableContent(Table<PC> element) {
			super(element);
		}
	}

	public static class TableCloseableContent<PC extends PalpableContent<PC>> extends
		CloseableNormalContent<PC> implements
		com.aoindustries.html.TableContent<TableCloseableContent<PC>> {

		protected TableCloseableContent(Table<PC> element) {
			super(element);
		}
	}

	public Table(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Table<PC> writeOpen() throws IOException {
		document.out.write("<table");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</table>");
	}

	@Override
	protected TableContent<PC> newC() {
		return new TableContent<>(this);
	}

	@Override
	protected TableCloseableContent<PC> newCC() {
		return new TableCloseableContent<>(this);
	}
}
