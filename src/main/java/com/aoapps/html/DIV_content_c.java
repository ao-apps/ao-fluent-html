/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2026  AO Industries, Inc.
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

import com.aoapps.html.any.AnyDIV_content_c;
import com.aoapps.html.any.Closeable;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-div-element">4.4.15 The div element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/div">&lt;div&gt;: The Content Division element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_div.asp">HTML div tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface DIV_content_c<
    PC extends Content<PC>,
    _c extends DIV_content_c<PC, _c>
    >
    extends AnyDIV_content_c<Document, PC, _c>,
    DIV_content<_c>,
    //
    // Unions:
    //
    // Inherited: Union_COLGROUP_ScriptSupporting_c<PC, _c>
    Union_DIV_DL_c<PC, _c>,
    // Inherited: Union_DL_Palpable_c<PC, _c>
    // Inherited: Union_Embedded_Interactive_c<PC, _c>
    // Inherited: Union_Embedded_Palpable_Phrasing_c<PC, _c>
    // Inherited: Union_Interactive_Phrasing_c<PC, _c>
    // Inherited: Union_Metadata_Phrasing_c<PC, _c>
    // Inherited: Union_Palpable_Phrasing_c<PC, _c>

    //
    // Content models:
    //
    // Inherited: Content_c<PC, _c>
    // Inherited: EmbeddedContent_c<PC, _c>
    FlowContent_c<PC, _c> {
  // Inherited: HeadingContent_c<PC, _c>
  // Inherited: InteractiveContent_c<PC, _c>
  // Inherited: PalpableContent_c<PC, _c>
  // Inherited: PhrasingContent_c<PC, _c>
  // Inherited: ScriptSupportingContent_c<PC, _c>
  // Inherited: SectioningContent_c<PC, _c>
  // Inherited: TextContent_c<PC, _c>

}
