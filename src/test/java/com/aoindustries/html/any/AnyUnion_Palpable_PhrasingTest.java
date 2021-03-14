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
 * @see  AnyUnion_Palpable_Phrasing
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AnyUnion_Palpable_PhrasingTest {

	private final Class<? extends AnyUnion_Palpable_Phrasing> testingClass;

	protected AnyUnion_Palpable_PhrasingTest(Class<? extends AnyUnion_Palpable_Phrasing> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyUnion_Palpable_PhrasingTest() {
		this(AnyUnion_Palpable_Phrasing.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyUnion_Palpable_Phrasing.class,
			//
			// Unions:
			//
			AnyUnion_Embedded_Interactive.class,
			AnyUnion_Embedded_Palpable_Phrasing.class,
			AnyUnion_Interactive_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			AnyUnion_Palpable_Phrasing.class,
			//
			// Content models:
			//
			Content.class,
			AnyTextContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			AnyUnion_Palpable_Phrasing.class
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
			"audio",
			"b",
			"bdi",
			"bdo",
			"button",
			"canvas",
			"cite",
			"code",
			"data",
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
			"map",
			"mark",
			// TODO: MathML math
			"meter",
			"object",
			"output",
			"progress",
			"q",
			"ruby",
			"s",
			"samp",
			"select",
			"small",
			"span",
			"strong",
			"sub",
			"sup",
			// TODO: SVG svg
			"textarea",
			"time",
			"u",
			"var",
			"video"
			// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals("Must be included in " + AnyUnionContentTest.class.getSimpleName() + ".getAllUnions()",
			-1,
			AoArrays.indexOf(AnyUnionContentTest.getAllUnions(), AnyUnion_Palpable_Phrasing.class)
		);
		InheritanceTests.testNoImplementInherited(Content.class, AnyUnion_Palpable_Phrasing.class);
	}
}
