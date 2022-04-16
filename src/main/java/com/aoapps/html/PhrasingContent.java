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

package com.aoapps.html;

import com.aoapps.html.any.AnyAREA;
import com.aoapps.html.any.AnyPhrasingContent;
import com.aoapps.html.any.Circle;
import com.aoapps.html.any.Suppliers;
import com.aoapps.lang.io.function.IOConsumerE;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#phrasing-content">3.2.5.2.5 Phrasing content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#phrasing_content">Phrasing content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PhrasingContent<
	__ extends PhrasingContent<__>
> extends AnyPhrasingContent<Document, __>,
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<__>
	// Inherited: Union_Embedded_Interactive<__>
	// Inherited: Union_Embedded_Palpable_Phrasing<__>
	// Inherited: Union_Interactive_Phrasing<__>
	Union_Metadata_Phrasing<__>,
	Union_Palpable_Phrasing<__>,

	//
	// Content models:
	//
	// Inherited: Content<__>
	EmbeddedContent<__>
	// Inherited: ScriptSupportingContent<__>
	// Inherited: TextContent<__>
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// <editor-fold defaultstate="collapsed" desc="AREA - if a descendent of map">
	@Override
	@SuppressWarnings("deprecation")
	default AREA<__> area() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new AREA<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	@Override
	default AREA<__> area(Rectangle rect) throws IOException {
		return area().shape(AnyAREA.Shape.RECT).coords(rect);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> AREA<__> area(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
		return area().shape(AnyAREA.Shape.RECT).coords(rect);
	}

	@Override
	default AREA<__> area(Circle circle) throws IOException {
		return area().shape(AnyAREA.Shape.CIRCLE).coords(circle);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> AREA<__> area(Suppliers.Circle<Ex> circle) throws IOException, Ex {
		return area().shape(AnyAREA.Shape.CIRCLE).coords(circle);
	}

	@Override
	default AREA<__> area(Polygon poly) throws IOException {
		return area().shape(AnyAREA.Shape.POLY).coords(poly);
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> AREA<__> area(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
		return area().shape(AnyAREA.Shape.POLY).coords(poly);
	}

	@Override
	default AREA<__> area(Shape shape) throws IOException {
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
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	default <Ex extends Throwable> AREA<__> area(Suppliers.Shape<Ex> shape) throws IOException, Ex {
		return area(shape == null ? null : shape.get());
	}
	// </editor-fold>
	// Inherited: AUDIO
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// <editor-fold defaultstate="collapsed" desc="BR">
	@Override
	@SuppressWarnings("deprecation")
	default BR<__> br() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new BR<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}
	// </editor-fold>
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// <editor-fold defaultstate="collapsed" desc="DATALIST">
	@Override
	@SuppressWarnings("deprecation")
	default DATALIST<__> datalist() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new DATALIST<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	/**
	 * Creates a datalist element with no attributes and the given body.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/datalist">&lt;datalist&gt;: The HTML Data List element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_datalist.asp">HTML datalist Tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 *
	 * @since HTML 5
	 */
	default <Ex extends Throwable> __ datalist__(IOConsumerE<? super DATALIST__<__>, Ex> datalist) throws IOException, Ex {
		return datalist().__(datalist);
	}

	@Override
	default DATALIST_c<__> datalist_c() throws IOException {
		return datalist()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="DEL">
	@Override
	@SuppressWarnings("deprecation")
	default DEL<__> del() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new DEL<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}

	@Override
	default DEL_c<__> del_c() throws IOException {
		return del()._c();
	}
	// </editor-fold>
	// Inherited: DFN
	// Inherited: EM
	// Inherited: EMBED
	// Inherited: I
	// Inherited: IFRAME
	// Inherited: IMG
	// Inherited: INPUT
	// Inherited: INS
	// Inherited: KBD
	// Inherited: LABEL
	// Inherited: LINK - if it is allowed in body
	// Inherited: MAP
	// Inherited: MARK
	// Inherited: MathML math
	// Inherited: META - if the itemprop attribute is present
	// Inherited: METER
	// Inherited: NOSCRIPT
	// Inherited: OBJECT
	// Inherited: OUTPUT
	// Inherited: PICTURE
	// Inherited: PROGRESS
	// Inherited: Q
	// Inherited: RUBY
	// Inherited: S
	// Inherited: SAMP
	// Inherited: SCRIPT
	// Inherited: SELECT
	// <editor-fold defaultstate="collapsed" desc="TODO: SLOT">
	// </editor-fold>
	// Inherited: SMALL
	// Inherited: SPAN
	// Inherited: STRONG
	// Inherited: SUB
	// Inherited: SUP
	// Inherited: SVG svg
	// Inherited: TEMPLATE
	// Inherited: TEXTAREA
	// Inherited: TIME
	// Inherited: U
	// Inherited: VAR
	// Inherited: VIDEO
	// <editor-fold defaultstate="collapsed" desc="WBR">
	@Override
	@SuppressWarnings("deprecation")
	default WBR<__> wbr() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new WBR<>(document, pc).writeOpen(document.getRawUnsafe(null));
	}
	// </editor-fold>
	// Inherited: autonomous custom elements
}
