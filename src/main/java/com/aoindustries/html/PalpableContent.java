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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#palpable-content">3.2.5.2.8 Palpable content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#palpable_content">Palpable content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PalpableContent<
	D  extends AnyDocument<D>,
	__ extends PalpableContent<D, __>
> extends
	//
	// Unions:
	//
	Union_DL_Palpable<D, __>,
	// Inherited: Union_Embedded_Interactive<D, __>
	// Inherited: Union_Embedded_Palpable_Phrasing<D, __>
	// Inherited: Union_Interactive_Phrasing<D, __>
	Union_Palpable_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	SectioningContent<D, __>,
	HeadingContent<D, __>,
	InteractiveContent<D, __>,
	// Inherited: TextContent<D, __> // that is not inter-element whitespace

	//
	// Factories:
	//
	// Inherited: A_factory<D, __>
	// Inherited: ABBR_factory<D, __>
	ADDRESS_factory<D, __>,
	// Inherited: ARTICLE_factory<D, __>
	// Inherited: ASIDE_factory<D, __>
	// Inherited: AUDIO_factory<D, __> // if the controls attribute is present
	// Inherited: B_factory<D, __>
	// Inherited: BDI_factory<D, __>
	// Inherited: BDO_factory<D, __>
	BLOCKQUOTE_factory<D, __>,
	// Inherited: BUTTON_factory<D, __>
	// Inherited: CANVAS_factory<D, __>
	// Inherited: CITE_factory<D, __>
	// Inherited: CODE_factory<D, __>
	// Inherited: DATA_factory<D, __>
	// Inherited: DETAILS_factory<D, __>
	// Inherited: DFN_factory<D, __>
	// Inherited: DIV_factory<D, __>
	DL_factory<D, __>, // if childen contain at least one name/value pair
	// Inherited: EM_factory<D, __>
	// Inherited: EMBED_factory<D, __>
	FIELDSET_factory<D, __>,
	FIGURE_factory<D, __>,
	FOOTER_factory<D, __>,
	FORM_factory<D, __>,
	// Inherited: H1_factory<D, __>
	// Inherited: H2_factory<D, __>
	// Inherited: H3_factory<D, __>
	// Inherited: H4_factory<D, __>
	// Inherited: H5_factory<D, __>
	// Inherited: H6_factory<D, __>
	HEADER_factory<D, __>,
	// Inherited: HGROUP_factory<D, __>
	// Inherited: I_factory<D, __>
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __>
	// Inherited: INPUT_factory<D, __> // if type attribute is not in the hidden state
	// Inherited: INS_factory<D, __>
	// Inherited: KBD_factory<D, __>
	// Inherited: LABEL_factory<D, __>
	MAIN_factory<D, __>,
	// Inherited: MAP_factory<D, __>
	// Inherited: MARK_factory<D, __>
	// Inherited: // TODO: MathML math
	// Inherited: MENU_factory<D, __> // if children include at least one li
	// Inherited: METER_factory<D, __>
	// Inherited: NAV_factory<D, __>
	// Inherited: OBJECT_factory<D, __>
	OL_factory<D, __>, // if children include at least one li
	// Inherited: OUTPUT_factory<D, __>
	P_factory<D, __>,
	PRE_factory<D, __>,
	// Inherited: PROGRESS_factory<D, __>
	// Inherited: Q_factory<D, __>
	// Inherited: RUBY_factory<D, __>
	// Inherited: S_factory<D, __>
	// Inherited: SAMP_factory<D, __>
	// Inherited: SECTION_factory<D, __>
	// Inherited: SELECT_factory<D, __>
	// Inherited: SMALL_factory<D, __>
	// Inherited: SPAN_factory<D, __>
	// Inherited: STRONG_factory<D, __>
	// Inherited: SUB_factory<D, __>
	// Inherited: SUP_factory<D, __>
	// Inherited: // TODO: SVG svg
	TABLE_factory<D, __>,
	// Inherited: TEXTAREA_factory<D, __>
	// Inherited: TIME_factory<D, __>
	// Inherited: U_factory<D, __>
	UL_factory<D, __> // if children include at least one li
	// Inherited: VAR_factory<D, __>
	// Inherited: VIDEO_factory<D, __>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
{
}
