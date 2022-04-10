/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021, 2022  AO Industries, Inc.
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

import com.aoapps.lang.io.function.IOConsumerE;
import java.io.IOException;

/**
 * This interface extends all content interfaces and supports all element types.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnyContent<
	__ extends AnyContent<__>
> extends com.aoapps.html.any.AnyContent<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	// Inherited: Union_DATALIST_OPTGROUP<__>
	// Inherited: Union_DIV_DL<__>
	// Inherited: Union_DL_Palpable<__>
	// Inherited: Union_Embedded_Interactive<__>
	// Inherited: Union_Embedded_Palpable_Phrasing<__>
	// Inherited: Union_Interactive_Phrasing<__>
	// Inherited: Union_Metadata_Phrasing<__>
	// Inherited: Union_Palpable_Phrasing<__>
	// Inherited: Union_TBODY_THEAD_TFOOT<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	// Inherited: EmbeddedContent<__>
	// Inherited: FlowContent<__>
	// Inherited: HeadingContent<__>
	// Inherited: InteractiveContent<__>
	ListContent<__>,
	MetadataContent<__>,
	// Inherited: PalpableContent<__>
	// Inherited: PhrasingContent<__>
	// Inherited: ScriptSupportingContent<__>
	// Inherited: SectioningContent<__>
	// Inherited: TextContent<__>

	//
	// Per-element content models:
	//
	COLGROUP_content<__>,
	DATALIST_content<__>,
	DIV_content<__>,
	DL_content<__>,
	HTML_content<__>,
	OBJECT_content<__>,
	// Inherited: OPTGROUP_content<__>
	SELECT_content<__>,
	TABLE_content<__>,
	TR_content<__>

	//
	// Others:
	//
	// Inherited: Whitespace
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="HTML">
	@Override
	@SuppressWarnings("deprecation")
	default HTML<__> html() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new HTML<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a html element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ html__(IOConsumerE<? super HTML__<__>, Ex> html) throws IOException, Ex {
		return html().__(html);
	}

	@Override
	default HTML_c<__> html_c() throws IOException {
		return html()._c();
	}
	// </editor-fold>
	// Inherited: HEAD
	// Inherited: TITLE
	// Inherited: BASE
	// Inherited: LINK
	// Inherited: META
	// Inherited: STYLE
	// Inherited: BODY
	// Inherited: ARTICLE
	// Inherited: SECTION
	// Inherited: NAV
	// Inherited: ASIDE
	// Inherited: H1
	// Inherited: H2
	// Inherited: H3
	// Inherited: H4
	// Inherited: H5
	// Inherited: H6
	// Inherited: HGROUP
	// Inherited: HEADER
	// Inherited: FOOTER
	// Inherited: ADDRESS
	// Inherited: P
	// Inherited: HR
	// Inherited: PRE
	// Inherited: BLOCKQUOTE
	// Inherited: OL
	// Inherited: UL
	// Inherited: MENU
	// Inherited: LI
	// Inherited: DL
	// Inherited: DT
	// Inherited: DD
	// Inherited: FIGURE
	// <editor-fold defaultstate="collapsed" desc="TODO: FIGCAPTION">
	// </editor-fold>
	// Inherited: MAIN
	// Inherited: DIV
	// Inherited: A
	// Inherited: EM
	// Inherited: STRONG
	// Inherited: SMALL
	// Inherited: S
	// Inherited: CITE
	// Inherited: Q
	// Inherited: DFN
	// Inherited: ABBR
	// Inherited: RUBY
	// <editor-fold defaultstate="collapsed" desc="TODO: RT">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: RP">
	// </editor-fold>
	// Inherited: DATA
	// Inherited: TIME
	// Inherited: CODE
	// Inherited: VAR
	// Inherited: SAMP
	// Inherited: KBD
	// Inherited: SUB
	// Inherited: SUP
	// Inherited: I
	// Inherited: B
	// Inherited: U
	// Inherited: MARK
	// Inherited: BDI
	// Inherited: BDO
	// Inherited: SPAN
	// Inherited: BR
	// Inherited: WBR
	// Inherited: INS
	// Inherited: DEL
	// Inherited: PICTURE
	// <editor-fold defaultstate="collapsed" desc="TODO: SOURCE">
	// </editor-fold>
	// Inherited: IMG
	// Inherited: IFRAME
	// Inherited: EMBED
	// Inherited: OBJECT
	// Inherited: PARAM
	// Inherited: VIDEO
	// Inherited: AUDIO
	// <editor-fold defaultstate="collapsed" desc="TODO: TRACK">
	// </editor-fold>
	// Inherited: MAP
	// Inherited: AREA
	// Inherited: MathML math
	// Inherited: SVG svg
	// Inherited: TABLE
	// Inherited: CAPTION
	// Inherited: COLGROUP
	// Inherited: COL
	// Inherited: TBODY
	// Inherited: THEAD
	// Inherited: TFOOT
	// <editor-fold defaultstate="collapsed" desc="Inherited: TR (removed deprecated since context is unknown)">
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("deprecation")
	default TR<__> tr() throws IOException {
		return TABLE_content.super.tr();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	@SuppressWarnings("deprecation")
	default <Ex extends Throwable> __ tr__(IOConsumerE<? super TR__<__>, Ex> tr) throws IOException, Ex {
		return TABLE_content.super.tr__(tr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("deprecation")
	default TR_c<__> tr_c() throws IOException {
		return TABLE_content.super.tr_c();
	}
	// </editor-fold>
	// Inherited: TD
	// Inherited: TH
	// Inherited: FORM
	// Inherited: LABEL
	// Inherited: INPUT
	// Inherited: BUTTON
	// Inherited: SELECT
	// Inherited: DATALIST
	// Inherited: OPTGROUP
	// Inherited: OPTION
	// Inherited: TEXTAREA
	// Inherited: OUTPUT
	// Inherited: PROGRESS
	// Inherited: METER
	// Inherited: FIELDSET
	// <editor-fold defaultstate="collapsed" desc="TODO: LEGEND">
	// </editor-fold>
	// Inherited: DETAILS
	// <editor-fold defaultstate="collapsed" desc="TODO: SUMMARY">
	// </editor-fold>
	// Inherited: DIALOG
	// Inherited: SCRIPT
	// Inherited: NOSCRIPT
	// Inherited: TEMPLATE
	// Inherited: SLOT
	// Inherited: CANVAS
	// Inherited: autonomous custom elements
}
