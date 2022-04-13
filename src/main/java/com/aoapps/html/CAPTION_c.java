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

import com.aoapps.html.any.AnyCAPTION_c;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/caption">&lt;caption&gt;: The Table Caption element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_caption.asp">HTML caption tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public final class CAPTION_c<
	PC extends TABLE_content<PC>
>
	extends AnyCAPTION_c<Document, PC, CAPTION_c<PC>>
	implements FlowContent<CAPTION_c<PC>> {

	CAPTION_c(CAPTION<PC> element) {
		super(element);
	}
}
