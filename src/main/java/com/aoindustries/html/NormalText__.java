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
 * A normal element that can have textual content.
 * <p>
 * See <a href="https://html.spec.whatwg.org/#normal-elements">13.1.2 Elements / Normal elements</a>.
 * </p>
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public abstract class NormalText__<
	PC extends Content<PC>,
	__ extends NormalText__<PC, __>
> extends Normal__<PC, __> implements Union_Palpable_Phrasing<__> {

	protected NormalText__(NormalText<?, PC, __, ?> element) {
		super(element);
	}
}