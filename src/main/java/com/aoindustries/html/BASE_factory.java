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

import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface BASE_factory<__ extends MetadataContent<__>> extends Content<__> {

	/**
	 * Opens a new base element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
	 * </ul>
	 */
	default BASE<__> base() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new BASE<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Shortcut to create a base with href only.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ base__(String href) throws IOException {
		return base().href(href).__();
	}
	// TODO: IOSupplierE version like A? (review others, too)
}
