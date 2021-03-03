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
 * Elements that are common to both {@link InteractiveContent} and {@link PhrasingContent}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Interactive_Phrasing<__ extends Union_Interactive_Phrasing<__>> extends
	//
	// Unions:
	//
	Union_Embedded_Interactive<__>,

	//
	// Content models:
	//
	// Inherited: Content<__>

	//
	// Factories:
	//
	A_factory<__>,
	// Inherited: AUDIO_factory<__>
	BUTTON_factory<__>,
	// Inherited: EMBED_factory<__>
	INPUT_factory<__>,
	// Inherited: IFRAME_factory<__>
	// Inherited: IMG_factory<__>
	LABEL_factory<__>,
	// Inherited: OBJECT_factory<__>
	SELECT_factory<__>,
	TEXTAREA_factory<__>
	// Inherited: VIDEO_factory<__>
{
}
