/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.aoapps.html;

import com.aoapps.html.any.AnyDD;
import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dd">&lt;dd&gt;: The Description Details element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_dd.asp">HTML dd tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public final class DD<
	PC extends Union_DIV_DL<PC>
> extends AnyDD<Document, PC, DD<PC>, DD__<PC>, DD_c<PC>> {

	DD(Document document, PC pc) {
		super(document, pc);
	}

	// Expose to this package, avoiding public to keep a clean API for optimal code assist
	@Override
	protected DD<PC> writeOpen(Writer unsafe) throws IOException {
		return super.writeOpen(unsafe);
	}

	@Override
	protected DD__<PC> new__() {
		return new DD__<>(this);
	}

	@Override
	protected DD_c<PC> new_c() {
		return new DD_c<>(this);
	}
}
