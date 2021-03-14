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
package com.aoindustries.html.any.attributes.Enum;

import com.aoindustries.html.any.AnyDocument;
import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.html.any.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.function.Function;

/**
 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
 *
 * @param  <E>   This element type
 * @param  <V>   This enum type to use for this attribute
 *
 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
 *
 * @author  AO Industries, Inc.
 */
@Deprecated
public interface Valign<
	E extends Element<?, ?, E> & Valign<E, V>,
	V extends Enum<V> & Function<AnyDocument<?>, String>
> {

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
	 *
	 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Attributes.Funnel
	default E valign(String valign) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "valign", MarkupType.NONE, valign, true, true);
	}

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #valign(java.lang.String)
	 *
	 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E valign(Suppliers.String<Ex> valign) throws IOException, Ex {
		return valign((valign == null) ? null : valign.get());
	}

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
	 *
	 * @see #valign(java.lang.String)
	 *
	 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	default E valign(V valign) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return valign((valign == null) ? null : valign.apply(element.getDocument()));
	}

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #valign(java.lang.Enum)
	 *
	 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E valign(IOSupplierE<? extends V, Ex> valign) throws IOException, Ex {
		return valign((valign== null) ? (V)null : valign.get());
	}
}
