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
public class COLGROUP<PC extends TABLE_content<PC>> extends
	Normal<COLGROUP<PC>, PC, COLGROUP__<PC>, COLGROUP_c<PC>> implements
	com.aoindustries.html.attributes.Integer.Span<COLGROUP<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<COLGROUP<PC>>
{

	public COLGROUP(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected COLGROUP<PC> writeOpen() throws IOException {
		document.out.write("<colgroup");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></colgroup>" : "</colgroup>");
	}

	@Override
	protected COLGROUP__<PC> new__() {
		return new COLGROUP__<>(this);
	}

	@Override
	protected COLGROUP_c<PC> new_c() {
		return new COLGROUP_c<>(this);
	}
}
