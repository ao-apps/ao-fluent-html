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
 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface A_factory<__ extends Union_Interactive_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new a element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 */
	default A<__> a() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new A<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new a element with the given href attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 */
	default A<__> a(String href) throws IOException {
		return a().href(href);
	}

	/**
	 * Opens a new a element with the given href attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 */
	default <Ex extends Throwable> A<__> a(IOSupplierE<? extends String, Ex> href) throws IOException, Ex {
		return a().href(href);
	}

	/**
	 * Creates an a element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ a__(IORunnableE<Ex> a) throws IOException, Ex {
		return a().__(a);
	}

	/**
	 * Creates an a element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	// TODO: How to limit content to not have interactive elements?
	default <Ex extends Throwable> __ a__(IOConsumerE<? super __, Ex> a) throws IOException, Ex {
		return a().__(a);
	}

	/**
	 * Creates an a element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 * <p>
	 * Since {@link TextContent} is not a part of {@link Union_Interactive_Phrasing},
	 * strictly speaking text is not allowed in all possible content models that can apply to <code>&lt;a&gt;</code>.
	 * However, since it is such a common operation, we've added it here.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 *
	 * @throws  IllegalStateException when {@code text != null} and current content model does not allow text
	 */
	default __ a__(Object text) throws IOException, IllegalStateException {
		return a().__(text);
	}

	/**
	 * Creates an empty a element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ a__() throws IOException {
		return a().__();
	}

	/**
	 * Creates an a element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
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
	default A_c<__> a_c() throws IOException {
		return a()._c();
	}
}
