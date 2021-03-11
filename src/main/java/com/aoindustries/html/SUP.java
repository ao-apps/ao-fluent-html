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
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class SUP<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, SUP<D, PC>, SUP__<D, PC>, SUP_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<SUP<D, PC>>
{

	public SUP(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected SUP<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<sup", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></sup>" : "</sup>", false);
	}

	@Override
	protected SUP__<D, PC> new__() {
		return new SUP__<>(this);
	}

	@Override
	protected SUP_c<D, PC> new_c() {
		return new SUP_c<>(this);
	}
}
