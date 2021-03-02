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
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  InteractiveContent
 *
 * @author  AO Industries, Inc.
 */
public class InteractiveContentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			InteractiveContent.class,
			//
			// Unions:
			//
			UnionContent.Embedded_Interactive.class,
			UnionContent.Interactive_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			InteractiveContent.class,
			//
			// Content models:
			//
			Content.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			InteractiveContent.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFactories() {
		FactoryTest.testFactories(
			InteractiveContent.class,
			//
			// Factories:
			//
			A_factory.class, // if the href attribute is present
			AUDIO_factory.class, // if the controls attribute is present
			BUTTON_factory.class,
			DETAILS_factory.class,
			EMBED_factory.class,
			IFRAME_factory.class,
			IMG_factory.class, // if the usemap attribute is present
			INPUT_factory.class, // if type attribute is not in the hidden state
			LABEL_factory.class,
			MENU_factory.class, // (MDN only) if the type attribute is in the toolbar state
			OBJECT_factory.class, // if the usemap attribute is present
			SELECT_factory.class,
			TEXTAREA_factory.class,
			VIDEO_factory.class // if the controls attribute is present
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ContentModelTest.class.getName() + ".getAllContentModels()",
			-1,
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), InteractiveContent.class)
		);
		InheritanceTests.testNoImplementInherited(InteractiveContent.class);
	}
}
