/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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
package com.aoindustries.html.any.attributes.Integer;

import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Height<E extends Element<?, ?, E> & Height<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
	 */
	@Attributes.Funnel
	default E height(int pixels) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Integer.attribute(element, "height", pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
	 */
	@Attributes.Funnel
	default E height(Integer pixels) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Integer.attribute(element, "height", pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #height(java.lang.Integer)
	 */
	default <Ex extends Throwable> E height(IOSupplierE<? extends Integer, Ex> pixels) throws IOException, Ex {
		return height((pixels == null) ? null : pixels.get());
	}
}
