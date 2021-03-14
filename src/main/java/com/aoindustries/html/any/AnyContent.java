/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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
import java.io.IOException;

/**
 * This interface extends all content interfaces and supports all element types.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnyContent<
	D  extends AnyDocument<D>,
	__ extends AnyContent<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: AnyUnion_COLGROUP_ScriptSupporting<D, __>
	// Inherited: AnyUnion_DATALIST_OPTGROUP<D, __>
	// Inherited: AnyUnion_DIV_DL<D, __>
	// Inherited: AnyUnion_DL_Palpable<D, __>
	// Inherited: AnyUnion_Embedded_Interactive<D, __>
	// Inherited: AnyUnion_Embedded_Palpable_Phrasing<D, __>
	// Inherited: AnyUnion_Interactive_Phrasing<D, __>
	// Inherited: AnyUnion_Metadata_Phrasing<D, __>
	// Inherited: AnyUnion_Palpable_Phrasing<D, __>
	// Inherited: AnyUnion_TBODY_THEAD_TFOOT<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	// Inherited: AnyEmbeddedContent<D, __>
	// Inherited: AnyFlowContent<D, __>
	// Inherited: AnyHeadingContent<D, __>
	// Inherited: AnyInteractiveContent<D, __>
	AnyListContent<D, __>,
	AnyMetadataContent<D, __>,
	// Inherited: AnyPalpableContent<D, __>
	// Inherited: AnyPhrasingContent<D, __>
	// Inherited: AnyScriptSupportingContent<D, __>
	// Inherited: AnySectioningContent<D, __>
	// Inherited: AnyTextContent<D, __>

	//
	// Per-element content models:
	//
	AnyCOLGROUP_content<D, __>,
	AnyDATALIST_content<D, __>,
	AnyDIV_content<D, __>,
	AnyDL_content<D, __>,
	AnyHTML_content<D, __>,
	// Inherited: AnyOPTGROUP_content<D, __>
	AnySELECT_content<D, __>,
	AnyTABLE_content<D, __>,
	AnyTR_content<D, __>

	//
	// Others:
	//
	// Inherited: WhitespaceWriter<D>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="HTML">
	/**
	 * Opens a new html element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 */
	@Factory("html")
	AnyHTML<D, __, ?, ?, ?> html() throws IOException;

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
	@Factory("html")
	default <Ex extends Throwable> __ html__(IORunnableE<Ex> html) throws IOException, Ex {
		return html().__(html);
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
	@Factory("html")
	default <Ex extends Throwable> __ html__any(IOConsumerE<? super AnyHTML__<D, __, ? extends AnyHTML__<D, __, ?>>, Ex> html) throws IOException, Ex {
		return html().__(html);
	}

	/**
	 * Creates an empty html element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("html")
	default __ html__() throws IOException {
		return html().__();
	}

	/**
	 * Creates a html element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("html")
	AnyHTML_c<D, __, ?> html_c() throws IOException;
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
	// <editor-fold defaultstate="collapsed" desc="FIGCAPTION">
	/**
	 * Opens a new figcaption element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-figcaption-element">4.4.13 The figcaption element</a>.
	 * </p>
	 */
	@Factory("figcaption")
	default void figcaption() throws IOException {
		throw new AssertionError("TODO: Implement figcaption");
	}
	// TODO: __ extends AnyFigcaptionContent<D, __> (where AnyFigcaptionContent extends AnyFlowContent + AnyFigcaption)
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
	// <editor-fold defaultstate="collapsed" desc="RT">
	/**
	 * Opens a new rt element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-rt-element">4.5.11 The rt element</a>.
	 * </p>
	 */
	@Factory("rt")
	default void rt() throws IOException {
		throw new AssertionError("TODO: Implement rt");
	}
	// TODO: __ extends RUBY_content<D, __>
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="RP">
	/**
	 * Opens a new rp element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-rp-element">4.5.12 The rp element</a>.
	 * </p>
	 */
	@Factory("rp")
	default void rp() throws IOException {
		throw new AssertionError("TODO: Implement rp");
	}
	// TODO: __ extends RUBY_content<D, __>
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
	// <editor-fold defaultstate="collapsed" desc="SOURCE">
	/**
	 * Opens a new source element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/embedded-content.html#the-source-element">4.8.2 The source element</a>.
	 * </p>
	 */
	@Factory("source")
	default void source() throws IOException {
		throw new AssertionError("TODO: Implement source");
	}
	// TODO: __ extends TODO<D, __>
	// </editor-fold>
	// Inherited: IMG
	// Inherited: IFRAME
	// Inherited: EMBED
	// Inherited: OBJECT
	// <editor-fold defaultstate="collapsed" desc="PARAM">
	// TODO: __ extends AnyObjectContent<D, __>
	/**
	 * Opens a new param element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-param-element">4.8.8 The param element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
	 * </ul>
	 */
	@Factory("param")
	AnyPARAM<D, __, ?> param() throws IOException;

	/**
	 * Creates a param element with the given name and value.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-param-element">4.8.8 The param element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("param")
	default __ param__(Object name, Object value) throws IOException {
		return param().name(name).value(value).__();
	}
	// TODO: More types like supported by ao-taglib (ParamsTag.java), including collection types, as "params__"?
	// </editor-fold>
	// Inherited: VIDEO
	// Inherited: AUDIO
	// <editor-fold defaultstate="collapsed" desc="TRACK">
	/**
	 * Opens a new track element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/media.html#the-track-element">4.8.11 The track element</a>.
	 * </p>
	 */
	@Factory("track")
	default void track() throws IOException {
		throw new AssertionError("TODO: Implement track");
	}
	// TODO: __ extends MediaContent<D, __>
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
	// Inherited: TR
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
	// <editor-fold defaultstate="collapsed" desc="LEGEND">
	/**
	 * Opens a new legend element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-legend-element">4.10.16 The legend element</a>.
	 * </p>
	 */
	@Factory("legend")
	default void legend() throws IOException {
		throw new AssertionError("TODO: Implement legend");
	}
	// TODO: __ extends FIELDSET_content<D, __>
	// </editor-fold>
	// Inherited: DETAILS
	// <editor-fold defaultstate="collapsed" desc="SUMMARY">
	/**
	 * Opens a new summary element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/interactive-elements.html#the-summary-element">4.11.2 The summary element</a>.
	 * </p>
	 */
	@Factory("summary")
	default void summary() throws IOException {
		throw new AssertionError("TODO: Implement summary");
	}
	// TODO: __ extends DETAILS_content<D, __>
	// </editor-fold>
	// Inherited: DIALOG
	// Inherited: SCRIPT
	// Inherited: NOSCRIPT
	// Inherited: TEMPLATE
	// Inherited: SLOT
	// Inherited: CANVAS
	// Inherited: autonomous custom elements
}
