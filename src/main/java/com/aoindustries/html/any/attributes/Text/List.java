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
package com.aoindustries.html.any.attributes.Text;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaWritable;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.html.any.Attributes;
import static com.aoindustries.html.any.Attributes.RESOURCES;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface List<E extends Element<?, ?, E> & List<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E list(Object list) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				element.getDocument().doctype,
				"list"
			);
		}
		return Attributes.Text.attribute(element, "list", MarkupType.NONE, list, true, true, textInXhtmlAttributeEncoder);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #list(java.lang.Object)
	 */
	default <Ex extends Throwable> E list(IOSupplierE<?, Ex> list) throws IOException, Ex {
		return list((list == null) ? null : list.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #list(java.lang.Object)
	 */
	default <Ex extends Throwable> E list(MediaWritable<Ex> list) throws IOException, Ex {
		return list((Object)list);
	}
}
