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
package com.aoindustries.html.any;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.html.any.AnySTYLE.Type;
import com.aoindustries.io.ContentType;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Coercion;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.lang.Strings;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">4.2.4 The link element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link">&lt;link&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
abstract public class AnyLINK<
	D  extends AnyDocument<D>,
	PC extends AnyUnion_Metadata_Phrasing<D, PC>,
	E  extends AnyLINK<D, PC, E>
> extends Void<D, PC, E> implements
	// TODO: as
	// TODO: charset
	com.aoindustries.html.any.attributes.Enum.Crossorigin<E, AnyLINK.Crossorigin>,
	// https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link#attr-disabled
	com.aoindustries.html.any.attributes.Boolean.Disabled<E>,
	com.aoindustries.html.any.attributes.Url.Href<E>,
	com.aoindustries.html.any.attributes.String.Hreflang<E>,
	com.aoindustries.html.any.attributes.Text.Media<E>,
	com.aoindustries.html.any.attributes.Enum.Rel<E, AnyLINK.Rel>,
	// TODO: rev
	// TODO: sizes
	// TODO: target (not standardized per MDN)
	// TODO: type
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<E>,
	com.aoindustries.html.any.attributes.event.window.Onerror<E>,
	com.aoindustries.html.any.attributes.event.window.Onload<E>
{

	private static final com.aoindustries.i18n.Resources RESOURCES =
		com.aoindustries.i18n.Resources.getResources(AnyLINK.class);

	protected AnyLINK(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<link", false);
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 */
	public enum Crossorigin implements Function<AnyDocument<?>, String> {
		ANONYMOUS(
			Attributes.NO_VALUE,
			"anonymous"
		),
		USE_CREDENTIALS(
			"use-credentials",
			"use-credentials"
		);

		private final String sgml;
		private final String xml;

		private Crossorigin(String sgml, String xml) {
			this.sgml = sgml;
			this.xml = xml;
		}

		@Override
		public String toString() {
			return xml;
		}

		@Override
		public String apply(AnyDocument<?> document) {
			if(document.serialization == Serialization.SGML) {
				return sgml;
			} else {
				assert document.serialization == Serialization.XML;
				return xml;
			}
		}
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 */
	private Object itemprop;

	// TODO: Is global property, move there and add See comment, still checking for link-specific rules here
	// TODO: Attributes...itemprop in global
	public E itemprop(Object itemprop) throws IOException {
		itemprop = Coercion.trimNullIfEmpty(itemprop);
		if(itemprop != null) {
			if(this.itemprop != null) {
				throw new LocalizedIllegalStateException(
					AnyDocument.RESOURCES,
					"duplicateAttribute",
					"link",
					"itemprop",
					Coercion.toString(this.itemprop),
					Coercion.toString(itemprop)
				);
			}
			this.itemprop = itemprop;
			if(this.rel != null) {
				throw new LocalizedIllegalStateException(RESOURCES, "relOrItemprop");
			}
			Writer out = document.getUnsafe(null);
			if(document.getAtnl()) {
				document.autoIndent(out, 1);
				out.write("itemprop=\"");
				document.clearAtnl();
			} else {
				out.write(" itemprop=\"");
			}
			Coercion.write(itemprop, textInXhtmlAttributeEncoder, out);
			out.append('"');
		}
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Link_types">Link types - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 */
	public enum Rel implements Function<AnyDocument<?>, String> {
		ALTERNATE("alternate"),
		/**
		 * @deprecated
		 */
		@Deprecated
		ARCHIVES("archives"), // MDN only
		AUTHOR("author"), // w3schools, MDN only
		CANONICAL("canonical"), // MDN only
		DNS_PREFETCH("dns-prefetch"),
		/**
		 * @deprecated
		 */
		@Deprecated
		FIRST("first"), // MDN only
		HELP("help"), // w3schools, MDN only
		ICON("icon"),
		IMPORT("import"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		INDEX("index"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		LAST("last"), // MDN only
		LICENSE("license"), // w3schools, MDN only
		MANIFEST("manifest"), // MDN only
		MODULEPRELOAD("modulepreload"),
		NEXT("next"),
		PINGBACK("pingback"),
		PRECONNECT("preconnect"),
		PREFETCH("prefetch"),
		PRELOAD("preload"),
		PRERENDER("prerender"),
		PREV("prev"), // w3schools, MDN only
		SEARCH("search"),
		SHORTLINK("shortlink"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		SIDEBAR("sidebar"), // MDN only
		STYLESHEET("stylesheet"),
		/**
		 * @deprecated
		 */
		@Deprecated
		UP("up"), // MDN only

		/**
		 * <p>
		 * To specify an icon for a single webpage or replace the website icon with a webpage-specific icon, add a link element to the webpage.
		 * </p>
		 * <p>
		 * See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.
		 * </p>
		 */
		APPLE_TOUCH_ICON("apple-touch-icon"),

		/**
		 * <p>
		 * On iOS, similar to native applications, you can specify a launch screen image that is displayed while your web application launches.
		 * </p>
		 * <p>
		 * See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.
		 * </p>
		 */
		APPLE_TOUCH_STARTUP_IMAGE("apple-touch-startup-image");

		private final String value;
		// TODO: Verify values by doctype

		private Rel(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public String apply(AnyDocument<?> document) {
			return value;
		}
	}

	private String rel;

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 */
	@Override
	public E rel(String rel) throws IOException {
		rel = Strings.trimNullIfEmpty(rel);
		if(rel != null) {
			if(this.rel != null) {
				throw new LocalizedIllegalStateException(
					AnyDocument.RESOURCES,
					"duplicateAttribute",
					"link",
					"rel",
					Coercion.toString(this.rel),
					Coercion.toString(rel)
				);
			}
			this.rel = rel;
			if(this.itemprop != null) {
				throw new LocalizedIllegalStateException(RESOURCES, "relOrItemprop");
			}
			com.aoindustries.html.any.attributes.Enum.Rel.super.rel(rel);
		}
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #rel(java.lang.String)
	 */
	@Override
	public <Ex extends Throwable> E rel(Suppliers.String<Ex> rel) throws IOException, Ex {
		return com.aoindustries.html.any.attributes.Enum.Rel.super.rel(rel);
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 *
	 * @see #rel(java.lang.String)
	 */
	@Override
	public E rel(Rel rel) throws IOException {
		return com.aoindustries.html.any.attributes.Enum.Rel.super.rel(rel);
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @see #rel(com.aoindustries.html.AnyLINK.Rel)
	 */
	@Override
	public <Ex extends Throwable> E rel(IOSupplierE<? extends Rel, Ex> rel) throws IOException, Ex {
		return com.aoindustries.html.any.attributes.Enum.Rel.super.rel(rel);
	}

	private String type;

	/**
	 * If the rel is {@link Rel#STYLESHEET}, the type is {@link Type#TEXT_CSS},
	 * and the {@link Doctype} is {@link Doctype#HTML5}, skips writing
	 * the type.
	 *
	 * See <a href="https://www.w3schools.com/tags/att_link_type.asp">HTML link type Attribute</a>.
	 */
	public E type(String type) throws IOException {
		type = Strings.trimNullIfEmpty(type);
		this.type = type;
		if(
			type != null
			&& !(
				document.doctype == Doctype.HTML5
				&& rel != null
				&& rel.equalsIgnoreCase(Rel.STYLESHEET.toString())
				&& ContentType.CSS.equalsIgnoreCase(type)
			)
		) {
			Writer out = document.getUnsafe(null);
			if(document.getAtnl()) {
				document.autoIndent(out, 1);
				out.write("type=\"");
				document.clearAtnl();
			} else {
				out.write(" type=\"");
			}
			encodeTextInXhtmlAttribute(type, out);
			out.append('"');
		}
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * If the rel is {@link Rel#STYLESHEET}, a {@linkplain #type(java.lang.String) type}
	 * has not been written, and the {@link Doctype} is not {@link Doctype#HTML5},
	 * writes the default type {@link Type#TEXT_CSS} for backward compatibility.
	 * </p>
	 * <p>
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * </p>
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 *
	 * @return  The parent content model this element is within
	 */
	@Override
	public PC __() throws IOException {
		if(
			type == null
			&& document.doctype != Doctype.HTML5
			&& rel != null
			&& rel.equalsIgnoreCase(Rel.STYLESHEET.toString())
		) {
			Writer out = document.getUnsafe(null);
			if(document.getAtnl()) {
				document.autoIndent(out, 1);
				out.write("type=\"" + ContentType.CSS + '"');
				document.clearAtnl();
			} else {
				out.write(" type=\"" + ContentType.CSS + '"');
			}
		}
		return super.__();
	}

	@Override
	protected void doAfterElement(Writer out) throws IOException {
		document.autoNl(out);
		if(rel == null && itemprop == null) {
			throw new LocalizedIllegalStateException(RESOURCES, "relOrItemprop");
		}
	}
}
