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
 * <li>See <a href="https://html.spec.whatwg.org/#attr-blockquote-cite">4.4.4 The blockquote element / cite</a>.</li>
 * <li>See <a href="https://html.spec.whatwg.org/#attr-q-cite">4.5.7 The q element / cite</a>.</li>
 * <li>See <a href="https://html.spec.whatwg.org/#attr-mod-cite">4.7.3 Attributes common to ins and del elements / cite</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
public interface Cite<E extends Element<E, ?> & Cite<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-blockquote-cite">4.4.4 The blockquote element / cite</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-q-cite">4.5.7 The q element / cite</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-mod-cite">4.7.3 Attributes common to ins and del elements / cite</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E cite(String cite) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Url.attribute(element, "cite", cite);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-blockquote-cite">4.4.4 The blockquote element / cite</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-q-cite">4.5.7 The q element / cite</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-mod-cite">4.7.3 Attributes common to ins and del elements / cite</a>.</li>
	 * </ul>
	 *
	 * @see #cite(java.lang.String)
	 */
	default <Ex extends Throwable> E cite(IOSupplierE<? extends String, Ex> cite) throws IOException, Ex {
		return cite((cite == null) ? null : cite.get());
	}
}
