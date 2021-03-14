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
package com.aoindustries.html.any.attributes.Integer;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.html.any.Attributes;
import static com.aoindustries.html.any.Attributes.RESOURCES;
import com.aoindustries.html.any.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
 * <blockquote>
 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
 * </blockquote>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Tabindex<E extends Element<?, ?, E> & Tabindex<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
	 * <blockquote>
	 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
	 * </blockquote>
	 */
	@Attributes.Funnel
	default E tabindex(int tabindex) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"invalidGlobalAttributeForDoctype",
				element.getDocument().doctype,
				"tabindex"
			);
		}
		return Attributes.Integer.attribute(element, "tabindex", tabindex);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
	 * <blockquote>
	 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
	 * </blockquote>
	 */
	@Attributes.Funnel
	default E tabindex(Integer tabindex) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"invalidGlobalAttributeForDoctype",
				element.getDocument().doctype,
				"tabindex"
			);
		}
		return Attributes.Integer.attribute(element, "tabindex", tabindex);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
	 * <blockquote>
	 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
	 * </blockquote>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #tabindex(java.lang.Integer)
	 */
	default <Ex extends Throwable> E tabindex(IOSupplierE<? extends Integer, Ex> tabindex) throws IOException, Ex {
		return tabindex((tabindex == null) ? null : tabindex.get());
	}
}
