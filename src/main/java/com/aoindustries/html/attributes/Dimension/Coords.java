/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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
package com.aoindustries.html.attributes.Dimension;

import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Circle;
import com.aoindustries.html.Element;
import com.aoindustries.html.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.lang.Strings;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Objects;

/**
 * <ul>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public interface Coords<E extends Element<?, ?, E> & Coords<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	@SuppressWarnings("deprecation")
	default E coords(String coords) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.Dimension.attribute(element, "coords", coords);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #coords(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E coords(IOSupplierE<? extends String, Ex> coords) throws IOException, Ex {
		return coords((coords == null) ? null : coords.get());
	}

	// RECT

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(int left, int top, int right, int bottom) throws IOException {
		return coords(
			Integer.toString(left),
			Integer.toString(top),
			Integer.toString(right),
			Integer.toString(bottom)
		);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(Integer left, Integer top, Integer right, Integer bottom) throws IOException {
		return coords(
			Objects.toString(left, null),
			Objects.toString(top, null),
			Objects.toString(right, null),
			Objects.toString(bottom, null)
		);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(Rectangle rect) throws IOException {
		if(rect != null) {
			return coords(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
		} else {
			return coords((Integer)null, null, null, null);
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #coords(java.awt.Rectangle)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E coords(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
		return coords((rect == null) ? null : rect.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @deprecated  In HTML4, the values are numbers of pixels or percentages, if a percent sign (%) is appended;
	 *              in HTML5, the values are numbers of CSS pixels.
	 */
	@Deprecated
	default E coords(String left, String top, String right, String bottom) throws IOException {
		left = Strings.trimNullIfEmpty(left);
		top = Strings.trimNullIfEmpty(top);
		right = Strings.trimNullIfEmpty(right);
		bottom = Strings.trimNullIfEmpty(bottom);
		if(left != null || top != null || right != null || bottom != null) {
			return coords(left + "," + top + "," + right + "," + bottom);
		} else {
			return coords((String)null);
		}
	}

	// CIRCLE

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(int x, int y, int radius) throws IOException {
		return coords(
			Integer.toString(x),
			Integer.toString(y),
			Integer.toString(radius)
		);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(Integer x, Integer y, Integer radius) throws IOException {
		return coords(
			Objects.toString(x, null),
			Objects.toString(y, null),
			Objects.toString(radius, null)
		);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(Circle circle) throws IOException {
		if(circle != null) {
			return coords(circle.getX(), circle.getY(), circle.getRadius());
		} else {
			return coords((Integer)null, null, null);
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #coords(com.aoindustries.html.Circle)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E coords(Suppliers.Circle<Ex> circle) throws IOException, Ex {
		return coords((circle == null) ? null : circle.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @deprecated  In HTML4, the values are numbers of pixels or percentages, if a percent sign (%) is appended;
	 *              in HTML5, the values are numbers of CSS pixels.
	 */
	@Deprecated
	default E coords(String x, String y, String radius) throws IOException {
		x = Strings.trimNullIfEmpty(x);
		y = Strings.trimNullIfEmpty(y);
		radius = Strings.trimNullIfEmpty(radius);
		if(x != null || y != null || radius != null) {
			return coords(x + "," + y + "," + radius);
		} else {
			return coords((String)null);
		}
	}

	// POLY

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(Point ... poly) throws IOException {
		// TODO: This could be done via a streaming attribute at the cost of not going through the current single funnel
		StringBuilder sb = new StringBuilder();
		if(poly != null) {
			for(Point p : poly) {
				if(p != null) {
					if(sb.length() > 0) sb.append(',');
					sb.append(p.x).append(',').append(p.y);
				}
			}
		}
		return coords(sb.length() == 0 ? (String)null : sb.toString());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(Polygon poly) throws IOException {
		if(poly == null || poly.npoints == 0) {
			return coords((String)null);
		} else {
			// TODO: This could be done via a streaming attribute at the cost of not going through the current single funnel
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < poly.npoints; i++) {
				if(sb.length() > 0) sb.append(',');
				sb.append(poly.xpoints[i]).append(',').append(poly.ypoints[i]);
			}
			return coords(sb.toString());
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #coords(java.awt.Polygon)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E coords(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
		return coords((poly == null) ? null : poly.get());
	}

	// Shape base class

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 */
	default E coords(java.awt.Shape shape) throws IOException {
		if(shape == null) return coords((String)null);
		if(shape instanceof Rectangle) return coords((Rectangle)shape);
		if(shape instanceof Circle) return coords((Circle)shape);
		if(shape instanceof Polygon) return coords((Polygon)shape);
		throw new LocalizedIllegalArgumentException(
			RESOURCES,
			"Dimension.Coords.unexpectedShape",
			java.awt.Shape.class.getName(),
			"coords",
			java.awt.Rectangle.class.getName(),
			Circle.class.getName(),
			java.awt.Polygon.class.getName(),
			shape.getClass().getName(),
			shape.toString()
		);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #coords(java.awt.Shape)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E coords(Suppliers.Shape<Ex> shape) throws IOException, Ex {
		return coords((shape == null) ? null : shape.get());
	}
}
