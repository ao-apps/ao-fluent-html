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

import com.aoindustries.html.any.AnyMAIN;
import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Where flow content is expected, but only if it is a hierarchically correct main element.
public class MAIN<
	PC extends PalpableContent<PC>
> extends
	AnyMAIN<Document, PC, MAIN<PC>, MAIN__<PC>, MAIN_c<PC>> {

	protected MAIN(Document document, PC pc) {
		super(document, pc);
	}

	// Expose to this package, avoiding public to keep a clean API for optimal code assist
	@Override
	protected MAIN<PC> writeOpen(Writer out) throws IOException {
		return super.writeOpen(out);
	}

	@Override
	protected MAIN__<PC> new__() {
		return new MAIN__<>(this);
	}

	@Override
	protected MAIN_c<PC> new_c() {
		return new MAIN_c<>(this);
	}
}
