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
 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Name<
	E extends Element<E, ?> & Name<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
	 */
	@Attributes.Funnel
	default E name(String name) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		// TODO: Is nullIfEmpty correct?  Is an empty name ever valid?
		return Attributes.String.attribute(element, "name", MarkupType.NONE, name, true, true);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
	 *
	 * @see #name(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E name(Suppliers.String<Ex> name) throws IOException, Ex {
		return name((name == null) ? null : name.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
	 *
	 * @see #name(java.lang.String)
	 */
	default E name(V name) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return name((name == null) ? null : name.apply(element.getDocument()));
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
	 *
	 * @see #name(java.lang.Enum)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E name(IOSupplierE<? extends V, Ex> name) throws IOException, Ex {
		return name((name== null) ? (V)null : name.get());
	}
}
