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
 * @see  AnyContent
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings("rawtypes")
public class AnyContentTest {

	private final Class<? extends AnyContent> testingClass;

	protected AnyContentTest(Class<? extends AnyContent> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyContentTest() {
		this(AnyContent.class);
	}

	@Test
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyContent.class,
			//
			// Unions:
			//
			AnyUnionContentTest.getAllUnions()
		);
	}

	@Test
	public void testContentModels() {
		ContentModelTest.testContentModels(
			AnyContent.class,
			//
			// Content models:
			//
			ContentModelTest.getAllContentModels()
		);
	}

	@Test
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			AnyContent.class,
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
		InheritanceTests.testNoImplementInherited(Content.class, AnyContent.class);
	}
}
