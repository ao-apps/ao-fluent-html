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
import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface TABLE_content<__ extends TABLE_content<__>> extends
	//
	// Unions:
	//
	UnionContent.TBODY_THEAD_TFOOT<__>,
	//
	// Content models:
	//
	// Inherited from TBODY_THEAD_TFOOT: Content<__>
	// Inherited from TBODY_THEAD_TFOOT: ScriptSupportingContent<__>
	//
	// Content types:
	//
	CAPTION_factory<__>,
	COLGROUP_factory<__>,
	THEAD_factory<__>,
	TBODY_factory<__>,
	// Inherited from TBODY_THEAD_TFOOT: TR_factory<__>
	TFOOT_factory<__>
	// Inherited from TBODY_THEAD_TFOOT: SCRIPT_factory<__>
	// Inherited from TBODY_THEAD_TFOOT: TEMPLATE_factory<__>
{

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default TR<__> tr() throws IOException {
		return UnionContent.TBODY_THEAD_TFOOT.super.tr();
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default <Ex extends Throwable> __ tr__(IORunnableE<Ex> tr) throws IOException, Ex {
		return UnionContent.TBODY_THEAD_TFOOT.super.tr__(tr);
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default <Ex extends Throwable> __ tr__(IOConsumerE<? super TR__<__>, Ex> tr) throws IOException, Ex {
		return UnionContent.TBODY_THEAD_TFOOT.super.tr__(tr);
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default __ tr__() throws IOException {
		return UnionContent.TBODY_THEAD_TFOOT.super.tr__();
	}
}
