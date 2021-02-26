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
 * <li>See <a href="https://html.spec.whatwg.org/#interactive-content">3.2.5.2.7 Interactive content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#interactive_content">Interactive content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface InteractiveContent<__ extends InteractiveContent<__>> extends
	//
	// Unions:
	//
	UnionContent.Interactive_Phrasing<__>,
	//
	// Content models:
	//
	// Inherited from Interactive_Phrasing: Content<__>
	//
	// Content types:
	//
	// Inherited from Interactive_Phrasing: A_factory<__> // if the href attribute is present
	// Inherited from Interactive_Phrasing: AUDIO_factory<__> // if the controls attribute is present
	// Inherited from Interactive_Phrasing: BUTTON_factory<__>
	DETAILS_factory<__>,
	// Inherited from Interactive_Phrasing: EMBED_factory<__>
	// Inherited from Interactive_Phrasing: IFRAME_factory<__>
	// Inherited from Interactive_Phrasing: IMG_factory<__> // if the usemap attribute is present
	// Inherited from Interactive_Phrasing: INPUT_factory<__> // if type attribute is not in the hidden state
	// Inherited from Interactive_Phrasing: LABEL_factory<__>
	MENU_factory<__> // (MDN only) if the type attribute is in the toolbar state
	// Inherited from Interactive_Phrasing: OBJECT_factory<__> // if the usemap attribute is present
	// Inherited from Interactive_Phrasing: SELECT_factory<__>
	// Inherited from Interactive_Phrasing: TEXTAREA_factory<__>
	// Inherited from Interactive_Phrasing: VIDEO_factory<__> // if the controls attribute is present
{
}
