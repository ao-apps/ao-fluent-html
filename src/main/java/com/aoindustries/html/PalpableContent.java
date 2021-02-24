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
	// Inherited from HeadingContent, InteractiveContent, SectioningContent, and Palpable_Phrasing: Content<C>
	SectioningContent<C>,
	HeadingContent<C>,
	InteractiveContent<C>,
	//
	// Content types:
	//
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Text.A<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Abbr<C>
	Contents.Sections.Address<C>,
	// Inherited from SectioningContent: Contents.Sections.Article<C>
	// Inherited from SectioningContent: Contents.Sections.Aside<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Audio<C> // if the controls attribute is present
	// Inherited from Palpable_Phrasing: Contents.Text.B<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Bdi<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Bdo<C>
	Contents.Grouping.Blockquote<C>,
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Button<C>
	// Inherited from Palpable_Phrasing: Contents.Scripting.Canvas<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Cite<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Code<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Data<C>
	// Inherited from InteractiveContent: Contents.Interactive.Details<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Dfn<C>
	Contents.Grouping.Div<C>,
	Contents.Grouping.Dl<C>, // if childen contain at least one name/value pair
	// Inherited from Palpable_Phrasing: Contents.Text.Em<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Embed<C>
	Contents.Forms.Fieldset<C>,
	Contents.Grouping.Figure<C>,
	Contents.Sections.Footer<C>,
	Contents.Forms.Form<C>,
	// Inherited from HeadingContent: Contents.Sections.H1<C>
	// Inherited from HeadingContent: Contents.Sections.H2<C>
	// Inherited from HeadingContent: Contents.Sections.H3<C>
	// Inherited from HeadingContent: Contents.Sections.H4<C>
	// Inherited from HeadingContent: Contents.Sections.H5<C>
	// Inherited from HeadingContent: Contents.Sections.H6<C>
	Contents.Sections.Header<C>,
	// Inherited from HeadingContent: Contents.Sections.Hgroup<C>
	// Inherited from Palpable_Phrasing: Contents.Text.I<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Iframe<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Img<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Input<C> // if type attribute is not in the hidden state
	// Inherited from Palpable_Phrasing: Contents.Edits.Ins<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Kbd<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Label<C>
	Contents.Grouping.Main<C>,
	// Inherited from Palpable_Phrasing: Contents.Embedded.Map<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Mark<C>
	// Inherited from Palpable_Phrasing: // TODO: MathML math
	// Inherited from InteractiveContent: Contents.Grouping.Menu<C> // if children include at least one li
	// Inherited from Palpable_Phrasing: Contents.Forms.Meter<C>
	// Inherited from SectioningContent: Contents.Sections.Nav<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Object<C>
	Contents.Grouping.Ol<C>, // if children include at least one li
	// Inherited from Palpable_Phrasing: Contents.Forms.Output<C>
	Contents.Grouping.P<C>,
	Contents.Grouping.Pre<C>,
	// Inherited from Palpable_Phrasing: Contents.Forms.Progress<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Q<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Ruby<C>
	// Inherited from Palpable_Phrasing: Contents.Text.S<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Samp<C>
	// Inherited from SectioningContent: Contents.Sections.Section<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Select<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Small<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Span<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Strong<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Sub<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Sup<C>
	// Inherited from Palpable_Phrasing: // TODO: SVG svg
	Contents.Tabular.Table<C>,
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Forms.Textarea<C>
	// Inherited from Palpable_Phrasing: Contents.Text.Time<C>
	// Inherited from Palpable_Phrasing: Contents.Text.U<C>
	Contents.Grouping.Ul<C> // if children include at least one li
	// Inherited from Palpable_Phrasing: Contents.Text.Var<C>
	// Inherited from InteractiveContent and Palpable_Phrasing: Contents.Embedded.Video<C>
	// Inherited from Palpable_Phrasing: // TODO: autonomous custom elements
	// Inherited from Palpable_Phrasing: TextContent<C> // that is not inter-element whitespace
{
}
