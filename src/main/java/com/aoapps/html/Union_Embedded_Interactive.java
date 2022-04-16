/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.html;

import com.aoapps.html.any.AnyOBJECT__;
import com.aoapps.html.any.AnyUnion_Embedded_Interactive;
import com.aoapps.lang.io.function.IOConsumerE;
import com.aoapps.lang.io.function.IOSupplierE;
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
	@SuppressWarnings("deprecation")
	default IMG<__> img() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new IMG<>(document, pc).writeOpen(document.getRawUnsafe(null));
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
	// <editor-fold defaultstate="collapsed" desc="OBJECT">
	@Override
	@SuppressWarnings("deprecation")
	default OBJECT<__> object() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new OBJECT<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates an object element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-object-element">4.8.7 The object element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object">&lt;object&gt;: The External Object element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_object.asp">HTML object tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 * @param  object  The content model of this element, which will be the parent content model of child elements.
	 *                 <p>
	 *                 Due to limitations in Java generics, this content model does not directly reflect the parent content
	 *                 model, despite this being a transparent content model.  Rather, it includes only the content model that
	 *                 always applies to this element type.
	 *                 </p>
	 *                 <p><em>
	 *                 For the full, context-aware content model, which will likely include more elements,
	 *                 {@linkplain AnyOBJECT__#pc() use the parent content model directly}.
	 *                 </em></p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 *
	 * @see  AnyOBJECT__#pc()
	 */
	default <Ex extends Throwable> __ object__(IOConsumerE<? super OBJECT__<__>, Ex> object) throws IOException, Ex {
		return object().__(object);
	}

	@Override
	default OBJECT_c<__> object_c() throws IOException {
		return object()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: VIDEO">
	// </editor-fold>
}
