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

import com.aoindustries.html.any.AnyI;
import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class I<
	PC extends Union_Palpable_Phrasing<PC>
> extends
	AnyI<Document, PC, I<PC>, I__<PC>, I_c<PC>> {

	protected I(Document document, PC pc) {
		super(document, pc);
	}


	// Expose to this package, avoiding public to keep a clean API for optimal code assist
	@Override
	protected I<PC> writeOpen(Writer out) throws IOException {
		return super.writeOpen(out);
	}
	@Override
	protected I__<PC> new__() {
		return new I__<>(this);
	}

	@Override
	protected I_c<PC> new_c() {
		return new I_c<>(this);
	}
}
