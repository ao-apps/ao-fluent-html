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
 * <li>See <a href="https://html.spec.whatwg.org/#palpable-content">3.2.5.2.8 Palpable content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#palpable_content">Palpable content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PalpableContent<__ extends PalpableContent<__>> extends
	//
	// Unions:
	//
	// Inherited: Union_Embedded_Interactive<__>
	// Inherited: Union_Embedded_Palpable_Phrasing<__>
	// Inherited: Union_Interactive_Phrasing<__>
	Union_Palpable_Phrasing<__>,
	//
	// Content models:
	//
	// Inherited: Content<__>
	SectioningContent<__>,
	HeadingContent<__>,
	InteractiveContent<__>,
	// Inherited: TextContent<__> // that is not inter-element whitespace
	//
	// Factories:
	//
	// Inherited: A_factory<__>
	// Inherited: ABBR_factory<__>
	ADDRESS_factory<__>,
	// Inherited: ARTICLE_factory<__>
	// Inherited: ASIDE_factory<__>
	// Inherited: AUDIO_factory<__> // if the controls attribute is present
	// Inherited: B_factory<__>
	// Inherited: BDI_factory<__>
	// Inherited: BDO_factory<__>
	BLOCKQUOTE_factory<__>,
	// Inherited: BUTTON_factory<__>
	// Inherited: CANVAS_factory<__>
	// Inherited: CITE_factory<__>
	// Inherited: CODE_factory<__>
	// Inherited: DATA_factory<__>
	// Inherited: DETAILS_factory<__>
	// Inherited: DFN_factory<__>
	DIV_factory<__>,
	DL_factory<__>, // if childen contain at least one name/value pair
	// Inherited: EM_factory<__>
	// Inherited: EMBED_factory<__>
	FIELDSET_factory<__>,
	FIGURE_factory<__>,
	FOOTER_factory<__>,
	FORM_factory<__>,
	// Inherited: H1_factory<__>
	// Inherited: H2_factory<__>
	// Inherited: H3_factory<__>
	// Inherited: H4_factory<__>
	// Inherited: H5_factory<__>
	// Inherited: H6_factory<__>
	HEADER_factory<__>,
	// Inherited: HGROUP_factory<__>
	// Inherited: I_factory<__>
	// Inherited: IFRAME_factory<__>
	// Inherited: IMG_factory<__>
	// Inherited: INPUT_factory<__> // if type attribute is not in the hidden state
	// Inherited: INS_factory<__>
	// Inherited: KBD_factory<__>
	// Inherited: LABEL_factory<__>
	MAIN_factory<__>,
	// Inherited: MAP_factory<__>
	// Inherited: MARK_factory<__>
	// Inherited: // TODO: MathML math
	// Inherited: MENU_factory<__> // if children include at least one li
	// Inherited: METER_factory<__>
	// Inherited: NAV_factory<__>
	// Inherited: OBJECT_factory<__>
	OL_factory<__>, // if children include at least one li
	// Inherited: OUTPUT_factory<__>
	P_factory<__>,
	PRE_factory<__>,
	// Inherited: PROGRESS_factory<__>
	// Inherited: Q_factory<__>
	// Inherited: RUBY_factory<__>
	// Inherited: S_factory<__>
	// Inherited: SAMP_factory<__>
	// Inherited: SECTION_factory<__>
	// Inherited: SELECT_factory<__>
	// Inherited: SMALL_factory<__>
	// Inherited: SPAN_factory<__>
	// Inherited: STRONG_factory<__>
	// Inherited: SUB_factory<__>
	// Inherited: SUP_factory<__>
	// Inherited: // TODO: SVG svg
	TABLE_factory<__>,
	// Inherited: TEXTAREA_factory<__>
	// Inherited: TIME_factory<__>
	// Inherited: U_factory<__>
	UL_factory<__> // if children include at least one li
	// Inherited: VAR_factory<__>
	// Inherited: VIDEO_factory<__>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
{
}
