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
import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface TABLE_content<
	D  extends AnyDocument<D>,
	__ extends TABLE_content<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<D, __>
	Union_TBODY_THEAD_TFOOT<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	// Inherited: ScriptSupportingContent<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="CAPTION">
	/**
	 * Opens a new caption element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 */
	@Factory("caption")
	default CAPTION<D, __> caption() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new CAPTION<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a caption element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("caption")
	default <Ex extends Throwable> __ caption__(IORunnableE<Ex> caption) throws IOException, Ex {
		return caption().__(caption);
	}

	/**
	 * Creates a caption element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("caption")
	default <Ex extends Throwable> __ caption__(IOConsumerE<? super CAPTION__<D, __>, Ex> caption) throws IOException, Ex {
		return caption().__(caption);
	}

	/**
	 * Creates a caption element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("caption")
	default __ caption__(Object text) throws IOException {
		return caption().__(text);
	}

	/**
	 * Creates an empty caption element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("caption")
	default __ caption__() throws IOException {
		return caption().__();
	}

	/**
	 * Creates a caption element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("caption")
	default CAPTION_c<D, __> caption_c() throws IOException {
		return caption()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="COLGROUP">
	/**
	 * Opens a new colgroup element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-colgroup-element">4.9.3 The colgroup element</a>.
	 * </p>
	 */
	@Factory("colgroup")
	default COLGROUP<D, __> colgroup() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new COLGROUP<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a colgroup element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-colgroup-element">4.9.3 The colgroup element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("colgroup")
	default <Ex extends Throwable> __ colgroup__(IORunnableE<Ex> colgroup) throws IOException, Ex {
		return colgroup().__(colgroup);
	}

	/**
	 * Creates a colgroup element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-colgroup-element">4.9.3 The colgroup element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("colgroup")
	default <Ex extends Throwable> __ colgroup__(IOConsumerE<? super COLGROUP__<D, __>, Ex> colgroup) throws IOException, Ex {
		return colgroup().__(colgroup);
	}

	/**
	 * Creates an empty colgroup element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-colgroup-element">4.9.3 The colgroup element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("colgroup")
	default __ colgroup__() throws IOException {
		return colgroup().__();
	}

	/**
	 * Creates a colgroup element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-colgroup-element">4.9.3 The colgroup element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("colgroup")
	default COLGROUP_c<D, __> colgroup_c() throws IOException {
		return colgroup()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="THEAD">
	/**
	 * Opens a new thead element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-thead-element">4.9.6 The thead element</a>.
	 * </p>
	 */
	@Factory("thead")
	default THEAD<D, __> thead() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
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
	@Factory("thead")
	default <Ex extends Throwable> __ thead__(IORunnableE<Ex> thead) throws IOException, Ex {
		return thead().__(thead);
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
	@Factory("thead")
	default <Ex extends Throwable> __ thead__(IOConsumerE<? super THEAD__<D, __>, Ex> thead) throws IOException, Ex {
		return thead().__(thead);
	}

	/**
	 * Creates an empty thead element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-thead-element">4.9.6 The thead element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("thead")
	default __ thead__() throws IOException {
		return thead().__();
	}

	/**
	 * Creates a thead element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-thead-element">4.9.6 The thead element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("thead")
	default THEAD_c<D, __> thead_c() throws IOException {
		return thead()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TBODY">
	/**
	 * Opens a new tbody element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tbody-element">4.9.5 The tbody element</a>.
	 * </p>
	 */
	@Factory("tbody")
	default TBODY<D, __> tbody() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
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
	@Factory("tbody")
	default <Ex extends Throwable> __ tbody__(IORunnableE<Ex> tbody) throws IOException, Ex {
		return tbody().__(tbody);
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
	@Factory("tbody")
	default <Ex extends Throwable> __ tbody__(IOConsumerE<? super TBODY__<D, __>, Ex> tbody) throws IOException, Ex {
		return tbody().__(tbody);
	}

	/**
	 * Creates an empty tbody element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tbody-element">4.9.5 The tbody element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tbody")
	default __ tbody__() throws IOException {
		return tbody().__();
	}

	/**
	 * Creates a tbody element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tbody-element">4.9.5 The tbody element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("tbody")
	default TBODY_c<D, __> tbody_c() throws IOException {
		return tbody()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Inherited: TR (deprecated to encourage use of <tbody> for SGML/XML consistency)">
	// TODO: Create a test to ensure all methods of TBODY_THEAD_TFOOT have been overridden and deprecated, based on a new @OverrideFactory annotation?
	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	//@Factory("tr")
	default TR<D, __> tr() throws IOException {
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
	//@Factory("tr")
	default <Ex extends Throwable> __ tr__(IORunnableE<Ex> tr) throws IOException, Ex {
		return Union_TBODY_THEAD_TFOOT.super.tr__(tr);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	//@Factory("tr")
	default <Ex extends Throwable> __ tr__(IOConsumerE<? super TR__<D, __>, Ex> tr) throws IOException, Ex {
		return Union_TBODY_THEAD_TFOOT.super.tr__(tr);
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	//@Factory("tr")
	default __ tr__() throws IOException {
		return Union_TBODY_THEAD_TFOOT.super.tr__();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TFOOT">
	/**
	 * Opens a new tfoot element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.
	 * </p>
	 */
	@Factory("tfoot")
	default TFOOT<D, __> tfoot() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new TFOOT<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a tfoot element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tfoot")
	default <Ex extends Throwable> __ tfoot__(IORunnableE<Ex> tfoot) throws IOException, Ex {
		return tfoot().__(tfoot);
	}

	/**
	 * Creates a tfoot element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tfoot")
	default <Ex extends Throwable> __ tfoot__(IOConsumerE<? super TFOOT__<D, __>, Ex> tfoot) throws IOException, Ex {
		return tfoot().__(tfoot);
	}

	/**
	 * Creates an empty tfoot element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("tfoot")
	default __ tfoot__() throws IOException {
		return tfoot().__();
	}

	/**
	 * Creates a tfoot element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-tfoot-element">4.9.7 The tfoot element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("tfoot")
	default TFOOT_c<D, __> tfoot_c() throws IOException {
		return tfoot()._c();
	}
	// </editor-fold>
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
