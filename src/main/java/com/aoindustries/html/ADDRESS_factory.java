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
 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface ADDRESS_factory<
	D  extends AnyDocument<D>,
	__ extends PalpableContent<D, __>
> extends Content<D, __> {

	/**
	 * Opens a new address element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 */
	default ADDRESS<D, __> address() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new ADDRESS<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an address element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ address__(IORunnableE<Ex> address) throws IOException, Ex {
		return address().__(address);
	}

	/**
	 * Creates an address element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ address__(IOConsumerE<? super ADDRESS__<D, __>, Ex> address) throws IOException, Ex {
		return address().__(address);
	}

	/**
	 * Creates an address element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ address__(Object text) throws IOException {
		return address().__(text);
	}

	/**
	 * Creates an empty address element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ address__() throws IOException {
		return address().__();
	}

	/**
	 * Creates an address element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default ADDRESS_c<D, __> address_c() throws IOException {
		return address()._c();
	}
}
