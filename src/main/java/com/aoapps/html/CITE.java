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

import com.aoapps.html.any.AnyCITE;
import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/cite">&lt;cite&gt;: The Citation element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_cite.asp">HTML cite Tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public final class CITE<
	PC extends Union_Palpable_Phrasing<PC>
> extends AnyCITE<Document, PC, CITE<PC>, CITE__<PC>, CITE_c<PC>> {

	CITE(Document document, PC pc) {
		super(document, pc);
	}

	// Expose to this package, avoiding public to keep a clean API for optimal code assist
	@Override
	protected CITE<PC> writeOpen(Writer unsafe) throws IOException {
		return super.writeOpen(unsafe);
	}

	@Override
	protected CITE__<PC> new__() {
		return new CITE__<>(this);
	}

	@Override
	protected CITE_c<PC> new_c() {
		return new CITE_c<>(this);
	}
}
