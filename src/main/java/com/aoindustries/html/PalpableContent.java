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
 * See <a href="https://html.spec.whatwg.org/#palpable-content">3.2.5.2.8 Palpable content</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public interface PalpableContent<PC extends Content> extends Content,
	Contents.Text.A<PC>,
	Contents.Text.Abbr<PC>,
	Contents.Sections.Address<PC>,
	Contents.Sections.Article<PC>,
	Contents.Sections.Aside<PC>,
	Contents.Embedded.Audio<PC>, // if the controls attribute is present
	Contents.Text.B<PC>,
	Contents.Text.Bdi<PC>,
	Contents.Text.Bdo<PC>,
	Contents.Grouping.Blockquote<PC>,
	Contents.Forms.Button<PC>,
	Contents.Scripting.Canvas<PC>,
	Contents.Text.Cite<PC>,
	Contents.Text.Code<PC>,
	Contents.Text.Data<PC>,
	Contents.Interactive.Details<PC>,
	Contents.Text.Dfn<PC>,
	Contents.Grouping.Div<PC>,
	Contents.Grouping.Dl<PC>, // if childen contain at least one name/value pair
	Contents.Text.Em<PC>,
	Contents.Embedded.Embed<PC>,
	Contents.Forms.Fieldset<PC>,
	Contents.Grouping.Figure<PC>,
	Contents.Sections.Footer<PC>,
	Contents.Forms.Form<PC>,
	Contents.Sections.H1<PC>,
	Contents.Sections.H2<PC>,
	Contents.Sections.H3<PC>,
	Contents.Sections.H4<PC>,
	Contents.Sections.H5<PC>,
	Contents.Sections.H6<PC>,
	Contents.Sections.Header<PC>,
	Contents.Sections.Hgroup<PC>,
	Contents.Text.I<PC>,
	Contents.Embedded.Iframe<PC>,
	Contents.Embedded.Img<PC>,
	Contents.Forms.Input<PC>, // if type attribute is not in the hidden state
	Contents.Edits.Ins<PC>,
	Contents.Text.Kbd<PC>,
	Contents.Forms.Label<PC>,
	Contents.Grouping.Main<PC>,
	Contents.Embedded.Map<PC>,
	Contents.Text.Mark<PC>,
	// TODO: MathML math
	Contents.Grouping.Menu<PC>, // if children include at least one li
	Contents.Forms.Meter<PC>,
	Contents.Sections.Nav<PC>,
	Contents.Embedded.Object<PC>,
	Contents.Grouping.Ol<PC>, // if children include at least one li
	Contents.Forms.Output<PC>,
	Contents.Grouping.P<PC>,
	Contents.Grouping.Pre<PC>,
	Contents.Forms.Progress<PC>,
	Contents.Text.Q<PC>,
	Contents.Text.Ruby<PC>,
	Contents.Text.S<PC>,
	Contents.Text.Samp<PC>,
	Contents.Sections.Section<PC>,
	Contents.Forms.Select<PC>,
	Contents.Text.Small<PC>,
	Contents.Text.Span<PC>,
	Contents.Text.Strong<PC>,
	Contents.Text.Sub<PC>,
	Contents.Text.Sup<PC>,
	// TODO: SVG svg
	Contents.Tabular.Table<PC>,
	Contents.Forms.Textarea<PC>,
	Contents.Text.Time<PC>,
	Contents.Text.U<PC>,
	Contents.Grouping.Ul<PC>, // if children include at least one li
	Contents.Text.Var<PC>,
	Contents.Embedded.Video<PC>,
	// TODO: autonomous custom elements
	TextContent<PC> // that is not inter-element whitespace
{
}
