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

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">4.8.14 The area element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface AREA_factory<
	D  extends AnyDocument<D>,
	__ extends PhrasingContent<D, __>
> extends Content<D, __> {

	/**
	 * Opens a new area element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 */
	default AREA<D, __> area() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new AREA<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 */
	default AREA<D, __> area(Rectangle rect) throws IOException {
		return area().shape(AREA.Shape.RECT).coords(rect);
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	default <Ex extends Throwable> AREA<D, __> area(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
		return area().shape(AREA.Shape.RECT).coords(rect);
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 */
	default AREA<D, __> area(Circle circle) throws IOException {
		return area().shape(AREA.Shape.CIRCLE).coords(circle);
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	default <Ex extends Throwable> AREA<D, __> area(Suppliers.Circle<Ex> circle) throws IOException, Ex {
		return area().shape(AREA.Shape.CIRCLE).coords(circle);
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 */
	default AREA<D, __> area(Polygon poly) throws IOException {
		return area().shape(AREA.Shape.POLY).coords(poly);
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	default <Ex extends Throwable> AREA<D, __> area(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
		return area().shape(AREA.Shape.POLY).coords(poly);
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 */
	default AREA<D, __> area(Shape shape) throws IOException {
		if (shape == null) {
			return area();
		}
		if (shape instanceof Rectangle) {
			return area((Rectangle) shape);
		}
		if (shape instanceof Circle) {
			return area((Circle) shape);
		}
		if (shape instanceof Polygon) {
			return area((Polygon) shape);
		}
		// Pass-through in a way that must result in an exception for the unknown type instead of duplicating long exception message here
		area().coords(shape);
		throw new AssertionError("IllegalArgumentException must have been thrown by coords for invalid Shape");
	}

	/**
	 * Opens a new area element with the given coords attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	default <Ex extends Throwable> AREA<D, __> area(Suppliers.Shape<Ex> shape) throws IOException, Ex {
		return area(shape == null ? null : shape.get());
	}
}
