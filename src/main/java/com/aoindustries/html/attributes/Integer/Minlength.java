/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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
package com.aoindustries.html.attributes.Integer;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Minlength<E extends Element<?, ?, E> & Minlength<E>> {

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
	 */
	@Attributes.Funnel
	default E minlength(int minlength) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Integer.attribute(element, "minlength", minlength);
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
	 */
	@Attributes.Funnel
	default E minlength(Integer minlength) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Integer.attribute(element, "minlength", minlength);
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #minlength(java.lang.Integer)
	 */
	default <Ex extends Throwable> E minlength(IOSupplierE<? extends Integer, Ex> minlength) throws IOException, Ex {
		return minlength((minlength == null) ? null : minlength.get());
	}
}
