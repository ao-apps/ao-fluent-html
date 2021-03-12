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
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#heading-content">3.2.5.2.4 Heading content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#heading_content">Heading content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface HeadingContent<
	D  extends AnyDocument<D>,
	__ extends HeadingContent<D, __>
> extends
	//
	// Content models:
	//
	Content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="H1">
	/**
	 * Opens a new h1 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@Factory("h1")
	default H1<D, __> h1() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new H1<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an h1 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h1")
	default <Ex extends Throwable> __ h1__(IORunnableE<Ex> h1) throws IOException, Ex {
		return h1().__(h1);
	}

	/**
	 * Creates an h1 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h1")
	default <Ex extends Throwable> __ h1__(IOConsumerE<? super H1__<D, __>, Ex> h1) throws IOException, Ex {
		return h1().__(h1);
	}

	/**
	 * Creates an h1 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h1")
	default __ h1__(Object text) throws IOException {
		return h1().__(text);
	}

	/**
	 * Creates an empty h1 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h1")
	default __ h1__() throws IOException {
		return h1().__();
	}

	/**
	 * Creates an h1 element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("h1")
	default H1_c<D, __> h1_c() throws IOException {
		return h1()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="H2">
	/**
	 * Opens a new h2 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@Factory("h2")
	default H2<D, __> h2() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new H2<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an h2 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h2")
	default <Ex extends Throwable> __ h2__(IORunnableE<Ex> h2) throws IOException, Ex {
		return h2().__(h2);
	}

	/**
	 * Creates an h2 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h2")
	default <Ex extends Throwable> __ h2__(IOConsumerE<? super H2__<D, __>, Ex> h2) throws IOException, Ex {
		return h2().__(h2);
	}

	/**
	 * Creates an h2 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h2")
	default __ h2__(Object text) throws IOException {
		return h2().__(text);
	}

	/**
	 * Creates an empty h2 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h2")
	default __ h2__() throws IOException {
		return h2().__();
	}

	/**
	 * Creates an h2 element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("h2")
	default H2_c<D, __> h2_c() throws IOException {
		return h2()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="H3">
	/**
	 * Opens a new h3 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@Factory("h3")
	default H3<D, __> h3() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new H3<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an h3 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h3")
	default <Ex extends Throwable> __ h3__(IORunnableE<Ex> h3) throws IOException, Ex {
		return h3().__(h3);
	}

	/**
	 * Creates an h3 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h3")
	default <Ex extends Throwable> __ h3__(IOConsumerE<? super H3__<D, __>, Ex> h3) throws IOException, Ex {
		return h3().__(h3);
	}

	/**
	 * Creates an h3 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h3")
	default __ h3__(Object text) throws IOException {
		return h3().__(text);
	}

	/**
	 * Creates an empty h3 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h3")
	default __ h3__() throws IOException {
		return h3().__();
	}

	/**
	 * Creates an h3 element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("h3")
	default H3_c<D, __> h3_c() throws IOException {
		return h3()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="H4">
	/**
	 * Opens a new h4 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@Factory("h4")
	default H4<D, __> h4() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new H4<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an h4 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h4")
	default <Ex extends Throwable> __ h4__(IORunnableE<Ex> h4) throws IOException, Ex {
		return h4().__(h4);
	}

	/**
	 * Creates an h4 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h4")
	default <Ex extends Throwable> __ h4__(IOConsumerE<? super H4__<D, __>, Ex> h4) throws IOException, Ex {
		return h4().__(h4);
	}

	/**
	 * Creates an h4 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h4")
	default __ h4__(Object text) throws IOException {
		return h4().__(text);
	}

	/**
	 * Creates an empty h4 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h4")
	default __ h4__() throws IOException {
		return h4().__();
	}

	/**
	 * Creates an h4 element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("h4")
	default H4_c<D, __> h4_c() throws IOException {
		return h4()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="H5">
	/**
	 * Opens a new h5 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@Factory("h5")
	default H5<D, __> h5() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new H5<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an h5 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h5")
	default <Ex extends Throwable> __ h5__(IORunnableE<Ex> h5) throws IOException, Ex {
		return h5().__(h5);
	}

	/**
	 * Creates an h5 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h5")
	default <Ex extends Throwable> __ h5__(IOConsumerE<? super H5__<D, __>, Ex> h5) throws IOException, Ex {
		return h5().__(h5);
	}

	/**
	 * Creates an h5 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h5")
	default __ h5__(Object text) throws IOException {
		return h5().__(text);
	}

	/**
	 * Creates an empty h5 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h5")
	default __ h5__() throws IOException {
		return h5().__();
	}

	/**
	 * Creates an h5 element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("h5")
	default H5_c<D, __> h5_c() throws IOException {
		return h5()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="H6">
	/**
	 * Opens a new h6 element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@Factory("h6")
	default H6<D, __> h6() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new H6<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an h6 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h6")
	default <Ex extends Throwable> __ h6__(IORunnableE<Ex> h6) throws IOException, Ex {
		return h6().__(h6);
	}

	/**
	 * Creates an h6 element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h6")
	default <Ex extends Throwable> __ h6__(IOConsumerE<? super H6__<D, __>, Ex> h6) throws IOException, Ex {
		return h6().__(h6);
	}

	/**
	 * Creates an h6 element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h6")
	default __ h6__(Object text) throws IOException {
		return h6().__(text);
	}

	/**
	 * Creates an empty h6 element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h6")
	default __ h6__() throws IOException {
		return h6().__();
	}

	/**
	 * Creates an h6 element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("h6")
	default H6_c<D, __> h6_c() throws IOException {
		return h6()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="H#">
	/**
	 * Opens a new h# element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	@Factory("h#")
	default <
		H   extends com.aoindustries.html.H<D, __, H, H__, H_c>,
		H__ extends com.aoindustries.html.H__<D, __, H__>,
		H_c extends com.aoindustries.html.H_c<D, __, H_c>
	> H h(int rank) throws IOException {
		switch(rank) {
			case 1 : return (H)h1();
			case 2 : return (H)h2();
			case 3 : return (H)h3();
			case 4 : return (H)h4();
			case 5 : return (H)h5();
			case 6 : return (H)h6();
			default :
				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
		}
	}
//	default <
//		E  extends H<E, __, h__, h_c>,
//		h__ extends H__<__, h__>,
//		h_c extends H_c<__, h_c>
//	> H<E, __, h__, h_c> h(int rank) throws IOException {
//		switch(rank) {
//			case 1 : return h1();
//			case 2 : return h2();
//			case 3 : return h3();
//			case 4 : return h4();
//			case 5 : return h5();
//			case 6 : return h6();
//			default :
//				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
//		}
//	}
//	default H<
//		?,
//		__,
//		? extends H__<__, ? extends H__<__, ?>>,
//		? extends H_c<__, ? extends H_c<__, ?>>
//	> h(int rank) throws IOException {
//		switch(rank) {
//			case 1 : return h1();
//			case 2 : return h2();
//			case 3 : return h3();
//			case 4 : return h4();
//			case 5 : return h5();
//			case 6 : return h6();
//			default :
//				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
//		}
//	}
//	@SuppressWarnings("unchecked")
//	default <
//		H extends com.aoindustries.html.H<
//			H,
//			__,
//			? extends H__<__, ?>,
//			? extends H_c<__, ?>
////			? extends H__<__, ? extends H__<__, ?>>,
////			? extends H_c<__, ? extends H_c<__, ?>>
////			? extends H__<__, ? extends H__<__, ? extends H__<__, ?>>>, // TODO: This nesting could go forever without self-referential generics
////			? extends H_c<__, ? extends H_c<__, ? extends H_c<__, ?>>>  // TODO: This nesting could go forever without self-referential generics
//		>
//	> H h(int rank) throws IOException {
//		switch(rank) {
//			case 1 : return (H)h1();
//			case 2 : return (H)h2();
//			case 3 : return (H)h3();
//			case 4 : return (H)h4();
//			case 5 : return (H)h5();
//			case 6 : return (H)h6();
//			default :
//				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
//		}
//	}

	/**
	 * Creates an h# element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h#")
	default <Ex extends Throwable> __ h__(int rank, IORunnableE<Ex> h) throws IOException, Ex {
		return h(rank).__(h);
	}

	/**
	 * Creates an h# element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h#")
	default <
		H__ extends com.aoindustries.html.H__<D, __, H__>,
		Ex extends Throwable
	> __ h__(int rank, IOConsumerE<? super H__, Ex> h) throws IOException, Ex {
		return h(rank).__(h);
	}
//	default <Ex extends Throwable> __ h__(int rank, IOConsumerE<? super H__<__, ?>, Ex> h) throws IOException, Ex {
//		return h(rank).__(h);
//	}
//	default <Ex extends Throwable> __ h__(int rank, IOConsumerE<? super H__<__, ? extends H__<__, ?>>, Ex> h) throws IOException, Ex {
//		return h(rank).__(h);
//	}

	/**
	 * Creates an h# element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h#")
	default __ h__(int rank, Object text) throws IOException {
		return h(rank).__(text);
	}

	/**
	 * Creates an empty h# element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("h#")
	default __ h__(int rank) throws IOException {
		return h(rank).__();
	}

	/**
	 * Creates an h# element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@SuppressWarnings("unchecked")
	@Factory("h#")
	default <
		H_c extends com.aoindustries.html.H_c<D, __, H_c>
	> H_c h_c(int rank) throws IOException {
		return (H_c)h(rank)._c();
	}
//	default H_c<__, ?> h_c(int rank) throws IOException {
//		return h(rank)._c();
//	}
//	default H_c<__, ? extends H_c<__, ?>> h_c(int rank) throws IOException {
//		return h(rank)._c();
//	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="HGROUP">
	/**
	 * Opens a new hgroup element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-hgroup-element">4.3.7 The hgroup element</a>.
	 * </p>
	 */
	@Factory("hgroup")
	default void hgroup() throws IOException {
		throw new AssertionError("TODO: Implement hgroup");
	}
	// </editor-fold>
}
