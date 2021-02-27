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
package com.aoindustries.html.attributes.Dimension;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Element;
import com.aoindustries.html.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
 *
 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
 *
 * @author  AO Industries, Inc.
 */
@Deprecated
public interface WidthHtml4Only<E extends Element<E, ?> & WidthHtml4Only<E>> extends Width<E> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Override
	@Attributes.Funnel
	default E width(int pixels) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype == Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"notSupportedInHtml5",
				"width"
			);
		}
		return Width.super.width(pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Override
	@Attributes.Funnel
	default E width(Integer pixels) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype == Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"notSupportedInHtml5",
				"width"
			);
		}
		return Width.super.width(pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Override
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E width(IOSupplierE<? extends Integer, Ex> pixels) throws IOException, Ex {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype == Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"notSupportedInHtml5",
				"width"
			);
		}
		return Width.super.width(pixels);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Override
	@Attributes.Funnel
	default E width(String pixelsOrPercent) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype == Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"notSupportedInHtml5",
				"width"
			);
		}
		return Width.super.width(pixelsOrPercent);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
	 *
	 * @see #width(java.lang.String)
	 *
	 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
	 */
	@Deprecated
	@Override
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E width(Suppliers.String<Ex> pixelsOrPercent) throws IOException, Ex {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype == Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"notSupportedInHtml5",
				"width"
			);
		}
		return Width.super.width(pixelsOrPercent);
	}
}
