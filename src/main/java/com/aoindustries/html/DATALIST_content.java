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
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface DATALIST_content<
	D  extends AnyDocument<D>,
	__ extends DATALIST_content<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<D, __>
	Union_DATALIST_OPTGROUP<D, __>,
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
	PhrasingContent<D, __>
	// Inherited: ScriptSupportingContent<D, __>
	// Inherited: TextContent<D, __>

	//
	// Factories:
	//
	// Inherited: A_factory<D, __>
	// Inherited: ABBR_factory<D, __>
	// Inherited: AREA_factory<D, __> // if a descendent of map
	// Inherited: AUDIO_factory<D, __>
	// Inherited: B_factory<D, __>
	// Inherited: BDI_factory<D, __>
	// Inherited: BDO_factory<D, __>
	// Inherited: BR_factory<D, __>
	// Inherited: BUTTON_factory<D, __>
	// Inherited: CANVAS_factory<D, __>
	// Inherited: CITE_factory<D, __>
	// Inherited: CODE_factory<D, __>
	// Inherited: DATA_factory<D, __>
	// Inherited: DATALIST_factory<D, __>
	// Inherited: DEL_factory<D, __>
	// Inherited: DFN_factory<D, __>
	// Inherited: EM_factory<D, __>
	// Inherited: EMBED_factory<D, __>
	// Inherited: I_factory<D, __>
	// Inherited: IFRAME_factory<D, __>
	// Inherited: IMG_factory<D, __>
	// Inherited: INPUT_factory<D, __>
	// Inherited: INS_factory<D, __>
	// Inherited: KBD_factory<D, __>
	// Inherited: LABEL_factory<D, __>
	// Inherited: LINK_factory<D, __> // if it is allowed in body
	// Inherited: MAP_factory<D, __>
	// Inherited: MARK_factory<D, __>
	// Inherited: // TODO: MathML math
	// Inherited: META_factory<D, __> // if the itemprop attribute is present
	// Inherited: METER_factory<D, __>
	// Inherited: NOSCRIPT_factory<D, __>
	// Inherited: OBJECT_factory<D, __>
	// Inherited: OPTION_factory<D, __>
	// Inherited: OUTPUT_factory<D, __>
	// Inherited: PICTURE_factory<D, __>
	// Inherited: PROGRESS_factory<D, __>
	// Inherited: Q_factory<D, __>
	// Inherited: RUBY_factory<D, __>
	// Inherited: S_factory<D, __>
	// Inherited: SAMP_factory<D, __>
	// Inherited: SCRIPT_factory<D, __>
	// Inherited: SELECT_factory<D, __>
	// Inherited: SLOT_factory<D, __>
	// Inherited: SMALL_factory<D, __>
	// Inherited: SPAN_factory<D, __>
	// Inherited: STRONG_factory<D, __>
	// Inherited: SUB_factory<D, __>
	// Inherited: SUP_factory<D, __>
	// Inherited: // TODO: SVG svg
	// Inherited: TEMPLATE_factory<D, __>
	// Inherited: TEXTAREA_factory<D, __>
	// Inherited: TIME_factory<D, __>
	// Inherited: U_factory<D, __>
	// Inherited: VAR_factory<D, __>
	// Inherited: VIDEO_factory<D, __>
	// Inherited: WBR_factory<D, __>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
{
}
