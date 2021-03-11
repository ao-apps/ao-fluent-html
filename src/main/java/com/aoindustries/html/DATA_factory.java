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
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface DATA_factory<
	D  extends AnyDocument<D>,
	__ extends Union_Palpable_Phrasing<D, __>
> extends Content<D, __> {

	/**
	 * Opens a new data element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 */
	default DATA<D, __> data() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DATA<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new data element with the given value attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 */
	default DATA<D, __> data(Object value) throws IOException {
		return data().value(value);
	}

	/**
	 * Opens a new data element with the given value attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	default <Ex extends Throwable> DATA<D, __> data(IOSupplierE<?, Ex> value) throws IOException, Ex {
		return data().value(value);
	}

	/**
	 * Creates a data element with the given value and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ data__(Object value, IORunnableE<Ex> data) throws IOException, Ex {
		return data(value).__(data);
	}

	/**
	 * Creates a data element with the given value and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ data__(Object value, IOConsumerE<? super DATA__<D, __>, Ex> data) throws IOException, Ex {
		return data(value).__(data);
	}

	/**
	 * Creates a data element with the given value and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ data__(Object value, Object text) throws IOException {
		return data(value).__(text);
	}

	/**
	 * Creates an empty data element with the given value.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ data__(Object value) throws IOException {
		return data(value).__();
	}

	/**
	 * Creates a data element with the given value then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default DATA_c<D, __> data_c(Object value) throws IOException {
		return data(value)._c();
	}
}
