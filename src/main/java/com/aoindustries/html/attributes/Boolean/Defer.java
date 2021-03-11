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
package com.aoindustries.html.attributes.Boolean;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Defer<E extends Element<?, ?, E> & Defer<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
	 */
	@Attributes.Funnel
	default E defer(boolean defer) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Boolean.attribute(element, "defer", defer);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
	 *
	 * @see #defer(boolean)
	 */
	default E defer(Boolean defer) throws IOException {
		return defer(defer != null && defer);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #defer(java.lang.Boolean)
	 */
	default <Ex extends Throwable> E defer(IOSupplierE<? extends Boolean, Ex> defer) throws IOException, Ex {
		return defer((defer == null) ? null : defer.get());
	}
}
