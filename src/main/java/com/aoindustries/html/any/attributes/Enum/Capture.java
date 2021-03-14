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
package com.aoindustries.html.any.attributes.Enum;

import com.aoindustries.html.any.AnyDocument;
import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.html.any.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 * @param  <V>   This enum type to use for this attribute
 *
 * @author  AO Industries, Inc.
 */
public interface Capture<
	E extends Element<?, ?, E> & Capture<E, V>,
	V extends Enum<V> & Function<AnyDocument<?>, String>
> {

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E capture(String capture) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "capture", MarkupType.NONE, capture, true, true);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #capture(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E capture(Suppliers.String<Ex> capture) throws IOException, Ex {
		return capture((capture == null) ? null : capture.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
	 * </ul>
	 *
	 * @see #capture(java.lang.String)
	 */
	default E capture(V capture) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return capture((capture == null) ? null : capture.apply(element.getDocument()));
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
	 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #capture(java.lang.Enum)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E capture(IOSupplierE<? extends V, Ex> capture) throws IOException, Ex {
		return capture((capture== null) ? (V)null : capture.get());
	}
}
