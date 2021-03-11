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
 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class H6<
	D  extends AnyDocument<D>,
	PC extends HeadingContent<D, PC>
> extends H<D, PC, H6<D, PC>, H6__<D, PC>, H6_c<D, PC>> {

	public H6(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected H6<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<h6", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></h6>" : "</h6>", false).autoNl(out);
	}

	@Override
	protected H6__<D, PC> new__() {
		return new H6__<>(this);
	}

	@Override
	protected H6_c<D, PC> new_c() {
		return new H6_c<>(this);
	}

	@Override
	public int getRank() {
		return 6;
	}
}
