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
 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Accept<E extends Element<E, ?> & Accept<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
	 */
	@Attributes.Funnel
	default E accept(Object accept) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Text.attribute(element, "accept", MarkupType.NONE, accept, true, true, textInXhtmlAttributeEncoder);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
	 *
	 * @see #accept(java.lang.Object)
	 */
	default <Ex extends Throwable> E accept(IOSupplierE<?, Ex> accept) throws IOException, Ex {
		return accept((accept == null) ? null : accept.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
	 *
	 * @see #accept(java.lang.Object)
	 */
	default <Ex extends Throwable> E accept(MediaWritable<Ex> accept) throws IOException, Ex {
		return accept((Object)accept);
	}
}
