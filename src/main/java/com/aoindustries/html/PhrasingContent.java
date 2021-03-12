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

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
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
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface PhrasingContent<
	D  extends AnyDocument<D>,
	__ extends PhrasingContent<D, __>
> extends
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<D, __>
	// Inherited: Union_Embedded_Interactive<D, __>
	// Inherited: Union_Embedded_Palpable_Phrasing<D, __>
	// Inherited: Union_Interactive_Phrasing<D, __>
	Union_Metadata_Phrasing<D, __>,
	Union_Palpable_Phrasing<D, __>,

	//
	// Content models:
	//
	// Inherited: Content<D, __>
	EmbeddedContent<D, __>
	// Inherited: ScriptSupportingContent<D, __>
	// Inherited: TextContent<D, __>
{
	//
	// Factories:
	//
	// Inherited: A
	// Inherited: ABBR
	// <editor-fold defaultstate="collapsed" desc="AREA - if a descendent of map">
	/**
	 * Opens a new area element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
	 * </ul>
	 */
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
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
	@Factory("area")
	default <Ex extends Throwable> AREA<D, __> area(Suppliers.Shape<Ex> shape) throws IOException, Ex {
		return area(shape == null ? null : shape.get());
	}
	// </editor-fold>
	// Inherited: AUDIO
	// Inherited: B
	// Inherited: BDI
	// Inherited: BDO
	// <editor-fold defaultstate="collapsed" desc="BR">
	/**
	 * Opens a new br element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-br-element">4.5.27 The br element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.</li>
	 * </ul>
	 */
	@Factory("br")
	default BR<D, __> br() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new BR<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a br element with no attributes.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-br-element">4.5.27 The br element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.</li>
	 * </ul>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("br")
	default __ br__() throws IOException {
		return br().__();
	}
	// </editor-fold>
	// Inherited: BUTTON
	// Inherited: CANVAS
	// Inherited: CITE
	// Inherited: CODE
	// Inherited: DATA
	// <editor-fold defaultstate="collapsed" desc="DATALIST">
	/**
	 * Opens a new datalist element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
	 * </p>
	 */
	@Factory("datalist")
	default DATALIST<D, __> datalist() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DATALIST<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a datalist element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("datalist")
	default <Ex extends Throwable> __ datalist__(IORunnableE<Ex> datalist) throws IOException, Ex {
		return datalist().__(datalist);
	}

	/**
	 * Creates a datalist element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("datalist")
	default <Ex extends Throwable> __ datalist__(IOConsumerE<? super DATALIST__<D, __>, Ex> datalist) throws IOException, Ex {
		return datalist().__(datalist);
	}

	/**
	 * Creates a datalist element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("datalist")
	default __ datalist__(Object text) throws IOException {
		return datalist().__(text);
	}

	/**
	 * Creates an empty datalist element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("datalist")
	default __ datalist__() throws IOException {
		return datalist().__();
	}

	/**
	 * Creates a datalist element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-datalist-element">4.10.8 The datalist element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@Factory("datalist")
	default DATALIST_c<D, __> datalist_c() throws IOException {
		return datalist()._c();
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="DEL">
	/**
	 * Opens a new del element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 */
	@Factory("del")
	default DEL<D, __> del() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new DEL<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a del element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("del")
	default <Ex extends Throwable> __ del__(IORunnableE<Ex> del) throws IOException, Ex {
		return del().__(del);
	}

	/**
	 * Creates a del element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("del")
	default <Ex extends Throwable> __ del__(IOConsumerE<? super __, Ex> del) throws IOException, Ex {
		return del().__(del);
	}

	/**
	 * Creates an empty del element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	@Factory("del")
	default __ del__() throws IOException {
		return del().__();
	}

	/**
	 * Creates a del element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/edits.html#the-del-element">4.7.2 The del element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *          <p>
	 *          Due to limitations in Java generics, this content model does not directly reflect the parent content
	 *          model, despite this being a transparent content model.  Rather, it includes only the content model that
	 *          always applies to this element type.
	 *          </p>
	 *          <p><em>
	 *          For the full, context-aware content model, which will likely include more elements,
	 *          {@linkplain Transparent_c#pc() use the parent content model directly}.
	 *          </em></p>
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 * @see  Transparent_c#pc()
	 */
	@Factory("del")
	default DEL_c<D, __> del_c() throws IOException {
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
	// <editor-fold defaultstate="collapsed" desc="SLOT">
	/**
	 * Opens a new slot element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-slot-element">4.12.4 The slot element</a>.
	 * </p>
	 */
	@Factory("slot")
	default void slot() throws IOException {
		throw new AssertionError("TODO: Implement slot");
	}
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
	/**
	 * Opens a new wbr element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-wbr-element">4.5.28 The wbr element</a>.
	 * </p>
	 */
	@Factory("wbr")
	default void wbr() throws IOException {
		throw new AssertionError("TODO: Implement wbr");
	}
	// </editor-fold>
	// Inherited: autonomous custom elements
}
