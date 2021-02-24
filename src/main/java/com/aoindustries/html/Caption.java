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

import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no descendant table elements.
public class Caption<PC extends TableContent<PC>> extends
	NormalTextElement<Caption<PC>, PC, Caption.CaptionContent<PC>, Caption.CaptionCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Caption<PC>>
{

	public static class CaptionContent<PC extends TableContent<PC>> extends
		NormalTextContent<PC, CaptionContent<PC>> implements
		FlowContent<CaptionContent<PC>> {

		protected CaptionContent(Caption<PC> element) {
			super(element);
		}
	}

	public static class CaptionCloseableContent<PC extends TableContent<PC>> extends
		CloseableNormalTextContent<PC, CaptionCloseableContent<PC>> implements
		FlowContent<CaptionCloseableContent<PC>> {

		protected CaptionCloseableContent(Caption<PC> element) {
			super(element);
		}
	}

	public Caption(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Caption<PC> writeOpen() throws IOException {
		document.out.write("<caption");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></caption>" : "</caption>");
	}

	@Override
	protected CaptionContent<PC> newC() {
		return new CaptionContent<>(this);
	}

	@Override
	protected CaptionCloseableContent<PC> newCC() {
		return new CaptionCloseableContent<>(this);
	}
}
