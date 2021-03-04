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
 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
 *
 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
 *
 * @author  AO Industries, Inc.
 */
@Deprecated
public interface Align<
	E extends Element<E, ?> & Align<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
	 *
	 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Attributes.Funnel
	default E align(String align) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "align", MarkupType.NONE, align, true, true);
	}

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
	 *
	 * @see #align(java.lang.String)
	 *
	 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E align(Suppliers.String<Ex> align) throws IOException, Ex {
		return align((align == null) ? null : align.get());
	}

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
	 *
	 * @see #align(java.lang.String)
	 *
	 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	default E align(V align) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return align((align == null) ? null : align.apply(element.getDocument()));
	}

	/**
	 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
	 *
	 * @see #align(java.lang.Enum)
	 *
	 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E align(IOSupplierE<? extends V, Ex> align) throws IOException, Ex {
		return align((align== null) ? (V)null : align.get());
	}
}
