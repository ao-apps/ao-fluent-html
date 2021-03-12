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

import com.aoindustries.collections.AoArrays;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  PhrasingContent
 *
 * @author  AO Industries, Inc.
 */
public class PhrasingContentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			PhrasingContent.class,
			//
			// Unions:
			//
			Union_COLGROUP_ScriptSupporting.class,
			Union_Embedded_Interactive.class,
			Union_Embedded_Palpable_Phrasing.class,
			Union_Interactive_Phrasing.class,
			Union_Metadata_Phrasing.class,
			Union_Palpable_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			PhrasingContent.class,
			//
			// Content models:
			//
			Content.class,
			EmbeddedContent.class,
			ScriptSupportingContent.class,
			TextContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			PhrasingContent.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	public void testFactories() throws IOException {
		FactoryTest.testFactories(
			PhrasingContent.class,
			//
			// Factories:
			//
			"a",
			"abbr",
			"area", // if a descendent of map
			"audio",
			"b",
			"bdi",
			"bdo",
			"br",
			"button",
			"canvas",
			"cite",
			"code",
			"data",
			"datalist",
			"del",
			"dfn",
			"em",
			"embed",
			"i",
			"iframe",
			"img",
			"input",
			"ins",
			"kbd",
			"label",
			"link", // if it is allowed in body
			"map",
			"mark",
			// TODO: MathML math
			"meta", // if the itemprop attribute is present
			"meter",
			"noscript",
			"object",
			"output",
			"picture",
			"progress",
			"q",
			"ruby",
			"s",
			"samp",
			"script",
			"select",
			"slot",
			"small",
			"span",
			"strong",
			"sub",
			"sup",
			// TODO: SVG svg
			"template",
			"textarea",
			"time",
			"u",
			"var",
			"video",
			"wbr"
			// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ContentModelTest.class.getSimpleName() + ".getAllContentModels()",
			-1,
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), PhrasingContent.class)
		);
		InheritanceTests.testNoImplementInherited(PhrasingContent.class);
	}
}
