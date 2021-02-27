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
package com.aoindustries.html.attributes.event.window;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Onpagehide<E extends Element<E, ?> & Onpagehide<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
	 */
	@Attributes.Funnel
	default E onpagehide(Object onpagehide) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Event.attribute(element, "onpagehide", onpagehide);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
	 *
	 * @see #onpagehide(java.lang.Object)
	 */
	default <Ex extends Throwable> E onpagehide(IOSupplierE<?, Ex> onpagehide) throws IOException, Ex {
		return onpagehide((onpagehide == null) ? null : onpagehide.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
	 *
	 * @see #onpagehide(java.lang.Object)
	 */
	default <Ex extends Throwable> E onpagehide(MediaWritable<Ex> onpagehide) throws IOException, Ex {
		return onpagehide((Object)onpagehide);
	}
}
