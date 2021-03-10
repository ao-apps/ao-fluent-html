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
 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface HTML_factory<__ extends Content<__>/* TODO? Document */> extends Content<__/* TODO? Document */> {

	/**
	 * Opens a new html element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 */
	default HTML<__> html() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new HTML<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a html element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ html__(IORunnableE<Ex> html) throws IOException, Ex {
		return html().__(html);
	}

	/**
	 * Creates a html element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ html__(IOConsumerE<? super HTML__<__>, Ex> html) throws IOException, Ex {
		return html().__(html);
	}

	/**
	 * Creates an empty html element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ html__() throws IOException {
		return html().__();
	}

	/**
	 * Creates a html element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default HTML_c<__> html_c() throws IOException {
		return html()._c();
	}
}
