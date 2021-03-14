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

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <E>   This element type
 * @param  <__>  This content model, which will be the parent content model of child elements
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class AnyABBR<
	D  extends AnyDocument<D>,
	PC extends AnyUnion_Palpable_Phrasing<D, PC>,
	E  extends AnyABBR<D, PC, E, __, _c>,
	__ extends AnyABBR__<D, PC, __>,
	// Would prefer "_c extends __ & Closeable<D, PC>", but "a type variable may not be followed by other bounds"
	_c extends AnyABBR_c<D, PC, _c>
> extends
	NormalText<D, PC, E, __, _c> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<E>
{

	protected AnyABBR(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<abbr", false);
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></abbr>" : "</abbr>", false);
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
	public E title(Object title) throws IOException {
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
	public <Ex extends Throwable> E title(IOSupplierE<?, Ex> title) throws IOException, Ex {
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
	public <Ex extends Throwable> E title(MediaWritable<Ex> title) throws IOException, Ex {
		return super.title(title);
	}
}
