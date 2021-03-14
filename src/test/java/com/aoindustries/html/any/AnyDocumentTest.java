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

import java.io.IOException;
import org.junit.Test;

/**
 * @see  AnyDocument
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("rawtypes")
public class AnyDocumentTest {

	private final Class<? extends AnyDocument> testingClass;

	protected AnyDocumentTest(Class<? extends AnyDocument> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyDocumentTest() {
		this(AnyDocument.class);
	}

	@Test
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyDocument.class,
			//
			// Unions:
			//
			AnyUnionContentTest.getAllUnions()
		);
	}

	@Test
	public void testContentModels() {
		ContentModelTest.testContentModels(
			AnyDocument.class,
			//
			// Content models:
			//
			ContentModelTest.getAllContentModels()
		);
	}

	@Test
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			AnyDocument.class,
			//
			// Per-element content models:
			//
			ElementContentModelTest.getAllElementContentModels()
		);
	}

	@Test
	public void testFactories() throws IOException {
		FactoryTest.testFactories(
			testingClass,
			//
			// Factories:
			//
			FactoryTest.getAllFactories()
		);
	}

	@Test
	public void testNoImplementInherited() {
		InheritanceTests.testNoImplementInherited(Content.class, AnyDocument.class);
	}
}
