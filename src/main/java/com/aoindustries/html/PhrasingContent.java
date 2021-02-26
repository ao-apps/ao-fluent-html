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
 * <li>See <a href="https://html.spec.whatwg.org/#phrasing-content">3.2.5.2.5 Phrasing content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#phrasing_content">Phrasing content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PhrasingContent<__ extends PhrasingContent<__>> extends
	//
	// Unions:
	//
	UnionContent.Metadata_Phrasing<__>,
	UnionContent.Palpable_Phrasing<__>,
	//
	// Content models:
	//
	// Inherited from EmbeddedContent and Palpable_Phrasing: Content<__>
	EmbeddedContent<__>,
	// Inherited from Metadata_Phrasing: ScriptSupportingContent<__>
	//
	// Content types:
	//
	// Inherited from Palpable_Phrasing: A_factory<__>
	// Inherited from Palpable_Phrasing: ABBR_factory<__>
	AREA_factory<__>, // if a descendent of map
	// Inherited from EmbeddedContent and Palpable_Phrasing: AUDIO_factory<__>
	// Inherited from Palpable_Phrasing: B_factory<__>
	// Inherited from Palpable_Phrasing: BDI_factory<__>
	// Inherited from Palpable_Phrasing: BDO_factory<__>
	BR_factory<__>,
	// Inherited from Palpable_Phrasing: BUTTON_factory<__>
	// Inherited from EmbeddedContent and Palpable_Phrasing: CANVAS_factory<__>
	// Inherited from Palpable_Phrasing: CITE_factory<__>
	// Inherited from Palpable_Phrasing: CODE_factory<__>
	// Inherited from Palpable_Phrasing: DATA_factory<__>
	DATALIST_factory<__>,
	DEL_factory<__>,
	// Inherited from Palpable_Phrasing: DFN_factory<__>
	// Inherited from Palpable_Phrasing: EM_factory<__>
	// Inherited from EmbeddedContent and Palpable_Phrasing: EMBED_factory<__>
	// Inherited from Palpable_Phrasing: I_factory<__>
	// Inherited from EmbeddedContent and Palpable_Phrasing: IFRAME_factory<__>
	// Inherited from EmbeddedContent and Palpable_Phrasing: IMG_factory<__>
	// Inherited from Palpable_Phrasing: INPUT_factory<__>
	// Inherited from Palpable_Phrasing: INS_factory<__>
	// Inherited from Palpable_Phrasing: KBD_factory<__>
	// Inherited from Palpable_Phrasing: LABEL_factory<__>
	// Inherited from Metadata_Phrasing: LINK_factory<__> // if it is allowed in body
	// Inherited from Palpable_Phrasing: MAP_factory<__>
	// Inherited from Palpable_Phrasing: MARK_factory<__>
	// Inherited from Palpable_Phrasing: // TODO: MathML math
	// Inherited from Metadata_Phrasing: META_factory<__> // if the itemprop attribute is present
	// Inherited from Palpable_Phrasing: METER_factory<__>
	// Inherited from Metadata_Phrasing: NOSCRIPT_factory<__>
	// Inherited from EmbeddedContent and Palpable_Phrasing: OBJECT_factory<__>
	// Inherited from Palpable_Phrasing: OUTPUT_factory<__>
	// Inherited from EmbeddedContent: PICTURE_factory<__>
	// Inherited from Palpable_Phrasing: PROGRESS_factory<__>
	// Inherited from Palpable_Phrasing: Q_factory<__>
	// Inherited from Palpable_Phrasing: RUBY_factory<__>
	// Inherited from Palpable_Phrasing: S_factory<__>
	// Inherited from Palpable_Phrasing: SAMP_factory<__>
	// Inherited from Metadata_Phrasing: SCRIPT_factory<__>
	// Inherited from Palpable_Phrasing: SELECT_factory<__>
	SLOT_factory<__>,
	// Inherited from Palpable_Phrasing: SMALL_factory<__>
	// Inherited from Palpable_Phrasing: SPAN_factory<__>
	// Inherited from Palpable_Phrasing: STRONG_factory<__>
	// Inherited from Palpable_Phrasing: SUB_factory<__>
	// Inherited from Palpable_Phrasing: SUP_factory<__>
	// Inherited from Palpable_Phrasing: // TODO: SVG svg
	// Inherited from Metadata_Phrasing: TEMPLATE_factory<__>
	// Inherited from Palpable_Phrasing: TEXTAREA_factory<__>
	// Inherited from Palpable_Phrasing: TIME_factory<__>
	// Inherited from Palpable_Phrasing: U_factory<__>
	// Inherited from Palpable_Phrasing: VAR_factory<__>
	// Inherited from EmbeddedContent and Palpable_Phrasing: VIDEO_factory<__>
	WBR_factory<__>
	// Inherited from Palpable_Phrasing: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
	// Inherited from Palpable_Phrasing: TextContent<__>
{
}
