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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-novalidate">4.10.18.6 Form submission attributes</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-novalidate">&lt;form&gt;</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/att_novalidate.asp">HTML novalidate Attribute</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Novalidate<E extends Element<?, ?, E> & Novalidate<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-novalidate">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-novalidate">&lt;form&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_novalidate.asp">HTML novalidate Attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E novalidate(boolean novalidate) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Boolean.attribute(element, "novalidate", novalidate);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-novalidate">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-novalidate">&lt;form&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_novalidate.asp">HTML novalidate Attribute</a>.</li>
	 * </ul>
	 *
	 * @see #novalidate(boolean)
	 */
	default E novalidate(Boolean novalidate) throws IOException {
		return novalidate(novalidate != null && novalidate);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#attr-fs-novalidate">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-novalidate">&lt;form&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_novalidate.asp">HTML novalidate Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #novalidate(java.lang.Boolean)
	 */
	default <Ex extends Throwable> E novalidate(IOSupplierE<? extends Boolean, Ex> novalidate) throws IOException, Ex {
		return novalidate((novalidate == null) ? null : novalidate.get());
	}
}
