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
package com.aoindustries.html.attributes.Url;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formaction">&lt;button&gt;: The Button element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formaction">&lt;input type="submit"&gt;</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formaction">&lt;input type="image"&gt;</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/att_formaction.asp">HTML formaction Attribute</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Formaction<E extends Element<?, ?, E> & Formaction<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formaction">&lt;button&gt;: The Button element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formaction">&lt;input type="submit"&gt;</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formaction">&lt;input type="image"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_formaction.asp">HTML formaction Attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E formaction(String formaction) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Url.attribute(element, "formaction", formaction);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formaction">&lt;button&gt;: The Button element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formaction">&lt;input type="submit"&gt;</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formaction">&lt;input type="image"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_formaction.asp">HTML formaction Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #formaction(java.lang.String)
	 */
	default <Ex extends Throwable> E formaction(IOSupplierE<? extends String, Ex> formaction) throws IOException, Ex {
		return formaction((formaction == null) ? null : formaction.get());
	}
}
