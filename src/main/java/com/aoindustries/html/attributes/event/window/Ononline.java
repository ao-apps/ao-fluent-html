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
package com.aoindustries.html.attributes.event.window;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Element;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
 *
 * @author  AO Industries, Inc.
 */
public interface Ononline<E extends Element<E, ?> & Ononline<E>> {

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
	 */
	@Attributes.Funnel
	default E ononline(Object ononline) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				element.getDocument().doctype,
				"ononline"
			);
		}
		return Attributes.Event.attribute(element, "ononline", ononline);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
	 *
	 * @see #ononline(java.lang.Object)
	 */
	default <Ex extends Throwable> E ononline(IOSupplierE<?, Ex> ononline) throws IOException, Ex {
		return ononline((ononline == null) ? null : ononline.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
	 *
	 * @see #ononline(java.lang.Object)
	 */
	default <Ex extends Throwable> E ononline(MediaWritable<Ex> ononline) throws IOException, Ex {
		return ononline((Object)ononline);
	}
}
