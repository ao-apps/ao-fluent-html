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
package com.aoindustries.html.attributes.Enum;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Document;
import com.aoindustries.html.Element;
import com.aoindustries.html.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
// TODO: Support java Charset, too
public interface Charset<
	E extends Element<E, ?> & Charset<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E charset(String charset) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				element.getDocument().doctype,
				"charset"
			);
		}
		return Attributes.String.attribute(element, "charset", MarkupType.NONE, charset, true, true);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
	 * </ul>
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E charset(Suppliers.String<Ex> charset) throws IOException, Ex {
		return charset((charset == null) ? null : charset.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
	 * </ul>
	 */
	default E charset(V charset) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return charset((charset == null) ? null : charset.apply(element.getDocument()));
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
	 * </ul>
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E charset(IOSupplierE<? extends V, Ex> charset) throws IOException, Ex {
		return charset((charset == null) ? (V)null : charset.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
	 * <li>See <a href="https://www.iana.org/assignments/character-sets/character-sets.xhtml">Character Sets</a>.</li>
	 * </ul>
	 */
	public enum Value implements Function<Document, String> {
		// TODO: Add other charsets here?
		US_ASCII("US-ASCII"),
		ISO_8859_1("ISO-8859-1"),
		UTF_8("UTF-8"),
		WINDOWS_1252("windows-1252");

		private final String value;

		private Value(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public String apply(Document document) {
			return value;
		}

		public String getValue() {
			return value;
		}
	}
}
