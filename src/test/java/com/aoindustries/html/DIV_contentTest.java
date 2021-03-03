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
 * @see  DIV_content
 *
 * @author  AO Industries, Inc.
 */
public class DIV_contentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			DIV_content.class,
			//
			// Unions:
			//
			Union_COLGROUP_ScriptSupporting.class,
			Union_DIV_DL.class,
			Union_DL_Palpable.class,
			Union_Embedded_Interactive.class,
			Union_Embedded_Palpable_Phrasing.class,
			Union_Interactive_Phrasing.class,
			Union_Metadata_Phrasing.class,
			Union_Palpable_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			DIV_content.class,
			//
			// Content models:
			//
			Content.class,
			EmbeddedContent.class,
			FlowContent.class,
			HeadingContent.class,
			InteractiveContent.class,
			PalpableContent.class,
			PhrasingContent.class,
			ScriptSupportingContent.class,
			SectioningContent.class,
			TextContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			DIV_content.class
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
			DIV_content.class,
			//
			// Factories:
			//
			A_factory.class,
			ABBR_factory.class,
			ADDRESS_factory.class,
			AREA_factory.class, // if a descendent of map
			ARTICLE_factory.class,
			ASIDE_factory.class,
			AUDIO_factory.class,
			B_factory.class,
			BDI_factory.class,
			BDO_factory.class,
			BLOCKQUOTE_factory.class,
			BR_factory.class,
			BUTTON_factory.class,
			CANVAS_factory.class,
			CITE_factory.class,
			CODE_factory.class,
			DATA_factory.class,
			DATALIST_factory.class,
			DD_factory.class,
			DEL_factory.class,
			DETAILS_factory.class,
			DFN_factory.class,
			DIALOG_factory.class,
			DIV_factory.class,
			DL_factory.class,
			DT_factory.class,
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
			HR_factory.class,
			I_factory.class,
			IFRAME_factory.class,
			IMG_factory.class,
			INPUT_factory.class,
			INS_factory.class,
			KBD_factory.class,
			LABEL_factory.class,
			LINK_factory.class, // if it is allowed in body
			MAIN_factory.class, // if it is a hierarchically correct main element
			MAP_factory.class,
			MARK_factory.class,
			// TODO: MathML math
			MENU_factory.class,
			META_factory.class, // if the itemprop attribute is present
			METER_factory.class,
			NAV_factory.class,
			NOSCRIPT_factory.class,
			OBJECT_factory.class,
			OL_factory.class,
			OUTPUT_factory.class,
			P_factory.class,
			PICTURE_factory.class,
			PRE_factory.class,
			PROGRESS_factory.class,
			Q_factory.class,
			RUBY_factory.class,
			S_factory.class,
			SAMP_factory.class,
			SCRIPT_factory.class,
			SECTION_factory.class,
			SELECT_factory.class,
			SLOT_factory.class,
			SMALL_factory.class,
			SPAN_factory.class,
			STRONG_factory.class,
			SUB_factory.class,
			SUP_factory.class,
			// TODO: SVG svg
			TABLE_factory.class,
			TEMPLATE_factory.class,
			TEXTAREA_factory.class,
			TIME_factory.class,
			U_factory.class,
			UL_factory.class,
			VAR_factory.class,
			VIDEO_factory.class,
			WBR_factory.class
			// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ElementContentModelTest.class.getSimpleName() + ".getAllElementContentModels()",
			-1,
			AoArrays.indexOf(ElementContentModelTest.getAllElementContentModels(), DIV_content.class)
		);
		InheritanceTests.testNoImplementInherited(DIV_content.class);
	}
}
