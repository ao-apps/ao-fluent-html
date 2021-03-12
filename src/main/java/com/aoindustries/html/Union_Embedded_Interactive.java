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

import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;

/**
 * Elements that are common to both {@link EmbeddedContent} and {@link InteractiveContent}.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Embedded_Interactive<
	D  extends AnyDocument<D>,
	__ extends Union_Embedded_Interactive<D, __>
> extends
	//
	// Content models:
	//
	Content<D, __>
{
	//
	// Factories:
	//
	// <editor-fold defaultstate="collapsed" desc="AUDIO">
	/**
	 * Opens a new audio element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/media.html#the-audio-element">4.8.10 The audio element</a>.
	 * </p>
	 */
	@Factory("audio")
	default void audio() throws IOException {
		throw new AssertionError("TODO: Implement audio");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="EMBED">
	/**
	 * Opens a new embed element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-embed-element">4.8.6 The embed element</a>.
	 * </p>
	 */
	@Factory("embed")
	default void embed() throws IOException {
		throw new AssertionError("TODO: Implement embed");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="IFRAME">
	/**
	 * Opens a new iframe element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-iframe-element">4.8.5 The iframe element</a>.
	 * </p>
	 */
	@Factory("iframe")
	default void iframe() throws IOException {
		throw new AssertionError("TODO: Implement iframe");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="IMG">
	/**
	 * Opens a new img element.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/embedded-content.html#the-img-element">4.8.3 The img element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
	 * </ul>
	 */
	@Factory("img")
	default IMG<D, __> img() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new IMG<>(document, pc).writeOpen(document.getUnsafe(null));
	}


	/**
	 * Opens a new img element with the given src attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/embedded-content.html#the-img-element">4.8.3 The img element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
	 * </ul>
	 */
	@Factory("img")
	default IMG<D, __> img(String src) throws IOException {
		return img().src(src);
	}

	/**
	 * Opens a new img element with the given src attribute.
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/embedded-content.html#the-img-element">4.8.3 The img element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Factory("img")
	default <Ex extends Throwable> IMG<D, __> img(IOSupplierE<? extends String, Ex> src) throws IOException, Ex {
		return img().src(src);
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="OBJECT">
	/**
	 * Opens a new object element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/iframe-embed-object.html#the-object-element">4.8.7 The object element</a>.
	 * </p>
	 */
	@Factory("object")
	default void object() throws IOException {
		throw new AssertionError("TODO: Implement object");
	}
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="VIDEO">
	/**
	 * Opens a new video element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/media.html#the-video-element">4.8.9 The video element</a>.
	 * </p>
	 */
	@Factory("video")
	default void video() throws IOException {
		throw new AssertionError("TODO: Implement video");
	}
	// </editor-fold>
}
