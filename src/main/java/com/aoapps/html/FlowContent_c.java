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

import com.aoapps.html.any.AnyFlowContent_c;
import com.aoapps.html.any.Closeable;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#flow-content">3.2.5.2.2 Flow content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#flow_content">Flow content</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface FlowContent_c<
    PC extends Content<PC>,
    _c extends FlowContent_c<PC, _c>
    >
    extends AnyFlowContent_c<Document, PC, _c>,
    FlowContent<_c>,
    //
    // Unions:
    //
    // Inherited: Union_COLGROUP_ScriptSupporting_c<PC, _c>
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
    // Inherited: HeadingContent_c<PC, _c>
    // Inherited: InteractiveContent_c<PC, _c>
    PalpableContent_c<PC, _c>,
    PhrasingContent_c<PC, _c> {
  // Inherited: ScriptSupportingContent_c<PC, _c>
  // Inherited: SectioningContent_c<PC, _c>
  // Inherited: TextContent_c<PC, _c>

}
