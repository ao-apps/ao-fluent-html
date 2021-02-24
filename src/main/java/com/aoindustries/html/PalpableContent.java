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
 * <li>See <a href="https://html.spec.whatwg.org/#palpable-content">3.2.5.2.8 Palpable content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#palpable_content">Palpable content</a>.</li>
 * </ul>
 *
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PalpableContent<C extends PalpableContent<C>> extends
	//
	// Unions:
	//
	UnionContent.Palpable_Phrasing<C>,
	//
	// Content models:
	//
	// Inherited from HeadingContent, InteractiveContent, SectioningContent, and Palpable_Phrasing: Content
	SectioningContent<C>,
	HeadingContent<C>,
	InteractiveContent<C>,
	//
	// Content types:
	//
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Text.A<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Abbr<PC>
	Contents.Sections.Address<C>,
	// Inherited from SectioningContent: Contents.Sections.Article<PC>
	// Inherited from SectioningContent: Contents.Sections.Aside<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Audio<PC> // if the controls attribute is present
	// Inherited from Palpable_Phrasing: Contents.Text.B<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Bdi<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Bdo<PC>
	Contents.Grouping.Blockquote<C>,
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Button<PC>
	// Inherited from Palpable_Phrasing: Contents.Scripting.Canvas<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Cite<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Code<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Data<PC>
	// Inherited from InteractiveContent: Contents.Interactive.Details<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Dfn<PC>
	Contents.Grouping.Div<C>,
	Contents.Grouping.Dl<C>, // if childen contain at least one name/value pair
	// Inherited from Palpable_Phrasing: Contents.Text.Em<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Embed<PC>
	Contents.Forms.Fieldset<C>,
	Contents.Grouping.Figure<C>,
	Contents.Sections.Footer<C>,
	Contents.Forms.Form<C>,
	// Inherited from HeadingContent: Contents.Sections.H1<PC>
	// Inherited from HeadingContent: Contents.Sections.H2<PC>
	// Inherited from HeadingContent: Contents.Sections.H3<PC>
	// Inherited from HeadingContent: Contents.Sections.H4<PC>
	// Inherited from HeadingContent: Contents.Sections.H5<PC>
	// Inherited from HeadingContent: Contents.Sections.H6<PC>
	Contents.Sections.Header<C>,
	// Inherited from HeadingContent: Contents.Sections.Hgroup<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.I<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Iframe<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Img<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Input<PC> // if type attribute is not in the hidden state
	// Inherited from Palpable_Phrasing: Contents.Edits.Ins<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Kbd<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Label<PC>
	Contents.Grouping.Main<C>,
	// Inherited from Palpable_Phrasing: Contents.Embedded.Map<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Mark<PC>
	// Inherited from Palpable_Phrasing: // TODO: MathML math
	// Inherited from InteractiveContent: Contents.Grouping.Menu<PC> // if children include at least one li
	// Inherited from Palpable_Phrasing: Contents.Forms.Meter<PC>
	// Inherited from SectioningContent: Contents.Sections.Nav<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Object<PC>
	Contents.Grouping.Ol<C>, // if children include at least one li
	// Inherited from Palpable_Phrasing: Contents.Forms.Output<PC>
	Contents.Grouping.P<C>,
	Contents.Grouping.Pre<C>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Progress<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Q<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Ruby<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.S<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Samp<PC>
	// Inherited from SectioningContent: Contents.Sections.Section<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Select<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Small<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Span<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Strong<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Sub<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Sup<PC>
	// Inherited from Palpable_Phrasing: // TODO: SVG svg
	Contents.Tabular.Table<C>,
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Textarea<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.Time<PC>
	// Inherited from Palpable_Phrasing: Contents.Text.U<PC>
	Contents.Grouping.Ul<C> // if children include at least one li
	// Inherited from Palpable_Phrasing: Contents.Text.Var<PC>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Video<PC>
	// Inherited from Palpable_Phrasing: // TODO: autonomous custom elements
	// Inherited from Palpable_Phrasing: TextContent<PC> // that is not inter-element whitespace
{
}
