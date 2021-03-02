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
			UnionContent.COLGROUP_ScriptSupporting.class,
			UnionContent.Embedded_Interactive.class,
			UnionContent.Embedded_Palpable_Phrasing.class,
			UnionContent.Interactive_Phrasing.class,
			UnionContent.Metadata_Phrasing.class,
			UnionContent.Palpable_Phrasing.class
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
	@SuppressWarnings("unchecked")
	public void testFactories() {
		FactoryTest.testFactories(
			PhrasingContent.class,
			//
			// Factories:
			//
			A_factory.class,
			ABBR_factory.class,
			AREA_factory.class, // if a descendent of map
			AUDIO_factory.class,
			B_factory.class,
			BDI_factory.class,
			BDO_factory.class,
			BR_factory.class,
			BUTTON_factory.class,
			CANVAS_factory.class,
			CITE_factory.class,
			CODE_factory.class,
			DATA_factory.class,
			DATALIST_factory.class,
			DEL_factory.class,
			DFN_factory.class,
			EM_factory.class,
			EMBED_factory.class,
			I_factory.class,
			IFRAME_factory.class,
			IMG_factory.class,
			INPUT_factory.class,
			INS_factory.class,
			KBD_factory.class,
			LABEL_factory.class,
			LINK_factory.class, // if it is allowed in body
			MAP_factory.class,
			MARK_factory.class,
			// TODO: MathML math
			META_factory.class, // if the itemprop attribute is present
			METER_factory.class,
			NOSCRIPT_factory.class,
			OBJECT_factory.class,
			OUTPUT_factory.class,
			PICTURE_factory.class,
			PROGRESS_factory.class,
			Q_factory.class,
			RUBY_factory.class,
			S_factory.class,
			SAMP_factory.class,
			SCRIPT_factory.class,
			SELECT_factory.class,
			SLOT_factory.class,
			SMALL_factory.class,
			SPAN_factory.class,
			STRONG_factory.class,
			SUB_factory.class,
			SUP_factory.class,
			// TODO: SVG svg
			TEMPLATE_factory.class,
			TEXTAREA_factory.class,
			TIME_factory.class,
			U_factory.class,
			VAR_factory.class,
			VIDEO_factory.class,
			WBR_factory.class
			// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ContentModelTest.class.getName() + ".getAllContentModels()",
			-1,
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), PhrasingContent.class)
		);
		InheritanceTests.testNoImplementInherited(PhrasingContent.class);
	}
}
