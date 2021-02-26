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

import com.aoindustries.encoding.Doctype;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface STYLE_factory<__ extends MetadataContent<__>> extends Content<__> {

	/**
	 * Opens a new style element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
	 * </ul>
	 *
	 * @see Doctype#styleType(java.lang.Appendable)
	 */
	default STYLE<__> style() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new STYLE<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Opens a new style element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
	 * </ul>
	 */
	default STYLE<__> style(String type) throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new STYLE<>(getDocument(), pc, type).writeOpen();
	}

	/**
	 * Opens a new style element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
	 * </ul>
	 */
	default<Ex extends Throwable> STYLE<__> style(Suppliers.String<Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}

	/**
	 * Opens a new style element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
	 * </ul>
	 */
	default STYLE<__> style(STYLE.Type type) throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new STYLE<>(getDocument(), pc, type).writeOpen();
	}

	/**
	 * Opens a new style element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
	 * </ul>
	 */
	default<Ex extends Throwable> STYLE<__> style(IOSupplierE<? extends STYLE.Type, Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}
	// TODO: style__() - go directly to out, since no attributes? Lambda versions, too
	// TODO: A version called HtmlWriter that extends ChainWriter to avoid all this passing of appendables?
	// TODO: html.input.style.type().print("...").__().  How far do we take this?
}
