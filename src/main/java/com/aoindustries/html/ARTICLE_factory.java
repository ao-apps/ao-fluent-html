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
 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface ARTICLE_factory<__ extends SectioningContent<__>> extends Content<__> {

	/**
	 * Opens a new article element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
	 * </p>
	 */
	default ARTICLE<__> article() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new ARTICLE<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an article element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ article__(IORunnableE<Ex> article) throws IOException, Ex {
		return article().__(article);
	}

	/**
	 * Creates an article element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ article__(IOConsumerE<? super ARTICLE__<__>, Ex> article) throws IOException, Ex {
		return article().__(article);
	}

	/**
	 * Creates an article element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ article__(Object text) throws IOException {
		return article().__(text);
	}

	/**
	 * Creates an empty article element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ article__() throws IOException {
		return article().__();
	}

	/**
	 * Creates an article element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default ARTICLE_c<__> article_c() throws IOException {
		return article()._c();
	}
}
