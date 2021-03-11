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

import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class BDI<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, BDI<D, PC>, BDI__<D, PC>, BDI_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<BDI<D, PC>>
{

	public BDI(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected BDI<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<bdi", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></bdi>" : "</bdi>", false);
	}

	@Override
	protected BDI__<D, PC> new__() {
		return new BDI__<>(this);
	}

	@Override
	protected BDI_c<D, PC> new_c() {
		return new BDI_c<>(this);
	}

	/**
	 * The {@linkplain GlobalAttributes#dir(java.lang.String) dir} global attribute defaults to <code>auto</code>
	 * on this element (it never inherits from the parent element like with other elements).
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 */
	@Override
	public BDI<D, PC> dir(String dir) throws IOException {
		return super.dir(dir);
	}

	/**
	 * The {@linkplain GlobalAttributes#dir(com.aoindustries.html.Suppliers.String) dir} global attribute defaults to <code>auto</code>
	 * on this element (it never inherits from the parent element like with other elements).
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> BDI<D, PC> dir(Suppliers.String<Ex> dir) throws IOException, Ex {
		return super.dir(dir);
	}

	/**
	 * The {@linkplain GlobalAttributes#dir(java.lang.Enum) dir} global attribute defaults to <code>auto</code>
	 * on this element (it never inherits from the parent element like with other elements).
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 */
	@Override
	public BDI<D, PC> dir(Value dir) throws IOException {
		return super.dir(dir);
	}

	/**
	 * The {@linkplain GlobalAttributes#dir(com.aoindustries.io.function.IOSupplierE) dir} global attribute defaults to <code>auto</code>
	 * on this element (it never inherits from the parent element like with other elements).
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> BDI<D, PC> dir(IOSupplierE<? extends Value, Ex> dir) throws IOException, Ex {
		return super.dir(dir);
	}
}
