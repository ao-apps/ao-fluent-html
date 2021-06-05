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
package com.aoapps.html;

import com.aoapps.html.any.AnyLINK;
import com.aoapps.html.any.AnyMETA;
import com.aoapps.html.any.AnyUnion_Metadata_Phrasing;
import java.io.IOException;

/**
 * Elements that are common to both {@link MetadataContent} and {@link PhrasingContent}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Metadata_Phrasing<
	__ extends Union_Metadata_Phrasing<__>
> extends AnyUnion_Metadata_Phrasing<Document, __>,
	//
	// Unions:
	//
	// Inherited: COLGROUP_ScriptSupporting<__>

	//
	// Content models:
	//
	// Inherited: Content<__>
	ScriptSupportingContent<__>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="LINK">
	@Override
	default LINK<__> link() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new LINK<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	@Override
	default LINK<__> link(AnyLINK.Rel rel) throws IOException {
		return link().rel(rel);
	}
	// No link__(), since either rel or itemprop is required
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="META">
	@Override
	default META<__> meta() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new META<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	@Override
	default META<__> meta(AnyMETA.Name name) throws IOException {
		return meta().name(name);
	}

	@Override
	default META<__> meta(AnyMETA.HttpEquiv httpEquiv) throws IOException {
		return meta().httpEquiv(httpEquiv);
	}

	@Override
	default META<__> meta(com.aoapps.html.any.attributes.Enum.Charset.Value charset) throws IOException {
		return meta().charset(charset);
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TODO: NOSCRIPT">
	// </editor-fold>
	// Inherited: SCRIPT
	// Inherited: TEMPLATE
}
