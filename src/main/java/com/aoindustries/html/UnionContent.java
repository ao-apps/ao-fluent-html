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

/**
 * Union content models represent the specific elements that are common between two or more content models,
 * but where the content models cannot inherit from one another.
 * These interfaces are not specifically part of the HTML specification, but are an artifact of this implementation.
 * These interfaces are primarily needed because there is "or" for generic upper bounds.
 * <p>
 * For example, both {@link B} and {@link I} are part of both {@link PalpableContent} and {@link PhrasingContent}.
 * Thus, when you have one, you know you can have the other (see {@link Palpable_Phrasing} in this case).
 * </p>
 *
 * @author  AO Industries, Inc.
 */
public class UnionContent {

	/** Make no instances. */
	private UnionContent() {}

	/**
	 * Elements that are common to both {@link COLGROUP_content} and {@link ScriptSupportingContent}.
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 */
	public static interface Colgroup_ScriptSupporting<__ extends Colgroup_ScriptSupporting<__>> extends
		//
		// Content models:
		//
		Content<__>,
		//
		// Content types:
		//
		TEMPLATE_factory<__>
	{
	}

	/**
	 * Elements that are common to both {@link EmbeddedContent} and {@link InteractiveContent}.
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 */
	public static interface Embedded_Interactive<__ extends Embedded_Interactive<__>> extends
		//
		// Content models:
		//
		Content<__>,
		//
		// Content types:
		//
		AUDIO_factory<__>,
		EMBED_factory<__>,
		IFRAME_factory<__>,
		IMG_factory<__>,
		OBJECT_factory<__>,
		VIDEO_factory<__>
	{
	}

	/**
	 * Elements that are common to all three of {@link EmbeddedContent}, {@link PalpableContent}, and
	 * {@link PhrasingContent}.
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 */
	public static interface Embedded_Palpable_Phrasing<__ extends Embedded_Palpable_Phrasing<__>> extends
		//
		// Content models:
		//
		Content<__>,
		//
		// Content types:
		//
		CANVAS_factory<__>
		// TODO: MathML math
		// TODO: SVG svg
	{
	}

	/**
	 * Elements that are common to both {@link InteractiveContent} and {@link PhrasingContent}.
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 */
	public static interface Interactive_Phrasing<__ extends Interactive_Phrasing<__>> extends
		//
		// Unions:
		//
		Embedded_Interactive<__>,
		//
		// Content models:
		//
		// Inherited from Embedded_Interactive: Content<__>
		//
		// Content types:
		//
		A_factory<__>,
		// Inherited from Embedded_Interactive: AUDIO_factory<__>
		BUTTON_factory<__>,
		// Inherited from Embedded_Interactive: EMBED_factory<__>
		INPUT_factory<__>,
		// Inherited from Embedded_Interactive: IFRAME_factory<__>
		// Inherited from Embedded_Interactive: IMG_factory<__>
		LABEL_factory<__>,
		// Inherited from Embedded_Interactive: OBJECT_factory<__>
		SELECT_factory<__>,
		TEXTAREA_factory<__>
		// Inherited from Embedded_Interactive: VIDEO_factory<__>
	{
	}

	/**
	 * Elements that are common to both {@link MetadataContent} and {@link PhrasingContent}.
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 */
	public static interface Metadata_Phrasing<__ extends Metadata_Phrasing<__>> extends
		//
		// Content models:
		//
		// Inherited from ScriptSupportingContent: Content<__>
		ScriptSupportingContent<__>,
		//
		// Content types:
		//
		LINK_factory<__>,
		META_factory<__>,
		NOSCRIPT_factory<__>
		// Inherited from ScriptSupportingContent: SCRIPT_factory<__>
		// Inherited from ScriptSupportingContent: TEMPLATE_factory<__>
	{
	}

	/**
	 * Elements that are common to both {@link PalpableContent} and {@link PhrasingContent}.
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 */
	public static interface Palpable_Phrasing<__ extends Palpable_Phrasing<__>> extends
		//
		// Unions:
		//
		Embedded_Palpable_Phrasing<__>,
		Interactive_Phrasing<__>,
		//
		// Content models:
		//
		// Inherited from Embedded_Palpable_Phrasing and Interactive_Phrasing: Content<__>
		//
		// Content types:
		//
		// Inherited from Interactive_Phrasing: A_factory<__>
		ABBR_factory<__>,
		// Inherited from Interactive_Phrasing: AUDIO_factory<__>
		B_factory<__>,
		BDI_factory<__>,
		BDO_factory<__>,
		// Inherited from Interactive_Phrasing: BUTTON_factory<__>
		// Inherited from Embedded_Palpable_Phrasing: CANVAS_factory<__>
		CITE_factory<__>,
		CODE_factory<__>,
		DATA_factory<__>,
		DFN_factory<__>,
		EM_factory<__>,
		// Inherited from Interactive_Phrasing: EMBED_factory<__>
		I_factory<__>,
		// Inherited from Interactive_Phrasing: IFRAME_factory<__>
		// Inherited from Interactive_Phrasing: IMG_factory<__>
		// Inherited from Interactive_Phrasing: INPUT_factory<__>
		INS_factory<__>,
		KBD_factory<__>,
		// Inherited from Interactive_Phrasing: LABEL_factory<__>
		MAP_factory<__>,
		MARK_factory<__>,
		// Inherited from Embedded_Palpable_Phrasing: // TODO: MathML math
		METER_factory<__>,
		// Inherited from Interactive_Phrasing: OBJECT_factory<__>
		OUTPUT_factory<__>,
		PROGRESS_factory<__>,
		Q_factory<__>,
		RUBY_factory<__>,
		S_factory<__>,
		SAMP_factory<__>,
		// Inherited from Interactive_Phrasing: SELECT_factory<__>
		SMALL_factory<__>,
		SPAN_factory<__>,
		STRONG_factory<__>,
		SUB_factory<__>,
		SUP_factory<__>,
		// Inherited from Embedded_Palpable_Phrasing: // TODO: SVG svg
		// Inherited from Interactive_Phrasing: TEXTAREA_factory<__>
		TIME_factory<__>,
		U_factory<__>,
		VAR_factory<__>,
		// Inherited from Interactive_Phrasing: VIDEO_factory<__>
		// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
		TextContent<__> // that is not inter-element whitespace
	{
	}

	/**
	 * Elements that are common to all three of {@link TBODY}, {@link THEAD}, and {@link TFOOT}.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.</li>
	 * </ul>
	 *
	 * @param  <__>  This content model, which will be the parent content model of child elements
	 *
	 * @author  AO Industries, Inc.
	 */
	public static interface TBODY_THEAD_TFOOT<__ extends TBODY_THEAD_TFOOT<__>> extends
		//
		// Content models:
		//
		// Inherited from ScriptSupportingContent: Content<__>
		ScriptSupportingContent<__>,
		//
		// Content types:
		//
		TR_factory<__>
		// Inherited from ScriptSupportingContent: SCRIPT_factory<__>
		// Inherited from ScriptSupportingContent: TEMPLATE_factory<__>
	{
	}
}
