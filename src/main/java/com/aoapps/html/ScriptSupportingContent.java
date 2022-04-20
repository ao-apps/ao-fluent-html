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

import com.aoapps.html.any.AnySCRIPT;
import com.aoapps.html.any.AnyScriptSupportingContent;
import com.aoapps.html.any.Suppliers;
import com.aoapps.lang.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/dom.html#script-supporting-elements">3.2.5.2.9 Script-supporting elements</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface ScriptSupportingContent<
  __ extends ScriptSupportingContent<__>
> extends AnyScriptSupportingContent<Document, __>,
  //
  // Unions:
  //
  Union_COLGROUP_ScriptSupporting<__>

  //
  // Content models:
  //
  // Inherited: Content<__>
{
  //
  // Factories:
  //
  // <editor-fold defaultstate="collapsed" desc="SCRIPT">
  @Override
  @SuppressWarnings("deprecation")
  default SCRIPT<__> script() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__)this;
    Document document = getDocument();
    return new SCRIPT<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  @Override
  @SuppressWarnings("deprecation")
  default SCRIPT<__> script(String type) throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__)this;
    Document document = getDocument();
    return new SCRIPT<>(document, pc, type).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * @param  <Ex>  An arbitrary exception type that may be thrown
   */
  @Override
  default <Ex extends Throwable> SCRIPT<__> script(Suppliers.String<Ex> type) throws IOException, Ex {
    return script((type == null) ? null : type.get());
  }

  @Override
  @SuppressWarnings("deprecation")
  default SCRIPT<__> script(AnySCRIPT.Type type) throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__)this;
    Document document = getDocument();
    return new SCRIPT<>(document, pc, type).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * @param  <Ex>  An arbitrary exception type that may be thrown
   */
  @Override
  default <Ex extends Throwable> SCRIPT<__> script(IOSupplierE<? extends AnySCRIPT.Type, Ex> type) throws IOException, Ex {
    return script((type == null) ? null : type.get());
  }
  // </editor-fold>
  // Inherited: TEMPLATE
}
