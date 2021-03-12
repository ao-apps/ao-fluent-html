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
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("deprecation")
public class HTML<
	D  extends AnyDocument<D>,
	PC extends Content<D, PC>
> extends
	Normal<D, PC, HTML<D, PC>, HTML__<D, PC>, HTML_c<D, PC>>
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <html>: AlmostGlobalAttributes<HTML<D, PC>>
{

	public HTML(D document, PC pc) {
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
	protected HTML<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(
			out,
			(document.serialization == Serialization.XML)
				? "<html xmlns=\"http://www.w3.org/1999/xhtml\""
				: "<html",
			false
		);
		return this;
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

	@Override
	protected HTML__<D, PC> new__() {
		return new HTML__<>(this);
	}

	@Override
	protected HTML_c<D, PC> new_c() {
		return new HTML_c<>(this);
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
	public HTML<D, PC> lang(String lang) throws IOException {
		// Write default lang="…"
		super.lang(lang);
		if(document.serialization == Serialization.XML) {
			// Add xml:lang="…"
			Attributes.String.attribute(this, "xml:lang", MarkupType.NONE, lang, true, true);
		}
		return this;
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
	public <Ex extends Throwable> HTML<D, PC> lang(IOSupplierE<? extends String, Ex> lang) throws IOException, Ex {
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
	public HTML<D, PC> lang(Locale lang) throws IOException {
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
	public <Ex extends Throwable> HTML<D, PC> lang(Suppliers.Locale<Ex> lang) throws IOException, Ex {
		return super.lang(lang);
	}
}
