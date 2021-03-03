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
 * @see  PalpableContent
 *
 * @author  AO Industries, Inc.
 */
public class PalpableContentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			PalpableContent.class,
			//
			// Unions:
			//
			Union_DL_Palpable.class,
			Union_Embedded_Interactive.class,
			Union_Embedded_Palpable_Phrasing.class,
			Union_Interactive_Phrasing.class,
			Union_Palpable_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			PalpableContent.class,
			//
			// Content models:
			//
			Content.class,
			SectioningContent.class,
			HeadingContent.class,
			InteractiveContent.class,
			TextContent.class // that is not inter-element whitespace
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			PalpableContent.class
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
			PalpableContent.class,
			//
			// Factories:
			//
			A_factory.class,
			ABBR_factory.class,
			ADDRESS_factory.class,
			ARTICLE_factory.class,
			ASIDE_factory.class,
			AUDIO_factory.class, // if the controls attribute is present
			B_factory.class,
			BDI_factory.class,
			BDO_factory.class,
			BLOCKQUOTE_factory.class,
			BUTTON_factory.class,
			CANVAS_factory.class,
			CITE_factory.class,
			CODE_factory.class,
			DATA_factory.class,
			DETAILS_factory.class,
			DFN_factory.class,
			DIV_factory.class,
			DL_factory.class, // if childen contain at least one name/value pair
			EM_factory.class,
			EMBED_factory.class,
			FIELDSET_factory.class,
			FIGURE_factory.class,
			FOOTER_factory.class,
			FORM_factory.class,
			H1_factory.class,
			H2_factory.class,
			H3_factory.class,
			H4_factory.class,
			H5_factory.class,
			H6_factory.class,
			HEADER_factory.class,
			HGROUP_factory.class,
			I_factory.class,
			IFRAME_factory.class,
			IMG_factory.class,
			INPUT_factory.class, // if type attribute is not in the hidden state
			INS_factory.class,
			KBD_factory.class,
			LABEL_factory.class,
			MAIN_factory.class,
			MAP_factory.class,
			MARK_factory.class,
			// TODO: MathML math
			MENU_factory.class, // if children include at least one li
			METER_factory.class,
			NAV_factory.class,
			OBJECT_factory.class,
			OL_factory.class, // if children include at least one li
			OUTPUT_factory.class,
			P_factory.class,
			PRE_factory.class,
			PROGRESS_factory.class,
			Q_factory.class,
			RUBY_factory.class,
			S_factory.class,
			SAMP_factory.class,
			SECTION_factory.class,
			SELECT_factory.class,
			SMALL_factory.class,
			SPAN_factory.class,
			STRONG_factory.class,
			SUB_factory.class,
			SUP_factory.class,
			// TODO: SVG svg
			TABLE_factory.class,
			TEXTAREA_factory.class,
			TIME_factory.class,
			U_factory.class,
			UL_factory.class, // if children include at least one li
			VAR_factory.class,
			VIDEO_factory.class
			// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ContentModelTest.class.getSimpleName() + ".getAllContentModels()",
			-1,
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), PalpableContent.class)
		);
		InheritanceTests.testNoImplementInherited(PalpableContent.class);
	}
}
