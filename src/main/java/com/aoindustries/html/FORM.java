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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no form element descendants.
public class FORM<
	D  extends AnyDocument<D>,
	PC extends PalpableContent<D, PC>
> extends
	NormalText<D, PC, FORM<D, PC>, FORM__<D, PC>, FORM_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// TODO: accept (MDN only, HTML-4 only)
	// TODO: accept-charset
	com.aoindustries.html.attributes.Url.Action<FORM<D, PC>>,
	// TODO: autocomplete
	com.aoindustries.html.attributes.Enum.Enctype<FORM<D, PC>, com.aoindustries.html.attributes.Enum.Enctype.Value>,
	com.aoindustries.html.attributes.Enum.Method<FORM<D, PC>, com.aoindustries.html.attributes.Enum.Method.Value>,
	// TODO: name (only support Id, and do name like <ao:iframe>?)  Deprecated as of html 4
	com.aoindustries.html.attributes.Boolean.Novalidate<FORM<D, PC>>,
	com.aoindustries.html.attributes.Enum.Target<FORM<D, PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
	// TODO: rel
	AlmostGlobalAttributes<FORM<D, PC>>
{

	public FORM(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected FORM<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<form", false); // TODO: Is whitespace around <form> ok? autoIndent() instead like SELECT?
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></form>", false);
		} else {
			document.autoNli(out).unsafe(out, "</form>", false);
		}
		document.autoNl(out); // TODO: Is whitespace around <form> ok? No final autoNl() like SELECT?
	}

	@Override
	protected FORM__<D, PC> new__() {
		return new FORM__<>(this);
	}

	@Override
	protected FORM_c<D, PC> new_c() {
		return new FORM_c<>(this);
	}
}
