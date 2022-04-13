/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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

import com.aoapps.html.any.AnyPalpableContent;
import com.aoapps.lang.io.function.IOConsumerE;
import com.aoapps.lang.io.function.IOSupplierE;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#palpable-content">3.2.5.2.8 Palpable content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#palpable_content">Palpable content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PalpableContent<
	__ extends PalpableContent<__>
> extends AnyPalpableContent<Document, __>,
	//
	// Unions:
	//
	Union_DL_Palpable<__>,
	// Inherited: Union_Embedded_Interactive<__>
	// Inherited: Union_Embedded_Palpable_Phrasing<__>
	// Inherited: Union_Interactive_Phrasing<__>
	Union_Palpable_Phrasing<__>,

	//
	// Content models:
	//
	// Inherited: Content<__>
	SectioningContent<__>,
	HeadingContent<__>,
	InteractiveContent<__>
	// Inherited: TextContent<__> // that is not inter-element whitespace
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// <editor-fold defaultstate="collapsed" desc="ADDRESS">
	@Override
	@SuppressWarnings("deprecation")
	default ADDRESS<__> address() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new ADDRESS<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates an address element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-address-element">4.3.10 The address element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/address">&lt;address&gt;: The Contact Address element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_address.asp">HTML address tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ address__(IOConsumerE<? super ADDRESS__<__>, Ex> address) throws IOException, Ex {
		return address().__(address);
	}

	@Override
	default ADDRESS_c<__> address_c() throws IOException {
		return address()._c();
	}
	// </editor-fold>
	// Inherited: ARTICLE
	// Inherited: ASIDE
	// Inherited: AUDIO - if the controls attribute is present
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// <editor-fold defaultstate="collapsed" desc="BLOCKQUOTE">
	@Override
	@SuppressWarnings("deprecation")
	default BLOCKQUOTE<__> blockquote() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new BLOCKQUOTE<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a blockquote element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_blockquote.asp">HTML blockquote tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ blockquote__(IOConsumerE<? super BLOCKQUOTE__<__>, Ex> blockquote) throws IOException, Ex {
		return blockquote().__(blockquote);
	}

	@Override
	default BLOCKQUOTE_c<__> blockquote_c() throws IOException {
		return blockquote()._c();
	}
	// </editor-fold>
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// Inherited: DETAILS
	// Inherited: DFN
	// Inherited: DIV
	// <editor-fold defaultstate="collapsed" desc="DL - if children contain at least one name/value pair">
	@Override
	@SuppressWarnings("deprecation")
	default DL<__> dl() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new DL<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a dl element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-dl-element">4.4.9 The dl element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dl">&lt;dl&gt;: The Description List element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_dl.asp">HTML dl tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ dl__(IOConsumerE<? super DL__<__>, Ex> dl) throws IOException, Ex {
		return dl().__(dl);
	}

	@Override
	default DL_c<__> dl_c() throws IOException {
		return dl()._c();
	}
	// </editor-fold>
	// Inherited: EM
	// Inherited: EMBED
	// <editor-fold defaultstate="collapsed" desc="TODO: FIELDSET">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: FIGURE">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="FOOTER">
	@Override
	@SuppressWarnings("deprecation")
	default FOOTER<__> footer() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new FOOTER<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a footer element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-footer-element">4.3.9 The footer element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/footer">&lt;footer&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_footer.asp">HTML footer Tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 *
	 * @since HTML 5
	 */
	default <Ex extends Throwable> __ footer__(IOConsumerE<? super FOOTER__<__>, Ex> footer) throws IOException, Ex {
		return footer().__(footer);
	}

	@Override
	default FOOTER_c<__> footer_c() throws IOException {
		return footer()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="FORM">
	@Override
	@SuppressWarnings("deprecation")
	default FORM<__> form() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new FORM<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	@Override
	default FORM<__> form(String action) throws IOException {
		return form().action(action);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> FORM<__> form(IOSupplierE<? extends String, Ex> action) throws IOException, Ex {
		return form().action(action);
	}

	/**
	 * Creates a form element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-form-element">4.10.3 The form element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form">&lt;form&gt;: The Form element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_form.asp">HTML form tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ form__(IOConsumerE<? super FORM__<__>, Ex> form) throws IOException, Ex {
		return form().__(form);
	}

	@Override
	default FORM_c<__> form_c() throws IOException {
		return form()._c();
	}
	// </editor-fold>
	// Inherited: H1
	// Inherited: H2
	// Inherited: H3
	// Inherited: H4
	// Inherited: H5
	// Inherited: H6
	// <editor-fold defaultstate="collapsed" desc="HEADER">
	@Override
	@SuppressWarnings("deprecation")
	default HEADER<__> header() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new HEADER<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a header element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-header-element">4.3.8 The header element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/header">&lt;header&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_header.asp">HTML header Tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 *
	 * @since HTML 5
	 */
	default <Ex extends Throwable> __ header__(IOConsumerE<? super HEADER__<__>, Ex> header) throws IOException, Ex {
		return header().__(header);
	}

	@Override
	default HEADER_c<__> header_c() throws IOException {
		return header()._c();
	}
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
	@Override
	@SuppressWarnings("deprecation")
	default MAIN<__> main() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new MAIN<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a main element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-main-element">4.4.14 The main element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/main">&lt;main&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_main.asp">HTML main Tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 *
	 * @since HTML 5
	 */
	default <Ex extends Throwable> __ main__(IOConsumerE<? super MAIN__<__>, Ex> main) throws IOException, Ex {
		return main().__(main);
	}

	@Override
	default MAIN_c<__> main_c() throws IOException {
		return main()._c();
	}
	// </editor-fold>
	// Inherited: MAP
	// Inherited: MARK
	// Inherited: MathML math
	// Inherited: MENU - if children include at least one li
	// Inherited: METER
	// Inherited: NAV
	// Inherited: OBJECT
	// <editor-fold defaultstate="collapsed" desc="OL - if children include at least one li">
	@Override
	@SuppressWarnings("deprecation")
	default OL<__> ol() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new OL<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates an ol element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ol-element">4.4.5 The ol element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/ol">&lt;ol&gt;: The Ordered List element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_ol.asp">HTML ol tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ ol__(IOConsumerE<? super OL__<__>, Ex> ol) throws IOException, Ex {
		return ol().__(ol);
	}

	@Override
	default OL_c<__> ol_c() throws IOException {
		return ol()._c();
	}
	// </editor-fold>
	// Inherited: OUTPUT
	// <editor-fold defaultstate="collapsed" desc="P">
	@Override
	@SuppressWarnings("deprecation")
	default P<__> p() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new P<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a p element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-p-element">4.4.1 The p element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/p">&lt;p&gt;: The Paragraph element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_p.asp">HTML p tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ p__(IOConsumerE<? super P__<__>, Ex> p) throws IOException, Ex {
		return p().__(p);
	}

	@Override
	default P_c<__> p_c() throws IOException {
		return p()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="PRE">
	@Override
	@SuppressWarnings("deprecation")
	default PRE<__> pre() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new PRE<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a pre element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/pre">&lt;pre&gt;: The Preformatted Text element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_pre.asp">HTML pre tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ pre__(IOConsumerE<? super PRE__<__>, Ex> pre) throws IOException, Ex {
		return pre().__(pre);
	}

	@Override
	default PRE_c<__> pre_c() throws IOException {
		return pre()._c();
	}
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
	@Override
	@SuppressWarnings("deprecation")
	default TABLE<__> table() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new TABLE<>(document, pc).writeOpen(document.getRawUnsafe(null));
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
	default <Ex extends Throwable> __ table__(IOConsumerE<? super TABLE__<__>, Ex> table) throws IOException, Ex {
		return table().__(table);
	}

	@Override
	default TABLE_c<__> table_c() throws IOException {
		return table()._c();
	}
	// </editor-fold>
	// Inherited: TEXTAREA
	// Inherited: TIME
	// Inherited: U
	// <editor-fold defaultstate="collapsed" desc="UL - if children include at least one li">
	@Override
	@SuppressWarnings("deprecation")
	default UL<__> ul() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new UL<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a ul element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-ul-element">4.4.6 The ul element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/ul">&lt;ul&gt;: The Unordered List element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_ul.asp">HTML ul tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ ul__(IOConsumerE<? super UL__<__>, Ex> ul) throws IOException, Ex {
		return ul().__(ul);
	}

	@Override
	default UL_c<__> ul_c() throws IOException {
		return ul()._c();
	}
	// </editor-fold>
	// Inherited: VAR
	// Inherited: VIDEO
	// Inherited: autonomous custom elements
}
