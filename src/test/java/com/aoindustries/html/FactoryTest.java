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

import org.junit.Test;

/**
 * Tests <code>*_factory</code> element factory interfaces,
 * which confirm a class implements the expected set of interfaces.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FactoryTest {

	/**
	 * Gets the set of all <code>*_factory</code> element factory interfaces.
	 */
	static Class<? extends Content>[] getAllFactories() {
		return new Class[] {
			ABBR_factory.class,
			ADDRESS_factory.class,
			A_factory.class,
			AREA_factory.class,
			ARTICLE_factory.class,
			ASIDE_factory.class,
			AUDIO_factory.class,
			BASE_factory.class,
			BDI_factory.class,
			BDO_factory.class,
			B_factory.class,
			BLOCKQUOTE_factory.class,
			BODY_factory.class,
			BR_factory.class,
			BUTTON_factory.class,
			CANVAS_factory.class,
			CAPTION_factory.class,
			CITE_factory.class,
			CODE_factory.class,
			COL_factory.class,
			COLGROUP_factory.class,
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
			EMBED_factory.class,
			EM_factory.class,
			FIELDSET_factory.class,
			FIGCAPTION_factory.class,
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
			HEAD_factory.class,
			HGROUP_factory.class,
			HR_factory.class,
			HTML_factory.class,
			I_factory.class,
			IFRAME_factory.class,
			IMG_factory.class,
			INPUT_factory.class,
			INS_factory.class,
			KBD_factory.class,
			LABEL_factory.class,
			LEGEND_factory.class,
			LI_factory.class,
			LINK_factory.class,
			MAIN_factory.class,
			MAP_factory.class,
			MARK_factory.class,
			MENU_factory.class,
			META_factory.class,
			METER_factory.class,
			NAV_factory.class,
			NOSCRIPT_factory.class,
			OBJECT_factory.class,
			OL_factory.class,
			OPTGROUP_factory.class,
			OPTION_factory.class,
			OUTPUT_factory.class,
			PARAM_factory.class,
			P_factory.class,
			PICTURE_factory.class,
			PRE_factory.class,
			PROGRESS_factory.class,
			Q_factory.class,
			RP_factory.class,
			RT_factory.class,
			RUBY_factory.class,
			SAMP_factory.class,
			SCRIPT_factory.class,
			SECTION_factory.class,
			SELECT_factory.class,
			S_factory.class,
			SLOT_factory.class,
			SMALL_factory.class,
			SOURCE_factory.class,
			SPAN_factory.class,
			STRONG_factory.class,
			STYLE_factory.class,
			SUB_factory.class,
			SUMMARY_factory.class,
			SUP_factory.class,
			TABLE_factory.class,
			TBODY_factory.class,
			TD_factory.class,
			TEMPLATE_factory.class,
			TEXTAREA_factory.class,
			TFOOT_factory.class,
			THEAD_factory.class,
			TH_factory.class,
			TIME_factory.class,
			TITLE_factory.class,
			TRACK_factory.class,
			TR_factory.class,
			U_factory.class,
			UL_factory.class,
			VAR_factory.class,
			VIDEO_factory.class,
			WBR_factory.class
		};
	}

	static void testFactories(Class<? extends Content> clazz, Class<? extends Content> ... expected) {
		InheritanceTests.testInterfaces(
			iface -> iface.getSimpleName().endsWith("_factory"),
			getAllFactories(),
			clazz,
			expected
		);
	}

	@Test
	public void testNoImplementInherited() {
		for(Class<? extends Content> iface : getAllFactories()) {
			InheritanceTests.testNoImplementInherited(iface);
		}
	}
}
