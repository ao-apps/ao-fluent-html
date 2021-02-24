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
 * See <a href="https://html.spec.whatwg.org/#content-models:space-characters">3.2.5 Content models / ASCII whitespace</a>.
 *
 * @param  <PC>  The parent object this whitespace is within
 *
 * @author  AO Industries, Inc.
 */
public interface Whitespace<PC> {

	/**
	 * Writes a newline, TODO: optionally followed by tab indentation.
	 * <p>
	 * This is {@code '\n'} on all platforms.  If a different newline is required,
	 * such as {@code "\r\n"} for email, filter the output.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	PC nl() throws IOException;
}
