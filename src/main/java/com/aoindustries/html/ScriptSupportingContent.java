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
 * See <a href="https://html.spec.whatwg.org/multipage/dom.html#script-supporting-elements">3.2.5.2.9 Script-supporting elements</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface ScriptSupportingContent<
	D  extends AnyDocument<D>,
	__ extends ScriptSupportingContent<D, __>
> extends
	//
	// Unions:
	//
	Union_COLGROUP_ScriptSupporting<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="SCRIPT">
	/**
	 * Opens a new script element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
	 * </ul>
	 *
	 * @see Doctype#scriptType(java.lang.Appendable)
	 */
	@Factory("script")
	default SCRIPT<D, __> script() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SCRIPT<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new script element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
	 * </ul>
	 */
	@Factory("script")
	default SCRIPT<D, __> script(String type) throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SCRIPT<>(document, pc, type).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new script element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Factory("script")
	default <Ex extends Throwable> SCRIPT<D, __> script(Suppliers.String<Ex> type) throws IOException, Ex {
		return script((type == null) ? null : type.get());
	}

	/**
	 * Opens a new script element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
	 * </ul>
	 */
	@Factory("script")
	default SCRIPT<D, __> script(SCRIPT.Type type) throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SCRIPT<>(document, pc, type).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new script element of the given type.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Factory("script")
	default <Ex extends Throwable> SCRIPT<D, __> script(IOSupplierE<? extends SCRIPT.Type, Ex> type) throws IOException, Ex {
		return script((type == null) ? null : type.get());
	}
	// </editor-fold>
	// Inherited: TEMPLATE
}
