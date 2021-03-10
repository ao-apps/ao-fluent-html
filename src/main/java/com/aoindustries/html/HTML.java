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
 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class HTML<PC extends Content<PC>/* TODO? Document */> extends
	Normal<HTML<PC>, PC, HTML__<PC>, HTML_c<PC>>
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <html>: AlmostGlobalAttributes<HTML<PC>>
{

	public HTML(Document document, PC pc) {
		super(document, pc);
	}

	/**
	 * Does not have indented content.
	 *
	 * @return {@code false} - does not indent
	 */
	@Override
	protected boolean isContentIndented() {
		return false;
	}

	@Override
	protected HTML<PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<html", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></html>", false);
		} else {
			document.autoNli(out).unsafe(out, "</html>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected HTML__<PC> new__() {
		return new HTML__<>(this);
	}

	@Override
	protected HTML_c<PC> new_c() {
		return new HTML_c<>(this);
	}
}
