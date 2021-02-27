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
package com.aoindustries.html.attributes.event.form;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.</li>
 * <li>See <a href="https://www.w3schools.com/jsref/event_onsearch.asp">onsearch Event</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
public interface Onsearch<E extends Element<E, ?> & Onsearch<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
	 */
	@Attributes.Funnel
	default E onsearch(Object onsearch) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Event.attribute(element, "onsearch", onsearch);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
	 *
	 * @see #onsearch(java.lang.Object)
	 */
	default <Ex extends Throwable> E onsearch(IOSupplierE<?, Ex> onsearch) throws IOException, Ex {
		return onsearch((onsearch == null) ? null : onsearch.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
	 *
	 * @see #onsearch(java.lang.Object)
	 */
	default <Ex extends Throwable> E onsearch(MediaWritable<Ex> onsearch) throws IOException, Ex {
		return onsearch((Object)onsearch);
	}
}
