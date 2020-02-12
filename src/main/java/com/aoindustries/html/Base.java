/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020  AO Industries, Inc.
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

import java.io.IOException;

/**
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.
 * See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Base extends EmptyElement<Base> implements
	Attributes.Url.Href<Base>
	// TODO: target
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <base>: Attributes.Event.AlmostGlobal<Base>
{

	public Base(Html html) {
		super(html);
	}

	@Override
	protected Base open() throws IOException {
		html.out.write("<base");
		return this;
	}
}
