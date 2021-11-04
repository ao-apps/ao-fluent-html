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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.aoapps.html.tests;

import com.aoapps.html.Content;
import com.aoapps.html.Document;
import com.aoapps.html.any.tests.AnyDocumentTest;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Test;

/**
 * @see  Document
 *
 * @author  AO Industries, Inc.
 */
public class DocumentTest extends AnyDocumentTest {

	public DocumentTest() {
		super(Document.class);
	}

	@Test
	@Override
	public void testUnions() {
		UnionContentTest.testUnions(
			Document.class,
			//
			// Unions:
			//
			UnionContentTest.getAllUnions()
		);
	}

	@Test
	@Override
	public void testContentModels() {
		ContentModelTest.testContentModels(
			Document.class,
			//
			// Content models:
			//
			ContentModelTest.getAllContentModels()
		);
	}

	@Test
	@Override
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			Document.class,
			//
			// Per-element content models:
			//
			ElementContentModelTest.getAllElementContentModels()
		);
	}

	@Test
	@Override
	public void testNoImplementInherited() {
		InheritanceTestHelper.testNoImplementInherited(Content.class, Document.class);
	}
}
