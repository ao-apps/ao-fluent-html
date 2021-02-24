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
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Blockquote<PC extends PalpableContent<PC>> extends
	NormalTextElement<Blockquote<PC>, PC, Blockquote.BlockquoteContent<PC>, Blockquote.BlockquoteCloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// TODO: cite
	Attributes.Event.AlmostGlobal<Blockquote<PC>>
{

	public static class BlockquoteContent<PC extends PalpableContent<PC>> extends
		NormalTextContent<PC, BlockquoteContent<PC>> implements
		PhrasingContent<BlockquoteContent<PC>> {

		protected BlockquoteContent(Blockquote<PC> element) {
			super(element);
		}
	}

	public static class BlockquoteCloseableContent<PC extends PalpableContent<PC>> extends
		CloseableNormalTextContent<PC, BlockquoteCloseableContent<PC>> implements
		PhrasingContent<BlockquoteCloseableContent<PC>> {

		protected BlockquoteCloseableContent(Blockquote<PC> element) {
			super(element);
		}
	}

	public Blockquote(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Blockquote<PC> writeOpen() throws IOException {
		document.out.write("<blockquote");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></blockquote>" : "</blockquote>");
	}

	@Override
	protected BlockquoteContent<PC> newC() {
		return new BlockquoteContent<>(this);
	}

	@Override
	protected BlockquoteCloseableContent<PC> newCC() {
		return new BlockquoteCloseableContent<>(this);
	}
}
