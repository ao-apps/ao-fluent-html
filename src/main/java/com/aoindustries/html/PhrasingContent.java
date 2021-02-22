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
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public interface PhrasingContent<PC extends PhrasingContent<PC>> extends
	//
	// Unions:
	//
	UnionContent.Metadata_Phrasing<PC>,
	UnionContent.Palpable_Phrasing<PC>,
	//
	// Content models:
	//
	// Inherited from EmbeddedContent and Palpable_Phrasing: Content,
	EmbeddedContent<PC>,
	//
	// Content types:
	//
	// Inherited from Palpable_Phrasing: Contents.Text.A<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Abbr<PC>,
	Contents.Embedded.Area<PC>, // if a descendent of map
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Audio<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.B<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Bdi<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Bdo<PC>,
	Contents.Text.Br<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Button<PC>,
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Scripting.Canvas<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Cite<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Code<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Data<PC>,
	Contents.Forms.Datalist<PC>,
	Contents.Edits.Del<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Dfn<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Em<PC>,
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Embed<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.I<PC>,
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Iframe<PC>,
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Img<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Input<PC>,
	// Inherited from Palpable_Phrasing: Contents.Edits.Ins<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Kbd<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Label<PC>,
	// Inherited from Metadata_Phrasing: Contents.Metadata.Link<PC>, // if it is allowed in body
	// Inherited from Palpable_Phrasing: Contents.Embedded.Map<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Mark<PC>,
	// Inherited from Palpable_Phrasing: // TODO: MathML math
	// Inherited from Metadata_Phrasing: Contents.Metadata.Meta<PC>, // if the itemprop attribute is present
	// Inherited from Palpable_Phrasing: Contents.Forms.Meter<PC>,
	// Inherited from Metadata_Phrasing: Contents.Scripting.Noscript<PC>,
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Object<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Output<PC>,
	// Inherited from EmbeddedContent: Contents.Embedded.Picture<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Progress<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Q<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Ruby<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.S<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Samp<PC>,
	// Inherited from Metadata_Phrasing: Contents.Scripting.Script<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Select<PC>,
	Contents.Scripting.Slot<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Small<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Span<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Strong<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Sub<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Sup<PC>,
	// Inherited from Palpable_Phrasing: // TODO: SVG svg
	// Inherited from Metadata_Phrasing: Contents.Scripting.Template<PC>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Textarea<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Time<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.U<PC>,
	// Inherited from Palpable_Phrasing: Contents.Text.Var<PC>,
	// Inherited from EmbeddedContent and Palpable_Phrasing: Contents.Embedded.Video<PC>,
	Contents.Text.Wbr<PC>
	// Inherited from Palpable_Phrasing: // TODO: autonomous custom elements
	// Inherited from Palpable_Phrasing: TextContent<PC>
{
}
