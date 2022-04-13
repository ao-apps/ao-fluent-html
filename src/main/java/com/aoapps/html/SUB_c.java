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

import com.aoapps.html.any.AnySUB_c;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/sub">&lt;sub&gt;: The Subscript element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_sub.asp">HTML sub tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public final class SUB_c<
	PC extends Union_Palpable_Phrasing<PC>
>
	extends AnySUB_c<Document, PC, SUB_c<PC>>
	implements PhrasingContent<SUB_c<PC>> {

	SUB_c(SUB<PC> element) {
		super(element);
	}
}
