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

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class ABBR<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, ABBR<D, PC>, ABBR__<D, PC>, ABBR_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<ABBR<D, PC>>
{

	public ABBR(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected ABBR<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<abbr", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></abbr>" : "</abbr>", false);
	}

	@Override
	protected ABBR__<D, PC> new__() {
		return new ABBR__<>(this);
	}

	@Override
	protected ABBR_c<D, PC> new_c() {
		return new ABBR_c<>(this);
	}

	/**
	 * The {@linkplain GlobalAttributes#title(java.lang.Object) title} attribute
	 * <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#attr-abbr-title">has special semantics</a>
	 * on this element: Full term or expansion of abbreviation.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 */
	@Override
	public ABBR<D, PC> title(Object title) throws IOException {
		return super.title(title);
	}

	/**
	 * The {@linkplain GlobalAttributes#title(com.aoindustries.io.function.IOSupplierE) title} attribute
	 * <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#attr-abbr-title">has special semantics</a>
	 * on this element: Full term or expansion of abbreviation.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> ABBR<D, PC> title(IOSupplierE<?, Ex> title) throws IOException, Ex {
		return super.title(title);
	}

	/**
	 * The {@linkplain GlobalAttributes#title(com.aoindustries.encoding.MediaWritable) title} attribute
	 * <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#attr-abbr-title">has special semantics</a>
	 * on this element: Full term or expansion of abbreviation.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> ABBR<D, PC> title(MediaWritable<Ex> title) throws IOException, Ex {
		return super.title(title);
	}
}
