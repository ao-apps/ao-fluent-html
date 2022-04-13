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

import com.aoapps.html.any.AnyMAIN_c;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/main">&lt;main&gt;</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_main.asp">HTML main Tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @since HTML 5
 *
 * @author  AO Industries, Inc.
 */
public final class MAIN_c<
	PC extends PalpableContent<PC>
>
	extends AnyMAIN_c<Document, PC, MAIN_c<PC>>
	implements FlowContent<MAIN_c<PC>> {

	MAIN_c(MAIN<PC> element) {
		super(element);
	}
}
