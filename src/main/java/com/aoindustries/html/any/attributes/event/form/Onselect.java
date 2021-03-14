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
package com.aoindustries.html.any.attributes.event.form;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.</li>
 * <li>See <a href="https://www.w3schools.com/jsref/event_onselect.asp">onselect Event</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/onselect">GlobalEventHandlers.onselect</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Onselect<E extends Element<?, ?, E> & Onselect<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
	 */
	@Attributes.Funnel
	default E onselect(Object onselect) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Event.attribute(element, "onselect", onselect);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #onselect(java.lang.Object)
	 */
	default <Ex extends Throwable> E onselect(IOSupplierE<?, Ex> onselect) throws IOException, Ex {
		return onselect((onselect == null) ? null : onselect.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #onselect(java.lang.Object)
	 */
	default <Ex extends Throwable> E onselect(MediaWritable<Ex> onselect) throws IOException, Ex {
		return onselect((Object)onselect);
	}
}
