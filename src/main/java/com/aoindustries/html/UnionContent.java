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
	 * Elements that are common to both {@link EmbeddedContent} and {@link InteractiveContent}.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static interface Embedded_Interactive<PC extends Embedded_Interactive<PC>> extends
		//
		// Content models:
		//
		Content,
		//
		// Content types:
		//
		Contents.Embedded.Audio<PC>,
		Contents.Embedded.Embed<PC>,
		Contents.Embedded.Iframe<PC>,
		Contents.Embedded.Img<PC>,
		Contents.Embedded.Object<PC>,
		Contents.Embedded.Video<PC>
	{
	}

	/**
	 * Elements that are common to all three of {@link EmbeddedContent}, {@link PalpableContent}, and
	 * {@link PhrasingContent}.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static interface Embedded_Palpable_Phrasing<PC extends Embedded_Palpable_Phrasing<PC>> extends
		//
		// Content models:
		//
		Content,
		//
		// Content types:
		//
		Contents.Scripting.Canvas<PC>
		// TODO: MathML math
		// TODO: SVG svg
	{
	}

	/**
	 * Elements that are common to both {@link InteractiveContent} and {@link PhrasingContent}.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static interface Interactive_Phrasing<PC extends Interactive_Phrasing<PC>> extends
		//
		// Unions:
		//
		Embedded_Interactive<PC>,
		//
		// Content models:
		//
		// Inherited from Embedded_Interactive: Content
		//
		// Content types:
		//
		Contents.Text.A<PC>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Audio<PC>
		Contents.Forms.Button<PC>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Embed<PC>
		Contents.Forms.Input<PC>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Iframe<PC>
		// Inherited from Embedded_Interactive: Contents.Embedded.Img<PC>
		Contents.Forms.Label<PC>,
		// Inherited from Embedded_Interactive: Contents.Embedded.Object<PC>
		Contents.Forms.Select<PC>,
		Contents.Forms.Textarea<PC>
		// Inherited from Embedded_Interactive: Contents.Embedded.Video<PC>
	{
	}

	/**
	 * Elements that are common to both {@link MetadataContent} and {@link PhrasingContent}.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static interface Metadata_Phrasing<PC extends Metadata_Phrasing<PC>> extends
		//
		// Content models:
		//
		Content,
		//
		// Content types:
		//
		Contents.Metadata.Link<PC>,
		Contents.Metadata.Meta<PC>,
		Contents.Scripting.Noscript<PC>,
		Contents.Scripting.Script<PC>,
		Contents.Scripting.Template<PC>
	{
	}

	/**
	 * Elements that are common to both {@link PalpableContent} and {@link PhrasingContent}.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static interface Palpable_Phrasing<PC extends Palpable_Phrasing<PC>> extends
		//
		// Unions:
		//
		Embedded_Palpable_Phrasing<PC>,
		Interactive_Phrasing<PC>,
		//
		// Content models:
		//
		// Inherited from Embedded_Palpable_Phrasing and Interactive_Phrasing: Content
		//
		// Content types:
		//
		// Inherited from Interactive_Phrasing: Contents.Text.A<PC>
		Contents.Text.Abbr<PC>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Audio<PC>
		Contents.Text.B<PC>,
		Contents.Text.Bdi<PC>,
		Contents.Text.Bdo<PC>,
		// Inherited from Interactive_Phrasing: Contents.Forms.Button<PC>
		// Inherited from Embedded_Palpable_Phrasing: Contents.Scripting.Canvas<PC>,
		Contents.Text.Cite<PC>,
		Contents.Text.Code<PC>,
		Contents.Text.Data<PC>,
		Contents.Text.Dfn<PC>,
		Contents.Text.Em<PC>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Embed<PC>
		Contents.Text.I<PC>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Iframe<PC>
		// Inherited from Interactive_Phrasing: Contents.Embedded.Img<PC>
		// Inherited from Interactive_Phrasing: Contents.Forms.Input<PC>
		Contents.Edits.Ins<PC>,
		Contents.Text.Kbd<PC>,
		// Inherited from Interactive_Phrasing: Contents.Forms.Label<PC>
		Contents.Embedded.Map<PC>,
		Contents.Text.Mark<PC>,
		// Inherited from Embedded_Palpable_Phrasing: // TODO: MathML math
		Contents.Forms.Meter<PC>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Object<PC>
		Contents.Forms.Output<PC>,
		Contents.Forms.Progress<PC>,
		Contents.Text.Q<PC>,
		Contents.Text.Ruby<PC>,
		Contents.Text.S<PC>,
		Contents.Text.Samp<PC>,
		// Inherited from Interactive_Phrasing: Contents.Forms.Select<PC>
		Contents.Text.Small<PC>,
		Contents.Text.Span<PC>,
		Contents.Text.Strong<PC>,
		Contents.Text.Sub<PC>,
		Contents.Text.Sup<PC>,
		// Inherited from Embedded_Palpable_Phrasing: // TODO: SVG svg
		// Inherited from Interactive_Phrasing: Contents.Forms.Textarea<PC>
		Contents.Text.Time<PC>,
		Contents.Text.U<PC>,
		Contents.Text.Var<PC>,
		// Inherited from Interactive_Phrasing: Contents.Embedded.Video<PC>
		// TODO: autonomous custom elements
		TextContent<PC> // that is not inter-element whitespace
	{
	}
}
