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

import com.aoindustries.encoding.WhitespaceWriter;
import java.io.IOException;

/**
 * The methods common to all content models.
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#content-models">3.2.5 Content models</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories">Content categories - Developer guides</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Content<__ extends Content<__>> extends WhitespaceWriter<__> {

	/**
	 * Gets the document for the current content model.  The document can be used to
	 * perform raw output or write elements not expected in the current context.
	 */
	Document getDocument();

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ nl() throws IOException {
		getDocument().nl();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ nl(int depthOffset) throws IOException {
		getDocument().nl(depthOffset);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default boolean getIndent() {
		return getDocument().getIndent();
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ setIndent(boolean indent) {
		getDocument().setIndent(indent);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default int getDepth() {
		return getDocument().getDepth();
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ setDepth(int depth) {
		getDocument().setDepth(depth);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ incDepth() {
		getDocument().incDepth();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ decDepth() {
		getDocument().decDepth();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}
}
