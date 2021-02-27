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
package com.aoindustries.html.attributes.Text;

import com.aoindustries.encoding.MediaWritable;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Value<E extends Element<E, ?> & Value<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
	 */
	@Attributes.Funnel
	default E value(Object value) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		// TODO: Review if trim-to-null is the best default.  Maybe default to "false" and override where should be true instead.
		return Attributes.Text.attribute(element, "value", MarkupType.NONE, value, false, true, textInXhtmlAttributeEncoder);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
	 *
	 * @see #value(java.lang.Object)
	 */
	default <Ex extends Throwable> E value(IOSupplierE<?, Ex> value) throws IOException, Ex {
		return value((value == null) ? null : value.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
	 *
	 * @see #value(java.lang.Object)
	 */
	default <Ex extends Throwable> E value(MediaWritable<Ex> value) throws IOException, Ex {
		return value((Object)value);
	}
}
