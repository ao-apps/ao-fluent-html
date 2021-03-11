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
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class CITE<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, CITE<D, PC>, CITE__<D, PC>, CITE_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<CITE<D, PC>>
{

	public CITE(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected CITE<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<cite", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></cite>" : "</cite>", false);
	}

	@Override
	protected CITE__<D, PC> new__() {
		return new CITE__<>(this);
	}

	@Override
	protected CITE_c<D, PC> new_c() {
		return new CITE_c<>(this);
	}
}
