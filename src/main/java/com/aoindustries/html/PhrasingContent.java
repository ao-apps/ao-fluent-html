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
 * See <a href="https://html.spec.whatwg.org/#phrasing-content">3.2.5.2.5 Phrasing content</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public interface PhrasingContent<PC extends Content> extends Content,
	Contents.Text.A<PC>,
	Contents.Text.Abbr<PC>,
	Contents.Embedded.Area<PC>, // if a descendent of map
	Contents.Embedded.Audio<PC>,
	Contents.Text.B<PC>,
	Contents.Text.Bdi<PC>,
	Contents.Text.Bdo<PC>,
	Contents.Text.Br<PC>,
	Contents.Forms.Button<PC>,
	Contents.Scripting.Canvas<PC>,
	Contents.Text.Cite<PC>,
	Contents.Text.Code<PC>,
	Contents.Text.Data<PC>,
	Contents.Forms.Datalist<PC>,
	Contents.Edits.Del<PC>,
	Contents.Text.Dfn<PC>,
	Contents.Text.Em<PC>,
	Contents.Embedded.Embed<PC>,
	Contents.Text.I<PC>,
	Contents.Embedded.Iframe<PC>,
	Contents.Embedded.Img<PC>,
	Contents.Forms.Input<PC>,
	Contents.Edits.Ins<PC>,
	Contents.Text.Kbd<PC>,
	Contents.Forms.Label<PC>,
	Contents.Metadata.Link<PC>, // if it is allowed in body
	Contents.Embedded.Map<PC>,
	Contents.Text.Mark<PC>,
	// TODO: MathML math
	Contents.Metadata.Meta<PC>, // if the itemprop attribute is present
	Contents.Forms.Meter<PC>,
	Contents.Scripting.Noscript<PC>,
	Contents.Embedded.Object<PC>,
	Contents.Forms.Output<PC>,
	Contents.Embedded.Picture<PC>,
	Contents.Forms.Progress<PC>,
	Contents.Text.Q<PC>,
	Contents.Text.Ruby<PC>,
	Contents.Text.S<PC>,
	Contents.Text.Samp<PC>,
	Contents.Scripting.Script<PC>,
	Contents.Forms.Select<PC>,
	Contents.Scripting.Slot<PC>,
	Contents.Text.Small<PC>,
	Contents.Text.Span<PC>,
	Contents.Text.Strong<PC>,
	Contents.Text.Sub<PC>,
	Contents.Text.Sup<PC>,
	// TODO: SVG svg
	Contents.Scripting.Template<PC>,
	Contents.Forms.Textarea<PC>,
	Contents.Text.Time<PC>,
	Contents.Text.U<PC>,
	Contents.Text.Var<PC>,
	Contents.Embedded.Video<PC>,
	Contents.Text.Wbr<PC>,
	// TODO: autonomous custom elements
	TextContent<PC>
{
}
