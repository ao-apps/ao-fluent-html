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

import com.aoindustries.html.attributes.Enum.Dir.Value;
import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface BDO_factory<__ extends Union_Palpable_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new bdo element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 */
	default BDO<__> bdo() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new BDO<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 */
	default BDO<__> bdo(String dir) throws IOException {
		return bdo().dir(dir);
	}

	/**
	 * Opens a new bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 */
	default <Ex extends Throwable> BDO<__> bdo(Suppliers.String<Ex> dir) throws IOException, Ex {
		return bdo().dir(dir);
	}

	/**
	 * Opens a new bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 */
	default BDO<__> bdo(Value dir) throws IOException {
		return bdo().dir(dir);
	}

	/**
	 * Opens a new bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 */
	default <Ex extends Throwable> BDO<__> bdo(IOSupplierE<? extends Value, Ex> dir) throws IOException, Ex {
		return bdo().dir(dir);
	}

	/**
	 * Creates a bdo element with the given dir attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ bdo__(Value dir, IORunnableE<Ex> bdo) throws IOException, Ex {
		return bdo(dir).__(bdo);
	}

	/**
	 * Creates a bdo element with the given dir attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ bdo__(Value dir, IOConsumerE<? super BDO__<__>, Ex> bdo) throws IOException, Ex {
		return bdo(dir).__(bdo);
	}

	/**
	 * Creates a bdo element with the given dir attribute and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ bdo__(Value dir, Object text) throws IOException {
		return bdo(dir).__(text);
	}

	/**
	 * Creates an empty bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ bdo__(Value dir) throws IOException {
		return bdo(dir).__();
	}

	/**
	 * Creates a bdo element with the given dir attribute then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default BDO_c<__> bdo_c(Value dir) throws IOException {
		return bdo(dir)._c();
	}
}
