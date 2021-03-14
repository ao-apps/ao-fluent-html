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
package com.aoindustries.html.any.attributes.Text;

import com.aoindustries.encoding.MediaWritable;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * An arbitrary attribute.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Attribute<E extends Element<?, ?, E> & Attribute<E>> {

	/**
	 * An arbitrary attribute.
	 *
	 * @param value  The attribute value, {@link Attributes#NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
	 *
	 * @deprecated  Please implement specific attributes as-needed
	 */
	@Deprecated
	@Attributes.Funnel
	default E attribute(String name, Object value) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		// TODO: Validate attribute name by doctype: https://dev.w3.org/html5/html-author/#attributes (XmlUtils could help)
		return Attributes.Text.attribute(element, name, MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
	}

	/**
	 * An arbitrary attribute.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 * @param value  The attribute value, {@link Attributes#NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
	 *
	 * @see #attribute(java.lang.String, java.lang.Object)
	 *
	 * @deprecated  Please implement specific attributes as-needed
	 */
	@Deprecated
	default <Ex extends Throwable> E attribute(String name, IOSupplierE<?, Ex> value) throws IOException, Ex {
		return attribute(name, (value == null) ? null : value.get());
	}

	/**
	 * An arbitrary attribute.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #attribute(java.lang.String, java.lang.Object)
	 *
	 * @deprecated  Please implement specific attributes as-needed
	 */
	@Deprecated
	default <Ex extends Throwable> E attribute(String name, MediaWritable<Ex> value) throws IOException, Ex {
		return attribute(name, (Object)value);
	}
}
