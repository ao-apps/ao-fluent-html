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

import com.aoapps.html.any.AnyH5;
import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public final class H5<
    PC extends HeadingContent<PC>
    > extends AnyH5<Document, PC, H5<PC>, H5__<PC>, H5_c<PC>> {

  H5(Document document, PC pc) {
    super(document, pc);
  }

  // Expose to this package, avoiding public to keep a clean API for optimal code assist
  @Override
  protected void writeOpen(Writer unsafe) throws IOException {
    super.writeOpen(unsafe);
  }

  @Override
  protected H5__<PC> new__() {
    return new H5__<>(this);
  }

  @Override
  protected H5_c<PC> new_c() {
    return new H5_c<>(this);
  }
}
