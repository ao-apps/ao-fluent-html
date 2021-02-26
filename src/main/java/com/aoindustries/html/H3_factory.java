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
 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface H3_factory<__ extends HeadingContent<__>> extends Content<__> {

	/**
	 * Opens a new h3 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	default H3<__> h3() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new H3<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Creates an h3 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ h3__(IORunnableE<Ex> h3) throws IOException, Ex {
		return h3().__(h3);
	}

	/**
	 * Creates an h3 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ h3__(IOConsumerE<? super H3__<__>, Ex> h3) throws IOException, Ex {
		return h3().__(h3);
	}

	/**
	 * Creates an h3 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ h3__(Object text) throws IOException {
		return h3().__(text);
	}

	/**
	 * Creates an empty h3 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ h3__() throws IOException {
		return h3().__();
	}
}
