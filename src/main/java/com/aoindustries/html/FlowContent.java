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
 * <li>See <a href="https://html.spec.whatwg.org/#flow-content">3.2.5.2.2 Flow content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#flow_content">Flow content</a>.</li>
 * </ul>
 *
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface FlowContent<C extends FlowContent<C>> extends
	//
	// Content models:
	//
	// Inherited from PalpableContent and PhrasingContent: Content
	// Inherited from PalpableContent: SectioningContent<PC>
	// Inherited from PalpableContent: HeadingContent<PC>
	PhrasingContent<C>,
	// Inherited from PhrasingContent: EmbeddedContent<PC>
	// Inherited from PalpableContent: InteractiveContent<PC>
	PalpableContent<C>,
	//
	// Content types:
	//
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.A<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Abbr<PC>
	// Inherited from PalpableContent: Contents.Sections.Address<PC>
	// Inherited from PhrasingContent: Contents.Embedded.Area<PC> // if a descendent of map
	// Inherited from PalpableContent: Contents.Sections.Article<PC>
	// Inherited from PalpableContent: Contents.Sections.Aside<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Audio<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.B<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Bdi<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Bdo<PC>
	// Inherited from PalpableContent: Contents.Grouping.Blockquote<PC>
	// Inherited from PhrasingContent: Contents.Text.Br<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Button<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Scripting.Canvas<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Cite<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Code<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Data<PC>
	// Inherited from PhrasingContent: Contents.Forms.Datalist<PC>
	// Inherited from PhrasingContent: Contents.Edits.Del<PC>
	// Inherited from PalpableContent: Contents.Interactive.Details<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Dfn<PC>
	Contents.Interactive.Dialog<C>,
	// Inherited from PalpableContent: Contents.Grouping.Div<PC>
	// Inherited from PalpableContent: Contents.Grouping.Dl<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Em<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Embed<PC>
	// Inherited from PalpableContent: Contents.Forms.Fieldset<PC>
	// Inherited from PalpableContent: Contents.Grouping.Figure<PC>
	// Inherited from PalpableContent: Contents.Sections.Footer<PC>
	// Inherited from PalpableContent: Contents.Forms.Formv
	// Inherited from PalpableContent: Contents.Sections.H1<PC>
	// Inherited from PalpableContent: Contents.Sections.H2<PC>
	// Inherited from PalpableContent: Contents.Sections.H3<PC>
	// Inherited from PalpableContent: Contents.Sections.H4<PC>
	// Inherited from PalpableContent: Contents.Sections.H5<PC>
	// Inherited from PalpableContent: Contents.Sections.H6<PC>
	// Inherited from PalpableContent: Contents.Sections.Header<PC>
	// Inherited from PalpableContent: Contents.Sections.Hgroup<PC>
	Contents.Grouping.Hr<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.I<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Iframe<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Img<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Input<PC>
	// Inherited from PhrasingContent and PalpableContent: Contents.Edits.Ins<PC>
	// Inherited from PhrasingContent and PalpableContent: Contents.Text.Kbd<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Label<PC>
	// Inherited from PhrasingContent: Contents.Metadata.Link<PC> // if it is allowed in body
	// Inherited from PalpableContent: Contents.Grouping.Main<PC> // if it is a hierarchically correct main element
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Map<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Mark<PC>
	// Inherited from PalpableContent and PhrasingContent: // TODO: MathML math
	// Inherited from PalpableContent: Contents.Grouping.Menu<PC>
	// Inherited from PhrasingContent: Contents.Metadata.Meta<PC> // if the itemprop attribute is present
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Meter<PC>
	// Inherited from PalpableContent: Contents.Sections.Nav<PC>
	// Inherited from PhrasingContent: Contents.Scripting.Noscript<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Object<PC>
	// Inherited from PalpableContent: Contents.Grouping.Ol<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Output<PC>
	// Inherited from PalpableContent: Contents.Grouping.P<PC>
	// Inherited from PhrasingContent: Contents.Embedded.Picture<PC>
	// Inherited from PalpableContent: Contents.Grouping.Pre<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Progress<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Q<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Ruby<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.S<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Samp<PC>
	// Inherited from PhrasingContent: Contents.Scripting.Script<PC>
	// Inherited from PalpableContent: Contents.Sections.Section<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Select<PC>
	// Inherited from PhrasingContent: Contents.Scripting.Slot<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Small<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Span<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Strong<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Sub<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Sup<PC>
	// Inherited from PalpableContent and PhrasingContent: // TODO: SVG svg
	// Inherited from PalpableContent: Contents.Tabular.Table<PC>
	// Inherited from PhrasingContent: Contents.Scripting.Template<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Textarea<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Time<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.U<PC>
	// Inherited from PalpableContent: Contents.Grouping.Ul<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Var<PC>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Video<PC>
	// Inherited from PhrasingContent: Contents.Text.Wbr<PC>
	// Inherited from PalpableContent and PhrasingContent: // TODO: autonomous custom elements
	// Inherited from PalpableContent and PhrasingContent: TextContent<PC>
{
}
