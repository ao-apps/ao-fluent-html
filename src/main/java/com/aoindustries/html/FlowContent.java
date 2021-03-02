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
	// Unions:
	//
	// Inherited: UnionContent.COLGROUP_ScriptSupporting<__>
	// Inherited: UnionContent.Embedded_Interactive<__>
	// Inherited: UnionContent.Embedded_Palpable_Phrasing<__>
	// Inherited: UnionContent.Interactive_Phrasing<__>
	// Inherited: UnionContent.Metadata_Phrasing<__>
	// Inherited: UnionContent.Palpable_Phrasing<__>
	//
	// Content models:
	//
	// Inherited: Content<__>
	// Inherited: SectioningContent<__>
	// Inherited: HeadingContent<__>
	PhrasingContent<__>,
	// Inherited: EmbeddedContent<__>
	// Inherited: InteractiveContent<__>
	PalpableContent<__>,
	// Inherited: ScriptSupportingContent<__>
	// Inherited: TextContent<__>
	//
	// Factories:
	//
	// Inherited: A_factory<__>
	// Inherited: ABBR_factory<__>
	// Inherited: ADDRESS_factory<__>
	// Inherited: AREA_factory<__> // if a descendent of map
	// Inherited: ARTICLE_factory<__>
	// Inherited: ASIDE_factory<__>
	// Inherited: AUDIO_factory<__>
	// Inherited: B_factory<__>
	// Inherited: BDI_factory<__>
	// Inherited: BDO_factory<__>
	// Inherited: BLOCKQUOTE_factory<__>
	// Inherited: BR_factory<__>
	// Inherited: BUTTON_factory<__>
	// Inherited: CANVAS_factory<__>
	// Inherited: CITE_factory<__>
	// Inherited: CODE_factory<__>
	// Inherited: DATA_factory<__>
	// Inherited: DATALIST_factory<__>
	// Inherited: DEL_factory<__>
	// Inherited: DETAILS_factory<__>
	// Inherited: DFN_factory<__>
	DIALOG_factory<__>,
	// Inherited: DIV_factory<__>
	// Inherited: DL_factory<__>
	// Inherited: EM_factory<__>
	// Inherited: EMBED_factory<__>
	// Inherited: FIELDSET_factory<__>
	// Inherited: FIGURE_factory<__>
	// Inherited: FOOTER_factory<__>
	// Inherited: FORM_factory<__>
	// Inherited: H1_factory<__>
	// Inherited: H2_factory<__>
	// Inherited: H3_factory<__>
	// Inherited: H4_factory<__>
	// Inherited: H5_factory<__>
	// Inherited: H6_factory<__>
	// Inherited: HEADER_factory<__>
	// Inherited: HGROUP_factory<__>
	HR_factory<__>
	// Inherited: I_factory<__>
	// Inherited: IFRAME_factory<__>
	// Inherited: IMG_factory<__>
	// Inherited: INPUT_factory<__>
	// Inherited: INS_factory<__>
	// Inherited: KBD_factory<__>
	// Inherited: LABEL_factory<__>
	// Inherited: LINK_factory<__> // if it is allowed in body
	// Inherited: MAIN_factory<__> // if it is a hierarchically correct main element
	// Inherited: MAP_factory<__>
	// Inherited: MARK_factory<__>
	// Inherited: // TODO: MathML math
	// Inherited: MENU_factory<__>
	// Inherited: META_factory<__> // if the itemprop attribute is present
	// Inherited: METER_factory<__>
	// Inherited: NAV_factory<__>
	// Inherited: NOSCRIPT_factory<__>
	// Inherited: OBJECT_factory<__>
	// Inherited: OL_factory<__>
	// Inherited: OUTPUT_factory<__>
	// Inherited: P_factory<__>
	// Inherited: PICTURE_factory<__>
	// Inherited: PRE_factory<__>
	// Inherited: PROGRESS_factory<__>
	// Inherited: Q_factory<__>
	// Inherited: RUBY_factory<__>
	// Inherited: S_factory<__>
	// Inherited: SAMP_factory<__>
	// Inherited: SCRIPT_factory<__>
	// Inherited: SECTION_factory<__>
	// Inherited: SELECT_factory<__>
	// Inherited: SLOT_factory<__>
	// Inherited: SMALL_factory<__>
	// Inherited: SPAN_factory<__>
	// Inherited: STRONG_factory<__>
	// Inherited: SUB_factory<__>
	// Inherited: SUP_factory<__>
	// Inherited: // TODO: SVG svg
	// Inherited: TABLE_factory<__>
	// Inherited: TEMPLATE_factory<__>
	// Inherited: TEXTAREA_factory<__>
	// Inherited: TIME_factory<__>
	// Inherited: U_factory<__>
	// Inherited: UL_factory<__>
	// Inherited: VAR_factory<__>
	// Inherited: VIDEO_factory<__>
	// Inherited: WBR_factory<__>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
{
}
