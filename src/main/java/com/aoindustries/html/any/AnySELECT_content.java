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

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-select-element">4.10.7 The select element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AnySELECT_content<
	D  extends AnyDocument<D>,
	__ extends AnySELECT_content<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: AnyUnion_COLGROUP_ScriptSupporting<D, __>
	// Inherited: AnyUnion_DATALIST_OPTGROUP<D, __>

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	// Inherited: AnyScriptSupportingContent<D, __>

	//
	// Per-element content models:
	//
	AnyOPTGROUP_content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="OPTGROUP">
	/**
	 * Opens a new optgroup element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 */
	@Factory("optgroup")
	AnyOPTGROUP<D, __, ?, ?, ?> optgroup() throws IOException;

	/**
	 * Creates an optgroup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("optgroup")
	default <Ex extends Throwable> __ optgroup__(IORunnableE<Ex> optgroup) throws IOException, Ex {
		return optgroup().__(optgroup);
	}

	/**
	 * Creates an optgroup element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("optgroup")
	default <Ex extends Throwable> __ optgroup__any(IOConsumerE<? super AnyOPTGROUP__<D, __, ? extends AnyOPTGROUP__<D, __, ?>>, Ex> optgroup) throws IOException, Ex {
		return optgroup().__(optgroup);
	}

	/**
	 * Creates an empty optgroup element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("optgroup")
	default __ optgroup__() throws IOException {
		return optgroup().__();
	}

	/**
	 * Creates an optgroup element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-optgroup-element">4.10.9 The optgroup element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("optgroup")
	AnyOPTGROUP_c<D, __, ?> optgroup_c() throws IOException;
	// </editor-fold>
	// Inherited: OPTION
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
