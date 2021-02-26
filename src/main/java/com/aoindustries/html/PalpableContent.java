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
	UnionContent.Palpable_Phrasing<__>,
	//
	// Content models:
	//
	// Inherited from HeadingContent, InteractiveContent, SectioningContent, and Palpable_Phrasing: Content<__>
	SectioningContent<__>,
	HeadingContent<__>,
	InteractiveContent<__>,
	//
	// Content types:
	//
	// Inherited from InteractiveContent and Palpable_Phrasing: A_factory<__>
	// Inherited from Palpable_Phrasing: ABBR_factory<__>
	ADDRESS_factory<__>,
	// Inherited from SectioningContent: ARTICLE_factory<__>
	// Inherited from SectioningContent: ASIDE_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: AUDIO_factory<__> // if the controls attribute is present
	// Inherited from Palpable_Phrasing: B_factory<__>
	// Inherited from Palpable_Phrasing: BDI_factory<__>
	// Inherited from Palpable_Phrasing: BDO_factory<__>
	BLOCKQUOTE_factory<__>,
	// Inherited from InteractiveContent and Palpable_Phrasing: BUTTON_factory<__>
	// Inherited from Palpable_Phrasing: CANVAS_factory<__>
	// Inherited from Palpable_Phrasing: CITE_factory<__>
	// Inherited from Palpable_Phrasing: CODE_factory<__>
	// Inherited from Palpable_Phrasing: DATA_factory<__>
	// Inherited from InteractiveContent: DETAILS_factory<__>
	// Inherited from Palpable_Phrasing: DFN_factory<__>
	DIV_factory<__>,
	DL_factory<__>, // if childen contain at least one name/value pair
	// Inherited from Palpable_Phrasing: EM_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: EMBED_factory<__>
	FIELDSET_factory<__>,
	FIGURE_factory<__>,
	FOOTER_factory<__>,
	FORM_factory<__>,
	// Inherited from HeadingContent: H1_factory<__>
	// Inherited from HeadingContent: H2_factory<__>
	// Inherited from HeadingContent: H3_factory<__>
	// Inherited from HeadingContent: H4_factory<__>
	// Inherited from HeadingContent: H5_factory<__>
	// Inherited from HeadingContent: H6_factory<__>
	HEADER_factory<__>,
	// Inherited from HeadingContent: HGROUP_factory<__>
	// Inherited from Palpable_Phrasing: I_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: IFRAME_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: IMG_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: INPUT_factory<__> // if type attribute is not in the hidden state
	// Inherited from Palpable_Phrasing: INS_factory<__>
	// Inherited from Palpable_Phrasing: KBD_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: LABEL_factory<__>
	MAIN_factory<__>,
	// Inherited from Palpable_Phrasing: MAP_factory<__>
	// Inherited from Palpable_Phrasing: MARK_factory<__>
	// Inherited from Palpable_Phrasing: // TODO: MathML math
	// Inherited from InteractiveContent: MENU_factory<__> // if children include at least one li
	// Inherited from Palpable_Phrasing: METER_factory<__>
	// Inherited from SectioningContent: NAV_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: OBJECT_factory<__>
	OL_factory<__>, // if children include at least one li
	// Inherited from Palpable_Phrasing: OUTPUT_factory<__>
	P_factory<__>,
	PRE_factory<__>,
	// Inherited from Palpable_Phrasing: PROGRESS_factory<__>
	// Inherited from Palpable_Phrasing: Q_factory<__>
	// Inherited from Palpable_Phrasing: RUBY_factory<__>
	// Inherited from Palpable_Phrasing: S_factory<__>
	// Inherited from Palpable_Phrasing: SAMP_factory<__>
	// Inherited from SectioningContent: SECTION_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: SELECT_factory<__>
	// Inherited from Palpable_Phrasing: SMALL_factory<__>
	// Inherited from Palpable_Phrasing: SPAN_factory<__>
	// Inherited from Palpable_Phrasing: STRONG_factory<__>
	// Inherited from Palpable_Phrasing: SUB_factory<__>
	// Inherited from Palpable_Phrasing: SUP_factory<__>
	// Inherited from Palpable_Phrasing: // TODO: SVG svg
	TABLE_factory<__>,
	// Inherited from InteractiveContent and Palpable_Phrasing: TEXTAREA_factory<__>
	// Inherited from Palpable_Phrasing: TIME_factory<__>
	// Inherited from Palpable_Phrasing: U_factory<__>
	UL_factory<__> // if children include at least one li
	// Inherited from Palpable_Phrasing: VAR_factory<__>
	// Inherited from InteractiveContent and Palpable_Phrasing: VIDEO_factory<__>
	// Inherited from Palpable_Phrasing: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
	// Inherited from Palpable_Phrasing: TextContent<__> // that is not inter-element whitespace
{
}
