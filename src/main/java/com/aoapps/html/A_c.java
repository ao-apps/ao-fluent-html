/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.aoapps.html;

import com.aoapps.html.any.AnyA_c;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-a-element">4.5.1 The a element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a">&lt;a&gt;: The Anchor element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_a.asp">HTML a tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public final class A_c<
	PC extends Union_Interactive_Phrasing<PC>
>
	extends AnyA_c<Document, PC, A_c<PC>>
	implements Union_Interactive_Phrasing<A_c<PC>> {

	A_c(A<PC> element) {
		super(element);
	}
}
