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

import com.aoindustries.html.attributes.Enum.Dir;
import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * Elements that are common to both {@link PalpableContent} and {@link PhrasingContent}.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Palpable_Phrasing<
	D  extends AnyDocument<D>,
	__ extends Union_Palpable_Phrasing<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Embedded_Interactive<D, __>
	Union_Embedded_Palpable_Phrasing<D, __>,
	Union_Interactive_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	TextContent<D, __> // that is not inter-element whitespace
{
	//
	// Factories:
	//
	// Inherited: A
	// <editor-fold defaultstate="collapsed" desc="ABBR">
	/**
	 * Opens a new abbr element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 */
	@Factory("abbr")
	default ABBR<D, __> abbr() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new ABBR<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an abbr element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("abbr")
	default <Ex extends Throwable> __ abbr__(IORunnableE<Ex> abbr) throws IOException, Ex {
		return abbr().__(abbr);
	}

	/**
	 * Creates an abbr element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("abbr")
	default <Ex extends Throwable> __ abbr__(IOConsumerE<? super ABBR__<D, __>, Ex> abbr) throws IOException, Ex {
		return abbr().__(abbr);
	}

	/**
	 * Creates an abbr element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("abbr")
	default __ abbr__(Object text) throws IOException {
		return abbr().__(text);
	}

	/**
	 * Creates an empty abbr element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("abbr")
	default __ abbr__() throws IOException {
		return abbr().__();
	}

	/**
	 * Creates an abbr element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-abbr-element">4.5.9 The abbr element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("abbr")
	default ABBR_c<D, __> abbr_c() throws IOException {
		return abbr()._c();
	}
	// </editor-fold>
	// Inherited: AUDIO
	// <editor-fold defaultstate="collapsed" desc="B">
	/**
	 * Opens a new b element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-b-element">4.5.21 The b element</a>.
	 * </p>
	 */
	@Factory("b")
	default B<D, __> b() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new B<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a b element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-b-element">4.5.21 The b element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("b")
	default <Ex extends Throwable> __ b__(IORunnableE<Ex> b) throws IOException, Ex {
		return b().__(b);
	}

	/**
	 * Creates a b element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-b-element">4.5.21 The b element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("b")
	default <Ex extends Throwable> __ b__(IOConsumerE<? super B__<D, __>, Ex> b) throws IOException, Ex {
		return b().__(b);
	}

	/**
	 * Creates a b element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-b-element">4.5.21 The b element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("b")
	default __ b__(Object text) throws IOException {
		return b().__(text);
	}

	/**
	 * Creates an empty b element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-b-element">4.5.21 The b element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("b")
	default __ b__() throws IOException {
		return b().__();
	}

	/**
	 * Creates a b element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-b-element">4.5.21 The b element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("b")
	default B_c<D, __> b_c() throws IOException {
		return b()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="BDI">
	/**
	 * Opens a new bdi element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 */
	@Factory("bdi")
	default BDI<D, __> bdi() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new BDI<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a bdi element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("bdi")
	default <Ex extends Throwable> __ bdi__(IORunnableE<Ex> bdi) throws IOException, Ex {
		return bdi().__(bdi);
	}

	/**
	 * Creates a bdi element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("bdi")
	default <Ex extends Throwable> __ bdi__(IOConsumerE<? super BDI__<D, __>, Ex> bdi) throws IOException, Ex {
		return bdi().__(bdi);
	}

	/**
	 * Creates a bdi element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("bdi")
	default __ bdi__(Object text) throws IOException {
		return bdi().__(text);
	}

	/**
	 * Creates an empty bdi element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("bdi")
	default __ bdi__() throws IOException {
		return bdi().__();
	}

	/**
	 * Creates a bdi element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdi-element">4.5.24 The bdi element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("bdi")
	default BDI_c<D, __> bdi_c() throws IOException {
		return bdi()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="BDO">
	/**
	 * Opens a new bdo element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 */
	@Factory("bdo")
	default BDO<D, __> bdo() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
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
	@Factory("bdo")
	default BDO<D, __> bdo(String dir) throws IOException {
		return bdo().dir(dir);
	}

	/**
	 * Opens a new bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 */
	@Factory("bdo")
	default <Ex extends Throwable> BDO<D, __> bdo(Suppliers.String<Ex> dir) throws IOException, Ex {
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
	@Factory("bdo")
	default BDO<D, __> bdo(Dir.Value dir) throws IOException {
		return bdo().dir(dir);
	}

	/**
	 * Opens a new bdo element with the given dir attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 */
	@Factory("bdo")
	default <Ex extends Throwable> BDO<D, __> bdo(IOSupplierE<? extends Dir.Value, Ex> dir) throws IOException, Ex {
		return bdo().dir(dir);
	}

	/**
	 * Creates a bdo element with the given dir attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("bdo")
	default <Ex extends Throwable> __ bdo__(Dir.Value dir, IORunnableE<Ex> bdo) throws IOException, Ex {
		return bdo(dir).__(bdo);
	}

	/**
	 * Creates a bdo element with the given dir attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-bdo-element">4.5.25 The bdo element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 * @param  dir  <em>The <code>auto</code> value must not be specified.</em>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("bdo")
	default <Ex extends Throwable> __ bdo__(Dir.Value dir, IOConsumerE<? super BDO__<D, __>, Ex> bdo) throws IOException, Ex {
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
	@Factory("bdo")
	default __ bdo__(Dir.Value dir, Object text) throws IOException {
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
	@Factory("bdo")
	default __ bdo__(Dir.Value dir) throws IOException {
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
	@Factory("bdo")
	default BDO_c<D, __> bdo_c(Dir.Value dir) throws IOException {
		return bdo(dir)._c();
	}
	// </editor-fold>
	// Inherited: BUTTON
	// Inherited: CANVAS
	// <editor-fold defaultstate="collapsed" desc="CITE">
	/**
	 * Opens a new cite element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
	 * </p>
	 */
	@Factory("cite")
	default CITE<D, __> cite() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new CITE<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a cite element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("cite")
	default <Ex extends Throwable> __ cite__(IORunnableE<Ex> cite) throws IOException, Ex {
		return cite().__(cite);
	}

	/**
	 * Creates a cite element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("cite")
	default <Ex extends Throwable> __ cite__(IOConsumerE<? super CITE__<D, __>, Ex> cite) throws IOException, Ex {
		return cite().__(cite);
	}

	/**
	 * Creates a cite element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("cite")
	default __ cite__(Object text) throws IOException {
		return cite().__(text);
	}

	/**
	 * Creates an empty cite element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("cite")
	default __ cite__() throws IOException {
		return cite().__();
	}

	/**
	 * Creates a cite element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-cite-element">4.5.6 The cite element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("cite")
	default CITE_c<D, __> cite_c() throws IOException {
		return cite()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="CODE">
	/**
	 * Opens a new code element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-code-element">4.5.15 The code element</a>.
	 * </p>
	 */
	@Factory("code")
	default CODE<D, __> code() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new CODE<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a code element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-code-element">4.5.15 The code element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("code")
	default <Ex extends Throwable> __ code__(IORunnableE<Ex> code) throws IOException, Ex {
		return code().__(code);
	}

	/**
	 * Creates a code element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-code-element">4.5.15 The code element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("code")
	default <Ex extends Throwable> __ code__(IOConsumerE<? super CODE__<D, __>, Ex> code) throws IOException, Ex {
		return code().__(code);
	}

	/**
	 * Creates a code element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-code-element">4.5.15 The code element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("code")
	default __ code__(Object text) throws IOException {
		return code().__(text);
	}

	/**
	 * Creates an empty code element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-code-element">4.5.15 The code element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("code")
	default __ code__() throws IOException {
		return code().__();
	}

	/**
	 * Creates a code element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-code-element">4.5.15 The code element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("code")
	default CODE_c<D, __> code_c() throws IOException {
		return code()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="DATA">
	/**
	 * Opens a new data element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 */
	@Factory("data")
	default DATA<D, __> data() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DATA<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new data element with the given value attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 */
	@Factory("data")
	default DATA<D, __> data(Object value) throws IOException {
		return data().value(value);
	}

	/**
	 * Opens a new data element with the given value attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Factory("data")
	default <Ex extends Throwable> DATA<D, __> data(IOSupplierE<?, Ex> value) throws IOException, Ex {
		return data().value(value);
	}

	/**
	 * Creates a data element with the given value and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("data")
	default <Ex extends Throwable> __ data__(Object value, IORunnableE<Ex> data) throws IOException, Ex {
		return data(value).__(data);
	}

	/**
	 * Creates a data element with the given value and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("data")
	default <Ex extends Throwable> __ data__(Object value, IOConsumerE<? super DATA__<D, __>, Ex> data) throws IOException, Ex {
		return data(value).__(data);
	}

	/**
	 * Creates a data element with the given value and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("data")
	default __ data__(Object value, Object text) throws IOException {
		return data(value).__(text);
	}

	/**
	 * Creates an empty data element with the given value.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("data")
	default __ data__(Object value) throws IOException {
		return data(value).__();
	}

	/**
	 * Creates a data element with the given value then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-data-element">4.5.13 The data element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("data")
	default DATA_c<D, __> data_c(Object value) throws IOException {
		return data(value)._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="DFN">
	/**
	 * Opens a new dfn element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 */
	@Factory("dfn")
	default DFN<D, __> dfn() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DFN<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a dfn element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dfn")
	default <Ex extends Throwable> __ dfn__(IORunnableE<Ex> dfn) throws IOException, Ex {
		return dfn().__(dfn);
	}

	/**
	 * Creates a dfn element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dfn")
	default <Ex extends Throwable> __ dfn__(IOConsumerE<? super DFN__<D, __>, Ex> dfn) throws IOException, Ex {
		return dfn().__(dfn);
	}

	/**
	 * Creates a dfn element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dfn")
	default __ dfn__(Object text) throws IOException {
		return dfn().__(text);
	}

	/**
	 * Creates an empty dfn element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("dfn")
	default __ dfn__() throws IOException {
		return dfn().__();
	}

	/**
	 * Creates a dfn element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-dfn-element">4.5.8 The dfn element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("dfn")
	default DFN_c<D, __> dfn_c() throws IOException {
		return dfn()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="EM">
	/**
	 * Opens a new em element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-em-element">4.5.2 The em element</a>.
	 * </p>
	 */
	@Factory("em")
	default EM<D, __> em() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new EM<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an em element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-em-element">4.5.2 The em element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("em")
	default <Ex extends Throwable> __ em__(IORunnableE<Ex> em) throws IOException, Ex {
		return em().__(em);
	}

	/**
	 * Creates an em element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-em-element">4.5.2 The em element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("em")
	default <Ex extends Throwable> __ em__(IOConsumerE<? super EM__<D, __>, Ex> em) throws IOException, Ex {
		return em().__(em);
	}

	/**
	 * Creates an em element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-em-element">4.5.2 The em element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("em")
	default __ em__(Object text) throws IOException {
		return em().__(text);
	}

	/**
	 * Creates an empty em element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-em-element">4.5.2 The em element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("em")
	default __ em__() throws IOException {
		return em().__();
	}

	/**
	 * Creates an em element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-em-element">4.5.2 The em element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("em")
	default EM_c<D, __> em_c() throws IOException {
		return em()._c();
	}
	// </editor-fold>
	// Inherited: EMBED
	// <editor-fold defaultstate="collapsed" desc="I">
	/**
	 * Opens a new i element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
	 * </p>
	 */
	@Factory("i")
	default I<D, __> i() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new I<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an i element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("i")
	default <Ex extends Throwable> __ i__(IORunnableE<Ex> i) throws IOException, Ex {
		return i().__(i);
	}

	/**
	 * Creates an i element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("i")
	default <Ex extends Throwable> __ i__(IOConsumerE<? super I__<D, __>, Ex> i) throws IOException, Ex {
		return i().__(i);
	}

	/**
	 * Creates an i element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("i")
	default __ i__(Object text) throws IOException {
		return i().__(text);
	}

	/**
	 * Creates an empty i element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("i")
	default __ i__() throws IOException {
		return i().__();
	}

	/**
	 * Creates an i element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-i-element">4.5.20 The i element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("i")
	default I_c<D, __> i_c() throws IOException {
		return i()._c();
	}
	// </editor-fold>
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: INPUT
	// <editor-fold defaultstate="collapsed" desc="INS">
	/**
	 * Opens a new ins element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-ins-element">4.7.1 The ins element</a>.
	 * </p>
	 */
	@Factory("ins")
	default INS<D, __> ins() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new INS<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an ins element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-ins-element">4.7.1 The ins element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ins")
	default <Ex extends Throwable> __ ins__(IORunnableE<Ex> ins) throws IOException, Ex {
		return ins().__(ins);
	}

	/**
	 * Creates an ins element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-ins-element">4.7.1 The ins element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ins")
	default <Ex extends Throwable> __ ins__(IOConsumerE<? super __, Ex> ins) throws IOException, Ex {
		return ins().__(ins);
	}

	/**
	 * Creates an ins element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-ins-element">4.7.1 The ins element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ins")
	default __ ins__(Object text) throws IOException {
		return ins().__(text);
	}

	/**
	 * Creates an empty ins element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-ins-element">4.7.1 The ins element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("ins")
	default __ ins__() throws IOException {
		return ins().__();
	}

	/**
	 * Creates an ins element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-ins-element">4.7.1 The ins element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *          <p>
	 *          Due to limitations in Java generics, this content model does not directly reflect the parent content
	 *          model, despite this being a transparent content model.  Rather, it includes only the content model that
	 *          always applies to this element type.
	 *          </p>
	 *          <p><em>
	 *          For the full, context-aware content model, which will likely include more elements,
	 *          {@linkplain Transparent_c#pc() use the parent content model directly}.
	 *          </em></p>
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 * @see  Transparent_c#pc()
	 */
	@Factory("ins")
	default INS_c<D, __> ins_c() throws IOException {
		return ins()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="KBD">
	/**
	 * Opens a new kbd element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-kbd-element">4.5.18 The kbd element</a>.
	 * </p>
	 */
	@Factory("kbd")
	default KBD<D, __> kbd() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new KBD<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a kbd element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-kbd-element">4.5.18 The kbd element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("kbd")
	default <Ex extends Throwable> __ kbd__(IORunnableE<Ex> kbd) throws IOException, Ex {
		return kbd().__(kbd);
	}

	/**
	 * Creates a kbd element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-kbd-element">4.5.18 The kbd element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("kbd")
	default <Ex extends Throwable> __ kbd__(IOConsumerE<? super KBD__<D, __>, Ex> kbd) throws IOException, Ex {
		return kbd().__(kbd);
	}

	/**
	 * Creates a kbd element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-kbd-element">4.5.18 The kbd element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("kbd")
	default __ kbd__(Object text) throws IOException {
		return kbd().__(text);
	}

	/**
	 * Creates an empty kbd element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-kbd-element">4.5.18 The kbd element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("kbd")
	default __ kbd__() throws IOException {
		return kbd().__();
	}

	/**
	 * Creates a kbd element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-kbd-element">4.5.18 The kbd element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("kbd")
	default KBD_c<D, __> kbd_c() throws IOException {
		return kbd()._c();
	}
	// </editor-fold>
	// Inherited: LABEL
	// <editor-fold defaultstate="collapsed" desc="MAP">
	/**
	 * Opens a new map element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 */
	@Factory("map")
	default MAP<D, __> map() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new MAP<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new map element with the given name attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 */
	@Factory("map")
	default MAP<D, __> map(String name) throws IOException {
		return map().name(name);
	}

	/**
	 * Opens a new map element with the given name attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Factory("map")
	default <Ex extends Throwable> MAP<D, __> map(IOSupplierE<? extends String, Ex> name) throws IOException, Ex {
		return map().name(name);
	}

	/**
	 * Creates a map element the given name attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("map")
	default <Ex extends Throwable> __ map__(String name, IORunnableE<Ex> map) throws IOException, Ex {
		return map(name).__(map);
	}

	/**
	 * Creates a map element the given name attribute and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("map")
	default <Ex extends Throwable> __ map__(String name, IOConsumerE<? super __, Ex> map) throws IOException, Ex {
		return map(name).__(map);
	}

	/**
	 * Creates a map element the given name attribute and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("map")
	default __ map__(String name, Object text) throws IOException {
		return map(name).__(text);
	}

	/**
	 * Creates an empty map element the given name attribute.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("map")
	default __ map__(String name) throws IOException {
		return map(name).__();
	}

	/**
	 * Creates a map element the given name attribute then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-map-element">4.8.13 The map element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *          <p>
	 *          Due to limitations in Java generics, this content model does not directly reflect the parent content
	 *          model, despite this being a transparent content model.  Rather, it includes only the content model that
	 *          always applies to this element type.
	 *          </p>
	 *          <p><em>
	 *          For the full, context-aware content model, which will likely include more elements,
	 *          {@linkplain Transparent_c#pc() use the parent content model directly}.
	 *          </em></p>
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 * @see  Transparent_c#pc()
	 */
	@Factory("map")
	default MAP_c<D, __> map_c(String name) throws IOException {
		return map(name)._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="MARK">
	/**
	 * Opens a new mark element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-mark-element">4.5.23 The mark element</a>.
	 * </p>
	 */
	@Factory("mark")
	default MARK<D, __> mark() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new MARK<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a mark element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-mark-element">4.5.23 The mark element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("mark")
	default <Ex extends Throwable> __ mark__(IORunnableE<Ex> mark) throws IOException, Ex {
		return mark().__(mark);
	}

	/**
	 * Creates a mark element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-mark-element">4.5.23 The mark element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("mark")
	default <Ex extends Throwable> __ mark__(IOConsumerE<? super MARK__<D, __>, Ex> mark) throws IOException, Ex {
		return mark().__(mark);
	}

	/**
	 * Creates a mark element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-mark-element">4.5.23 The mark element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("mark")
	default __ mark__(Object text) throws IOException {
		return mark().__(text);
	}

	/**
	 * Creates an empty mark element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-mark-element">4.5.23 The mark element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("mark")
	default __ mark__() throws IOException {
		return mark().__();
	}

	/**
	 * Creates a mark element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-mark-element">4.5.23 The mark element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("mark")
	default MARK_c<D, __> mark_c() throws IOException {
		return mark()._c();
	}
	// </editor-fold>
	// Inherited: MathML math
	// <editor-fold defaultstate="collapsed" desc="METER">
	/**
	 * Opens a new meter element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-meter-element">4.10.14 The meter element</a>.
	 * </p>
	 */
	@Factory("meter")
	default METER<D, __> meter() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new METER<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	// TODO: The value attribute must be specified, create convenience methods that set the value (like A.href)

	/**
	 * Creates a meter element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-meter-element">4.10.14 The meter element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("meter")
	default <Ex extends Throwable> __ meter__(IORunnableE<Ex> meter) throws IOException, Ex {
		return meter().__(meter);
	}

	/**
	 * Creates a meter element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-meter-element">4.10.14 The meter element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("meter")
	default <Ex extends Throwable> __ meter__(IOConsumerE<? super METER__<D, __>, Ex> meter) throws IOException, Ex {
		return meter().__(meter);
	}

	/**
	 * Creates a meter element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-meter-element">4.10.14 The meter element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("meter")
	default __ meter__(Object text) throws IOException {
		return meter().__(text);
	}

	/**
	 * Creates an empty meter element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-meter-element">4.10.14 The meter element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("meter")
	default __ meter__() throws IOException {
		return meter().__();
	}

	/**
	 * Creates a meter element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-meter-element">4.10.14 The meter element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("meter")
	default METER_c<D, __> meter_c() throws IOException {
		return meter()._c();
	}
	// </editor-fold>
	// Inherited: OBJECT
	// <editor-fold defaultstate="collapsed" desc="OUTPUT">
	/**
	 * Opens a new output element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
	 * </p>
	 */
	@Factory("output")
	default OUTPUT<D, __> output() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new OUTPUT<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates an output element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("output")
	default <Ex extends Throwable> __ output__(IORunnableE<Ex> output) throws IOException, Ex {
		return output().__(output);
	}

	/**
	 * Creates an output element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("output")
	default <Ex extends Throwable> __ output__(IOConsumerE<? super OUTPUT__<D, __>, Ex> output) throws IOException, Ex {
		return output().__(output);
	}

	/**
	 * Creates an output element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("output")
	default __ output__(Object text) throws IOException {
		return output().__(text);
	}

	/**
	 * Creates an empty output element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("output")
	default __ output__() throws IOException {
		return output().__();
	}

	/**
	 * Creates an output element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("output")
	default OUTPUT_c<D, __> output_c() throws IOException {
		return output()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="PROGRESS">
	/**
	 * Opens a new progress element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
	 * </p>
	 */
	@Factory("progress")
	default PROGRESS<D, __> progress() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new PROGRESS<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a progress element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("progress")
	default <Ex extends Throwable> __ progress__(IORunnableE<Ex> progress) throws IOException, Ex {
		return progress().__(progress);
	}

	/**
	 * Creates a progress element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("progress")
	default <Ex extends Throwable> __ progress__(IOConsumerE<? super PROGRESS__<D, __>, Ex> progress) throws IOException, Ex {
		return progress().__(progress);
	}

	/**
	 * Creates a progress element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("progress")
	default __ progress__(Object text) throws IOException {
		return progress().__(text);
	}

	/**
	 * Creates an empty progress element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("progress")
	default __ progress__() throws IOException {
		return progress().__();
	}

	/**
	 * Creates a progress element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("progress")
	default PROGRESS_c<D, __> progress_c() throws IOException {
		return progress()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Q">
	/**
	 * Opens a new q element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-q-element">4.5.7 The q element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element</a>.</li>
	 * </ul>
	 */
	@Factory("q")
	default Q<D, __> q() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new Q<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a q element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-q-element">4.5.7 The q element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("q")
	default <Ex extends Throwable> __ q__(IORunnableE<Ex> q) throws IOException, Ex {
		return q().__(q);
	}

	/**
	 * Creates a q element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-q-element">4.5.7 The q element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("q")
	default <Ex extends Throwable> __ q__(IOConsumerE<? super Q__<D, __>, Ex> q) throws IOException, Ex {
		return q().__(q);
	}

	/**
	 * Creates a q element with no attributes and a text body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-q-element">4.5.7 The q element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("q")
	default __ q__(Object text) throws IOException {
		return q().__(text);
	}

	/**
	 * Creates an empty q element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-q-element">4.5.7 The q element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("q")
	default __ q__() throws IOException {
		return q().__();
	}

	/**
	 * Creates a q element with no attributes then begins element content
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-q-element">4.5.7 The q element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element</a>.</li>
	 * </ul>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("q")
	default Q_c<D, __> q_c() throws IOException {
		return q()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="RUBY">
	/**
	 * Opens a new ruby element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-ruby-element">4.5.10 The ruby element</a>.
	 * </p>
	 */
	@Factory("ruby")
	default void ruby() throws IOException {
		throw new AssertionError("TODO: Implement ruby");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="S">
	/**
	 * Opens a new s element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-s-element">4.5.5 The s element</a>.
	 * </p>
	 */
	@Factory("s")
	default S<D, __> s() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new S<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a s element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-s-element">4.5.5 The s element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("s")
	default <Ex extends Throwable> __ s__(IORunnableE<Ex> s) throws IOException, Ex {
		return s().__(s);
	}

	/**
	 * Creates a s element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-s-element">4.5.5 The s element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("s")
	default <Ex extends Throwable> __ s__(IOConsumerE<? super S__<D, __>, Ex> s) throws IOException, Ex {
		return s().__(s);
	}

	/**
	 * Creates a s element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-s-element">4.5.5 The s element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("s")
	default __ s__(Object text) throws IOException {
		return s().__(text);
	}

	/**
	 * Creates an empty s element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-s-element">4.5.5 The s element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("s")
	default __ s__() throws IOException {
		return s().__();
	}

	/**
	 * Creates a s element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-s-element">4.5.5 The s element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("s")
	default S_c<D, __> s_c() throws IOException {
		return s()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SAMP">
	/**
	 * Opens a new samp element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-samp-element">4.5.17 The samp element</a>.
	 * </p>
	 */
	@Factory("samp")
	default SAMP<D, __> samp() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SAMP<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a samp element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-samp-element">4.5.17 The samp element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("samp")
	default <Ex extends Throwable> __ samp__(IORunnableE<Ex> samp) throws IOException, Ex {
		return samp().__(samp);
	}

	/**
	 * Creates a samp element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-samp-element">4.5.17 The samp element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("samp")
	default <Ex extends Throwable> __ samp__(IOConsumerE<? super SAMP__<D, __>, Ex> samp) throws IOException, Ex {
		return samp().__(samp);
	}

	/**
	 * Creates a samp element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-samp-element">4.5.17 The samp element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("samp")
	default __ samp__(Object text) throws IOException {
		return samp().__(text);
	}

	/**
	 * Creates an empty samp element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-samp-element">4.5.17 The samp element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("samp")
	default __ samp__() throws IOException {
		return samp().__();
	}

	/**
	 * Creates a samp element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-samp-element">4.5.17 The samp element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("samp")
	default SAMP_c<D, __> samp_c() throws IOException {
		return samp()._c();
	}
	// </editor-fold>
	// Inherited: SELECT
	// <editor-fold defaultstate="collapsed" desc="SMALL">
	/**
	 * Opens a new small element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-small-element">4.5.4 The small element</a>.
	 * </p>
	 */
	@Factory("small")
	default SMALL<D, __> small() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SMALL<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a small element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-small-element">4.5.4 The small element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("small")
	default <Ex extends Throwable> __ small__(IORunnableE<Ex> small) throws IOException, Ex {
		return small().__(small);
	}

	/**
	 * Creates a small element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-small-element">4.5.4 The small element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("small")
	default <Ex extends Throwable> __ small__(IOConsumerE<? super SMALL__<D, __>, Ex> small) throws IOException, Ex {
		return small().__(small);
	}

	/**
	 * Creates a small element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-small-element">4.5.4 The small element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("small")
	default __ small__(Object text) throws IOException {
		return small().__(text);
	}

	/**
	 * Creates an empty small element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-small-element">4.5.4 The small element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("small")
	default __ small__() throws IOException {
		return small().__();
	}

	/**
	 * Creates a small element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-small-element">4.5.4 The small element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("small")
	default SMALL_c<D, __> small_c() throws IOException {
		return small()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SPAN">
	/**
	 * Opens a new span element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 */
	@Factory("span")
	default SPAN<D, __> span() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SPAN<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a span element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("span")
	default <Ex extends Throwable> __ span__(IORunnableE<Ex> span) throws IOException, Ex {
		return span().__(span);
	}

	/**
	 * Creates a span element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("span")
	default <Ex extends Throwable> __ span__(IOConsumerE<? super SPAN__<D, __>, Ex> span) throws IOException, Ex {
		return span().__(span);
	}

	/**
	 * Creates a span element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("span")
	default __ span__(Object text) throws IOException {
		return span().__(text);
	}

	/**
	 * Creates an empty span element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("span")
	default __ span__() throws IOException {
		return span().__();
	}

	/**
	 * Creates a span element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-span-element">4.5.26 The span element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("span")
	default SPAN_c<D, __> span_c() throws IOException {
		return span()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="STRONG">
	/**
	 * Opens a new strong element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 */
	@Factory("strong")
	default STRONG<D, __> strong() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new STRONG<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a strong element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("strong")
	default <Ex extends Throwable> __ strong__(IORunnableE<Ex> strong) throws IOException, Ex {
		return strong().__(strong);
	}

	/**
	 * Creates a strong element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("strong")
	default <Ex extends Throwable> __ strong__(IOConsumerE<? super STRONG__<D, __>, Ex> strong) throws IOException, Ex {
		return strong().__(strong);
	}

	/**
	 * Creates a strong element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("strong")
	default __ strong__(Object text) throws IOException {
		return strong().__(text);
	}

	/**
	 * Creates an empty strong element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("strong")
	default __ strong__() throws IOException {
		return strong().__();
	}

	/**
	 * Creates a strong element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("strong")
	default STRONG_c<D, __> strong_c() throws IOException {
		return strong()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SUB">
	/**
	 * Opens a new sub element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 */
	@Factory("sub")
	default SUB<D, __> sub() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SUB<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a sub element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sub")
	default <Ex extends Throwable> __ sub__(IORunnableE<Ex> sub) throws IOException, Ex {
		return sub().__(sub);
	}

	/**
	 * Creates a sub element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sub")
	default <Ex extends Throwable> __ sub__(IOConsumerE<? super SUB__<D, __>, Ex> sub) throws IOException, Ex {
		return sub().__(sub);
	}

	/**
	 * Creates a sub element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sub")
	default __ sub__(Object text) throws IOException {
		return sub().__(text);
	}

	/**
	 * Creates an empty sub element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sub")
	default __ sub__() throws IOException {
		return sub().__();
	}

	/**
	 * Creates a sub element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("sub")
	default SUB_c<D, __> sub_c() throws IOException {
		return sub()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SUP">
	/**
	 * Opens a new sup element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 */
	@Factory("sup")
	default SUP<D, __> sup() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new SUP<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a sup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sup")
	default <Ex extends Throwable> __ sup__(IORunnableE<Ex> sup) throws IOException, Ex {
		return sup().__(sup);
	}

	/**
	 * Creates a sup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sup")
	default <Ex extends Throwable> __ sup__(IOConsumerE<? super SUP__<D, __>, Ex> sup) throws IOException, Ex {
		return sup().__(sup);
	}

	/**
	 * Creates a sup element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sup")
	default __ sup__(Object text) throws IOException {
		return sup().__(text);
	}

	/**
	 * Creates an empty sup element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("sup")
	default __ sup__() throws IOException {
		return sup().__();
	}

	/**
	 * Creates a sup element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("sup")
	default SUP_c<D, __> sup_c() throws IOException {
		return sup()._c();
	}
	// </editor-fold>
	// Inherited: SVG svg
	// Inherited: TEXTAREA
	// <editor-fold defaultstate="collapsed" desc="TIME">
	/**
	 * Opens a new time element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-time-element">4.5.14 The time element</a>.
	 * </p>
	 */
	@Factory("time")
	default void time() throws IOException {
		throw new AssertionError("TODO: Implement time");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="U">
	/**
	 * Opens a new u element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 */
	@Factory("u")
	default U<D, __> u() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new U<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a u element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("u")
	default <Ex extends Throwable> __ u__(IORunnableE<Ex> u) throws IOException, Ex {
		return u().__(u);
	}

	/**
	 * Creates a u element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("u")
	default <Ex extends Throwable> __ u__(IOConsumerE<? super U__<D, __>, Ex> u) throws IOException, Ex {
		return u().__(u);
	}

	/**
	 * Creates a u element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("u")
	default __ u__(Object text) throws IOException {
		return u().__(text);
	}

	/**
	 * Creates an empty u element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("u")
	default __ u__() throws IOException {
		return u().__();
	}

	/**
	 * Creates a u element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("u")
	default U_c<D, __> u_c() throws IOException {
		return u()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="VAR">
	/**
	 * Opens a new var element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
	 * </p>
	 */
	@Factory("var")
	default VAR<D, __> var() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new VAR<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a var element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("var")
	default <Ex extends Throwable> __ var__(IORunnableE<Ex> var) throws IOException, Ex {
		return var().__(var);
	}

	/**
	 * Creates a var element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("var")
	default <Ex extends Throwable> __ var__(IOConsumerE<? super VAR__<D, __>, Ex> var) throws IOException, Ex {
		return var().__(var);
	}

	/**
	 * Creates a var element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("var")
	default __ var__(Object text) throws IOException {
		return var().__(text);
	}

	/**
	 * Creates an empty var element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("var")
	default __ var__() throws IOException {
		return var().__();
	}

	/**
	 * Creates a var element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-var-element">4.5.16 The var element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("var")
	default VAR_c<D, __> var_c() throws IOException {
		return var()._c();
	}
	// </editor-fold>
	// Inherited: VIDEO
	// <editor-fold defaultstate="collapsed" desc="TODO: autonomous custom elements">
	// 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
	// </editor-fold>
}
