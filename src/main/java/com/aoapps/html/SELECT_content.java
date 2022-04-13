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

import com.aoapps.html.any.AnySELECT_content;
import com.aoapps.lang.io.function.IOConsumerE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-select-element">4.10.7 The select element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select">&lt;section&gt;: The HTML Select element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_select.asp">HTML select tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface SELECT_content<
	__ extends SELECT_content<__>
> extends AnySELECT_content<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	// Inherited: Union_DATALIST_OPTGROUP<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	// Inherited: ScriptSupportingContent<__>

	//
	// Per-element content models:
	//
	OPTGROUP_content<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="OPTGROUP">
	@Override
	@SuppressWarnings("deprecation")
	default OPTGROUP<__> optgroup() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new OPTGROUP<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates an optgroup element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/optgroup">&lt;optgroup&gt;: The Option Group element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_optgroup.asp">HTML optgroup tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ optgroup__(IOConsumerE<? super OPTGROUP__<__>, Ex> optgroup) throws IOException, Ex {
		return optgroup().__(optgroup);
	}

	@Override
	default OPTGROUP_c<__> optgroup_c() throws IOException {
		return optgroup()._c();
	}
	// </editor-fold>
	// Inherited: OPTION
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
