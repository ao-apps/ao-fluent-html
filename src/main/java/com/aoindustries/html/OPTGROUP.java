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
 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class OPTGROUP<
	D  extends AnyDocument<D>,
	PC extends SELECT_content<D, PC>
> extends
	Normal<D, PC, OPTGROUP<D, PC>, OPTGROUP__<D, PC>, OPTGROUP_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	com.aoindustries.html.attributes.Boolean.Disabled<OPTGROUP<D, PC>>,
	com.aoindustries.html.attributes.Text.Label<OPTGROUP<D, PC>>,
	AlmostGlobalAttributes<OPTGROUP<D, PC>>
{

	public OPTGROUP(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected OPTGROUP<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<optgroup", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></optgroup>", false);
		} else {
			document.autoNli(out).unsafe(out, "</optgroup>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected OPTGROUP__<D, PC> new__() {
		return new OPTGROUP__<>(this);
	}

	@Override
	protected OPTGROUP_c<D, PC> new_c() {
		return new OPTGROUP_c<>(this);
	}
}
