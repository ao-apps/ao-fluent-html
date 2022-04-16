/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.html;

import com.aoapps.html.any.AnyDIV_content;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-div-element">4.4.15 The div element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/div">&lt;div&gt;: The Content Division element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_div.asp">HTML div tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface DIV_content<
	__ extends DIV_content<__>
> extends AnyDIV_content<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	Union_DIV_DL<__>,
	// Inherited: Union_DL_Palpable<__>
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
	FlowContent<__>
	// Inherited: HeadingContent<__>
	// Inherited: InteractiveContent<__>
	// Inherited: PalpableContent<__>
	// Inherited: PhrasingContent<__>
	// Inherited: ScriptSupportingContent<__>
	// Inherited: SectioningContent<__>
	// Inherited: TextContent<__>
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// Inherited: ADDRESS
	// Inherited: AREA - if a descendent of map
	// Inherited: ARTICLE
	// Inherited: ASIDE
	// Inherited: AUDIO
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// Inherited: BLOCKQUOTE
	// Inherited: BR
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// Inherited: DATALIST
	// Inherited: DD
	// Inherited: DEL
	// Inherited: DETAILS
	// Inherited: DFN
	// Inherited: DIALOG
	// Inherited: DIV
	// Inherited: DL
	// Inherited: DT
	// Inherited: EM
	// Inherited: EMBED
	// Inherited: FIELDSET
	// Inherited: FIGURE
	// Inherited: FOOTER
	// Inherited: FORM
	// Inherited: H1
	// Inherited: H2
	// Inherited: H3
	// Inherited: H4
	// Inherited: H5
	// Inherited: H6
	// Inherited: HEADER
	// Inherited: HGROUP
	// Inherited: HR
	// Inherited: I
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: INPUT
	// Inherited: INS
	// Inherited: KBD
	// Inherited: LABEL
	// Inherited: LINK - if it is allowed in body
	// Inherited: MAIN - if it is a hierarchically correct main element
	// Inherited: MAP
	// Inherited: MARK
	// Inherited: MathML math
	// Inherited: MENU
	// Inherited: META - if the itemprop attribute is present
	// Inherited: METER
	// Inherited: NAV
	// Inherited: NOSCRIPT
	// Inherited: OBJECT
	// Inherited: OL
	// Inherited: OUTPUT
	// Inherited: P
	// Inherited: PICTURE
	// Inherited: PRE
	// Inherited: PROGRESS
	// Inherited: Q
	// Inherited: RUBY
	// Inherited: S
	// Inherited: SAMP
	// Inherited: SCRIPT
	// Inherited: SECTION
	// Inherited: SELECT
	// Inherited: SLOT
	// Inherited: SMALL
	// Inherited: SPAN
	// Inherited: STRONG
	// Inherited: SUB
	// Inherited: SUP
	// Inherited: SVG svg
	// Inherited: TABLE
	// Inherited: TEMPLATE
	// Inherited: TEXTAREA
	// Inherited: TIME
	// Inherited: U
	// Inherited: UL
	// Inherited: VAR
	// Inherited: VIDEO
	// Inherited: WBR
	// Inherited: autonomous custom elements
}
