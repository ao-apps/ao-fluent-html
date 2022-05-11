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

import com.aoapps.html.any.AnyInteractiveContent;
import com.aoapps.lang.io.function.IOConsumerE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#interactive-content">3.2.5.2.7 Interactive content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#interactive_content">Interactive content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface InteractiveContent<
    __ extends InteractiveContent<__>
    > extends AnyInteractiveContent<Document, __>,
    //
    // Unions:
    //
    // Inherited: Union_Embedded_Interactive<__>
    Union_Interactive_Phrasing<__> {

  //
  // Content models:
  //
  // Inherited: Content<__>

  //
  // Factories:
  //
  // Inherited: A - if the href attribute is present
  // Inherited: AUDIO - if the controls attribute is present
  // Inherited: BUTTON
  // <editor-fold defaultstate="collapsed" desc="TODO: DETAILS">
  // </editor-fold>
  // Inherited: EMBED
  // Inherited: IFRAME
  // Inherited: IMG - if the usemap attribute is present
  // Inherited: INPUT - if type attribute is not in the hidden state
  // Inherited: LABEL
  // <editor-fold defaultstate="collapsed" desc="MENU - (MDN only) if the type attribute is in the toolbar state">
  @Override
  @SuppressWarnings("deprecation")
  default MENU<__> menu() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    return new MENU<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * Creates a menu element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-menu-element">4.4.7 The menu element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/menu">&lt;menu&gt;: The Menu element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_menu.asp">HTML menu Tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ menu__(IOConsumerE<? super MENU__<__>, Ex> menu) throws IOException, Ex {
    return menu().__(menu);
  }

  @Override
  default MENU_c<__> menu_c() throws IOException {
    return menu()._c();
  }
  // </editor-fold>
  // Inherited: OBJECT - if the usemap attribute is present
  // Inherited: SELECT
  // Inherited: TEXTAREA
  // Inherited: VIDEO - if the controls attribute is present
}
