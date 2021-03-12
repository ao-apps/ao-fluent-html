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
 * Elements that are common to both {@link DIV_content} and {@link DL_content}.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_DIV_DL<
	D  extends AnyDocument<D>,
	__ extends Union_DIV_DL<D, __>
> extends
	//
	// Content models:
	//
	Content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="DD">
	/**
	 * Opens a new dd element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
	 * </p>
	 */
	@Factory("dd")
	default DD<D, __> dd() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DD<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a dd element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dd")
	default <Ex extends Throwable> __ dd__(IORunnableE<Ex> dd) throws IOException, Ex {
		return dd().__(dd);
	}

	/**
	 * Creates a dd element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dd")
	default <Ex extends Throwable> __ dd__(IOConsumerE<? super DD__<D, __>, Ex> dd) throws IOException, Ex {
		return dd().__(dd);
	}

	/**
	 * Creates a dd element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dd")
	default __ dd__(Object text) throws IOException {
		return dd().__(text);
	}

	/**
	 * Creates an empty dd element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dd")
	default __ dd__() throws IOException {
		return dd().__();
	}

	/**
	 * Creates a dd element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dd-element">4.4.11 The dd element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("dd")
	default DD_c<D, __> dd_c() throws IOException {
		return dd()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="DT">
	/**
	 * Opens a new dt element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
	 * </p>
	 */
	@Factory("dt")
	default DT<D, __> dt() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DT<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a dt element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dt")
	default <Ex extends Throwable> __ dt__(IORunnableE<Ex> dt) throws IOException, Ex {
		return dt().__(dt);
	}

	/**
	 * Creates a dt element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dt")
	default <Ex extends Throwable> __ dt__(IOConsumerE<? super DT__<D, __>, Ex> dt) throws IOException, Ex {
		return dt().__(dt);
	}

	/**
	 * Creates a dt element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dt")
	default __ dt__(Object text) throws IOException {
		return dt().__(text);
	}

	/**
	 * Creates an empty dt element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dt")
	default __ dt__() throws IOException {
		return dt().__();
	}

	/**
	 * Creates a dt element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dt-element">4.4.10 The dt element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("dt")
	default DT_c<D, __> dt_c() throws IOException {
		return dt()._c();
	}
	// </editor-fold>
}
