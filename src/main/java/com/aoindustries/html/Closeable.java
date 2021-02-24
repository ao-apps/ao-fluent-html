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

import java.io.IOException;

/**
 * When the content of an element is closed, the element's ending tag is written then the enclosing content model is
 * returned.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public interface Closeable<PC extends Content> extends java.io.Closeable {

	/**
	 * Closes the content and ends the parent tag.
	 * This is for use in try-with-resources, and simply calls {@link #__()}.
	 *
	 * @see  #__()
	 */
	@Override
	default void close() throws IOException {
		__();
	}

	/**
	 * Closes the content and ends the parent tag.
	 *
	 * @return  The parent content model this element is within
	 *
	 * @see  #close()
	 */
	PC __() throws IOException;
}
