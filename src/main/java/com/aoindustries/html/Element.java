/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2021  AO Industries, Inc.
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

/**
 * See <a href="https://html.spec.whatwg.org/#elements-2">13.1.2 Elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Should every element have a __() closing method?
abstract public class Element<E extends Element<E, PC>, PC extends Content<PC>> implements
	Whitespace<E>,
	// Allow any arbitrary attributes
	Attributes.Text.Attribute<E>,
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Global<E>
{

	protected final Document document;
	protected final PC pc;

	protected Element(Document document, PC pc) {
		this.document = document;
		this.pc = pc;
	}

	@Override
	public E nl() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.nl();
		return element;
	}

	/**
	 * Writes the beginning of the opening tag.
	 *
	 * @return  The element instance to use.
	 *          This may substitute the element with a different instance, when appropriate.
	 */
	abstract protected E writeOpen() throws IOException;
}
