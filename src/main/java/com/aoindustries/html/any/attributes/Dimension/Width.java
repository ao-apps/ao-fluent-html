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
package com.aoindustries.html.any.attributes.Dimension;

import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.html.any.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Width<E extends Element<?, ?, E> & Width<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 */
	@Attributes.Funnel
	default E width(int pixels) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Dimension.attribute(element, "width", pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 */
	@Attributes.Funnel
	default E width(Integer pixels) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Dimension.attribute(element, "width", pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #width(java.lang.Integer)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E width(IOSupplierE<? extends Integer, Ex> pixels) throws IOException, Ex {
		return width((pixels == null) ? null : pixels.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
	 */
	@Deprecated
	@Attributes.Funnel
	default E width(String pixelsOrPercent) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Dimension.attribute(element, "width", pixelsOrPercent);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #width(java.lang.String)
	 *
	 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
	 */
	@Deprecated
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E width(Suppliers.String<Ex> pixelsOrPercent) throws IOException, Ex {
		return width((pixelsOrPercent == null) ? null : pixelsOrPercent.get());
	}
}
