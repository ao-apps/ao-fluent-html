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

import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface META_factory<__ extends Union_Metadata_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new meta element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
	 * </ul>
	 */
	default META<__> meta() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new META<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new meta element with the given name attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
	 * </ul>
	 *
	 * @see #meta()
	 * @see com.aoindustries.html.META#name(java.lang.Enum)
	 */
	default META<__> meta(META.Name name) throws IOException {
		return meta().name(name);
	}

	/**
	 * Opens a new meta element with the given name http-equiv.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
	 * </ul>
	 *
	 * @see #meta()
	 * @see com.aoindustries.html.META#httpEquiv(java.lang.Enum)
	 */
	default META<__> meta(META.HttpEquiv httpEquiv) throws IOException {
		return meta().httpEquiv(httpEquiv);
	}

	/**
	 * Opens a new meta element with the given charset attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
	 * </ul>
	 *
	 * @see #meta()
	 * @see com.aoindustries.html.META#charset(java.lang.Enum)
	 */
	default META<__> meta(com.aoindustries.html.attributes.Enum.Charset.Value charset) throws IOException {
		return meta().charset(charset);
	}
	// No meta__(), since either name, http-equiv, or itemprop is required (TODO: confirm itemprop-only metas?)
}
