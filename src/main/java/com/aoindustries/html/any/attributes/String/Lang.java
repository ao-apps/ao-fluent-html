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
package com.aoindustries.html.any.attributes.String;

import com.aoindustries.html.any.Attributes;
import com.aoindustries.html.any.Element;
import com.aoindustries.html.any.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.Locale;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#the-lang-and-xml:lang-attributes">3.2.6.2 The lang and xml:lang attributes</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang">lang - HTML: HyperText Markup Language | MDN</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/att_lang.asp">HTML lang Attribute</a>.</li>
 * </ul>
 *
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
// Idea: ThreadLocale could be leveraged to automatically add lang attributes when current language has changed.
//       But this would begin to change the nature of this API.
public interface Lang<E extends Element<?, ?, E> & Lang<E>> {

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#the-lang-and-xml:lang-attributes">3.2.6.2 The lang and xml:lang attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang">lang - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_lang.asp">HTML lang Attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E lang(String lang) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return Attributes.String.attribute(element, "lang", MarkupType.NONE, lang, true, true);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#the-lang-and-xml:lang-attributes">3.2.6.2 The lang and xml:lang attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang">lang - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_lang.asp">HTML lang Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #lang(java.lang.String)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E lang(IOSupplierE<? extends String, Ex> lang) throws IOException, Ex {
		return lang((lang == null) ? null : lang.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#the-lang-and-xml:lang-attributes">3.2.6.2 The lang and xml:lang attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang">lang - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_lang.asp">HTML lang Attribute</a>.</li>
	 * </ul>
	 *
	 * @see #lang(java.lang.String)
	 */
	default E lang(Locale lang) throws IOException {
		return lang((lang == null) ? null : lang.toLanguageTag());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#the-lang-and-xml:lang-attributes">3.2.6.2 The lang and xml:lang attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang">lang - HTML: HyperText Markup Language | MDN</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_lang.asp">HTML lang Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #lang(java.util.Locale)
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E lang(Suppliers.Locale<Ex> lang) throws IOException, Ex {
		return lang((lang == null) ? null : lang.get());
	}
}
