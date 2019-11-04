/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019  AO Industries, Inc.
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

import java.io.IOException;

/**
 * Variant bounded by {@link String}, since can't have multiple methods with different bounds due to erasure.
 *
 * @author  AO Industries, Inc.
 */
@FunctionalInterface
public interface StringSupplierE<Ex extends Throwable> extends SupplierE<String,Ex> {

	/**
	 * Special value used in-place of return values that should result in an empty
	 * attribute (expected on {@link Serialization#SGML} only).
	 * This distinguishes from a return value of {@code null}, which causes the
	 * attribute to not be added at all.
	 * <p>
	 * In order to never conflict with an actual attribute value, this string is
	 * compared by identity, not by value.
	 * </p>
	 */
	String NO_VALUE = new String("<<<NO_VALUE>>>"); // Use string constructor to ensure unique instance for identity comparisons

	/**
	 * @return  The value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
	 */
	@Override
	String get(Serialization serialization, Doctype doctype) throws IOException, Ex;
}
