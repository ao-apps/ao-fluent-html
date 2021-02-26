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
 * <li>See <a href="https://html.spec.whatwg.org/#flow-content">3.2.5.2.2 Flow content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#flow_content">Flow content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface FlowContent<__ extends FlowContent<__>> extends
	//
	// Content models:
	//
	// Inherited from PalpableContent and PhrasingContent: Content<__>
	// Inherited from PalpableContent: SectioningContent<__>
	// Inherited from PalpableContent: HeadingContent<__>
	PhrasingContent<__>,
	// Inherited from PhrasingContent: EmbeddedContent<__>
	// Inherited from PalpableContent: InteractiveContent<__>
	PalpableContent<__>,
	//
	// Content types:
	//
	// Inherited from PalpableContent and PhrasingContent: A_factory<__>
	// Inherited from PalpableContent and PhrasingContent: ABBR_factory<__>
	// Inherited from PalpableContent: ADDRESS_factory<__>
	// Inherited from PhrasingContent: AREA_factory<__> // if a descendent of map
	// Inherited from PalpableContent: ARTICLE_factory<__>
	// Inherited from PalpableContent: ASIDE_factory<__>
	// Inherited from PalpableContent and PhrasingContent: AUDIO_factory<__>
	// Inherited from PalpableContent and PhrasingContent: B_factory<__>
	// Inherited from PalpableContent and PhrasingContent: BDI_factory<__>
	// Inherited from PalpableContent and PhrasingContent: BDO_factory<__>
	// Inherited from PalpableContent: BLOCKQUOTE_factory<__>
	// Inherited from PhrasingContent: BR_factory<__>
	// Inherited from PalpableContent and PhrasingContent: BUTTON_factory<__>
	// Inherited from PalpableContent and PhrasingContent: CANVAS_factory<__>
	// Inherited from PalpableContent and PhrasingContent: CITE_factory<__>
	// Inherited from PalpableContent and PhrasingContent: CODE_factory<__>
	// Inherited from PalpableContent and PhrasingContent: DATA_factory<__>
	// Inherited from PhrasingContent: DATALIST_factory<__>
	// Inherited from PhrasingContent: DEL_factory<__>
	// Inherited from PalpableContent: DETAILS_factory<__>
	// Inherited from PalpableContent and PhrasingContent: DFN_factory<__>
	DIALOG_factory<__>,
	// Inherited from PalpableContent: DIV_factory<__>
	// Inherited from PalpableContent: DL_factory<__>
	// Inherited from PalpableContent and PhrasingContent: EM_factory<__>
	// Inherited from PalpableContent and PhrasingContent: EMBED_factory<__>
	// Inherited from PalpableContent: FIELDSET_factory<__>
	// Inherited from PalpableContent: FIGURE_factory<__>
	// Inherited from PalpableContent: FOOTER_factory<__>
	// Inherited from PalpableContent: FORM_factory<__>
	// Inherited from PalpableContent: H1_factory<__>
	// Inherited from PalpableContent: H2_factory<__>
	// Inherited from PalpableContent: H3_factory<__>
	// Inherited from PalpableContent: H4_factory<__>
	// Inherited from PalpableContent: H5_factory<__>
	// Inherited from PalpableContent: H6_factory<__>
	// Inherited from PalpableContent: HEADER_factory<__>
	// Inherited from PalpableContent: HGROUP_factory<__>
	HR_factory<__>
	// Inherited from PalpableContent and PhrasingContent: I_factory<__>
	// Inherited from PalpableContent and PhrasingContent: IFRAME_factory<__>
	// Inherited from PalpableContent and PhrasingContent: IMG_factory<__>
	// Inherited from PalpableContent and PhrasingContent: INPUT_factory<__>
	// Inherited from PhrasingContent and PalpableContent: INS_factory<__>
	// Inherited from PhrasingContent and PalpableContent: KBD_factory<__>
	// Inherited from PalpableContent and PhrasingContent: LABEL_factory<__>
	// Inherited from PhrasingContent: LINK_factory<__> // if it is allowed in body
	// Inherited from PalpableContent: MAIN_factory<__> // if it is a hierarchically correct main element
	// Inherited from PalpableContent and PhrasingContent: MAP_factory<__>
	// Inherited from PalpableContent and PhrasingContent: MARK_factory<__>
	// Inherited from PalpableContent and PhrasingContent: // TODO: MathML math
	// Inherited from PalpableContent: MENU_factory<__>
	// Inherited from PhrasingContent: META_factory<__> // if the itemprop attribute is present
	// Inherited from PalpableContent and PhrasingContent: METER_factory<__>
	// Inherited from PalpableContent: NAV_factory<__>
	// Inherited from PhrasingContent: NOSCRIPT_factory<__>
	// Inherited from PalpableContent and PhrasingContent: OBJECT_factory<__>
	// Inherited from PalpableContent: OL_factory<__>
	// Inherited from PalpableContent and PhrasingContent: OUTPUT_factory<__>
	// Inherited from PalpableContent: P_factory<__>
	// Inherited from PhrasingContent: PICTURE_factory<__>
	// Inherited from PalpableContent: PRE_factory<__>
	// Inherited from PalpableContent and PhrasingContent: PROGRESS_factory<__>
	// Inherited from PalpableContent and PhrasingContent: Q_factory<__>
	// Inherited from PalpableContent and PhrasingContent: RUBY_factory<__>
	// Inherited from PalpableContent and PhrasingContent: S_factory<__>
	// Inherited from PalpableContent and PhrasingContent: SAMP_factory<__>
	// Inherited from PhrasingContent: SCRIPT_factory<__>
	// Inherited from PalpableContent: SECTION_factory<__>
	// Inherited from PalpableContent and PhrasingContent: SELECT_factory<__>
	// Inherited from PhrasingContent: SLOT_factory<__>
	// Inherited from PalpableContent and PhrasingContent: SMALL_factory<__>
	// Inherited from PalpableContent and PhrasingContent: SPAN_factory<__>
	// Inherited from PalpableContent and PhrasingContent: STRONG_factory<__>
	// Inherited from PalpableContent and PhrasingContent: SUB_factory<__>
	// Inherited from PalpableContent and PhrasingContent: SUP_factory<__>
	// Inherited from PalpableContent and PhrasingContent: // TODO: SVG svg
	// Inherited from PalpableContent: TABLE_factory<__>
	// Inherited from PhrasingContent: TEMPLATE_factory<__>
	// Inherited from PalpableContent and PhrasingContent: TEXTAREA_factory<__>
	// Inherited from PalpableContent and PhrasingContent: TIME_factory<__>
	// Inherited from PalpableContent and PhrasingContent: U_factory<__>
	// Inherited from PalpableContent: UL_factory<__>
	// Inherited from PalpableContent and PhrasingContent: VAR_factory<__>
	// Inherited from PalpableContent and PhrasingContent: VIDEO_factory<__>
	// Inherited from PhrasingContent: WBR_factory<__>
	// Inherited from PalpableContent and PhrasingContent: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
	// Inherited from PalpableContent and PhrasingContent: TextContent<__>
{
}
