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
package com.aoindustries.html.any;

import java.io.IOException;

/**
 * Elements that are common to both {@link AnyCOLGROUP_content} and {@link AnyScriptSupportingContent}.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnyUnion_COLGROUP_ScriptSupporting<
	D  extends AnyDocument<D>,
	__ extends AnyUnion_COLGROUP_ScriptSupporting<D, __>
> extends
	//
	// Content models:
	//
	Content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="TEMPLATE">
	/**
	 * Opens a new template element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-template-element">4.12.3 The template element</a>.
	 * </p>
	 */
	@Factory("template")
	default void template() throws IOException {
		throw new AssertionError("TODO: Implement template");
	}
	// </editor-fold>
}
