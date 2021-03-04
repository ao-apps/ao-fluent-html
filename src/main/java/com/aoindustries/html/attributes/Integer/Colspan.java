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
 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Colspan<E extends Element<E, ?> & Colspan<E>> {

	/**
	 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
	 *
	 * @param  colspan  Will not write the attribute when is {@code 1}.
	 */
	@Attributes.Funnel
	default E colspan(int colspan) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(colspan != 1) Attributes.Integer.attribute(element, "colspan", colspan);
		return element;
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
	 *
	 * @param  colspan  Will not write the attribute when is {@code null} or {@code 1}.
	 */
	@Attributes.Funnel
	default E colspan(Integer colspan) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(colspan != null && colspan != 1) Attributes.Integer.attribute(element, "colspan", colspan);
		return element;
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
	 *
	 * @param  colspan  Will not write the attribute when is {@code null} or {@code 1}.
	 *
	 * @see #colspan(java.lang.Integer)
	 */
	default <Ex extends Throwable> E colspan(IOSupplierE<? extends Integer, Ex> colspan) throws IOException, Ex {
		return colspan((colspan == null) ? null : colspan.get());
	}
}
