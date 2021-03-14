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
package com.aoindustries.html.any.attributes.event.mouse;

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
 *
 * @param  <E>   This element type
 *
 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel onwheel} attribute instead.
 *
 * @author  AO Industries, Inc.
 */
@Deprecated
public interface Onmousewheel<E extends Element<?, ?, E> & Onmousewheel<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
	 *
	 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(java.lang.Object) onwheel} attribute instead.
	 */
	@Deprecated
	@Attributes.Funnel
	default E onmousewheel(Object onmousewheel) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Event.attribute(element, "onmousewheel", onmousewheel);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #onmousewheel(java.lang.Object)
	 *
	 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(com.aoindustries.io.function.IOSupplierE) onwheel} attribute instead.
	 */
	@Deprecated
	default <Ex extends Throwable> E onmousewheel(IOSupplierE<?, Ex> onmousewheel) throws IOException, Ex {
		return onmousewheel((onmousewheel == null) ? null : onmousewheel.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #onmousewheel(java.lang.Object)
	 *
	 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(com.aoindustries.encoding.MediaWritable) onwheel} attribute instead.
	 */
	@Deprecated
	default <Ex extends Throwable> E onmousewheel(MediaWritable<Ex> onmousewheel) throws IOException, Ex {
		return onmousewheel((Object)onmousewheel);
	}
}
