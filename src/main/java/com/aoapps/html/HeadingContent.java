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

import com.aoapps.html.any.AnyHeadingContent;
import com.aoapps.lang.io.function.IOConsumerE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#heading-content">3.2.5.2.4 Heading content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#heading_content">Heading content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface HeadingContent<
    __ extends HeadingContent<__>
    > extends AnyHeadingContent<Document, __>,
    //
    // Content models:
    //
    Content<__> {
  //
  // Factories:
  //
  // <editor-fold defaultstate="collapsed" desc="H1">
  /**
   * Opens a new h1 element.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   */
  @Override
  @SuppressWarnings("deprecation")
  default H1<__> h1() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    H1<__> e = new H1<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates an h1 element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ h1__(IOConsumerE<? super H1__<__>, Ex> h1) throws IOException, Ex {
    return h1().__(h1);
  }

  @Override
  default H1_c<__> h1_c() throws IOException {
    return h1()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="H2">
  @Override
  @SuppressWarnings("deprecation")
  default H2<__> h2() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    H2<__> e = new H2<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates an h2 element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ h2__(IOConsumerE<? super H2__<__>, Ex> h2) throws IOException, Ex {
    return h2().__(h2);
  }

  @Override
  default H2_c<__> h2_c() throws IOException {
    return h2()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="H3">
  @Override
  @SuppressWarnings("deprecation")
  default H3<__> h3() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    H3<__> e = new H3<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates an h3 element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ h3__(IOConsumerE<? super H3__<__>, Ex> h3) throws IOException, Ex {
    return h3().__(h3);
  }

  @Override
  default H3_c<__> h3_c() throws IOException {
    return h3()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="H4">
  @Override
  @SuppressWarnings("deprecation")
  default H4<__> h4() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    H4<__> e = new H4<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates an h4 element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ h4__(IOConsumerE<? super H4__<__>, Ex> h4) throws IOException, Ex {
    return h4().__(h4);
  }

  @Override
  default H4_c<__> h4_c() throws IOException {
    return h4()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="H5">
  @Override
  @SuppressWarnings("deprecation")
  default H5<__> h5() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    H5<__> e = new H5<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates an h5 element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ h5__(IOConsumerE<? super H5__<__>, Ex> h5) throws IOException, Ex {
    return h5().__(h5);
  }

  @Override
  default H5_c<__> h5_c() throws IOException {
    return h5()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="H6">
  @Override
  @SuppressWarnings("deprecation")
  default H6<__> h6() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    H6<__> e = new H6<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates an h6 element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements">&lt;h1&gt;-&lt;h6&gt;: The HTML Section Heading elements</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_hn.asp">HTML h1 to h6 tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ h6__(IOConsumerE<? super H6__<__>, Ex> h6) throws IOException, Ex {
    return h6().__(h6);
  }

  @Override
  default H6_c<__> h6_c() throws IOException {
    return h6()._c();
  }
  // </editor-fold>
  // Inherited: H#
  // <editor-fold defaultstate="collapsed" desc="TODO: HGROUP">
  // </editor-fold>
}
