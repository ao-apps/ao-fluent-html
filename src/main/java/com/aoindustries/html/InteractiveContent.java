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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#interactive-content">3.2.5.2.7 Interactive content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#interactive_content">Interactive content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface InteractiveContent<
	D  extends AnyDocument<D>,
	__ extends InteractiveContent<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Union_Embedded_Interactive<D, __>
	Union_Interactive_Phrasing<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
{
	//
	// Factories:
	//
	// Inherited: A - if the href attribute is present
	// Inherited: AUDIO - if the controls attribute is present
	// Inherited: BUTTON
	// <editor-fold defaultstate="collapsed" desc="DETAILS">
	/**
	 * Opens a new details element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/interactive-elements.html#the-details-element">4.11.1 The details element</a>.
	 * </p>
	 */
	@Factory("details")
	default void details() throws IOException {
		throw new AssertionError("TODO: Implement details");
	}
	// </editor-fold>
	// Inherited: EMBED
	// Inherited: IFRAME
	// Inherited: IMG - if the usemap attribute is present
	// Inherited: INPUT - if type attribute is not in the hidden state
	// Inherited: LABEL
	// <editor-fold defaultstate="collapsed" desc="MENU - (MDN only) if the type attribute is in the toolbar state">
	/**
	 * Opens a new menu element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-menu-element">4.4.7 The menu element</a>.
	 * </p>
	 */
	@Factory("menu")
	default MENU<D, __> menu() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new MENU<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a menu element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-menu-element">4.4.7 The menu element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("menu")
	default <Ex extends Throwable> __ menu__(IORunnableE<Ex> menu) throws IOException, Ex {
		return menu().__(menu);
	}

	/**
	 * Creates a menu element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-menu-element">4.4.7 The menu element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("menu")
	default <Ex extends Throwable> __ menu__(IOConsumerE<? super MENU__<D, __>, Ex> menu) throws IOException, Ex {
		return menu().__(menu);
	}

	/**
	 * Creates an empty menu element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-menu-element">4.4.7 The menu element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("menu")
	default __ menu__() throws IOException {
		return menu().__();
	}

	/**
	 * Creates a menu element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-menu-element">4.4.7 The menu element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("menu")
	default MENU_c<D, __> menu_c() throws IOException {
		return menu()._c();
	}
	// </editor-fold>
	// Inherited: OBJECT - if the usemap attribute is present
	// Inherited: SELECT
	// Inherited: TEXTAREA
	// Inherited: VIDEO - if the controls attribute is present
}
