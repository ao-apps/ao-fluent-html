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
 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class DD<
	D  extends AnyDocument<D>,
	PC extends Union_DIV_DL<D, PC>
> extends
	NormalText<D, PC, DD<D, PC>, DD__<D, PC>, DD_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<DD<D, PC>>
{

	public DD(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected DD<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<dd", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></dd>" : "</dd>", false).autoNl(out);
	}

	@Override
	protected DD__<D, PC> new__() {
		return new DD__<>(this);
	}

	@Override
	protected DD_c<D, PC> new_c() {
		return new DD_c<>(this);
	}
}
