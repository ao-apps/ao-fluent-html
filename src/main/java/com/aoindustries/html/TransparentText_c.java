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
 * See <a href="https://html.spec.whatwg.org/#transparent-content-models">3.2.5.3 Transparent content models</a>.
 * </p>
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public abstract class TransparentText_c<
	PC extends Content<PC>,
	_c extends TransparentText_c<PC, _c>
> extends Transparent_c<PC, _c> implements Union_Palpable_Phrasing<_c> {

	protected TransparentText_c(TransparentText<?, PC, _c> element) {
		super(element);
	}
}
