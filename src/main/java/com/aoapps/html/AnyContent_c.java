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

import com.aoapps.html.any.Closeable;

/**
 * This interface extends all content interfaces and supports all element types.
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnyContent_c<
    PC extends Content<PC>,
    _c extends AnyContent_c<PC, _c>
    >
    extends com.aoapps.html.any.AnyContent_c<Document, PC, _c>,
    AnyContent<_c>,
    //
    // Unions:
    //
    // Inherited: Union_COLGROUP_ScriptSupporting_c<PC, _c>
    // Inherited: Union_DATALIST_OPTGROUP_c<PC, _c>
    // Inherited: Union_DIV_DL_c<PC, _c>
    // Inherited: Union_DL_Palpable_c<PC, _c>
    // Inherited: Union_Embedded_Interactive_c<PC, _c>
    // Inherited: Union_Embedded_Palpable_Phrasing_c<PC, _c>
    // Inherited: Union_Interactive_Phrasing_c<PC, _c>
    // Inherited: Union_Metadata_Phrasing_c<PC, _c>
    // Inherited: Union_Palpable_Phrasing_c<PC, _c>
    // Inherited: Union_TBODY_THEAD_TFOOT_c<PC, _c>

    //
    // Content models:
    //
    // Inherited: Content_c<PC, _c>
    // Inherited: EmbeddedContent_c<PC, _c>
    // Inherited: FlowContent_c<PC, _c>
    // Inherited: HeadingContent_c<PC, _c>
    // Inherited: InteractiveContent_c<PC, _c>
    ListContent_c<PC, _c>,
    MetadataContent_c<PC, _c>,
    // Inherited: PalpableContent_c<PC, _c>
    // Inherited: PhrasingContent_c<PC, _c>
    // Inherited: ScriptSupportingContent_c<PC, _c>
    // Inherited: SectioningContent_c<PC, _c>
    // Inherited: TextContent_c<PC, _c>

    //
    // Per-element content models:
    //
    COLGROUP_content_c<PC, _c>,
    DATALIST_content_c<PC, _c>,
    DIV_content_c<PC, _c>,
    DL_content_c<PC, _c>,
    HTML_content_c<PC, _c>,
    OBJECT_content_c<PC, _c>,
    // Inherited: OPTGROUP_content_c<PC, _c>
    SELECT_content_c<PC, _c>,
    TABLE_content_c<PC, _c>,
    TR_content_c<PC, _c> {

  //
  // Others:
  //
  // Inherited: Whitespace

}
