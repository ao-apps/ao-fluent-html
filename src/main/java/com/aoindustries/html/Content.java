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
import com.aoindustries.io.Writable;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;
import java.io.Writer;

/**
 * The methods common to all content models.
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#content-models">3.2.5 Content models</a>.</li>
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

	// <editor-fold desc="Unsafe">
	/**
	 * Gets the current writer this document is writing to, which may be used for raw output.
	 * <p>
	 * Please prefer {@link #unsafe()}, which is compatible with try-with-resources blocks.
	 * The writer returned here is the real, underlying writer.
	 * </p>
	 *
	 * @param  endsNewline  Indicates whether the data that will be written will end in a {@link #NL}.
	 *                      When non-null, will call {@link #setAtnl(boolean)} with the given value.
	 *
	 * @throws  IllegalStateException  when output has been set to {@code null}.
	 *
	 * @see  #getUnsafe()
	 * @see  Document#setOut(java.io.Writer)
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default Writer getUnsafe(Boolean endsNewline) throws IllegalStateException {
		return getDocument().getUnsafe(endsNewline);
	}

	/**
	 * Gets the current writer this document is writing to, which may be used for raw output.
	 * <p>
	 * Please prefer {@link #unsafe()}, which is compatible with try-with-resources blocks.
	 * The writer returned here is the real, underlying writer.
	 * </p>
	 * <p>
	 * With no knowledge of what will be written, calls {@link #clearAtnl()} to be safe.
	 * </p>
	 *
	 * @throws  IllegalStateException  when output has been set to {@code null}.
	 *
	 * @see  #getUnsafe(java.lang.Boolean)
	 * @see  Document#setOut(java.io.Writer)
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default Writer getUnsafe() throws IllegalStateException {
		return getDocument().getUnsafe();
	}

	/**
	 * Performs raw output of a single character, automatically determining {@link #setAtnl(boolean)}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(char ch) throws IOException {
		getDocument().unsafe(ch);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output, automatically determining {@link #setAtnl(boolean)}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(char[] cbuf) throws IOException {
		getDocument().unsafe(cbuf);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output, automatically determining {@link #setAtnl(boolean)}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(char[] cbuf, int offset, int len) throws IOException {
		getDocument().unsafe(cbuf, offset, len);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output, automatically determining {@link #setAtnl(boolean)}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(CharSequence csq) throws IOException {
		getDocument().unsafe(csq);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output, automatically determining {@link #setAtnl(boolean)}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(CharSequence csq, int start, int end) throws IOException {
		getDocument().unsafe(csq, start, end);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output, automatically determining {@link #setAtnl(boolean)}.
	 * <p>
	 * When no knowledge of what will be written, calls {@link #clearAtnl()} to be safe.
	 * </p>
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(Object unsafe) throws IOException {
		getDocument().unsafe(unsafe);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output, automatically determining {@link #setAtnl(boolean)}.
	 * <p>
	 * When no knowledge of what will be written, calls {@link #clearAtnl()} to be safe.
	 * </p>
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default <Ex extends Throwable> __ unsafe(IOSupplierE<?, Ex> unsafe) throws IOException, Ex {
		getDocument().unsafe(unsafe);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output.
	 * <p>
	 * With no knowledge of what will be written, calls {@link #clearAtnl()} to be safe.
	 * </p>
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ unsafe(Writable unsafe) throws IOException {
		getDocument().unsafe(unsafe);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs raw output.
	 * This is well suited for use in a try-with-resources block.
	 * <p>
	 * With no knowledge of what will be written, calls {@link #clearAtnl()} to be safe.
	 * </p>
	 *
	 * @return  a writer for direct output, which will ignore any calls to {@link Writer#close()}
	 *          to be safely used in a try-with-resources block.
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default Writer unsafe() throws IOException {
		return getDocument().unsafe();
	}

	// TODO: Include a new interface similar to TextContent called "Unsafe" that Content would also extend?
	// </editor-fold>

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ nl() throws IOException {
		getDocument().nl();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ nli() throws IOException {
		getDocument().nli();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ nli(int depthOffset) throws IOException {
		getDocument().nli(depthOffset);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ indent() throws IOException {
		getDocument().indent();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ indent(int depthOffset) throws IOException {
		getDocument().indent(depthOffset);
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

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ sp() throws IOException {
		getDocument().sp();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// Note: Must be implemented in Document to avoid infinite recursion
	@Override
	default __ sp(int count) throws IOException {
		getDocument().sp(count);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	// <editor-fold desc="Automatic Newline and Indentation">
	/**
	 * Gets if automatic newline (and indentation when {@linkplain #getIndent() enabled}) is currently enabled,
	 * off by default.
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default boolean getAutonli() {
		return getDocument().getAutonli();
	}

	/**
	 * Enables or disabled automatic newline (and indentation when {@linkplain #getIndent() enabled}).
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ setAutonli(boolean autonli) {
		getDocument().setAutonli(autonli);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Gets the at newline flag.
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default boolean getAtnl() {
		return getDocument().getAtnl();
	}

	/**
	 * Flags is at a newline.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ setAtnl() {
		getDocument().setAtnl();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Sets the at newline flag.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ setAtnl(boolean atnl) {
		getDocument().setAtnl(atnl);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Clears the at newline flag.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ clearAtnl() {
		getDocument().clearAtnl();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs automatic newline when
	 * {@link #getAutonli()} and not {@link #getAtnl()}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ autoNl() throws IOException {
		getDocument().autoNl();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs automatic newline when {@link #getAutonli()} and not {@link #getAtnl()},
	 * followed by automatic indentation when {@linkplain #getIndent() enabled})
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ autoNli() throws IOException {
		getDocument().autoNli();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs automatic newline when {@link #getAutonli()} and not {@link #getAtnl()},
	 * followed by automatic indentation with a depth offset when {@linkplain #getIndent() enabled})
	 *
	 * @param  depthOffset  A value added to the current indentation depth.
	 *                      For example, pass {@code -1} when performing a newline before a closing tag or ending curly brace.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ autoNli(int depthOffset) throws IOException {
		getDocument().autoNli(depthOffset);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs automatic indentation when
	 * {@link #getAutonli()}, {@link #getIndent()}, and {@link #getAtnl()}.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ autoIndent() throws IOException {
		getDocument().autoIndent();
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}

	/**
	 * Performs automatic indentation with a depth offset when
	 * {@link #getAutonli()}, {@link #getIndent()}, and {@link #getAtnl()}.
	 *
	 * @param  depthOffset  A value added to the current indentation depth.
	 *                      For example, pass {@code -1} when performing a newline before a closing tag or ending curly brace.
	 *
	 * @return  {@code this} content model
	 */
	// Note: Must be implemented in Document to avoid infinite recursion
	default __ autoIndent(int depthOffset) throws IOException {
		getDocument().autoIndent(depthOffset);
		@SuppressWarnings("unchecked") __ c = (__)this;
		return c;
	}
	// </editor-fold>
}
