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
 * @see  AnyDIV_content
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("rawtypes")
public class AnyDIV_contentTest {

	private final Class<? extends AnyDIV_content> testingClass;

	protected AnyDIV_contentTest(Class<? extends AnyDIV_content> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyDIV_contentTest() {
		this(AnyDIV_content.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyDIV_content.class,
			//
			// Unions:
			//
			AnyUnion_COLGROUP_ScriptSupporting.class,
			AnyUnion_DIV_DL.class,
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
			AnyDIV_content.class,
			//
			// Content models:
			//
			Content.class,
			AnyEmbeddedContent.class,
			AnyFlowContent.class,
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
			AnyDIV_content.class
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
			"dd",
			"del",
			"details",
			"dfn",
			"dialog",
			"div",
			"dl",
			"dt",
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
			"Must be included in " + ElementContentModelTest.class.getSimpleName() + ".getAllElementContentModels()",
			-1,
			AoArrays.indexOf(ElementContentModelTest.getAllElementContentModels(), AnyDIV_content.class)
		);
		InheritanceTests.testNoImplementInherited(Content.class, AnyDIV_content.class);
	}
}
