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
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class VAR<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, VAR<D, PC>, VAR__<D, PC>, VAR_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<VAR<D, PC>>
{

	public VAR(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected VAR<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<var", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></var>" : "</var>", false);
	}

	@Override
	protected VAR__<D, PC> new__() {
		return new VAR__<>(this);
	}

	@Override
	protected VAR_c<D, PC> new_c() {
		return new VAR_c<>(this);
	}
}
