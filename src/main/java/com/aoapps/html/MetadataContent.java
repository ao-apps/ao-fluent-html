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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.aoapps.html;

import com.aoapps.html.any.AnyMetadataContent;
import com.aoapps.html.any.AnySTYLE;
import com.aoapps.html.any.Suppliers;
import com.aoapps.lang.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#metadata-content">3.2.5.2.1 Metadata content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#metadata_content">Metadata content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface MetadataContent<
	__ extends MetadataContent<__>
> extends AnyMetadataContent<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	Union_Metadata_Phrasing<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	// Inherited: ScriptSupportingContent<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="BASE">
	@Override
	default BASE<__> base() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new BASE<>(document, pc).writeOpen(document.getUnsafe(null));
	}
	// </editor-fold>
	// Inherited: LINK
	// Inherited: META
	// Inherited: NOSCRIPT
	// Inherited: SCRIPT
	// <editor-fold defaultstate="collapsed" desc="STYLE">
	@Override
	default STYLE<__> style() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new STYLE<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	@Override
	default STYLE<__> style(String type) throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new STYLE<>(document, pc, type).writeOpen(document.getUnsafe(null));
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> STYLE<__> style(Suppliers.String<Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}

	@Override
	default STYLE<__> style(AnySTYLE.Type type) throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new STYLE<>(document, pc, type).writeOpen(document.getUnsafe(null));
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> STYLE<__> style(IOSupplierE<? extends AnySTYLE.Type, Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}
	// </editor-fold>
	// Inherited: TEMPLATE - WHATWG only
	// <editor-fold defaultstate="collapsed" desc="TITLE">
	@Override
	default TITLE<__> title() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new TITLE<>(document, pc).writeOpen(document.getUnsafe(null));
	}
	// </editor-fold>
}
