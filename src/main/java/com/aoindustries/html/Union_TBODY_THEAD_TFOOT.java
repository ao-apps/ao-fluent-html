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

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * Elements that are common to all three of {@link TBODY}, {@link THEAD}, and {@link TFOOT}.
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tbody-element">4.9.5 The tbody element</a>.</li>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-thead-element">4.9.6 The thead element</a>.</li>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_TBODY_THEAD_TFOOT<
	D  extends AnyDocument<D>,
	__ extends Union_TBODY_THEAD_TFOOT<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: COLGROUP_ScriptSupporting<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	ScriptSupportingContent<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="TR">
	/**
	 * Opens a new tr element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tr-element">4.9.8 The tr element</a>.
	 * </p>
	 */
	@Factory("tr")
	default TR<D, __> tr() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new TR<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a tr element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tr-element">4.9.8 The tr element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tr")
	default <Ex extends Throwable> __ tr__(IORunnableE<Ex> tr) throws IOException, Ex {
		return tr().__(tr);
	}

	/**
	 * Creates a tr element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tr-element">4.9.8 The tr element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tr")
	default <Ex extends Throwable> __ tr__(IOConsumerE<? super TR__<D, __>, Ex> tr) throws IOException, Ex {
		return tr().__(tr);
	}

	/**
	 * Creates an empty tr element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tr-element">4.9.8 The tr element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tr")
	default __ tr__() throws IOException {
		return tr().__();
	}

	/**
	 * Creates a tr element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tr-element">4.9.8 The tr element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("tr")
	default TR_c<D, __> tr_c() throws IOException {
		return tr()._c();
	}
	// </editor-fold>
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
