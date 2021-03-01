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
package com.aoindustries.html.attributes.Enum;

import com.aoindustries.html.Attributes;
import com.aoindustries.html.Document;
import com.aoindustries.html.Element;
import com.aoindustries.html.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#attr-fs-formtarget">4.10.18.6 Form submission attributes</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-target">&lt;form&gt;</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
public interface Formtarget<
	E extends Element<E, ?> & Formtarget<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-fs-formtarget">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-target">&lt;form&gt;</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E formtarget(String formtarget) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "formtarget", MarkupType.NONE, formtarget, true, true);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-fs-formtarget">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-target">&lt;form&gt;</a>.</li>
	 * </ul>
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E formtarget(Suppliers.String<Ex> formtarget) throws IOException, Ex {
		return formtarget((formtarget == null) ? null : formtarget.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-fs-formtarget">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-target">&lt;form&gt;</a>.</li>
	 * </ul>
	 */
	default E formtarget(V formtarget) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return formtarget((formtarget == null) ? null : formtarget.apply(element.getDocument()));
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#attr-fs-formtarget">4.10.18.6 Form submission attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-target">&lt;form&gt;</a>.</li>
	 * </ul>
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E formtarget(IOSupplierE<? extends V, Ex> formtarget) throws IOException, Ex {
		return formtarget((formtarget == null) ? (V)null : formtarget.get());
	}
}
