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
 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Rowspan<E extends Element<E, ?> & Rowspan<E>> {

	/**
	 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
	 */
	@Attributes.Funnel
	default E rowspan(int rowspan) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Integer.attribute(element, "rowspan", rowspan);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
	 */
	@Attributes.Funnel
	default E rowspan(Integer rowspan) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Integer.attribute(element, "rowspan", rowspan);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
	 *
	 * @see #rowspan(java.lang.Integer)
	 */
	default <Ex extends Throwable> E rowspan(IOSupplierE<? extends Integer, Ex> rowspan) throws IOException, Ex {
		return rowspan((rowspan == null) ? null : rowspan.get());
	}
}
