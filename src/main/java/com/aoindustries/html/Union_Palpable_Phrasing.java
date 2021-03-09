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
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Palpable_Phrasing<__ extends Union_Palpable_Phrasing<__>> extends
	//
	// Unions:
	//
	// Inherited: Embedded_Interactive<__>
	Union_Embedded_Palpable_Phrasing<__>,
	Union_Interactive_Phrasing<__>,

	//
	// Content models:
	//
	// Inherited: Content<__>
	TextContent<__>, // that is not inter-element whitespace

	//
	// Factories:
	//
	// Inherited: A_factory<__>
	ABBR_factory<__>,
	// Inherited: AUDIO_factory<__>
	B_factory<__>,
	BDI_factory<__>,
	BDO_factory<__>,
	// Inherited: BUTTON_factory<__>
	// Inherited: CANVAS_factory<__>
	CITE_factory<__>,
	CODE_factory<__>,
	DATA_factory<__>,
	DFN_factory<__>,
	EM_factory<__>,
	// Inherited: EMBED_factory<__>
	I_factory<__>,
	// Inherited: IFRAME_factory<__>
	// Inherited: IMG_factory<__>
	// Inherited: INPUT_factory<__>
	INS_factory<__>,
	KBD_factory<__>,
	// Inherited: LABEL_factory<__>
	MAP_factory<__>,
	MARK_factory<__>,
	// Inherited: // TODO: MathML math
	METER_factory<__>,
	// Inherited: OBJECT_factory<__>
	OUTPUT_factory<__>,
	PROGRESS_factory<__>,
	Q_factory<__>,
	RUBY_factory<__>,
	S_factory<__>,
	SAMP_factory<__>,
	// Inherited: SELECT_factory<__>
	SMALL_factory<__>,
	SPAN_factory<__>,
	STRONG_factory<__>,
	SUB_factory<__>,
	SUP_factory<__>,
	// Inherited: // TODO: SVG svg
	// Inherited: TEXTAREA_factory<__>
	TIME_factory<__>,
	U_factory<__>,
	VAR_factory<__>
	// Inherited: VIDEO_factory<__>
	// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
{
}
