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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#phrasing-content">3.2.5.2.5 Phrasing content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#phrasing_content">Phrasing content</a>.</li>
 * </ul>
 *
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PhrasingContent<C extends PhrasingContent<C>> extends
	//
	// Unions:
	//
	UnionContent.Metadata_Phrasing<C>,
	UnionContent.Palpable_Phrasing<C>,
	//
	// Content models:
	//
	// Inherited from EmbeddedContent and Palpable_Phrasing: Content<C>
	EmbeddedContent<C>,
	// Inherited from Metadata_Phrasing: ScriptSupportingContent
	//
	// Content types:
	//
	// Inherited from Palpable_Phrasing: Contents.Text.A<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Abbr<C>
	Contents.Embedded.Area<C>, // if a descendent of map
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Audio<C>
	// Inherited from Palpable_Phrasing: Contents.Text.B<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Bdi<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Bdo<C>
	Contents.Text.Br<C>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Button<C>
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Scripting.Canvas<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Cite<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Code<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Data<C>
	Contents.Forms.Datalist<C>,
	Contents.Edits.Del<C>,
	// Inherited from Palpable_Phrasing: Contents.Text.Dfn<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Em<C>
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Embed<C>
	// Inherited from Palpable_Phrasing: Contents.Text.I<C>
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Iframe<C>
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Img<C>
	// Inherited from Palpable_Phrasing: Contents.Forms.Input<C>
	// Inherited from Palpable_Phrasing: Contents.Edits.Ins<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Kbd<C>
	// Inherited from Palpable_Phrasing: Contents.Forms.Label<C>
	// Inherited from Metadata_Phrasing: Contents.Metadata.Link<C> // if it is allowed in body
	// Inherited from Palpable_Phrasing: Contents.Embedded.Map<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Mark<C>
	// Inherited from Palpable_Phrasing: // TODO: MathML math
	// Inherited from Metadata_Phrasing: Contents.Metadata.Meta<C> // if the itemprop attribute is present
	// Inherited from Palpable_Phrasing: Contents.Forms.Meter<C>
	// Inherited from Metadata_Phrasing: Contents.Scripting.Noscript<C>
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Object<C>
	// Inherited from Palpable_Phrasing: Contents.Forms.Output<C>
	// Inherited from EmbeddedContent: Contents.Embedded.Picture<C>
	// Inherited from Palpable_Phrasing: Contents.Forms.Progress<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Q<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Ruby<C>
	// Inherited from Palpable_Phrasing: Contents.Text.S<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Samp<C>
	// Inherited from Metadata_Phrasing: Contents.Scripting.Script<C>
	// Inherited from Palpable_Phrasing: Contents.Forms.Select<C>
	Contents.Scripting.Slot<C>,
	// Inherited from Palpable_Phrasing: Contents.Text.Small<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Span<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Strong<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Sub<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Sup<C>
	// Inherited from Palpable_Phrasing: // TODO: SVG svg
	// Inherited from Metadata_Phrasing: Contents.Scripting.Template<C>
	// Inherited from Palpable_Phrasing: Contents.Forms.Textarea<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Time<C>
	// Inherited from Palpable_Phrasing: Contents.Text.U<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Var<C>
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Video<C>
	Contents.Text.Wbr<C>
	// Inherited from Palpable_Phrasing: // TODO: autonomous custom elements
	// Inherited from Palpable_Phrasing: TextContent<C>
{
}
