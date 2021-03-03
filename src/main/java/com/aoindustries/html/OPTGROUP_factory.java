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
 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface OPTGROUP_factory<__ extends SELECT_content<__>> extends Content<__> {

	/**
	 * Opens a new optgroup element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 */
	default OPTGROUP<__> optgroup() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new OPTGROUP<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Creates an optgroup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ optgroup__(IORunnableE<Ex> optgroup) throws IOException, Ex {
		return optgroup().__(optgroup);
	}

	/**
	 * Creates an optgroup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ optgroup__(IOConsumerE<? super OPTGROUP__<__>, Ex> optgroup) throws IOException, Ex {
		return optgroup().__(optgroup);
	}

	/**
	 * Creates an empty optgroup element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ optgroup__() throws IOException {
		return optgroup().__();
	}

	/**
	 * Creates an optgroup element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default OPTGROUP_c<__> optgroup_c() throws IOException {
		return optgroup()._c();
	}
}
