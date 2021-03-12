/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#global-attributes">3.2.6 Global attributes</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/ref_standardattributes.asp">HTML Global attributes</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public interface GlobalAttributes<E extends Element<?, ?, E> & GlobalAttributes<E>> extends
	// TODO: accesskey
	// TODO: autocapitalize
	// TODO: autofocus (whatwg only)
	com.aoindustries.html.attributes.Text.Class<E>,
	// TODO: contenteditable
	// TODO: contextmenu (deprecated)
	com.aoindustries.html.attributes.Text.Data<E>,
	com.aoindustries.html.attributes.Enum.Dir<E, com.aoindustries.html.attributes.Enum.Dir.Value>,
	// TODO: draggable
	// TODO: dropzone (experimental)
	// TODO: enterkeyhint (whatwg only)
	// TODO: exportparts (experimental)
	// TODO: hidden
	com.aoindustries.html.attributes.Text.Id<E>,
	// TODO: inputmode
	// TODO: is
	// TODO: itemid
	// TODO: itemprop
	// TODO: itemref
	// TODO: itemscope
	// TODO: itemtype
	com.aoindustries.html.attributes.String.Lang<E>,
	// TODO: none (whatwg only)
	// TODO: part (experimental)
	// TODO: slot
	// TODO: spellcheck (experimental)
	com.aoindustries.html.attributes.Text.Style<E>,
	com.aoindustries.html.attributes.Integer.Tabindex<E>,
	com.aoindustries.html.attributes.Text.Title<E>,
	// TODO: translate (experimental)
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Form
	com.aoindustries.html.attributes.event.form.Oncontextmenu<E>,
	// TODO: onautocomplete
	// TODO: onautocompleteerror
	// Mouse
	com.aoindustries.html.attributes.event.mouse.Onmousewheel<E>,
	com.aoindustries.html.attributes.event.mouse.Onwheel<E>,
	// Drag
	com.aoindustries.html.attributes.event.drag.Ondrag<E>,
	com.aoindustries.html.attributes.event.drag.Ondragend<E>,
	com.aoindustries.html.attributes.event.drag.Ondragenter<E>,
	com.aoindustries.html.attributes.event.drag.Ondragleave<E>,
	com.aoindustries.html.attributes.event.drag.Ondragover<E>,
	com.aoindustries.html.attributes.event.drag.Ondragstart<E>,
	com.aoindustries.html.attributes.event.drag.Ondrop<E>,
	// Clipboard
	com.aoindustries.html.attributes.event.clipboard.Oncopy<E>,
	com.aoindustries.html.attributes.event.clipboard.Oncut<E>,
	com.aoindustries.html.attributes.event.clipboard.Onpaste<E>
	// TODO: onsort? https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
{
	// No methods, just adding attributes
}
