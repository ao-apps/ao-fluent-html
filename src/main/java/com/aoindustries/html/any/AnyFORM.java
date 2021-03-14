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
package com.aoindustries.html.any;

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
 * @param  <E>   This element type
 * @param  <__>  This content model, which will be the parent content model of child elements
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no form element descendants.
abstract public class AnyFORM<
	D  extends AnyDocument<D>,
	PC extends AnyPalpableContent<D, PC>,
	E  extends AnyFORM<D, PC, E, __, _c>,
	__ extends AnyFORM__<D, PC, __>,
	// Would prefer "_c extends __ & Closeable<D, PC>", but "a type variable may not be followed by other bounds"
	_c extends AnyFORM_c<D, PC, _c>
> extends
	NormalText<D, PC, E, __, _c> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// TODO: accept (MDN only, HTML-4 only)
	// TODO: accept-charset
	com.aoindustries.html.any.attributes.Url.Action<E>,
	// TODO: autocomplete
	com.aoindustries.html.any.attributes.Enum.Enctype<E, com.aoindustries.html.any.attributes.Enum.Enctype.Value>,
	com.aoindustries.html.any.attributes.Enum.Method<E, com.aoindustries.html.any.attributes.Enum.Method.Value>,
	// TODO: name (only support Id, and do name like <ao:iframe>?)  Deprecated as of html 4
	com.aoindustries.html.any.attributes.Boolean.Novalidate<E>,
	com.aoindustries.html.any.attributes.Enum.Target<E, com.aoindustries.html.any.attributes.Enum.Target.Value>,
	// TODO: rel
	AlmostGlobalAttributes<E>
{

	protected AnyFORM(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<form", false); // TODO: Is whitespace around <form> ok? autoIndent() instead like AnySELECT?
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
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
		document.autoNl(out); // TODO: Is whitespace around <form> ok? No final autoNl() like AnySELECT?
	}
}
