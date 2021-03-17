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

import com.aoindustries.html.any.AnyOBJECT_content;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-object-element">4.8.7 The object element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface OBJECT_content<
	__ extends OBJECT_content<__>
> extends AnyOBJECT_content<Document, __>,
	//
	// Unions:
	//
	Union_Embedded_Interactive<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
{
	//
	// Factories:
	//
	// Inherited: AUDIO
	// Inherited: EMBED
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: OBJECT
	// <editor-fold defaultstate="collapsed" desc="PARAM">
	@Override
	default PARAM<__> param() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new PARAM<>(document, pc).writeOpen(document.getUnsafe(null));
	}
	// </editor-fold>
	// Inherited: VIDEO
}
