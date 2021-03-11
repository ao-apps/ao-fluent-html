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
 * Elements that are common to both {@link PalpableContent} and {@link PhrasingContent}.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Palpable_Phrasing<
	D  extends AnyDocument<D>,
	__ extends Union_Palpable_Phrasing<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Embedded_Interactive<D, __>
	Union_Embedded_Palpable_Phrasing<D, __>,
	Union_Interactive_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	TextContent<D, __>, // that is not inter-element whitespace

	//
	// Factories:
	//
	// Inherited: A_factory<D, __>
	ABBR_factory<D, __>,
	// Inherited: AUDIO_factory<D, __>
	B_factory<D, __>,
	BDI_factory<D, __>,
	BDO_factory<D, __>,
	// Inherited: BUTTON_factory<D, __>
	// Inherited: CANVAS_factory<D, __>
	CITE_factory<D, __>,
	CODE_factory<D, __>,
	DATA_factory<D, __>,
	DFN_factory<D, __>,
	EM_factory<D, __>,
	// Inherited: EMBED_factory<D, __>
	I_factory<D, __>,
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __>
	// Inherited: INPUT_factory<D, __>
	INS_factory<D, __>,
	KBD_factory<D, __>,
	// Inherited: LABEL_factory<D, __>
	MAP_factory<D, __>,
	MARK_factory<D, __>,
	// Inherited: // TODO: MathML math
	METER_factory<D, __>,
	// Inherited: OBJECT_factory<D, __>
	OUTPUT_factory<D, __>,
	PROGRESS_factory<D, __>,
	Q_factory<D, __>,
	RUBY_factory<D, __>,
	S_factory<D, __>,
	SAMP_factory<D, __>,
	// Inherited: SELECT_factory<D, __>
	SMALL_factory<D, __>,
	SPAN_factory<D, __>,
	STRONG_factory<D, __>,
	SUB_factory<D, __>,
	SUP_factory<D, __>,
	// Inherited: // TODO: SVG svg
	// Inherited: TEXTAREA_factory<D, __>
	TIME_factory<D, __>,
	U_factory<D, __>,
	VAR_factory<D, __>
	// Inherited: VIDEO_factory<D, __>
	// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
{
}
