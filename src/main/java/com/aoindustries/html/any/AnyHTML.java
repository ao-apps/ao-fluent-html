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
package com.aoindustries.html.any;

import com.aoindustries.encoding.Serialization;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-html-element">4.1.1 The html element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <E>   This element type
 * @param  <__>  This content model, which will be the parent content model of child elements
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation") // TODO: Why here?  Review if needed everywhere is used
abstract public class AnyHTML<
	D  extends AnyDocument<D>,
	PC extends Content<D, PC>,
	E  extends AnyHTML<D, PC, E, __, _c>,
	__ extends AnyHTML__<D, PC, __>,
	// Would prefer "_c extends __ & Closeable<D, PC>", but "a type variable may not be followed by other bounds"
	_c extends AnyHTML_c<D, PC, _c>
> extends
	Normal<D, PC, E, __, _c>
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <html>: AlmostGlobalAttributes<E>
{

	protected AnyHTML(D document, PC pc) {
		super(document, pc);
	}

	/**
	 * Does not have indented content.
	 *
	 * @return {@code false} - does not indent
	 */
	@Override
	protected boolean isContentIndented() {
		return false;
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(
			out,
			(document.serialization == Serialization.XML)
				? "<html xmlns=\"http://www.w3.org/1999/xhtml\""
				: "<html",
			false
		);
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></html>", false);
		} else {
			document.autoNli(out).unsafe(out, "</html>", false);
		}
		document.autoNl(out);
	}

	/**
	 * <p>
	 * In addition to the default <code>lang="…"</code>, also adds <code>xml:lang="…"</code> when the
	 * {@link AnyDocument#serialization} is {@link Serialization#XML}.
	 * </p>
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#attr-xml:lang">Global attributes - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_language_codes.asp">HTML ISO Language Code Reference</a>.</li>
	 * </ul>
	 * <hr>
	 * {@inheritDoc}
	 */
	@Override
	public E lang(String lang) throws IOException {
		// Write default lang="…"
		super.lang(lang);
		@SuppressWarnings("unchecked") E element = (E)this;
		if(document.serialization == Serialization.XML) {
			// Add xml:lang="…"
			Attributes.String.attribute(element, "xml:lang", MarkupType.NONE, lang, true, true);
		}
		return element;
	}

	/**
	 * <p>
	 * In addition to the default <code>lang="…"</code>, also adds <code>xml:lang="…"</code> when the
	 * {@link AnyDocument#serialization} is {@link Serialization#XML}.
	 * </p>
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#attr-xml:lang">Global attributes - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_language_codes.asp">HTML ISO Language Code Reference</a>.</li>
	 * </ul>
	 * <hr>
	 * {@inheritDoc}
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #lang(java.lang.String)
	 */
	@Override
	public <Ex extends Throwable> E lang(IOSupplierE<? extends String, Ex> lang) throws IOException, Ex {
		return super.lang(lang);
	}

	/**
	 * <p>
	 * In addition to the default <code>lang="…"</code>, also adds <code>xml:lang="…"</code> when the
	 * {@link AnyDocument#serialization} is {@link Serialization#XML}.
	 * </p>
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#attr-xml:lang">Global attributes - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_language_codes.asp">HTML ISO Language Code Reference</a>.</li>
	 * </ul>
	 * <hr>
	 * {@inheritDoc}
	 *
	 * @see #lang(java.lang.String)
	 */
	@Override
	public E lang(Locale lang) throws IOException {
		return super.lang(lang);
	}

	/**
	 * <p>
	 * In addition to the default <code>lang="…"</code>, also adds <code>xml:lang="…"</code> when the
	 * {@link AnyDocument#serialization} is {@link Serialization#XML}.
	 * </p>
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#attr-xml:lang">Global attributes - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_language_codes.asp">HTML ISO Language Code Reference</a>.</li>
	 * </ul>
	 * <hr>
	 * {@inheritDoc}
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #lang(java.util.Locale)
	 */
	@Override
	public <Ex extends Throwable> E lang(Suppliers.Locale<Ex> lang) throws IOException, Ex {
		return super.lang(lang);
	}
}
