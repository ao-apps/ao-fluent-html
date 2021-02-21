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
 * <li>See <a href="https://html.spec.whatwg.org/#interactive-content">3.2.5.2.7 Interactive content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#interactive_content">Interactive content</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public interface InteractiveContent<PC extends InteractiveContent<PC>> extends
	//
	// Content models:
	//
	Content,
	//
	// Content types:
	//
	Contents.Text.A<PC>, // if the href attribute is present
	Contents.Embedded.Audio<PC>, // if the controls attribute is present
	Contents.Forms.Button<PC>,
	Contents.Interactive.Details<PC>,
	Contents.Embedded.Embed<PC>,
	Contents.Embedded.Iframe<PC>,
	Contents.Embedded.Img<PC>, // if the usemap attribute is present
	Contents.Forms.Input<PC>, // if type attribute is not in the hidden state
	Contents.Forms.Label<PC>,
	Contents.Grouping.Menu<PC>, // (MDN only) if the type attribute is in the toolbar state
	Contents.Embedded.Object<PC>, // if the usemap attribute is present
	Contents.Forms.Select<PC>,
	Contents.Forms.Textarea<PC>,
	Contents.Embedded.Video<PC> // if the controls attribute is present
{
}
