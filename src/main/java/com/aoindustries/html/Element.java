/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2021  AO Industries, Inc.
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
 * See <a href="https://html.spec.whatwg.org/#elements-2">13.1.2 Elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Should every element have a __() closing method?
//       Should every element implement Closeable, even if does not have a specific __()?
abstract public class Element<E extends Element<E, PC>, PC extends Content<PC>> implements
	WhitespaceWriter<E>,
	// Allow any arbitrary attributes
	com.aoindustries.html.attributes.Text.Attribute<E>,
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	GlobalAttributes<E>
{

	protected final Document document;
	protected final PC pc;

	protected Element(Document document, PC pc) {
		this.document = document;
		this.pc = pc;
	}

	public Document getDocument() {
		return document;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#nl()}.
	 * </p>
	 */
	@Override
	public E nl() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.nl();
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#nli()}.
	 * </p>
	 */
	@Override
	public E nli() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.nli();
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#nli(int)}.
	 * </p>
	 */
	@Override
	public E nli(int depthOffset) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.nli(depthOffset);
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#indent()}.
	 * </p>
	 */
	@Override
	public E indent() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.indent();
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#indent(int)}.
	 * </p>
	 */
	@Override
	public E indent(int depthOffset) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.indent(depthOffset);
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#getIndent()}.
	 * </p>
	 */
	@Override
	public boolean getIndent() {
		return document.getIndent();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#setIndent(boolean)}.
	 * </p>
	 */
	@Override
	public E setIndent(boolean indent) {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.setIndent(indent);
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#getDepth()}.
	 * </p>
	 */
	@Override
	public int getDepth() {
		return document.getDepth();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#setDepth(int)}.
	 * </p>
	 */
	@Override
	public E setDepth(int depth) {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.setDepth(depth);
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#incDepth()}.
	 * </p>
	 */
	@Override
	public E incDepth() {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.incDepth();
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#decDepth()}.
	 * </p>
	 */
	@Override
	public E decDepth() {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.decDepth();
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#sp()}.
	 * </p>
	 */
	@Override
	public E sp() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.sp();
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#sp(int)}.
	 * </p>
	 */
	@Override
	public E sp(int count) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.sp(count);
		return element;
	}

	/**
	 * Writes the beginning of the opening tag.
	 *
	 * @return  The element instance to use.
	 *          This may substitute the element with a different instance, when appropriate.
	 */
	abstract protected E writeOpen() throws IOException;
}
