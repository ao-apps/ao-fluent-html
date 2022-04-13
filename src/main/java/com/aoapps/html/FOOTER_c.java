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

import com.aoapps.html.any.AnyFOOTER_c;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/footer">&lt;footer&gt;</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_footer.asp">HTML footer Tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @since HTML 5
 *
 * @author  AO Industries, Inc.
 */
public final class FOOTER_c<
	PC extends PalpableContent<PC>
>
	extends AnyFOOTER_c<Document, PC, FOOTER_c<PC>>
	implements FlowContent<FOOTER_c<PC>> {

	FOOTER_c(FOOTER<PC> element) {
		super(element);
	}
}
