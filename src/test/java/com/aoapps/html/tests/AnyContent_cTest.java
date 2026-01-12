/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2026  AO Industries, Inc.
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

import com.aoapps.html.AnyContent_c;
import com.aoapps.html.Content_c;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Test;

/**
 * @see  AnyContent_c
 *
 * @author  AO Industries, Inc.
 */
public class AnyContent_cTest extends com.aoapps.html.any.tests.AnyContent_cTest {

  public AnyContent_cTest() {
    super(AnyContent_c.class);
  }

  @Test
  @Override
  public void testUnions() {
    UnionContent_cTest.testUnions(
        AnyContent_c.class,
        //
        // Unions:
        //
        UnionContent_cTest.getAllUnions()
    );
  }

  @Test
  @Override
  public void testContentModels() {
    ContentModel_cTest.testContentModels(
        AnyContent_c.class,
        //
        // Content models:
        //
        ContentModel_cTest.getAllContentModels()
    );
  }

  @Test
  @Override
  public void testElementContentModels() {
    ElementContentModel_cTest.testElementContentModels(
        AnyContent_c.class,
        //
        // Per-element content models:
        //
        ElementContentModel_cTest.getAllElementContentModels()
    );
  }

  @Test
  @Override
  public void testNoImplementInherited() {
    InheritanceTestHelper.testNoImplementInherited(Content_c.class, AnyContent_c.class);
  }
}
