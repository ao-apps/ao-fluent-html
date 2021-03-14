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
 * @see  AnyPalpableContent
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("rawtypes")
public class AnyPalpableContentTest {

	private final Class<? extends AnyPalpableContent> testingClass;

	protected AnyPalpableContentTest(Class<? extends AnyPalpableContent> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyPalpableContentTest() {
		this(AnyPalpableContent.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyPalpableContent.class,
			//
			// Unions:
			//
			AnyUnion_DL_Palpable.class,
			AnyUnion_Embedded_Interactive.class,
			AnyUnion_Embedded_Palpable_Phrasing.class,
			AnyUnion_Interactive_Phrasing.class,
			AnyUnion_Palpable_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			AnyPalpableContent.class,
			//
			// Content models:
			//
			Content.class,
			AnySectioningContent.class,
			AnyHeadingContent.class,
			AnyInteractiveContent.class,
			AnyTextContent.class // that is not inter-element whitespace
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			AnyPalpableContent.class
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
			"article",
			"aside",
			"audio", // if the controls attribute is present
			"b",
			"bdi",
			"bdo",
			"blockquote",
			"button",
			"canvas",
			"cite",
			"code",
			"data",
			"details",
			"dfn",
			"div",
			"dl", // if childen contain at least one name/value pair
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
			"i",
			"iframe",
			"img",
			"input", // if type attribute is not in the hidden state
			"ins",
			"kbd",
			"label",
			"main",
			"map",
			"mark",
			// TODO: MathML math
			"menu", // if children include at least one li
			"meter",
			"nav",
			"object",
			"ol", // if children include at least one li
			"output",
			"p",
			"pre",
			"progress",
			"q",
			"ruby",
			"s",
			"samp",
			"section",
			"select",
			"small",
			"span",
			"strong",
			"sub",
			"sup",
			// TODO: SVG svg
			"table",
			"textarea",
			"time",
			"u",
			"ul", // if children include at least one li
			"var",
			"video"
			// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ContentModelTest.class.getSimpleName() + ".getAllContentModels()",
			-1,
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), AnyPalpableContent.class)
		);
		InheritanceTests.testNoImplementInherited(Content.class, AnyPalpableContent.class);
	}
}
