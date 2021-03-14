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
package com.aoindustries.html.any;

import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-param-element">4.8.8 The param element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
abstract public class AnyPARAM<
	D  extends AnyDocument<D>,
	// TODO: PC extends AnyObjectContent<D, PC>
	PC extends Content<D, PC>,
	E  extends AnyPARAM<D, PC, E>
> extends Void<D, PC, E> implements
	com.aoindustries.html.any.attributes.Text.Name<E>,
	// TODO: type (deprecated)
	com.aoindustries.html.any.attributes.Text.Value<E>
	// TODO: valuetype (deprecated)
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <param>: AlmostGlobalAttributes<E>
{

	protected AnyPARAM(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<param", false);
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	@Override
	protected void doAfterElement(Writer out) throws IOException {
		document.autoNl(out);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_param_name.asp">HTML param name Attribute</a>.
	 */
	@Override
	public E name(Object name) throws IOException {
		// Overridden to not trim-to-null
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Text.attribute(element, "name", MarkupType.NONE, name, false, false, textInXhtmlAttributeEncoder);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_param_value.asp">HTML param value Attribute</a>.
	 */
	@Override
	public E value(Object value) throws IOException {
		// Overridden to not trim-to-null
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Text.attribute(element, "value", MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
	}
}
