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

import java.io.IOException;

/**
 * A normal element that can have textual content.
 * <p>
 * See <a href="https://html.spec.whatwg.org/#normal-elements">13.1.2 Elements / Normal elements</a>.
 * </p>
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <C>   This content model, which will be the parent content model of child elements
 * @param  <CC>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class NormalTextElement<
	E  extends NormalTextElement<E, PC, C, CC>,
	PC extends Content<PC>,
	C  extends NormalTextElement.NormalTextContent<PC, C>,
	// Would prefer "CC extends C & Closeable<PC>", but "a type variable may not be followed by other bounds"
	CC extends NormalTextElement.CloseableNormalTextContent<PC, CC>
> extends NormalElement<E, PC, C, CC> {

	public static abstract class NormalTextContent<
		PC extends Content<PC>,
		C  extends NormalTextContent<PC, C>
	> extends NormalContent<PC, C> implements UnionContent.Palpable_Phrasing<C> {

		protected NormalTextContent(NormalTextElement<?, PC, C, ?> element) {
			super(element);
		}
	}

	public static abstract class CloseableNormalTextContent<
		PC extends Content<PC>,
		CC extends CloseableNormalTextContent<PC, CC>
	> extends CloseableNormalContent<PC, CC> implements UnionContent.Palpable_Phrasing<CC> {

		protected CloseableNormalTextContent(NormalTextElement<?, PC, ?, CC> element) {
			super(element);
		}
	}

	protected NormalTextElement(Document document, PC pc) {
		super(document, pc);
	}

	/**
	 * Ends attributes, writes a text body, then closes this element.
	 *
	 * @return  The parent content model this element is within
	 *
	 * @see  Document#text(java.lang.Object)
	 */
	public PC __(Object text) throws IOException {
		if(text != null) {
			document.out.write('>');
			document.text(text);
			writeClose(false);
		} else {
			writeClose(true);
		}
		return pc;
	}
}
