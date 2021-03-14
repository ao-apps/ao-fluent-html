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

/**
 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnyDATALIST_content<
	D  extends AnyDocument<D>,
	__ extends AnyDATALIST_content<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: AnyUnion_COLGROUP_ScriptSupporting<D, __>
	AnyUnion_DATALIST_OPTGROUP<D, __>,
	// Inherited: AnyUnion_Embedded_Interactive<D, __>
	// Inherited: AnyUnion_Embedded_Palpable_Phrasing<D, __>
	// Inherited: AnyUnion_Interactive_Phrasing<D, __>
	// Inherited: AnyUnion_Metadata_Phrasing<D, __>
	// Inherited: AnyUnion_Palpable_Phrasing<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	// Inherited: AnyEmbeddedContent<D, __>
	AnyPhrasingContent<D, __>
	// Inherited: AnyScriptSupportingContent<D, __>
	// Inherited: AnyTextContent<D, __>
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// Inherited: AREA - if a descendent of map
	// Inherited: AUDIO
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// Inherited: BR
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// Inherited: DATALIST
	// Inherited: DEL
	// Inherited: DFN
	// Inherited: EM
	// Inherited: EMBED
	// Inherited: I
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: INPUT
	// Inherited: INS
	// Inherited: KBD
	// Inherited: LABEL
	// Inherited: LINK - if it is allowed in body
	// Inherited: MAP
	// Inherited: MARK
	// Inherited: MathML math
	// Inherited: META - if the itemprop attribute is present
	// Inherited: METER
	// Inherited: NOSCRIPT
	// Inherited: OBJECT
	// Inherited: OPTION
	// Inherited: OUTPUT
	// Inherited: PICTURE
	// Inherited: PROGRESS
	// Inherited: Q
	// Inherited: RUBY
	// Inherited: S
	// Inherited: SAMP
	// Inherited: SCRIPT
	// Inherited: SELECT
	// Inherited: SLOT
	// Inherited: SMALL
	// Inherited: SPAN
	// Inherited: STRONG
	// Inherited: SUB
	// Inherited: SUP
	// Inherited: SVG svg
	// Inherited: TEMPLATE
	// Inherited: TEXTAREA
	// Inherited: TIME
	// Inherited: U
	// Inherited: VAR
	// Inherited: VIDEO
	// Inherited: WBR
	// Inherited: autonomous custom elements
}
