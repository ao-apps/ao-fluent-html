/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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

import com.aoindustries.encoding.Doctype;
import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_required.asp">HTML required Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Required<E extends Element<?, ?, E> & Required<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_required.asp">HTML required Attribute</a>.
	 */
	@Attributes.Funnel
	default E required(boolean required) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				element.getDocument().doctype,
				"required"
			);
		}
		return Attributes.Boolean.attribute(element, "required", required);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_required.asp">HTML required Attribute</a>.
	 *
	 * @see #required(boolean)
	 */
	default E required(Boolean required) throws IOException {
		return required(required != null && required);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_required.asp">HTML required Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #required(java.lang.Boolean)
	 */
	default <Ex extends Throwable> E required(IOSupplierE<? extends Boolean, Ex> required) throws IOException, Ex {
		return required((required == null) ? null : required.get());
	}
}
