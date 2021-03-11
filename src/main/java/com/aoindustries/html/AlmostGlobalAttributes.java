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
 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
 * Supported HTML tags:
 * <blockquote>
 * All HTML elements, EXCEPT: &lt;base&gt;, &lt;bdo&gt;, &lt;br&gt;, &lt;head&gt;, &lt;html&gt;, &lt;iframe&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;
 * </blockquote>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface AlmostGlobalAttributes<E extends Element<?, ?, E> & AlmostGlobalAttributes<E>> extends
	// Form
	com.aoindustries.html.attributes.event.form.Onblur<E>,
	com.aoindustries.html.attributes.event.form.Onfocus<E>,
	// Keyboard
	com.aoindustries.html.attributes.event.keyboard.Onkeydown<E>,
	com.aoindustries.html.attributes.event.keyboard.Onkeypress<E>,
	com.aoindustries.html.attributes.event.keyboard.Onkeyup<E>,
	// Mouse
	com.aoindustries.html.attributes.event.mouse.Onclick<E>,
	com.aoindustries.html.attributes.event.mouse.Ondblclick<E>,
	com.aoindustries.html.attributes.event.mouse.Onmousedown<E>,
	com.aoindustries.html.attributes.event.mouse.Onmousemove<E>,
	com.aoindustries.html.attributes.event.mouse.Onmouseout<E>,
	com.aoindustries.html.attributes.event.mouse.Onmouseover<E>,
	com.aoindustries.html.attributes.event.mouse.Onmouseup<E>
{
	// No methods, just adding event types
}
