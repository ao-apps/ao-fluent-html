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

/**
 * A transparent element that can have textual content.
 * <p>
 * See <a href="https://html.spec.whatwg.org/multipage/dom.html#transparent-content-models">3.2.5.3 Transparent content models</a>.
 * </p>
 * <p>
 * Due to limitations in Java generics, this content model does not directly reflect the parent content
 * model, despite this being a transparent content model.  Rather, it includes only the content model that
 * always applies to this element type.
 * </p>
 * <p><em>
 * For the full, context-aware content model, which will likely include more elements,
 * {@linkplain Transparent_c#pc() use the parent content model directly}.
 * </em></p>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @see  Transparent_c#pc()
 *
 * @author  AO Industries, Inc.
 */
public abstract class TransparentText_c<
	D  extends AnyDocument<D>,
	PC extends Content<D, PC>,
	_c extends TransparentText_c<D, PC, _c>
> extends Transparent_c<D, PC, _c> implements Union_Palpable_Phrasing<D, _c> {

	protected TransparentText_c(TransparentText<D, PC, ?, _c> element) {
		super(element);
	}
}
