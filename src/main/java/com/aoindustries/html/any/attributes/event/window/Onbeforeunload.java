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
package com.aoindustries.html.any.attributes.event.window;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Onbeforeunload<E extends Element<?, ?, E> & Onbeforeunload<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
	 */
	@Attributes.Funnel
	default E onbeforeunload(Object onbeforeunload) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Event.attribute(element, "onbeforeunload", onbeforeunload);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #onbeforeunload(java.lang.Object)
	 */
	default <Ex extends Throwable> E onbeforeunload(IOSupplierE<?, Ex> onbeforeunload) throws IOException, Ex {
		return onbeforeunload((onbeforeunload == null) ? null : onbeforeunload.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #onbeforeunload(java.lang.Object)
	 */
	default <Ex extends Throwable> E onbeforeunload(MediaWritable<Ex> onbeforeunload) throws IOException, Ex {
		return onbeforeunload((Object)onbeforeunload);
	}
}
