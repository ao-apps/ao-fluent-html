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

/**
 * See <a href="https://html.spec.whatwg.org/multipage/dom.html#transparent-content-models">3.2.5.3 Transparent content models</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within,
 *               which may also be the parent content model of child elements
 * @param  <E>   This element type
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class Transparent<
	D  extends AnyDocument<D>,
	PC extends Content<D, PC>,
	E  extends Transparent<D, PC, E, _c>,
	// Would prefer "_c extends PC & Closeable<D, PC>", but "a type variable may not be followed by other bounds"
	_c extends Transparent_c<D, PC, _c>
> extends Normal<D, PC, E, PC, _c> {

	protected Transparent(D document, PC pc) {
		super(document, pc);
	}

	/**
	 * Uses the parent context directly, instead of an element-specific uncloseable content model.
	 *
	 * @return  The parent content model this element is within
	 */
	@Override
	final protected PC new__() {
		return pc;
	}
}
