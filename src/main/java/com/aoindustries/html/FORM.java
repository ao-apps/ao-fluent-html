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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-form-element">4.10.3 The form element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no form element descendants.
public class FORM<PC extends PalpableContent<PC>> extends
	NormalText<FORM<PC>, PC, FORM__<PC>, FORM_c<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// TODO: accept (MDN only, HTML-4 only)
	// TODO: accept-charset
	com.aoindustries.html.attributes.Url.Action<FORM<PC>>,
	// TODO: autocomplete
	com.aoindustries.html.attributes.Enum.Enctype<FORM<PC>, com.aoindustries.html.attributes.Enum.Enctype.Value>,
	com.aoindustries.html.attributes.Enum.Method<FORM<PC>, com.aoindustries.html.attributes.Enum.Method.Value>,
	// TODO: name (only support Id, and do name like <ao:iframe>?)  Deprecated as of html 4
	com.aoindustries.html.attributes.Boolean.Novalidate<FORM<PC>>,
	com.aoindustries.html.attributes.Enum.Target<FORM<PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
	// TODO: rel
	AlmostGlobalAttributes<FORM<PC>>
{

	public FORM(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected FORM<PC> writeOpen() throws IOException {
		document.out.write("<form");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></form>" : "</form>");
	}

	@Override
	protected FORM__<PC> new__() {
		return new FORM__<>(this);
	}

	@Override
	protected FORM_c<PC> new_c() {
		return new FORM_c<>(this);
	}
}
