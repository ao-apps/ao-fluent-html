/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020, 2021  AO Industries, Inc.
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

import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.
 * See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Param extends EmptyElement<Param> implements
	Attributes.Text.Name<Param>,
	// TODO: type (deprecated)
	Attributes.Text.Value<Param>
	// TODO: valuetype (deprecated)
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <param>: Attributes.Event.AlmostGlobal<Param>
{

	public Param(Document document) {
		super(document);
	}

	@Override
	protected Param open() throws IOException {
		document.out.write("<param");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_param_name.asp">HTML param name Attribute</a>.
	 */
	@Override
	public Param name(Object name) throws IOException {
		// Overridden to not trim-to-null
		return com.aoindustries.html.Attributes.Text.attribute(this, "name", MarkupType.NONE, name, false, false, textInXhtmlAttributeEncoder);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_param_value.asp">HTML param value Attribute</a>.
	 */
	@Override
	public Param value(Object value) throws IOException {
		// Overridden to not trim-to-null
		return com.aoindustries.html.Attributes.Text.attribute(this, "value", MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
	}
}
