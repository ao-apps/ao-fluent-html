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
 * Elements that are common to both {@link DATALIST_content} and {@link OPTGROUP_content}.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_DATALIST_OPTGROUP<
	D  extends AnyDocument<D>,
	__ extends Union_DATALIST_OPTGROUP<D, __>
> extends
	//
	// Content models:
	//
	Content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="OPTION">
	/**
	 * Opens a new option element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-option-element">4.10.10 The option element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
	 * </ul>
	 */
	@Factory("option")
	default OPTION<D, __> option() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new OPTION<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an empty option element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-option-element">4.10.10 The option element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("option")
	default __ option__() throws IOException {
		return option().__();
	}
	// </editor-fold>
}
