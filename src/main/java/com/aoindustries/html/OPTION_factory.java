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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface OPTION_factory<__ extends Union_DATALIST_OPTGROUP<__>> extends Content<__> {

	/**
	 * Opens a new option element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
	 * </ul>
	 */
	default OPTION<__> option() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new OPTION<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Creates an empty option element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ option__() throws IOException {
		return option().__();
	}
}
