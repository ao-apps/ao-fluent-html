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
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Phrasing content, but there must be no dfn element descendants.
public class DFN<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, DFN<D, PC>, DFN__<D, PC>, DFN_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<DFN<D, PC>>
{

	public DFN(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected DFN<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<dfn", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></dfn>" : "</dfn>", false);
	}

	@Override
	protected DFN__<D, PC> new__() {
		return new DFN__<>(this);
	}

	@Override
	protected DFN_c<D, PC> new_c() {
		return new DFN_c<>(this);
	}

	/**
	 * The {@linkplain GlobalAttributes#title(java.lang.Object) title} attribute
	 * <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#attr-dfn-title">has special semantics</a>
	 * on this element: Full term or expansion of abbreviation.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 */
	@Override
	public DFN<D, PC> title(Object title) throws IOException {
		return super.title(title);
	}

	/**
	 * The {@linkplain GlobalAttributes#title(com.aoindustries.io.function.IOSupplierE) title} attribute
	 * <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#attr-dfn-title">has special semantics</a>
	 * on this element: Full term or expansion of abbreviation.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> DFN<D, PC> title(IOSupplierE<?, Ex> title) throws IOException, Ex {
		return super.title(title);
	}

	/**
	 * The {@linkplain GlobalAttributes#title(com.aoindustries.encoding.MediaWritable) title} attribute
	 * <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#attr-dfn-title">has special semantics</a>
	 * on this element: Full term or expansion of abbreviation.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> DFN<D, PC> title(MediaWritable<Ex> title) throws IOException, Ex {
		return super.title(title);
	}
}
