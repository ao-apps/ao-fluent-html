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
 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: indent before, and newline after?  Review whitespace rules.  (this decision will affect FORM, too)
//       https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Whitespace
public class SELECT<PC extends Union_Interactive_Phrasing<PC>> extends
	Normal<SELECT<PC>, PC, SELECT__<PC>, SELECT_c<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// TODO: autocomplete
	com.aoindustries.html.attributes.Boolean.Disabled<SELECT<PC>>,
	// TODO: form
	com.aoindustries.html.attributes.Boolean.Multiple<SELECT<PC>>,
	com.aoindustries.html.attributes.Text.Name<SELECT<PC>>,
	com.aoindustries.html.attributes.Boolean.Required<SELECT<PC>>,
	com.aoindustries.html.attributes.Integer.Size<SELECT<PC>>,
	AlmostGlobalAttributes<SELECT<PC>>
{

	public SELECT(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected SELECT<PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<select", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></select>", false);
		} else {
			document.autoNli(out).unsafe(out, "</select>", false);
		}
	}

	@Override
	protected SELECT__<PC> new__() {
		return new SELECT__<>(this);
	}

	@Override
	protected SELECT_c<PC> new_c() {
		return new SELECT_c<>(this);
	}
}
