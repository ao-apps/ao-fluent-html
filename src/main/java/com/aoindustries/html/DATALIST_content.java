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
 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface DATALIST_content<__ extends DATALIST_content<__>> extends
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	Union_DATALIST_OPTGROUP<__>,
	// Inherited: Union_Embedded_Interactive<__>
	// Inherited: Union_Embedded_Palpable_Phrasing<__>
	// Inherited: Union_Interactive_Phrasing<__>
	// Inherited: Union_Metadata_Phrasing<__>
	// Inherited: Union_Palpable_Phrasing<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	// Inherited: EmbeddedContent<__>
	PhrasingContent<__>
	// Inherited: ScriptSupportingContent<__>
	// Inherited: TextContent<__>

	//
	// Factories:
	//
	// Inherited: A_factory<__>
	// Inherited: ABBR_factory<__>
	// Inherited: AREA_factory<__> // if a descendent of map
	// Inherited: AUDIO_factory<__>
	// Inherited: B_factory<__>
	// Inherited: BDI_factory<__>
	// Inherited: BDO_factory<__>
	// Inherited: BR_factory<__>
	// Inherited: BUTTON_factory<__>
	// Inherited: CANVAS_factory<__>
	// Inherited: CITE_factory<__>
	// Inherited: CODE_factory<__>
	// Inherited: DATA_factory<__>
	// Inherited: DATALIST_factory<__>
	// Inherited: DEL_factory<__>
	// Inherited: DFN_factory<__>
	// Inherited: EM_factory<__>
	// Inherited: EMBED_factory<__>
	// Inherited: I_factory<__>
	// Inherited: IFRAME_factory<__>
	// Inherited: IMG_factory<__>
	// Inherited: INPUT_factory<__>
	// Inherited: INS_factory<__>
	// Inherited: KBD_factory<__>
	// Inherited: LABEL_factory<__>
	// Inherited: LINK_factory<__> // if it is allowed in body
	// Inherited: MAP_factory<__>
	// Inherited: MARK_factory<__>
	// Inherited: // TODO: MathML math
	// Inherited: META_factory<__> // if the itemprop attribute is present
	// Inherited: METER_factory<__>
	// Inherited: NOSCRIPT_factory<__>
	// Inherited: OBJECT_factory<__>
	// Inherited: OPTION_factory<__>
	// Inherited: OUTPUT_factory<__>
	// Inherited: PICTURE_factory<__>
	// Inherited: PROGRESS_factory<__>
	// Inherited: Q_factory<__>
	// Inherited: RUBY_factory<__>
	// Inherited: S_factory<__>
	// Inherited: SAMP_factory<__>
	// Inherited: SCRIPT_factory<__>
	// Inherited: SELECT_factory<__>
	// Inherited: SLOT_factory<__>
	// Inherited: SMALL_factory<__>
	// Inherited: SPAN_factory<__>
	// Inherited: STRONG_factory<__>
	// Inherited: SUB_factory<__>
	// Inherited: SUP_factory<__>
	// Inherited: // TODO: SVG svg
	// Inherited: TEMPLATE_factory<__>
	// Inherited: TEXTAREA_factory<__>
	// Inherited: TIME_factory<__>
	// Inherited: U_factory<__>
	// Inherited: VAR_factory<__>
	// Inherited: VIDEO_factory<__>
	// Inherited: WBR_factory<__>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
{
}
