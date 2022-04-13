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

import com.aoapps.html.any.AnyDATA;
import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/data">&lt;data&gt;: The Data element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_data.asp">HTML data tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @since HTML 5
 *
 * @author  AO Industries, Inc.
 */
public final class DATA<
	PC extends Union_Palpable_Phrasing<PC>
> extends AnyDATA<Document, PC, DATA<PC>, DATA__<PC>, DATA_c<PC>> {

	DATA(Document document, PC pc) {
		super(document, pc);
	}

	// Expose to this package, avoiding public to keep a clean API for optimal code assist
	@Override
	protected DATA<PC> writeOpen(Writer unsafe) throws IOException {
		return super.writeOpen(unsafe);
	}

	@Override
	protected DATA__<PC> new__() {
		return new DATA__<>(this);
	}

	@Override
	protected DATA_c<PC> new_c() {
		return new DATA_c<>(this);
	}
}
