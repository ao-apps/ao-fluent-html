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
	// Inherited from PalpableContent and PhrasingContent: Content<C>
	// Inherited from PalpableContent: SectioningContent<C>
	// Inherited from PalpableContent: HeadingContent<C>
	PhrasingContent<C>,
	// Inherited from PhrasingContent: EmbeddedContent<C>
	// Inherited from PalpableContent: InteractiveContent<C>
	PalpableContent<C>,
	//
	// Content types:
	//
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.A<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Abbr<C>
	// Inherited from PalpableContent: Contents.Sections.Address<C>
	// Inherited from PhrasingContent: Contents.Embedded.Area<C> // if a descendent of map
	// Inherited from PalpableContent: Contents.Sections.Article<C>
	// Inherited from PalpableContent: Contents.Sections.Aside<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Audio<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.B<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Bdi<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Bdo<C>
	// Inherited from PalpableContent: Contents.Grouping.Blockquote<C>
	// Inherited from PhrasingContent: Contents.Text.Br<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Button<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Scripting.Canvas<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Cite<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Code<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Data<C>
	// Inherited from PhrasingContent: Contents.Forms.Datalist<C>
	// Inherited from PhrasingContent: Contents.Edits.Del<C>
	// Inherited from PalpableContent: Contents.Interactive.Details<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Dfn<C>
	Contents.Interactive.Dialog<C>,
	// Inherited from PalpableContent: Contents.Grouping.Div<C>
	// Inherited from PalpableContent: Contents.Grouping.Dl<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Em<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Embed<C>
	// Inherited from PalpableContent: Contents.Forms.Fieldset<C>
	// Inherited from PalpableContent: Contents.Grouping.Figure<C>
	// Inherited from PalpableContent: Contents.Sections.Footer<C>
	// Inherited from PalpableContent: Contents.Forms.Formv
	// Inherited from PalpableContent: Contents.Sections.H1<C>
	// Inherited from PalpableContent: Contents.Sections.H2<C>
	// Inherited from PalpableContent: Contents.Sections.H3<C>
	// Inherited from PalpableContent: Contents.Sections.H4<C>
	// Inherited from PalpableContent: Contents.Sections.H5<C>
	// Inherited from PalpableContent: Contents.Sections.H6<C>
	// Inherited from PalpableContent: Contents.Sections.Header<C>
	// Inherited from PalpableContent: Contents.Sections.Hgroup<C>
	Contents.Grouping.Hr<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.I<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Iframe<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Img<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Input<C>
	// Inherited from PhrasingContent and PalpableContent: Contents.Edits.Ins<C>
	// Inherited from PhrasingContent and PalpableContent: Contents.Text.Kbd<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Label<C>
	// Inherited from PhrasingContent: Contents.Metadata.Link<C> // if it is allowed in body
	// Inherited from PalpableContent: Contents.Grouping.Main<C> // if it is a hierarchically correct main element
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Map<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Mark<C>
	// Inherited from PalpableContent and PhrasingContent: // TODO: MathML math
	// Inherited from PalpableContent: Contents.Grouping.Menu<C>
	// Inherited from PhrasingContent: Contents.Metadata.Meta<C> // if the itemprop attribute is present
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Meter<C>
	// Inherited from PalpableContent: Contents.Sections.Nav<C>
	// Inherited from PhrasingContent: Contents.Scripting.Noscript<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Object<C>
	// Inherited from PalpableContent: Contents.Grouping.Ol<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Output<C>
	// Inherited from PalpableContent: Contents.Grouping.P<C>
	// Inherited from PhrasingContent: Contents.Embedded.Picture<C>
	// Inherited from PalpableContent: Contents.Grouping.Pre<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Progress<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Q<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Ruby<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.S<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Samp<C>
	// Inherited from PhrasingContent: Contents.Scripting.Script<C>
	// Inherited from PalpableContent: Contents.Sections.Section<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Select<C>
	// Inherited from PhrasingContent: Contents.Scripting.Slot<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Small<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Span<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Strong<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Sub<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Sup<C>
	// Inherited from PalpableContent and PhrasingContent: // TODO: SVG svg
	// Inherited from PalpableContent: Contents.Tabular.Table<C>
	// Inherited from PhrasingContent: Contents.Scripting.Template<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Forms.Textarea<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Time<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.U<C>
	// Inherited from PalpableContent: Contents.Grouping.Ul<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Text.Var<C>
	// Inherited from PalpableContent and PhrasingContent: Contents.Embedded.Video<C>
	// Inherited from PhrasingContent: Contents.Text.Wbr<C>
	// Inherited from PalpableContent and PhrasingContent: // TODO: autonomous custom elements
	// Inherited from PalpableContent and PhrasingContent: TextContent<C>
{
}
