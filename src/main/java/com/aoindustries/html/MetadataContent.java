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

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#metadata-content">3.2.5.2.1 Metadata content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#metadata_content">Metadata content</a>.</li>
 * </ul>
 *
 * @param  <C>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface MetadataContent<C extends MetadataContent<C>> extends
	//
	// Unions:
	//
	UnionContent.Metadata_Phrasing<C>,
	//
	// Content models:
	//
	// Inherited from Metadata_Phrasing: Content<C>
	// Inherited from Metadata_Phrasing: ScriptSupportingContent<C>
	//
	// Content types:
	//
	Contents.Metadata.Base<C>,
	// Inherited from Metadata_Phrasing: Contents.Metadata.Link<C>
	// Inherited from Metadata_Phrasing: Contents.Metadata.Meta<C>
	// Inherited from Metadata_Phrasing: Contents.Scripting.Noscript<C>
	// Inherited from Metadata_Phrasing: Contents.Scripting.Script<C>
	Contents.Metadata.Style<C>,
	// Inherited from Metadata_Phrasing: Contents.Scripting.Template<C> // WHATWG only
	Contents.Metadata.Title<C>
{
}
