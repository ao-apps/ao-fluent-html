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

import com.aoindustries.encoding.Serialization;
import com.aoindustries.html.any.AnyTABLE_content;
import com.aoindustries.io.function.IOConsumerE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface TABLE_content<
	__ extends TABLE_content<__>
> extends AnyTABLE_content<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	Union_TBODY_THEAD_TFOOT<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	// Inherited: ScriptSupportingContent<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="CAPTION">
	@Override
	default CAPTION<__> caption() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new CAPTION<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a caption element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ caption__(IOConsumerE<? super CAPTION__<__>, Ex> caption) throws IOException, Ex {
		return caption().__(caption);
	}

	@Override
	default CAPTION_c<__> caption_c() throws IOException {
		return caption()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="COLGROUP">
	@Override
	default COLGROUP<__> colgroup() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new COLGROUP<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a colgroup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-colgroup-element">4.9.3 The colgroup element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ colgroup__(IOConsumerE<? super COLGROUP__<__>, Ex> colgroup) throws IOException, Ex {
		return colgroup().__(colgroup);
	}

	@Override
	default COLGROUP_c<__> colgroup_c() throws IOException {
		return colgroup()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="THEAD">
	@Override
	default THEAD<__> thead() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new THEAD<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a thead element with no attributes and the given head.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-thead-element">4.9.6 The thead element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ thead__(IOConsumerE<? super THEAD__<__>, Ex> thead) throws IOException, Ex {
		return thead().__(thead);
	}

	@Override
	default THEAD_c<__> thead_c() throws IOException {
		return thead()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TBODY">
	@Override
	default TBODY<__> tbody() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new TBODY<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a tbody element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tbody-element">4.9.5 The tbody element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ tbody__(IOConsumerE<? super TBODY__<__>, Ex> tbody) throws IOException, Ex {
		return tbody().__(tbody);
	}

	@Override
	default TBODY_c<__> tbody_c() throws IOException {
		return tbody()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Inherited: TR (deprecated to encourage use of <tbody> for SGML/XML consistency)">
	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default TR<__> tr() throws IOException {
		return Union_TBODY_THEAD_TFOOT.super.tr();
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default <Ex extends Throwable> __ tr__(IOConsumerE<? super TR__<__>, Ex> tr) throws IOException, Ex {
		return Union_TBODY_THEAD_TFOOT.super.tr__(tr);
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default TR_c<__> tr_c() throws IOException {
		return Union_TBODY_THEAD_TFOOT.super.tr_c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TFOOT">
	@Override
	default TFOOT<__> tfoot() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new TFOOT<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a tfoot element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ tfoot__(IOConsumerE<? super TFOOT__<__>, Ex> tfoot) throws IOException, Ex {
		return tfoot().__(tfoot);
	}

	@Override
	default TFOOT_c<__> tfoot_c() throws IOException {
		return tfoot()._c();
	}
	// </editor-fold>
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
