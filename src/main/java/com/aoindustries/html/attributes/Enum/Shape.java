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
package com.aoindustries.html.attributes.Enum;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Document;
import com.aoindustries.html.Element;
import com.aoindustries.html.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.function.Function;

/**
 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Shape<
	E extends Element<E, ?> & Shape<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
	 */
	@Attributes.Funnel
	default E shape(String shape) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "shape", MarkupType.NONE, shape, true, true);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
	 *
	 * @see #shape(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E shape(Suppliers.String<Ex> shape) throws IOException, Ex {
		return shape((shape == null) ? null : shape.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
	 *
	 * @see #shape(java.lang.String)
	 */
	default E shape(V shape) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return shape((shape == null) ? null : shape.apply(element.getDocument()));
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
	 *
	 * @see #shape(java.lang.Enum)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E shape(IOSupplierE<? extends V, Ex> shape) throws IOException, Ex {
		return shape((shape== null) ? (V)null : shape.get());
	}
}
