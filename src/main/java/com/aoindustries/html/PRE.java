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

/**
 * See <a href="https://html.spec.whatwg.org/#the-pre-element">4.4.3 The pre element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class PRE<PC extends PalpableContent<PC>> extends
	NormalText<PRE<PC>, PC, PRE__<PC>, PRE_c<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<PRE<PC>>
{

	// TODO: Disable autonl, too, once feature added
	private boolean oldIndent;

	public PRE(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected PRE<PC> writeOpen() throws IOException {
		oldIndent = document.getIndent();
		if(oldIndent) document.setIndent(false);
		document.out.write("<pre");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></pre>" : "</pre>");
		if(oldIndent) document.setIndent(true);
	}

	@Override
	protected PRE__<PC> new__() {
		return new PRE__<>(this);
	}

	@Override
	protected PRE_c<PC> new_c() {
		return new PRE_c<>(this);
	}
}
