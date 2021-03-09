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
 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface SPAN_factory<__ extends Union_Palpable_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new span element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 */
	default SPAN<__> span() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new SPAN<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a span element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ span__(IORunnableE<Ex> span) throws IOException, Ex {
		return span().__(span);
	}

	/**
	 * Creates a span element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ span__(IOConsumerE<? super SPAN__<__>, Ex> span) throws IOException, Ex {
		return span().__(span);
	}

	/**
	 * Creates a span element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ span__(Object text) throws IOException {
		return span().__(text);
	}

	/**
	 * Creates an empty span element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ span__() throws IOException {
		return span().__();
	}

	/**
	 * Creates a span element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default SPAN_c<__> span_c() throws IOException {
		return span()._c();
	}
}
