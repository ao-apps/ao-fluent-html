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

import com.aoapps.html.any.AnyDATALIST_content;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/datalist">&lt;datalist&gt;: The HTML Data List element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_datalist.asp">HTML datalist Tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @since HTML 5
 *
 * @author  AO Industries, Inc.
 */
public interface DATALIST_content<
    __ extends DATALIST_content<__>
    > extends AnyDATALIST_content<Document, __>,
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
    PhrasingContent<__> {
  // Inherited: ScriptSupportingContent<__>
  // Inherited: TextContent<__>

  //
  // Factories:
  //
  // Inherited: A
  // Inherited: ABBR
  // Inherited: AREA - if a descendent of map
  // Inherited: AUDIO
  // Inherited: B
  // Inherited: BDI
  // Inherited: BDO
  // Inherited: BR
  // Inherited: BUTTON
  // Inherited: CANVAS
  // Inherited: CITE
  // Inherited: CODE
  // Inherited: DATA
  // Inherited: DATALIST
  // Inherited: DEL
  // Inherited: DFN
  // Inherited: EM
  // Inherited: EMBED
  // Inherited: I
  // Inherited: IFRAME
  // Inherited: IMG
  // Inherited: INPUT
  // Inherited: INS
  // Inherited: KBD
  // Inherited: LABEL
  // Inherited: LINK - if it is allowed in body
  // Inherited: MAP
  // Inherited: MARK
  // Inherited: MathML math
  // Inherited: META - if the itemprop attribute is present
  // Inherited: METER
  // Inherited: NOSCRIPT
  // Inherited: OBJECT
  // Inherited: OPTION
  // Inherited: OUTPUT
  // Inherited: PICTURE
  // Inherited: PROGRESS
  // Inherited: Q
  // Inherited: RUBY
  // Inherited: S
  // Inherited: SAMP
  // Inherited: SCRIPT
  // Inherited: SELECT
  // Inherited: SLOT
  // Inherited: SMALL
  // Inherited: SPAN
  // Inherited: STRONG
  // Inherited: SUB
  // Inherited: SUP
  // Inherited: SVG svg
  // Inherited: TEMPLATE
  // Inherited: TEXTAREA
  // Inherited: TIME
  // Inherited: U
  // Inherited: VAR
  // Inherited: VIDEO
  // Inherited: WBR
  // Inherited: autonomous custom elements
}
