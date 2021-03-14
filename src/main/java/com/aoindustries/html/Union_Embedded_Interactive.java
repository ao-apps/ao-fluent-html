/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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

import com.aoindustries.html.any.AnyUnion_Embedded_Interactive;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * Elements that are common to both {@link EmbeddedContent} and {@link InteractiveContent}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Embedded_Interactive<
	__ extends Union_Embedded_Interactive<__>
> extends AnyUnion_Embedded_Interactive<Document, __>,
	//
	// Content models:
	//
	Content<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="TODO: AUDIO">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: EMBED">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: IFRAME">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="IMG">
	@Override
	default IMG<__> img() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new IMG<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	@Override
	default IMG<__> img(String src) throws IOException {
		return img().src(src);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> IMG<__> img(IOSupplierE<? extends String, Ex> src) throws IOException, Ex {
		return img().src(src);
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: OBJECT">
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: VIDEO">
	// </editor-fold>
}
