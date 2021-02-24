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

/**
 * Elements that are common to all three of {@link Tbody}, {@link Thead}, and {@link Tfoot}.
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.</li>
 * <li>See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.</li>
 * <li>See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.</li>
 * </ul>
 *
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface TbodyTheadTfootContent<C extends TbodyTheadTfootContent<C>> extends
	//
	// Content models:
	//
	// Inherited from ScriptSupportingContent: Content
	ScriptSupportingContent<C>,
	//
	// Content types:
	//
	Contents.Tabular.Tr<C>
	// Inherited from ScriptSupportingContent: Contents.Scripting.Script<C>
	// Inherited from ScriptSupportingContent: Contents.Scripting.Template<C>
{
}
