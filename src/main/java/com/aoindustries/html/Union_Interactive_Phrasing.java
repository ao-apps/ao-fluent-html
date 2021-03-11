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
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Interactive_Phrasing<
	D  extends AnyDocument<D>,
	__ extends Union_Interactive_Phrasing<D, __>
> extends
	//
	// Unions:
	//
	Union_Embedded_Interactive<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>

	//
	// Factories:
	//
	A_factory<D, __>,
	// Inherited: AUDIO_factory<D, __>
	BUTTON_factory<D, __>,
	// Inherited: EMBED_factory<D, __>
	INPUT_factory<D, __>,
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __>
	LABEL_factory<D, __>,
	// Inherited: OBJECT_factory<D, __>
	SELECT_factory<D, __>,
	TEXTAREA_factory<D, __>
	// Inherited: VIDEO_factory<D, __>
{
}
