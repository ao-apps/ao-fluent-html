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
 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-li-element">4.4.8 The li element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class LI<
	D  extends AnyDocument<D>,
	PC extends ListContent<D, PC>
> extends
	NormalText<D, PC, LI<D, PC>, LI__<D, PC>, LI_c<D, PC>> implements
	// TODO: value (If the element is not a child of an ul or menu element)
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<LI<D, PC>>
{

	public LI(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected LI<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<li", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></li>" : "</li>", false).autoNl(out);
	}

	@Override
	protected LI__<D, PC> new__() {
		return new LI__<>(this);
	}

	@Override
	protected LI_c<D, PC> new_c() {
		return new LI_c<>(this);
	}
}
