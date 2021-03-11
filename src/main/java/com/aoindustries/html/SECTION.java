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
 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-section-element">4.3.3 The section element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class SECTION<
	D  extends AnyDocument<D>,
	PC extends SectioningContent<D, PC>
> extends
	NormalText<D, PC, SECTION<D, PC>, SECTION__<D, PC>, SECTION_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<SECTION<D, PC>>
{

	public SECTION(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected SECTION<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<section", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></section>", false);
		} else {
			document.autoNli(out).unsafe(out, "</section>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected SECTION__<D, PC> new__() {
		return new SECTION__<>(this);
	}

	@Override
	protected SECTION_c<D, PC> new_c() {
		return new SECTION_c<>(this);
	}
}
