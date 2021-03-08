/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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
package com.aoindustries.html;

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface UL_factory<__ extends PalpableContent<__>> extends Content<__> {

	/**
	 * Opens a new ul element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 */
	default UL<__> ul() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new UL<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a ul element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ ul__(IORunnableE<Ex> ul) throws IOException, Ex {
		return ul().__(ul);
	}

	/**
	 * Creates a ul element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ ul__(IOConsumerE<? super UL__<__>, Ex> ul) throws IOException, Ex {
		return ul().__(ul);
	}

	/**
	 * Creates an empty ul element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ ul__() throws IOException {
		return ul().__();
	}

	/**
	 * Creates a ul element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default UL_c<__> ul_c() throws IOException {
		return ul()._c();
	}
}
