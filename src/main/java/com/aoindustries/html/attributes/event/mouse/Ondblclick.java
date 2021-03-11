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
package com.aoindustries.html.attributes.event.mouse;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Ondblclick<E extends Element<?, ?, E> & Ondblclick<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
	 */
	@Attributes.Funnel
	default E ondblclick(Object ondblclick) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Event.attribute(element, "ondblclick", ondblclick);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #ondblclick(java.lang.Object)
	 */
	default <Ex extends Throwable> E ondblclick(IOSupplierE<?, Ex> ondblclick) throws IOException, Ex {
		return ondblclick((ondblclick == null) ? null : ondblclick.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #ondblclick(java.lang.Object)
	 */
	default <Ex extends Throwable> E ondblclick(MediaWritable<Ex> ondblclick) throws IOException, Ex {
		return ondblclick((Object)ondblclick);
	}
}
