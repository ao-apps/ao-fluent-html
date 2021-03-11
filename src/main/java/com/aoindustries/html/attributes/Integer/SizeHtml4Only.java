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
package com.aoindustries.html.attributes.Integer;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @deprecated  Not supported in HTML5.
 *
 * @author  AO Industries, Inc.
 */
@Deprecated
public interface SizeHtml4Only<E extends Element<?, ?, E> & SizeHtml4Only<E>> extends Size<E> {

	/**
	 * @deprecated  Not supported in HTML5.
	 */
	@Deprecated
	@Override
	@Attributes.Funnel
	default E size(int size) throws IOException {
		return Size.super.size(size);
	}

	/**
	 * @deprecated  Not supported in HTML5.
	 */
	@Deprecated
	@Override
	@Attributes.Funnel
	default E size(Integer size) throws IOException {
		return Size.super.size(size);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #size(java.lang.Integer)
	 *
	 * @deprecated  Not supported in HTML5.
	 */
	@Deprecated
	@Override
	default <Ex extends Throwable> E size(IOSupplierE<? extends Integer, Ex> size) throws IOException, Ex {
		return Size.super.size(size);
	}
}
