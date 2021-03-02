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

import com.aoindustries.lang.reflect.Classes;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Assert;

/**
 * Support class for inheritance hierarchy tests.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
class InheritanceTests {

	private InheritanceTests() {}

	/**
	 * Makes sure no class or interface directly implements an interface that is also inherited.
	 */
	static void testNoImplementInherited(Class<? extends Content> clazz) {
		// Find all inherited interfaces
		Set<Class<? extends Content>> inherited = new LinkedHashSet<>();
		for(Class<?> iface : clazz.getInterfaces()) {
			if(Content.class.isAssignableFrom(iface)) {
				Class<? extends Content> contentIface = iface.asSubclass(Content.class);
				Set<Class<? extends Content>> higherInherited = Classes.getAllClasses(contentIface, Content.class);
				Assert.assertTrue(higherInherited.remove(contentIface));
				inherited.addAll(higherInherited);
			}
		}
		// Look for any direct interface that is also inherited
		for(Class<?> iface : clazz.getInterfaces()) {
			if(Content.class.isAssignableFrom(iface)) {
				Class<? extends Content> contentIface = iface.asSubclass(Content.class);
				//System.out.println("Direct interface: " + contentIface);
				Assert.assertFalse(
					clazz.getName() + " may not both directly implement and inherit " + contentIface.getName() + " - comment-out direct implements to be inherited-only.",
					inherited.contains(contentIface)
				);
				testNoImplementInherited(contentIface);
			}
		}
	}

	// TODO: Test generic upper bounds consistency between Element, Content, and Factories?
}
