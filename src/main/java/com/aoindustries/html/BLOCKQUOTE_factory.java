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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface BLOCKQUOTE_factory<__ extends PalpableContent<__>> extends Content<__> {

	/**
	 * Opens a new blockquote element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
	 * </ul>
	 */
	default BLOCKQUOTE<__> blockquote() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new BLOCKQUOTE<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Creates a blockquote element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ blockquote__(IORunnableE<Ex> blockquote) throws IOException, Ex {
		return blockquote().__(blockquote);
	}

	/**
	 * Creates a blockquote element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ blockquote__(IOConsumerE<? super BLOCKQUOTE__<__>, Ex> blockquote) throws IOException, Ex {
		return blockquote().__(blockquote);
	}

	/**
	 * Creates a blockquote element with no attributes and a text body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ blockquote__(Object text) throws IOException {
		return blockquote().__(text);
	}

	/**
	 * Creates an empty blockquote element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ blockquote__() throws IOException {
		return blockquote().__();
	}
}
