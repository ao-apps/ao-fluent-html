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
package com.aoindustries.html.attributes.Url;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Src<E extends Element<E, ?> & Src<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
	 */
	@Attributes.Funnel
	default E src(String src) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Url.attribute(element, "src", src);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
	 *
	 * @see #src(java.lang.String)
	 */
	default <Ex extends Throwable> E src(IOSupplierE<? extends String, Ex> src) throws IOException, Ex {
		return src((src == null) ? null : src.get());
	}
}