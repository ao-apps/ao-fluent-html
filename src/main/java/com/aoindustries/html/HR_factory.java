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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-hr-element">4.4.2 The hr element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface HR_factory<
	D  extends AnyDocument<D>,
	__ extends FlowContent<D, __>
> extends Content<D, __> {

	/**
	 * Opens a new hr element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-hr-element">4.4.2 The hr element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
	 * </ul>
	 */
	default HR<D, __> hr() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new HR<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an empty hr element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-hr-element">4.4.2 The hr element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ hr__() throws IOException {
		return hr().__();
	}
}
