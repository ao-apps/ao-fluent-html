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
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface TableContent<C extends TableContent<C>> extends
	//
	// Content models:
	//
	// Inherited from TbodyTheadTfootContent: Content
	// Inherited from TbodyTheadTfootContent: ScriptSupportingContent<C>
	TbodyTheadTfootContent<C>,
	//
	// Content types:
	//
	Contents.Tabular.Caption<C>,
	Contents.Tabular.Colgroup<C>,
	Contents.Tabular.Thead<C>,
	Contents.Tabular.Tbody<C>,
	// Inherited from TbodyTheadTfootContent: Contents.Tabular.Tr<C>
	// TODO: Deprecated methods here, since using tbody is most consistent in XHTML?
	//       Contents.Tabular.Tr<C>,
	Contents.Tabular.Tfoot<C>
	// Inherited from TbodyTheadTfootContent: Contents.Scripting.Script<C>
	// Inherited from TbodyTheadTfootContent: Contents.Scripting.Template<C>
{

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default Tr<C> tr() throws IOException {
		return TbodyTheadTfootContent.super.tr();
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default <Ex extends Throwable> C tr__(IORunnableE<Ex> tr) throws IOException, Ex {
		return TbodyTheadTfootContent.super.tr__(tr);
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default <Ex extends Throwable> C tr__(IOConsumerE<? super Tr.TrContent<C>, Ex> tr) throws IOException, Ex {
		return TbodyTheadTfootContent.super.tr__(tr);
	}

	/**
	 * @deprecated  For maximum compatibility with both {@link Serialization#SGML} and {@link Serialization#XML},
	 *              it is recommended to always use <code>&lt;tbody&gt;</code>.
	 */
	@Deprecated
	@Override
	default C tr__() throws IOException {
		return TbodyTheadTfootContent.super.tr__();
	}
}
