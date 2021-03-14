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

import com.aoindustries.collections.AoArrays;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  AnyFlowContent
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("rawtypes")
public class AnyFlowContentTest {

	private final Class<? extends AnyFlowContent> testingClass;

	protected AnyFlowContentTest(Class<? extends AnyFlowContent> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyFlowContentTest() {
		this(AnyFlowContent.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyFlowContent.class,
			//
			// Unions:
			//
			AnyUnion_COLGROUP_ScriptSupporting.class,
			AnyUnion_DL_Palpable.class,
			AnyUnion_Embedded_Interactive.class,
			AnyUnion_Embedded_Palpable_Phrasing.class,
			AnyUnion_Interactive_Phrasing.class,
			AnyUnion_Metadata_Phrasing.class,
			AnyUnion_Palpable_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			AnyFlowContent.class,
			//
			// Content models:
			//
			Content.class,
			AnyEmbeddedContent.class,
			AnyHeadingContent.class,
			AnyInteractiveContent.class,
			AnyPalpableContent.class,
			AnyPhrasingContent.class,
			AnyScriptSupportingContent.class,
			AnySectioningContent.class,
			AnyTextContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			AnyFlowContent.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	public void testFactories() throws IOException {
		FactoryTest.testFactories(
			testingClass,
			//
			// Factories:
			//
			"a",
			"abbr",
			"address",
			"area", // if a descendent of map
			"article",
			"aside",
			"audio",
			"b",
			"bdi",
			"bdo",
			"blockquote",
			"br",
			"button",
			"canvas",
			"cite",
			"code",
			"data",
			"datalist",
			"del",
			"details",
			"dfn",
			"dialog",
			"div",
			"dl",
			"em",
			"embed",
			"fieldset",
			"figure",
			"footer",
			"form",
			"h1",
			"h2",
			"h3",
			"h4",
			"h5",
			"h6",
			"h#",
			"header",
			"hgroup",
			"hr",
			"i",
			"iframe",
			"img",
			"input",
			"ins",
			"kbd",
			"label",
			"link", // if it is allowed in body
			"main", // if it is a hierarchically correct main element
			"map",
			"mark",
			// TODO: MathML math
			"menu",
			"meta", // if the itemprop attribute is present
			"meter",
			"nav",
			"noscript",
			"object",
			"ol",
			"output",
			"p",
			"picture",
			"pre",
			"progress",
			"q",
			"ruby",
			"s",
			"samp",
			"script",
			"section",
			"select",
			"slot",
			"small",
			"span",
			"strong",
			"sub",
			"sup",
			// TODO: SVG svg
			"table",
			"template",
			"textarea",
			"time",
			"u",
			"ul",
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
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), AnyFlowContent.class)
		);
		InheritanceTests.testNoImplementInherited(Content.class, AnyFlowContent.class);
	}
}
