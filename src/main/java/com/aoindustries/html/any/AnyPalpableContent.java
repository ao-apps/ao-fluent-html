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
package com.aoindustries.html.any;

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#palpable-content">3.2.5.2.8 Palpable content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#palpable_content">Palpable content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnyPalpableContent<
	D  extends AnyDocument<D>,
	__ extends AnyPalpableContent<D, __>
> extends
	//
	// Unions:
	//
	AnyUnion_DL_Palpable<D, __>,
	// Inherited: AnyUnion_Embedded_Interactive<D, __>
	// Inherited: AnyUnion_Embedded_Palpable_Phrasing<D, __>
	// Inherited: AnyUnion_Interactive_Phrasing<D, __>
	AnyUnion_Palpable_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	AnySectioningContent<D, __>,
	AnyHeadingContent<D, __>,
	AnyInteractiveContent<D, __>
	// Inherited: AnyTextContent<D, __> // that is not inter-element whitespace
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// <editor-fold defaultstate="collapsed" desc="ADDRESS">
	/**
	 * Opens a new address element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 */
	@Factory("address")
	AnyADDRESS<D, __, ?, ?, ?> address() throws IOException;

	/**
	 * Creates an address element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("address")
	default <Ex extends Throwable> __ address__(IORunnableE<Ex> address) throws IOException, Ex {
		return address().__(address);
	}

	/**
	 * Creates an address element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("address")
	default <Ex extends Throwable> __ address__any(IOConsumerE<? super AnyADDRESS__<D, __, ? extends AnyADDRESS__<D, __, ?>>, Ex> address) throws IOException, Ex {
		return address().__(address);
	}

	/**
	 * Creates an address element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("address")
	default __ address__(Object text) throws IOException {
		return address().__(text);
	}

	/**
	 * Creates an empty address element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("address")
	default __ address__() throws IOException {
		return address().__();
	}

	/**
	 * Creates an address element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("address")
	AnyADDRESS_c<D, __, ?> address_c() throws IOException;
	// </editor-fold>
	// Inherited: ARTICLE
	// Inherited: ASIDE
	// Inherited: AUDIO - if the controls attribute is present
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// <editor-fold defaultstate="collapsed" desc="BLOCKQUOTE">
	/**
	 * Opens a new blockquote element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * </ul>
	 */
	@Factory("blockquote")
	AnyBLOCKQUOTE<D, __, ?, ?, ?> blockquote() throws IOException;

	/**
	 * Creates a blockquote element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("blockquote")
	default <Ex extends Throwable> __ blockquote__(IORunnableE<Ex> blockquote) throws IOException, Ex {
		return blockquote().__(blockquote);
	}

	/**
	 * Creates a blockquote element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("blockquote")
	default <Ex extends Throwable> __ blockquote__any(IOConsumerE<? super AnyBLOCKQUOTE__<D, __, ? extends AnyBLOCKQUOTE__<D, __, ?>>, Ex> blockquote) throws IOException, Ex {
		return blockquote().__(blockquote);
	}

	/**
	 * Creates a blockquote element with no attributes and a text body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("blockquote")
	default __ blockquote__(Object text) throws IOException {
		return blockquote().__(text);
	}

	/**
	 * Creates an empty blockquote element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("blockquote")
	default __ blockquote__() throws IOException {
		return blockquote().__();
	}

	/**
	 * Creates a blockquote element with no attributes then begins element content
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * </ul>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("blockquote")
	AnyBLOCKQUOTE_c<D, __, ?> blockquote_c() throws IOException;
	// </editor-fold>
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// Inherited: DETAILS
	// Inherited: DFN
	// Inherited: DIV
	// <editor-fold defaultstate="collapsed" desc="DL - if childen contain at least one name/value pair">
	/**
	 * Opens a new dl element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dl-element">4.4.9 The dl element</a>.
	 * </p>
	 */
	@Factory("dl")
	AnyDL<D, __, ?, ?, ?> dl() throws IOException;

	/**
	 * Creates a dl element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dl-element">4.4.9 The dl element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dl")
	default <Ex extends Throwable> __ dl__(IORunnableE<Ex> dl) throws IOException, Ex {
		return dl().__(dl);
	}

	/**
	 * Creates a dl element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dl-element">4.4.9 The dl element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dl")
	default <Ex extends Throwable> __ dl__any(IOConsumerE<? super AnyDL__<D, __, ? extends AnyDL__<D, __, ?>>, Ex> dl) throws IOException, Ex {
		return dl().__(dl);
	}

	/**
	 * Creates an empty dl element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dl-element">4.4.9 The dl element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dl")
	default __ dl__() throws IOException {
		return dl().__();
	}

	/**
	 * Creates a dl element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dl-element">4.4.9 The dl element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("dl")
	AnyDL_c<D, __, ?> dl_c() throws IOException;
	// </editor-fold>
	// Inherited: EM
	// Inherited: EMBED
	// <editor-fold defaultstate="collapsed" desc="FIELDSET">
	/**
	 * Opens a new fieldset element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-fieldset-element">4.10.15 The fieldset element</a>.
	 * </p>
	 */
	@Factory("fieldset")
	default void fieldset() throws IOException {
		throw new AssertionError("TODO: Implement fieldset");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="FIGURE">
	/**
	 * Opens a new figure element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-figure-element">4.4.12 The figure element</a>.
	 * </p>
	 */
	@Factory("figure")
	default void figure() throws IOException {
		throw new AssertionError("TODO: Implement figure");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="FOOTER">
	/**
	 * Opens a new footer element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.
	 * </p>
	 */
	@Factory("footer")
	AnyFOOTER<D, __, ?, ?, ?> footer() throws IOException;

	/**
	 * Creates a footer element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("footer")
	default <Ex extends Throwable> __ footer__(IORunnableE<Ex> footer) throws IOException, Ex {
		return footer().__(footer);
	}

	/**
	 * Creates a footer element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("footer")
	default <Ex extends Throwable> __ footer__any(IOConsumerE<? super AnyFOOTER__<D, __, ? extends AnyFOOTER__<D, __, ?>>, Ex> footer) throws IOException, Ex {
		return footer().__(footer);
	}

	/**
	 * Creates a footer element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("footer")
	default __ footer__(Object text) throws IOException {
		return footer().__(text);
	}

	/**
	 * Creates an empty footer element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("footer")
	default __ footer__() throws IOException {
		return footer().__();
	}

	/**
	 * Creates a footer element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("footer")
	AnyFOOTER_c<D, __, ?> footer_c() throws IOException;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="FORM">
	/**
	 * Opens a new form element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 */
	@Factory("form")
	AnyFORM<D, __, ?, ?, ?> form() throws IOException;

	/**
	 * Opens a new form element with the given action attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 */
	@Factory("form")
	AnyFORM<D, __, ?, ?, ?> form(String action) throws IOException;

	/**
	 * Opens a new form element with the given action attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Factory("form")
	<Ex extends Throwable> AnyFORM<D, __, ?, ?, ?> form(IOSupplierE<? extends String, Ex> action) throws IOException, Ex;

	/**
	 * Creates a form element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("form")
	default <Ex extends Throwable> __ form__(IORunnableE<Ex> form) throws IOException, Ex {
		return form().__(form);
	}

	/**
	 * Creates a form element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("form")
	default <Ex extends Throwable> __ form__any(IOConsumerE<? super AnyFORM__<D, __, ? extends AnyFORM__<D, __, ?>>, Ex> form) throws IOException, Ex {
		return form().__(form);
	}

	/**
	 * Creates a form element with no attributes and a text body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("form")
	default __ form__(Object text) throws IOException {
		return form().__(text);
	}

	/**
	 * Creates an empty form element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("form")
	default __ form__() throws IOException {
		return form().__();
	}

	/**
	 * Creates a form element with no attributes then begins element content
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;</a>.</li>
	 * </ul>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("form")
	AnyFORM_c<D, __, ?> form_c() throws IOException;
	// </editor-fold>
	// Inherited: H1
	// Inherited: H2
	// Inherited: H3
	// Inherited: H4
	// Inherited: H5
	// Inherited: H6
	// <editor-fold defaultstate="collapsed" desc="HEADER">
	/**
	 * Opens a new header element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.
	 * </p>
	 */
	@Factory("header")
	AnyHEADER<D, __, ?, ?, ?> header() throws IOException;

	/**
	 * Creates a header element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("header")
	default <Ex extends Throwable> __ header__(IORunnableE<Ex> header) throws IOException, Ex {
		return header().__(header);
	}

	/**
	 * Creates a header element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("header")
	default <Ex extends Throwable> __ header__any(IOConsumerE<? super AnyHEADER__<D, __, ? extends AnyHEADER__<D, __, ?>>, Ex> header) throws IOException, Ex {
		return header().__(header);
	}

	/**
	 * Creates a header element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("header")
	default __ header__(Object text) throws IOException {
		return header().__(text);
	}

	/**
	 * Creates an empty header element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("header")
	default __ header__() throws IOException {
		return header().__();
	}

	/**
	 * Creates a header element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("header")
	AnyHEADER_c<D, __, ?> header_c() throws IOException;
	// </editor-fold>
	// Inherited: HGROUP
	// Inherited: I
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: INPUT - if type attribute is not in the hidden state
	// Inherited: INS
	// Inherited: KBD
	// Inherited: LABEL
	// <editor-fold defaultstate="collapsed" desc="MAIN">
	/**
	 * Opens a new main element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
	 * </p>
	 */
	@Factory("main")
	AnyMAIN<D, __, ?, ?, ?> main() throws IOException;

	/**
	 * Creates a main element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("main")
	default <Ex extends Throwable> __ main__(IORunnableE<Ex> main) throws IOException, Ex {
		return main().__(main);
	}

	/**
	 * Creates a main element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("main")
	default <Ex extends Throwable> __ main__any(IOConsumerE<? super AnyMAIN__<D, __, ? extends AnyMAIN__<D, __, ?>>, Ex> main) throws IOException, Ex {
		return main().__(main);
	}

	/**
	 * Creates a main element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("main")
	default __ main__(Object text) throws IOException {
		return main().__(text);
	}

	/**
	 * Creates an empty main element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("main")
	default __ main__() throws IOException {
		return main().__();
	}

	/**
	 * Creates a main element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("main")
	AnyMAIN_c<D, __, ?> main_c() throws IOException;
	// </editor-fold>
	// Inherited: MAP
	// Inherited: MARK
	// Inherited: MathML math
	// Inherited: MENU - if children include at least one li
	// Inherited: METER
	// Inherited: NAV
	// Inherited: OBJECT
	// <editor-fold defaultstate="collapsed" desc="OL - if children include at least one li">
	/**
	 * Opens a new ol element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ol-element">4.4.5 The ol element</a>.
	 * </p>
	 */
	@Factory("ol")
	AnyOL<D, __, ?, ?, ?> ol() throws IOException;

	/**
	 * Creates an ol element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ol-element">4.4.5 The ol element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ol")
	default <Ex extends Throwable> __ ol__(IORunnableE<Ex> ol) throws IOException, Ex {
		return ol().__(ol);
	}

	/**
	 * Creates an ol element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ol-element">4.4.5 The ol element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ol")
	default <Ex extends Throwable> __ ol__any(IOConsumerE<? super AnyOL__<D, __, ? extends AnyOL__<D, __, ?>>, Ex> ol) throws IOException, Ex {
		return ol().__(ol);
	}

	/**
	 * Creates an empty ol element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ol-element">4.4.5 The ol element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ol")
	default __ ol__() throws IOException {
		return ol().__();
	}

	/**
	 * Creates an ol element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ol-element">4.4.5 The ol element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("ol")
	AnyOL_c<D, __, ?> ol_c() throws IOException;
	// </editor-fold>
	// Inherited: OUTPUT
	// <editor-fold defaultstate="collapsed" desc="P">
	/**
	 * Opens a new p element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.
	 * </p>
	 */
	@Factory("p")
	AnyP<D, __, ?, ?, ?> p() throws IOException;

	/**
	 * Creates a p element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("p")
	default <Ex extends Throwable> __ p__(IORunnableE<Ex> p) throws IOException, Ex {
		return p().__(p);
	}

	/**
	 * Creates a p element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("p")
	default <Ex extends Throwable> __ p__any(IOConsumerE<? super AnyP__<D, __, ? extends AnyP__<D, __, ?>>, Ex> p) throws IOException, Ex {
		return p().__(p);
	}

	/**
	 * Creates a p element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("p")
	default __ p__(Object text) throws IOException {
		return p().__(text);
	}

	/**
	 * Creates an empty p element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("p")
	default __ p__() throws IOException {
		return p().__();
	}

	/**
	 * Creates a p element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("p")
	AnyP_c<D, __, ?> p_c() throws IOException;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="PRE">
	/**
	 * Opens a new pre element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
	 * </p>
	 */
	@Factory("pre")
	AnyPRE<D, __, ?, ?, ?> pre() throws IOException;

	/**
	 * Creates a pre element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("pre")
	default <Ex extends Throwable> __ pre__(IORunnableE<Ex> pre) throws IOException, Ex {
		return pre().__(pre);
	}

	/**
	 * Creates a pre element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("pre")
	default <Ex extends Throwable> __ pre__any(IOConsumerE<? super AnyPRE__<D, __, ? extends AnyPRE__<D, __, ?>>, Ex> pre) throws IOException, Ex {
		return pre().__(pre);
	}

	/**
	 * Creates a pre element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("pre")
	default __ pre__(Object text) throws IOException {
		return pre().__(text);
	}

	/**
	 * Creates an empty pre element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("pre")
	default __ pre__() throws IOException {
		return pre().__();
	}

	/**
	 * Creates a pre element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("pre")
	AnyPRE_c<D, __, ?> pre_c() throws IOException;
	// </editor-fold>
	// Inherited: PROGRESS
	// Inherited: Q
	// Inherited: RUBY
	// Inherited: S
	// Inherited: SAMP
	// Inherited: SECTION
	// Inherited: SELECT
	// Inherited: SMALL
	// Inherited: SPAN
	// Inherited: STRONG
	// Inherited: SUB
	// Inherited: SUP
	// Inherited: SVG svg
	// <editor-fold defaultstate="collapsed" desc="TABLE">
	/**
	 * Opens a new table element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
	 * </ul>
	 */
	@Factory("table")
	AnyTABLE<D, __, ?, ?, ?> table() throws IOException;

	/**
	 * Creates a table element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("table")
	default <Ex extends Throwable> __ table__(IORunnableE<Ex> table) throws IOException, Ex {
		return table().__(table);
	}

	/**
	 * Creates a table element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("table")
	default <Ex extends Throwable> __ table__any(IOConsumerE<? super AnyTABLE__<D, __, ? extends AnyTABLE__<D, __, ?>>, Ex> table) throws IOException, Ex {
		return table().__(table);
	}

	/**
	 * Creates an empty table element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("table")
	default __ table__() throws IOException {
		return table().__();
	}

	/**
	 * Creates a table element with no attributes then begins element content
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-table-element">4.9.1 The table element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table">&lt;table&gt;: The Table element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_table.asp">HTML table tag</a>.</li>
	 * </ul>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("table")
	AnyTABLE_c<D, __, ?> table_c() throws IOException;
	// </editor-fold>
	// Inherited: TEXTAREA
	// Inherited: TIME
	// Inherited: U
	// <editor-fold defaultstate="collapsed" desc="UL - if children include at least one li">
	/**
	 * Opens a new ul element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 */
	@Factory("ul")
	AnyUL<D, __, ?, ?, ?> ul() throws IOException;

	/**
	 * Creates a ul element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ul")
	default <Ex extends Throwable> __ ul__(IORunnableE<Ex> ul) throws IOException, Ex {
		return ul().__(ul);
	}

	/**
	 * Creates a ul element with no attributes and the given foot.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ul")
	default <Ex extends Throwable> __ ul__any(IOConsumerE<? super AnyUL__<D, __, ? extends AnyUL__<D, __, ?>>, Ex> ul) throws IOException, Ex {
		return ul().__(ul);
	}

	/**
	 * Creates an empty ul element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ul")
	default __ ul__() throws IOException {
		return ul().__();
	}

	/**
	 * Creates a ul element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ul-element">4.4.6 The ul element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("ul")
	AnyUL_c<D, __, ?> ul_c() throws IOException;
	// </editor-fold>
	// Inherited: VAR
	// Inherited: VIDEO
	// Inherited: autonomous custom elements
}
