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
 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface MAP_factory<__ extends Union_Palpable_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new map element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 */
	default MAP<__> map() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new MAP<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new map element with the given name attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 */
	default MAP<__> map(String name) throws IOException {
		return map().name(name);
	}

	/**
	 * Opens a new map element with the given name attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 */
	default <Ex extends Throwable> MAP<__> map(IOSupplierE<? extends String, Ex> name) throws IOException, Ex {
		return map().name(name);
	}

	/**
	 * Creates a map element the given name attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ map__(String name, IORunnableE<Ex> map) throws IOException, Ex {
		return map(name).__(map);
	}

	/**
	 * Creates a map element the given name attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ map__(String name, IOConsumerE<? super __, Ex> map) throws IOException, Ex {
		return map(name).__(map);
	}

	/**
	 * Creates a map element the given name attribute and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ map__(String name, Object text) throws IOException {
		return map(name).__(text);
	}

	/**
	 * Creates an empty map element the given name attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ map__(String name) throws IOException {
		return map(name).__();
	}

	/**
	 * Creates a map element the given name attribute then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default MAP_c<__> map_c(String name) throws IOException {
		return map(name)._c();
	}
}
