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

import com.aoindustries.html.any.AnyUnion_DL_Palpable;
import com.aoindustries.io.function.IOConsumerE;
import java.io.IOException;

/**
 * Elements that are common to both {@link DL_content} and {@link PalpableContent}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_DL_Palpable<
	__ extends Union_DL_Palpable<__>
> extends AnyUnion_DL_Palpable<Document, __>,
	//
	// Content models:
	//
	Content<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="DIV">
	@Override
	default DIV<__> div() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new DIV<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a div element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-div-element">4.4.15 The div element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ div__(IOConsumerE<? super DIV__<__>, Ex> div) throws IOException, Ex {
		return div().__(div);
	}

	@Override
	default DIV_c<__> div_c() throws IOException {
		return div()._c();
	}
	// </editor-fold>
}
