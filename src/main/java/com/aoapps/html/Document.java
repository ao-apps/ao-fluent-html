/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021, 2022  AO Industries, Inc.
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

import com.aoapps.encoding.ChainWriter;
import com.aoapps.encoding.Doctype;
import com.aoapps.encoding.EncodingContext;
import com.aoapps.encoding.Serialization;
import com.aoapps.html.any.AnyDocument;
import com.aoapps.lang.Coercion;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * Fluent Java DSL for high-performance HTML generation.
 * <p>
 * This class implements all content interfaces and supports all element types.
 * </p>
 * <p>
 * See also <a href="https://github.com/xmlet/HtmlFlow">HtmlFlow</a>.
 * </p>
 *
 * @author  AO Industries, Inc.
 */
public final class Document extends AnyDocument<Document> implements AnyContent<Document> {

  /**
   * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
   *              Not doing so may result in {@link IllegalStateException}.
   *              <p>
   *              Will be through {@link Coercion#optimize(java.io.Writer, com.aoapps.lang.io.Encoder)}
   *              with {@code encoder = null}.
   *              </p>
   *
   * @see  #setOut(java.io.Writer)
   */
  public Document(EncodingContext encodingContext, Writer out) {
    super(encodingContext, out);
  }

  /**
   * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
   *              Not doing so may result in {@link IllegalStateException}.
   *              <p>
   *              Will be through {@link Coercion#optimize(java.io.Writer, com.aoapps.lang.io.Encoder)}
   *              with {@code encoder = null}.
   *              </p>
   *
   * @see  #setOut(java.io.Writer)
   */
  public Document(Serialization serialization, Doctype doctype, Charset characterEncoding, Writer out) {
    this(
        new EncodingContext() {
          @Override
          public Serialization getSerialization() {
            return serialization;
          }

          @Override
          public Doctype getDoctype() {
            return doctype;
          }

          @Override
          public Charset getCharacterEncoding() {
            return characterEncoding;
          }
        },
        out
    );
  }

  /**
   * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
   *              Not doing so may result in {@link IllegalStateException}.
   *              <p>
   *              Will be through {@link Coercion#optimize(java.io.Writer, com.aoapps.lang.io.Encoder)}
   *              with {@code encoder = null}.
   *              </p>
   *
   * @see  #setOut(java.io.Writer)
   * @see  EncodingContext#DEFAULT
   */
  public Document(Writer out) {
    this(EncodingContext.DEFAULT, out);
  }

  /**
   * Unwraps the given chain writer.
   *
   * @param  out  After being unwrapped, will be through {@link Coercion#optimize(java.io.Writer, com.aoapps.lang.io.Encoder)}
   *              with {@code encoder = null}.
   */
  public Document(ChainWriter out) {
    this(out.getEncodingContext(), out.getPrintWriter());
  }
}
