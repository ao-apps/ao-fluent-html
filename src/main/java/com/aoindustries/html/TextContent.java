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
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
// TODO: Separate Whitespace? https://html.spec.whatwg.org/#inter-element-whitespace
//       Could skip encoding (but verified when assertions enabled)
public interface TextContent<C extends UnionContent.Palpable_Phrasing<C>> extends Content {

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default C text(char ch) throws IOException {
		getDocument().text(ch);
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}

	// TODO: codePoint?

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default C text(char[] cbuf) throws IOException {
		getDocument().text(cbuf);
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default C text(char[] cbuf, int offset, int len) throws IOException {
		getDocument().text(cbuf, offset, len);
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}

	// TODO: text(CharSequence)?
	// TODO: text(CharSequence, int, int)?

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default C text(Object text) throws IOException {
		getDocument().text(text);
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default <Ex extends Throwable> C text(IOSupplierE<?, Ex> text) throws IOException, Ex {
		getDocument().text(text);
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}

	/**
	 * Writes the given text with proper encoding.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default <Ex extends Throwable> C text(MediaWritable<Ex> text) throws IOException, Ex {
		getDocument().text(text);
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}

	/**
	 * Writes the given text with proper encoding.
	 * This is well suited for use in a try-with-resources block.
	 * <p>
	 * Does not perform any translation markups.
	 * </p>
	 *
	 * @return  A new writer that may be used for arbitrary text
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	// TODO: __() method to end text?  Call it "ContentWriter"?
	default MediaWriter text() throws IOException {
		return getDocument().text();
	}

	/**
	 * This is {@code '\n'} on all platforms.  If a different newline is required,
	 * such as {@code "\r\n"} for email, filter the output.
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// TODO: Separate Whitespace?
	// Note: Must be implemented in Document to avoid infinite recursion
	default C nl() throws IOException {
		getDocument().nl();
		@SuppressWarnings("unchecked") C c = (C)this;
		return c;
	}
}
