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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#embedded-content-2">3.2.5.2.6 Embedded content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#embedded_content">Embedded content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface EmbeddedContent<
	D  extends AnyDocument<D>,
	__ extends EmbeddedContent<D, __>
> extends
	//
	// Unions:
	//
	Union_Embedded_Interactive<D, __>,
	Union_Embedded_Palpable_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>

	//
	// Factories:
	//
	// Inherited: AUDIO_factory<D, __>
	// Inherited: CANVAS_factory<D, __>
	// Inherited: EMBED_factory<D, __>
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __>
	// Inherited: // TODO: MathML math
	// Inherited: OBJECT_factory<D, __>
	PICTURE_factory<D, __>
	// Inherited: // TODO: SVG svg
	// Inherited: VIDEO_factory<D, __>
{
}
