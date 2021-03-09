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
 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no heading content descendants, no sectioning content descendants, and no header, footer, or address element descendants.
public class ADDRESS<PC extends PalpableContent<PC>> extends
	NormalText<ADDRESS<PC>, PC, ADDRESS__<PC>, ADDRESS_c<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<ADDRESS<PC>>
{

	public ADDRESS(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected ADDRESS<PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<address", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></address>", false);
		} else {
			document.autoNli(out).unsafe(out, "</address>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected ADDRESS__<PC> new__() {
		return new ADDRESS__<>(this);
	}

	@Override
	protected ADDRESS_c<PC> new_c() {
		return new ADDRESS_c<>(this);
	}
}
