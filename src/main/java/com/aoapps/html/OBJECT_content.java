/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022, 2025  AO Industries, Inc.
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

import com.aoapps.html.any.AnyOBJECT_content;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-object-element">4.8.7 The object element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object">&lt;object&gt;: The External Object element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_object.asp">HTML object tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface OBJECT_content<
    __ extends OBJECT_content<__>
    > extends AnyOBJECT_content<Document, __>,
    //
    // Unions:
    //
    Union_Embedded_Interactive<__> {

  //
  // Content models:
  //
  // Inherited: Content<__>

  //
  // Factories:
  //
  // Inherited: AUDIO
  // Inherited: EMBED
  // Inherited: IFRAME
  // Inherited: IMG
  // Inherited: OBJECT
  // <editor-fold defaultstate="collapsed" desc="PARAM">
  @Override
  @SuppressWarnings("deprecation")
  default PARAM<__> param() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    PARAM<__> e = new PARAM<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }
  // </editor-fold>
  // Inherited: VIDEO
}
