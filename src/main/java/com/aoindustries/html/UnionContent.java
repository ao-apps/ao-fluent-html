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

import com.aoindustries.html.Contents.Text.B;
import com.aoindustries.html.Contents.Text.I;

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
	 * Elements that are common to both {@link ColgroupContent} and {@link ScriptSupportingContent}.
	 *
	 * @param  <C>  This content model, which will be the parent content model of child elements
	 */
	public static interface Colgroup_ScriptSupporting<C extends Colgroup_ScriptSupporting<C>> extends
		//
		// Content models:
		//
		Content,
		//
		// Content types:
		//
		Contents.Scripting.Template<C>
	{
	}

	/**
	 * Elements that are common to both {@link EmbeddedContent} and {@link InteractiveContent}.
	 *
	 * @param  <C>  This content model, which will be the parent content model of child elements
	 */
	public static interface Embedded_Interactive<C extends Embedded_Interactive<C>> extends
		//
		// Content models:
		//
		Content,
		//
		// Content types:
		//
		Contents.Embedded.Audio<C>,
		Contents.Embedded.Embed<C>,
		Contents.Embedded.Iframe<C>,
		Contents.Embedded.Img<C>,
		Contents.Embedded.Object<C>,
		Contents.Embedded.Video<C>
	{
	}

	/**
	 * Elements that are common to all three of {@link EmbeddedContent}, {@link PalpableContent}, and
	 * {@link PhrasingContent}.
	 *
	 * @param  <C>  This content model, which will be the parent content model of child elements
	 */
	public static interface Embedded_Palpable_Phrasing<C extends Embedded_Palpable_Phrasing<C>> extends
		//
		// Content models:
		//
		Content,
		//
		// Content types:
		//
		Contents.Scripting.Canvas<C>
		// TODO: MathML math
		// TODO: SVG svg
	{
	}

	/**
	 * Elements that are common to both {@link InteractiveContent} and {@link PhrasingContent}.
	 *
	 * @param  <C>  This content model, which will be the parent content model of child elements
	 */
	public static interface Interactive_Phrasing<C extends Interactive_Phrasing<C>> extends
		//
		// Unions:
		//
		Embedded_Interactive<C>,
		//
		// Content models:
		//
		// Inherited from Embedded_Interactive: Content
		//
		// Content types:
		//
		Contents.Text.A<C>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Audio<PC>
		Contents.Forms.Button<C>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Embed<PC>
		Contents.Forms.Input<C>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Iframe<PC>
		// Inherited from Embedded_Interactive: Contents.Embedded.Img<PC>
		Contents.Forms.Label<C>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Object<PC>
		Contents.Forms.Select<C>,
		Contents.Forms.Textarea<C>
		// Inherited from Embedded_Interactive: Contents.Embedded.Video<PC>
	{
	}

	/**
	 * Elements that are common to both {@link MetadataContent} and {@link PhrasingContent}.
	 *
	 * @param  <C>  This content model, which will be the parent content model of child elements
	 */
	public static interface Metadata_Phrasing<C extends Metadata_Phrasing<C>> extends
		//
		// Content models:
		//
		// Inherited from ScriptSupportingContent: Content
		ScriptSupportingContent<C>,
		//
		// Content types:
		//
		Contents.Metadata.Link<C>,
		Contents.Metadata.Meta<C>,
		Contents.Scripting.Noscript<C>
		// Inherited from ScriptSupportingContent: Contents.Scripting.Script<C>
		// Inherited from ScriptSupportingContent: Contents.Scripting.Template<C>
	{
	}

	/**
	 * Elements that are common to both {@link PalpableContent} and {@link PhrasingContent}.
	 *
	 * @param  <C>  This content model, which will be the parent content model of child elements
	 */
	public static interface Palpable_Phrasing<C extends Palpable_Phrasing<C>> extends
		//
		// Unions:
		//
		Embedded_Palpable_Phrasing<C>,
		Interactive_Phrasing<C>,
		//
		// Content models:
		//
		// Inherited from Embedded_Palpable_Phrasing and Interactive_Phrasing: Content
		//
		// Content types:
		//
		// Inherited from Interactive_Phrasing: Contents.Text.A<PC>
		Contents.Text.Abbr<C>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Audio<PC>
		Contents.Text.B<C>,
		Contents.Text.Bdi<C>,
		Contents.Text.Bdo<C>,
		// Inherited from Interactive_Phrasing: Contents.Forms.Button<PC>
		// Inherited from Embedded_Palpable_Phrasing: Contents.Scripting.Canvas<PC>
		Contents.Text.Cite<C>,
		Contents.Text.Code<C>,
		Contents.Text.Data<C>,
		Contents.Text.Dfn<C>,
		Contents.Text.Em<C>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Embed<PC>
		Contents.Text.I<C>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Iframe<PC>
		// Inherited from Interactive_Phrasing: Contents.Embedded.Img<PC>
		// Inherited from Interactive_Phrasing: Contents.Forms.Input<PC>
		Contents.Edits.Ins<C>,
		Contents.Text.Kbd<C>,
		// Inherited from Interactive_Phrasing: Contents.Forms.Label<PC>
		Contents.Embedded.Map<C>,
		Contents.Text.Mark<C>,
		// Inherited from Embedded_Palpable_Phrasing: // TODO: MathML math
		Contents.Forms.Meter<C>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Object<PC>
		Contents.Forms.Output<C>,
		Contents.Forms.Progress<C>,
		Contents.Text.Q<C>,
		Contents.Text.Ruby<C>,
		Contents.Text.S<C>,
		Contents.Text.Samp<C>,
		// Inherited from Interactive_Phrasing: Contents.Forms.Select<PC>
		Contents.Text.Small<C>,
		Contents.Text.Span<C>,
		Contents.Text.Strong<C>,
		Contents.Text.Sub<C>,
		Contents.Text.Sup<C>,
		// Inherited from Embedded_Palpable_Phrasing: // TODO: SVG svg
		// Inherited from Interactive_Phrasing: Contents.Forms.Textarea<PC>
		Contents.Text.Time<C>,
		Contents.Text.U<C>,
		Contents.Text.Var<C>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Video<PC>
		// TODO: autonomous custom elements
		TextContent<C> // that is not inter-element whitespace
	{
	}
}
