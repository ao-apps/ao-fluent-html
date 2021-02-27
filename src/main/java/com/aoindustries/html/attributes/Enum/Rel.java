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
 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Rel<
	E extends Element<E, ?> & Rel<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
	 */
	@Attributes.Funnel
	default E rel(String rel) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "rel", MarkupType.NONE, rel, true, true);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
	 *
	 * @see #rel(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E rel(Suppliers.String<Ex> rel) throws IOException, Ex {
		return rel((rel == null) ? null : rel.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
	 *
	 * @see #rel(java.lang.String)
	 */
	default E rel(V rel) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return rel((rel == null) ? null : rel.apply(element.getDocument()));
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
	 *
	 * @see #rel(java.lang.Enum)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E rel(IOSupplierE<? extends V, Ex> rel) throws IOException, Ex {
		return rel((rel== null) ? (V)null : rel.get());
	}
}
