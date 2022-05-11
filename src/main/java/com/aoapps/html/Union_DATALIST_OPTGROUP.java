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

import com.aoapps.html.any.AnyUnion_DATALIST_OPTGROUP;
import com.aoapps.lang.io.function.IOConsumerE;
import java.io.IOException;

/**
 * Elements that are common to both {@link DATALIST_content} and {@link OPTGROUP_content}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_DATALIST_OPTGROUP<
    __ extends Union_DATALIST_OPTGROUP<__>
    > extends AnyUnion_DATALIST_OPTGROUP<Document, __>,
    //
    // Content models:
    //
    Content<__> {
  //
  // Factories:
  //
  // <editor-fold defaultstate="collapsed" desc="OPTION">
  @Override
  @SuppressWarnings("deprecation")
  default OPTION<__> option() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    return new OPTION<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * Creates an option element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-option-element">4.10.10 The option element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/option">&lt;option&gt;: The HTML Option element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ option__(IOConsumerE<? super OPTION__<__>, Ex> option) throws IOException, Ex {
    return option().__(option);
  }

  @Override
  default OPTION_c<__> option_c() throws IOException {
    return option()._c();
  }
  // </editor-fold>
}
