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

import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#flow-content">3.2.5.2.2 Flow content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#flow_content">Flow content</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface FlowContent<
	D  extends AnyDocument<D>,
	__ extends FlowContent<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<D, __>
	// Inherited: Union_DL_Palpable<D, __>
	// Inherited: Union_Embedded_Interactive<D, __>
	// Inherited: Union_Embedded_Palpable_Phrasing<D, __>
	// Inherited: Union_Interactive_Phrasing<D, __>
	// Inherited: Union_Metadata_Phrasing<D, __>
	// Inherited: Union_Palpable_Phrasing<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	// Inherited: EmbeddedContent<D, __>
	// Inherited: HeadingContent<D, __>
	// Inherited: InteractiveContent<D, __>
	PalpableContent<D, __>,
	PhrasingContent<D, __>
	// Inherited: ScriptSupportingContent<D, __>
	// Inherited: SectioningContent<D, __>
	// Inherited: TextContent<D, __>
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// Inherited: ADDRESS
	// Inherited: AREA - if a descendent of map
	// Inherited: ARTICLE
	// Inherited: ASIDE
	// Inherited: AUDIO
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// Inherited: BLOCKQUOTE
	// Inherited: BR
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// Inherited: DATALIST
	// Inherited: DEL
	// Inherited: DETAILS
	// Inherited: DFN
	// <editor-fold defaultstate="collapsed" desc="DIALOG">
	/**
	 * Opens a new dialog element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/interactive-elements.html#the-dialog-element">4.11.4 The dialog element</a>.
	 * </p>
	 */
	@Factory("dialog")
	default void dialog() throws IOException {
		throw new AssertionError("TODO: Implement dialog");
	}
	// </editor-fold>
	// Inherited: DIV
	// Inherited: DL
	// Inherited: EM
	// Inherited: EMBED
	// Inherited: FIELDSET
	// Inherited: FIGURE
	// Inherited: FOOTER
	// Inherited: FORM
	// Inherited: H1
	// Inherited: H2
	// Inherited: H3
	// Inherited: H4
	// Inherited: H5
	// Inherited: H6
	// Inherited: HEADER
	// Inherited: HGROUP
	// <editor-fold defaultstate="collapsed" desc="HR">
	/**
	 * Opens a new hr element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-hr-element">4.4.2 The hr element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
	 * </ul>
	 */
	@Factory("hr")
	default HR<D, __> hr() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new HR<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an empty hr element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-hr-element">4.4.2 The hr element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("hr")
	default __ hr__() throws IOException {
		return hr().__();
	}
	// </editor-fold>
	// Inherited: I
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: INPUT
	// Inherited: INS
	// Inherited: KBD
	// Inherited: LABEL
	// Inherited: LINK - if it is allowed in body
	// Inherited: MAIN - if it is a hierarchically correct main element
	// Inherited: MAP
	// Inherited: MARK
	// Inherited: MathML math
	// Inherited: MENU
	// Inherited: META - if the itemprop attribute is present
	// Inherited: METER
	// Inherited: NAV
	// Inherited: NOSCRIPT
	// Inherited: OBJECT
	// Inherited: OL
	// Inherited: OUTPUT
	// Inherited: P
	// Inherited: PICTURE
	// Inherited: PRE
	// Inherited: PROGRESS
	// Inherited: Q
	// Inherited: RUBY
	// Inherited: S
	// Inherited: SAMP
	// Inherited: SCRIPT
	// Inherited: SECTION
	// Inherited: SELECT
	// Inherited: SLOT
	// Inherited: SMALL
	// Inherited: SPAN
	// Inherited: STRONG
	// Inherited: SUB
	// Inherited: SUP
	// Inherited: SVG svg
	// Inherited: TABLE
	// Inherited: TEMPLATE
	// Inherited: TEXTAREA
	// Inherited: TIME
	// Inherited: U
	// Inherited: UL
	// Inherited: VAR
	// Inherited: VIDEO
	// Inherited: WBR
	// Inherited: autonomous custom elements
}
