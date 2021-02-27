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
package com.aoindustries.html.attributes.Text;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
 * <blockquote>
 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
 * </blockquote>
 *
 * @author  AO Industries, Inc.
 */
public interface StyleNoHtml4<E extends Element<E, ?> & StyleNoHtml4<E>> extends Style<E> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
	 * <blockquote>
	 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
	 * </blockquote>
	 */
	@Override
	@Attributes.Funnel
	default E style(Object style) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"invalidGlobalAttributeForDoctype",
				element.getDocument().doctype,
				"style"
			);
		}
		return Style.super.style(style);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
	 * <blockquote>
	 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
	 * </blockquote>
	 */
	@Override
	@Attributes.Funnel
	default E style(Object ... style) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"invalidGlobalAttributeForDoctype",
				element.getDocument().doctype,
				"style"
			);
		}
		return Style.super.style(style);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
	 * <blockquote>
	 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
	 * </blockquote>
	 *
	 * @see #style(java.lang.Object)
	 */
	@Override
	default <Ex extends Throwable> E style(IOSupplierE<?, Ex> style) throws IOException, Ex {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"invalidGlobalAttributeForDoctype",
				element.getDocument().doctype,
				"style"
			);
		}
		return Style.super.style(style);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
	 * <blockquote>
	 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
	 * </blockquote>
	 *
	 * @see #style(java.lang.Object)
	 */
	@Override
	default <Ex extends Throwable> E style(MediaWritable<Ex> style) throws IOException, Ex {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"invalidGlobalAttributeForDoctype",
				element.getDocument().doctype,
				"style"
			);
		}
		return Style.super.style(style);
	}
}
