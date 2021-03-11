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
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no header, footer, sectioning content, or heading content descendants.
public class DT<
	D  extends AnyDocument<D>,
	PC extends Union_DIV_DL<D, PC>
> extends
	NormalText<D, PC, DT<D, PC>, DT__<D, PC>, DT_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<DT<D, PC>>
{

	public DT(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected DT<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<dt", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.unsafe(out, closeAttributes ? "></dt>" : "</dt>", false).autoNl(out);
	}

	@Override
	protected DT__<D, PC> new__() {
		return new DT__<>(this);
	}

	@Override
	protected DT_c<D, PC> new_c() {
		return new DT_c<>(this);
	}
}
