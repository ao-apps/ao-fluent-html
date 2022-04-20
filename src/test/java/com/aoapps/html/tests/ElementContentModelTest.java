/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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

import com.aoapps.html.COLGROUP_content;
import com.aoapps.html.Content;
import com.aoapps.html.DATALIST_content;
import com.aoapps.html.DIV_content;
import com.aoapps.html.DL_content;
import com.aoapps.html.HTML_content;
import com.aoapps.html.OBJECT_content;
import com.aoapps.html.OPTGROUP_content;
import com.aoapps.html.SELECT_content;
import com.aoapps.html.TABLE_content;
import com.aoapps.html.TR_content;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Test;

/**
 * Tests <code>*_content</code> per-element content model interfaces,
 * which confirm a class implements the expected set of interfaces.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ElementContentModelTest {

  /**
   * Gets the set of all <code>*_content</code> per-element content model interfaces.
   */
  static Class<? extends Content>[] getAllElementContentModels() {
    return new Class[] {
      COLGROUP_content.class,
      DATALIST_content.class,
      DIV_content.class,
      DL_content.class,
      HTML_content.class,
      OBJECT_content.class,
      OPTGROUP_content.class,
      SELECT_content.class,
      TABLE_content.class,
      TR_content.class
    };
  }

  static void testElementContentModels(Class<? extends Content> clazz, Class<? extends Content> ... expected) {
    InheritanceTestHelper.testInterfaces(
      Content.class,
      iface -> iface.getSimpleName().endsWith("_content"),
      getAllElementContentModels(),
      clazz,
      expected
    );
  }

  @Test
  public void testNoImplementInherited() {
    for (Class<? extends Content> iface : getAllElementContentModels()) {
      InheritanceTestHelper.testNoImplementInherited(Content.class, iface);
    }
  }
}
