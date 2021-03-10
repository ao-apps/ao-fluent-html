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

import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-base-element">4.2.3 The base element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class BASE<PC extends MetadataContent<PC>> extends VoidElement<BASE<PC>, PC> implements
	com.aoindustries.html.attributes.Url.Href<BASE<PC>>,
	com.aoindustries.html.attributes.Enum.Target<BASE<PC>, com.aoindustries.html.attributes.Enum.Target.Value>
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <base>: AlmostGlobalAttributes<BASE<PC>>
{

	public BASE(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected BASE<PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<base", false);
		return this;
	}

	@Override
	protected void doAfterElement(Writer out) throws IOException {
		document.autoNl(out);
	}
}
