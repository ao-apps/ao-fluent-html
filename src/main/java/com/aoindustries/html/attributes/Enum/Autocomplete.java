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
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import com.aoindustries.html.Attributes;
import static com.aoindustries.html.Attributes.RESOURCES;
import com.aoindustries.html.Document;
import com.aoindustries.html.Element;
import com.aoindustries.html.Suppliers;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.lang.Strings;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
public interface Autocomplete<
	E extends Element<E, ?> & Autocomplete<E, V>,
	V extends Enum<V> & Function<Document, String>
> {

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E autocomplete(String autocomplete) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		if(element.getDocument().doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				element.getDocument().doctype,
				"autocomplete"
			);
		}
		return Attributes.String.attribute(element, "autocomplete", MarkupType.NONE, autocomplete, true, true);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
	 * </ul>
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E autocomplete(Suppliers.String<Ex> autocomplete) throws IOException, Ex {
		return autocomplete((autocomplete == null) ? null : autocomplete.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
	 * </ul>
	 */
	default E autocomplete(V autocomplete) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		return autocomplete((autocomplete == null) ? null : autocomplete.apply(element.getDocument()));
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
	 * </ul>
	 */
	@SuppressWarnings("overloads")
	default <Ex extends Throwable> E autocomplete(IOSupplierE<? extends V, Ex> autocomplete) throws IOException, Ex {
		return autocomplete((autocomplete== null) ? (V)null : autocomplete.get());
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	default E autocomplete(String ... autocomplete) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		Document document = element.getDocument();
		if(document.doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				document.doctype,
				"autocomplete"
			);
		}
		if(autocomplete != null) {
			Writer out = document.getUnsafe(null);
			boolean didOne = false;
			for(String value : autocomplete) {
				String trimmed = Strings.trimNullIfEmpty(value);
				if(trimmed != null) {
					if(!didOne) {
						if(document.getAtnl()) {
							// Can't access: document.autoIndent(out, 1);
							document.autoIndent(1);
							out.write("autocomplete=\"");
							document.clearAtnl();
						} else {
							out.write(" autocomplete=\"");
						}
						didOne = true;
					} else {
						out.append(' ');
					}
					encodeTextInXhtmlAttribute(trimmed, out);
				}
			}
			if(didOne) out.append('"');
		}
		return element;
	}

	// TODO: More variants for suppliers of arrays, arrays of suppliers, iterables, ...?

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
	 * </ul>
	 */
	@Attributes.Funnel
	@SuppressWarnings("unchecked") // generic varargs
	default E autocomplete(V ... autocomplete) throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		Document document = element.getDocument();
		if(document.doctype != Doctype.HTML5) {
			throw new LocalizedIllegalArgumentException(
				RESOURCES,
				"onlySupportedInHtml5",
				document.doctype,
				"autocomplete"
			);
		}
		if(autocomplete != null) {
			Writer out = document.getUnsafe(null);
			boolean didOne = false;
			for(V value : autocomplete) {
				if(value != null) {
					if(!didOne) {
						if(document.getAtnl()) {
							// Can't access: document.autoIndent(out, 1);
							document.autoIndent(1);
							out.write("autocomplete=\"");
							document.clearAtnl();
						} else {
							out.write(" autocomplete=\"");
						}
						didOne = true;
					} else {
						out.append(' ');
					}
					encodeTextInXhtmlAttribute(value.apply(document), out);
				}
			}
			if(didOne) out.append('"');
		}
		return element;
	}

	// TODO: More variants for suppliers of arrays, arrays of suppliers, iterables, ...?
}
