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
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
 *
 * @param  <E>   This element type
 * @param  <V>   This enum type to use for this attribute
 *
 * @author  AO Industries, Inc.
 */
public interface Crossorigin<
	E extends Element<?, ?, E> & Crossorigin<E, V>,
	V extends Enum<V> & Function<AnyDocument<?>, String>
> {

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 */
	@Attributes.Funnel
	default E crossorigin(String crossorigin) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "crossorigin", MarkupType.NONE, crossorigin, true, true);
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #crossorigin(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E crossorigin(Suppliers.String<Ex> crossorigin) throws IOException, Ex {
		return crossorigin((crossorigin == null) ? null : crossorigin.get());
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 *
	 * @see #crossorigin(java.lang.String)
	 */
	default E crossorigin(V crossorigin) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return crossorigin((crossorigin == null) ? null : crossorigin.apply(element.getDocument()));
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #crossorigin(java.lang.Enum)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E crossorigin(IOSupplierE<? extends V, Ex> crossorigin) throws IOException, Ex {
		return crossorigin((crossorigin== null) ? (V)null : crossorigin.get());
	}
}
