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
package com.aoindustries.html.attributes.Boolean;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-formnovalidate">4.10.18.6 Form submission attributes</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formnovalidate">&lt;button&gt;: The Button element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formnovalidate">&lt;input type="submit"&gt;</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formnovalidate">&lt;input type="image"&gt;</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
public interface Formnovalidate<E extends Element<E, ?> & Formnovalidate<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-formnovalidate">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formnovalidate">&lt;button&gt;: The Button element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formnovalidate">&lt;input type="submit"&gt;</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formnovalidate">&lt;input type="image"&gt;</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E formnovalidate(boolean formnovalidate) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Boolean.attribute(element, "formnovalidate", formnovalidate);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-formnovalidate">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formnovalidate">&lt;button&gt;: The Button element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formnovalidate">&lt;input type="submit"&gt;</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formnovalidate">&lt;input type="image"&gt;</a>.</li>
	 * </ul>
	 *
	 * @see #formnovalidate(boolean)
	 */
	default E formnovalidate(Boolean formnovalidate) throws IOException {
		return formnovalidate(formnovalidate != null && formnovalidate);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-formnovalidate">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-formnovalidate">&lt;button&gt;: The Button element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit#formnovalidate">&lt;input type="submit"&gt;</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image#formnovalidate">&lt;input type="image"&gt;</a>.</li>
	 * </ul>
	 *
	 * @see #formnovalidate(java.lang.Boolean)
	 */
	default <Ex extends Throwable> E formnovalidate(IOSupplierE<? extends Boolean, Ex> formnovalidate) throws IOException, Ex {
		return formnovalidate((formnovalidate == null) ? null : formnovalidate.get());
	}
}
