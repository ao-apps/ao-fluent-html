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
 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface DEL_factory<
	D  extends AnyDocument<D>,
	__ extends PhrasingContent<D, __>
> extends Content<D, __> {

	/**
	 * Opens a new del element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 */
	default DEL<D, __> del() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DEL<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a del element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ del__(IORunnableE<Ex> del) throws IOException, Ex {
		return del().__(del);
	}

	/**
	 * Creates a del element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ del__(IOConsumerE<? super __, Ex> del) throws IOException, Ex {
		return del().__(del);
	}

	/**
	 * Creates an empty del element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ del__() throws IOException {
		return del().__();
	}

	/**
	 * Creates a del element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *          <p>
	 *          Due to limitations in Java generics, this content model does not directly reflect the parent content
	 *          model, despite this being a transparent content model.  Rather, it includes only the content model that
	 *          always applies to this element type.
	 *          </p>
	 *          <p><em>
	 *          For the full, context-aware content model, which will likely include more elements,
	 *          {@linkplain Transparent_c#pc() use the parent content model directly}.
	 *          </em></p>
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 * @see  Transparent_c#pc()
	 */
	default DEL_c<D, __> del_c() throws IOException {
		return del()._c();
	}
}
