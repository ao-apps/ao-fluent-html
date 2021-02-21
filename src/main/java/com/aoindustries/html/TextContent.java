/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.html;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#text-content">3.2.5.2.5 Phrasing content / Text</a>.
 *
 * @param  <PC>  The parent content model this text is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Separate Whitespace? https://html.spec.whatwg.org/#inter-element-whitespace
//       Could skip encoding (but verified when assertions enabled)
public interface TextContent<PC extends Content> extends Content {

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 */
	PC text(char ch) throws IOException;

	// TODO: codePoint?

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 */
	PC text(char[] cbuf) throws IOException;

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 */
	PC text(char[] cbuf, int offset, int len) throws IOException;

	// TODO: text(CharSequence)?
	// TODO: text(CharSequence, int, int)?

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 * </p>
	 */
	PC text(Object text) throws IOException;

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 * </p>
	 */
	<Ex extends Throwable> PC text(IOSupplierE<?, Ex> text) throws IOException, Ex;

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 */
	<Ex extends Throwable> PC text(MediaWritable<Ex> text) throws IOException, Ex;

	/**
	 * Writes the given text with proper encoding.
	 * This is well suited for use in a try-with-resources block.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 *
	 * @return  A new writer that may be used for arbitrary text
	 */
	MediaWriter text() throws IOException;

	/**
	 * This is {@code '\n'} on all platforms.  If a different newline is required,
	 * such as {@code "\r\n"} for email, filter the output.
	 */
	// TODO: Separate Whitespace?
	PC nl() throws IOException;
}
