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

import com.aoapps.html.any.AnyTR_content;
import com.aoapps.lang.io.function.IOConsumerE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tr-element">4.9.8 The tr element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/tr">&lt;tr&gt;: The Table Row element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_tr.asp">HTML tr tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface TR_content<
	__ extends TR_content<__>
> extends AnyTR_content<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	ScriptSupportingContent<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="TD">
	@Override
	@SuppressWarnings("deprecation")
	default TD<__> td() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new TD<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a td element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-td-element">4.9.9 The td element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td">&lt;td&gt;: The Table Data Cell element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_td.asp">HTML td tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ td__(IOConsumerE<? super TD__<__>, Ex> td) throws IOException, Ex {
		return td().__(td);
	}

	@Override
	default TD_c<__> td_c() throws IOException {
		return td()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TH">
	@Override
	@SuppressWarnings("deprecation")
	default TH<__> th() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new TH<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a th element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-th-element">4.9.10 The th element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/th">&lt;th&gt;: The Table Header element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_th.asp">HTML th tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ th__(IOConsumerE<? super TH__<__>, Ex> th) throws IOException, Ex {
		return th().__(th);
	}

	@Override
	default TH_c<__> th_c() throws IOException {
		return th()._c();
	}
	// </editor-fold>
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
