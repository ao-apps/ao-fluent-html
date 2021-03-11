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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#flow-content">3.2.5.2.2 Flow content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#flow_content">Flow content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface FlowContent<
	D  extends AnyDocument<D>,
	__ extends FlowContent<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<D, __>
	// Inherited: Union_DL_Palpable<D, __>
	// Inherited: Union_Embedded_Interactive<D, __>
	// Inherited: Union_Embedded_Palpable_Phrasing<D, __>
	// Inherited: Union_Interactive_Phrasing<D, __>
	// Inherited: Union_Metadata_Phrasing<D, __>
	// Inherited: Union_Palpable_Phrasing<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	// Inherited: EmbeddedContent<D, __>
	// Inherited: HeadingContent<D, __>
	// Inherited: InteractiveContent<D, __>
	PalpableContent<D, __>,
	PhrasingContent<D, __>,
	// Inherited: ScriptSupportingContent<D, __>
	// Inherited: SectioningContent<D, __>
	// Inherited: TextContent<D, __>

	//
	// Factories:
	//
	// Inherited: A_factory<D, __>
	// Inherited: ABBR_factory<D, __>
	// Inherited: ADDRESS_factory<D, __>
	// Inherited: AREA_factory<D, __> // if a descendent of map
	// Inherited: ARTICLE_factory<D, __>
	// Inherited: ASIDE_factory<D, __>
	// Inherited: AUDIO_factory<D, __>
	// Inherited: B_factory<D, __>
	// Inherited: BDI_factory<D, __>
	// Inherited: BDO_factory<D, __>
	// Inherited: BLOCKQUOTE_factory<D, __>
	// Inherited: BR_factory<D, __>
	// Inherited: BUTTON_factory<D, __>
	// Inherited: CANVAS_factory<D, __>
	// Inherited: CITE_factory<D, __>
	// Inherited: CODE_factory<D, __>
	// Inherited: DATA_factory<D, __>
	// Inherited: DATALIST_factory<D, __>
	// Inherited: DEL_factory<D, __>
	// Inherited: DETAILS_factory<D, __>
	// Inherited: DFN_factory<D, __>
	DIALOG_factory<D, __>,
	// Inherited: DIV_factory<D, __>
	// Inherited: DL_factory<D, __>
	// Inherited: EM_factory<D, __>
	// Inherited: EMBED_factory<D, __>
	// Inherited: FIELDSET_factory<D, __>
	// Inherited: FIGURE_factory<D, __>
	// Inherited: FOOTER_factory<D, __>
	// Inherited: FORM_factory<D, __>
	// Inherited: H1_factory<D, __>
	// Inherited: H2_factory<D, __>
	// Inherited: H3_factory<D, __>
	// Inherited: H4_factory<D, __>
	// Inherited: H5_factory<D, __>
	// Inherited: H6_factory<D, __>
	// Inherited: HEADER_factory<D, __>
	// Inherited: HGROUP_factory<D, __>
	HR_factory<D, __>
	// Inherited: I_factory<D, __>
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __>
	// Inherited: INPUT_factory<D, __>
	// Inherited: INS_factory<D, __>
	// Inherited: KBD_factory<D, __>
	// Inherited: LABEL_factory<D, __>
	// Inherited: LINK_factory<D, __> // if it is allowed in body
	// Inherited: MAIN_factory<D, __> // if it is a hierarchically correct main element
	// Inherited: MAP_factory<D, __>
	// Inherited: MARK_factory<D, __>
	// Inherited: // TODO: MathML math
	// Inherited: MENU_factory<D, __>
	// Inherited: META_factory<D, __> // if the itemprop attribute is present
	// Inherited: METER_factory<D, __>
	// Inherited: NAV_factory<D, __>
	// Inherited: NOSCRIPT_factory<D, __>
	// Inherited: OBJECT_factory<D, __>
	// Inherited: OL_factory<D, __>
	// Inherited: OUTPUT_factory<D, __>
	// Inherited: P_factory<D, __>
	// Inherited: PICTURE_factory<D, __>
	// Inherited: PRE_factory<D, __>
	// Inherited: PROGRESS_factory<D, __>
	// Inherited: Q_factory<D, __>
	// Inherited: RUBY_factory<D, __>
	// Inherited: S_factory<D, __>
	// Inherited: SAMP_factory<D, __>
	// Inherited: SCRIPT_factory<D, __>
	// Inherited: SECTION_factory<D, __>
	// Inherited: SELECT_factory<D, __>
	// Inherited: SLOT_factory<D, __>
	// Inherited: SMALL_factory<D, __>
	// Inherited: SPAN_factory<D, __>
	// Inherited: STRONG_factory<D, __>
	// Inherited: SUB_factory<D, __>
	// Inherited: SUP_factory<D, __>
	// Inherited: // TODO: SVG svg
	// Inherited: TABLE_factory<D, __>
	// Inherited: TEMPLATE_factory<D, __>
	// Inherited: TEXTAREA_factory<D, __>
	// Inherited: TIME_factory<D, __>
	// Inherited: U_factory<D, __>
	// Inherited: UL_factory<D, __>
	// Inherited: VAR_factory<D, __>
	// Inherited: VIDEO_factory<D, __>
	// Inherited: WBR_factory<D, __>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
{
}
