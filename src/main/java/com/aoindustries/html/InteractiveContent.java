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
	Union_Interactive_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>

	//
	// Factories:
	//
	// Inherited: A_factory<D, __> // if the href attribute is present
	// Inherited: AUDIO_factory<D, __> // if the controls attribute is present
	// Inherited: BUTTON_factory<D, __>
	DETAILS_factory<D, __>,
	// Inherited: EMBED_factory<D, __>
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __> // if the usemap attribute is present
	// Inherited: INPUT_factory<D, __> // if type attribute is not in the hidden state
	// Inherited: LABEL_factory<D, __>
	MENU_factory<D, __> // (MDN only) if the type attribute is in the toolbar state
	// Inherited: OBJECT_factory<D, __> // if the usemap attribute is present
	// Inherited: SELECT_factory<D, __>
	// Inherited: TEXTAREA_factory<D, __>
	// Inherited: VIDEO_factory<D, __> // if the controls attribute is present
{
}
