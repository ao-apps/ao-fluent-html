/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020, 2021  AO Industries, Inc.
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

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.Serialization;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class Meta<PC extends UnionContent.Metadata_Phrasing<PC>> extends VoidElement<Meta<PC>, PC> implements
	Attributes.Enum.Charset<Meta<PC>, Attributes.Enum.Charset.Value>,
	Attributes.Text.Content<Meta<PC>>,
	Attributes.Enum.HttpEquiv<Meta<PC>, Meta.HttpEquiv>,
	Attributes.Enum.Name<Meta<PC>, Meta.Name>
	// TODO: scheme
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <meta>: Attributes.Event.AlmostGlobal<Meta>
{

	public Meta(Document document) {
		super(document);
	}

	@Override
	protected Meta<PC> open() throws IOException {
		document.out.write("<meta");
		return this;
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * </ul>
	 */
	public enum HttpEquiv implements Attributes.Enum.EnumSupplier {
		/**
		 * Defines the default language of the page.
		 * It can be overridden by the lang attribute on any element. 
		 *
		 * @deprecated  Do not use this value, as it is obsolete. Prefer the <code>lang</code> attribute on the &lt;html&gt; element.
		 */
		@Deprecated
		CONTENT_LANGUAGE("content-language"),

		/**
		 * Allows page authors to define a <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy">content policy</a> for the current page.
		 * Content policies mostly specify allowed server origins and script endpoints which help guard against cross-site scripting attacks.
		 */
		CONTENT_SECURITY_POLICY("content-security-policy"),

		/**
		 * <p>
		 * Authors should specify the default scripting language for all scripts in a document.
		 * </p>
		 * <p>
		 * See <a href="https://www.w3.org/TR/REC-html40/interact/scripts.html#h-18.2.2.1">Scripts in HTML documents</a>.
		 * </p>
		 *
		 * @deprecated  This is no longer required as of HTML 5.
		 */
		@Deprecated
		CONTENT_SCRIPT_TYPE("content-script-type"),

		/**
		 * <p>
		 * Authors must specify the style sheet language of style information associated with an HTML document.
		 * </p>
		 * <p>
		 * See <a href="https://www.w3.org/TR/html401/present/styles.html#h-14.2.1">Style Sheets in HTML documents</a>.
		 * </p>
		 *
		 * @deprecated  This is no longer required as of HTML 5.
		 */
		@Deprecated
		CONTENT_STYLE_TYPE("content-style-type"),

		/**
		 * Defines the <a href="https://developer.mozilla.org/en-US/docs/Glossary/MIME_type">MIME type</a> of the document, followed by its character encoding.
		 * It follows the same syntax as the HTTP <code>content-type</code> entity-header field,
		 * but as it is inside a HTML page, most values other than <code>text/html</code> are impossible.
		 * Therefore the valid syntax for its <code>content</code> is the string '<code>text/html</code>'
		 * followed by a character set with the following syntax: '<code>; charset=IANAcharset</code>',
		 * where <code>IANAcharset</code> is the <i>preferred MIME name</i> for a character set as
		 * <a href="https://www.iana.org/assignments/character-sets">defined by the IANA</a>.
		 *
		 * @deprecated  Do not use this value, as it is obsolete. Use the {@link Attributes.Enum.Charset charset}
		 *              attribute on the {@link Meta &lt;meta&gt;} element.
		 */
		@Deprecated
		CONTENT_TYPE("content-type"),

		/**
		 * <p>
		 * The <a href="https://www.w3.org/TR/2010/WD-html-markup-20100624/meta.html#meta">meta</a> element with a
		 * <code>http-equiv</code> attribute whose value is "<code>default-style</code>" represents a pragma directive
		 * that specifies the document’s preferred stylesheet.
		 * </p>
		 * <ul>
		 * <li>See <a href="https://www.w3.org/TR/2010/WD-html-markup-20100624/meta.http-equiv.default-style.html">HTML5: meta http-equiv=default-style – “preferred stylesheet” pragma directive</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
		 * </ul>
		 */
		DEFAULT_STYLE("default-style"),

		/**
		 * This instruction specifies:
		 * <ul>
		 * <li>
		 *   The number of seconds until the page should be reloaded
		 *   - only if the {@link Attributes.Text.Content content} attribute contains a positive integer.
		 * </li>
		 * <li>
		 *   The number of seconds until the page should redirect to another
		 *   - only if the {@link Attributes.Text.Content content} attribute contains a
		 *   positive integer followed by the string '<code>;url=</code>', and a valid URL.
		 * </li>
		 * </ul>
		 */
		REFRESH("refresh"),

		/**
		 * Defines a cookie for the page. Its content must follow the syntax defined in the
		 * <a href="https://tools.ietf.org/html/draft-ietf-httpstate-cookie-14">IETF HTTP Cookie Specification</a>.
		 *
		 * @deprecated  Do not use this instruction, as it is obsolete. Use the HTTP header
		 *              <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Set-Cookie">Set-Cookie</a>
		 *              instead. It was removed from the standard, and is no longer supported at all in
		 *              <a href="https://www.fxsitecompat.dev/en-CA/docs/2019/setting-cookies-with-meta-http-equiv-is-no-longer-allowed/">Firefox 68</a>
		 *              and <a href="https://www.chromestatus.com/feature/6170540112871424">Chrome 65</a>.
		 */
		@Deprecated
		SET_COOKIE("set-cookie");

		private final java.lang.String value;

		private HttpEquiv(java.lang.String value) {
			this.value = value;
		}

		@Override
		public java.lang.String toString() {
			return value;
		}

		@Override
		public java.lang.String get(Serialization serialization, Doctype doctype) {
			return value;
		}

		public java.lang.String getValue() {
			return value;
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_meta_name.asp">HTML meta name Attribute</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-name">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * </ul>
	 */
	public enum Name implements Attributes.Enum.EnumSupplier {
		/**
		 * <code>application-name</code> which defines the name of the application running in the web page. 
		 */
		APPLICATION_NAME("application-name"),

		/**
		 * <code>author</code> which defines the name of the document's author.
		 */
		AUTHOR("author"),

		/**
		 * <code>description</code> which contains a short and accurate summary of the content of the page.
		 * Several browsers, like Firefox and Opera, use this as the default description of bookmarked pages.
		 */
		DESCRIPTION("description"),

		/**
		 * <code>generator</code> which contains the identifier of the software that generated the page.
		 */
		GENERATOR("generator"),

		/**
		 * <code>keywords</code> which contains words relevant to the page's content separated by commas.
		 */
		KEYWORDS("keywords"),

		/**
		 * <code>referrer</code> which controls the
		 * HTTP <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Referer">Referer</a>
		 * header attached to requests sent from the document.
		 */
		REFERRER("referrer"),

		/**
		 * <code>theme-color</code> which indicates a suggested color that user agents should use
		 * to customize the display of the page or of the surrounding user interface.
		 */
		THEME_COLOR("theme-color"),

		/**
		 * Specifies one or more color schemes with which the document is compatible.
		 */
		COLOR_SCHEME("color-scheme"),

		/**
		 * <code>creator</code> which defines the name of the creator of the document,
		 * such as an organization or institution.
		 */
		CREATOR("creator"),

		/**
		 * <code>googlebot</code>, a synonym of {@link #ROBOTS robots},
		 * is only followed by Googlebot (the indexing crawler for Google).
		 */
		GOOGLEBOT("googlebot"),

		/**
		 * <code>publisher</code> which defines the name of the document's publisher.
		 */
		PUBLISHER("publisher"),

		/**
		 * <p>
		 * The robots meta tag lets you utilize a granular, page-specific
		 * approach to controlling how an individual page should be indexed
		 * and served to users.
		 * </p>
		 * <p>
		 * See <a href="https://developers.google.com/search/reference/robots_meta_tag">Robots meta tag, data-nosnippet, and X-Robots-Tag specifications</a>.
		 * </p>
		 */
		ROBOTS("robots"),

		/**
		 * <code>slurp</code>, is a synonym of {@link #ROBOTS robots},
		 * but only for Slurp - the crawler for Yahoo Search.
		 */
		SLURP("slurp"),

		/**
		 * <code>viewport</code>, which gives hints about the size of the initial size of the viewport.
		 * Used by mobile devices only. 
		 */
		VIEWPORT("viewport"),

		/**
		 * <p>
		 * On iOS, you can specify a web application title for the launch icon.
		 * </p>
		 * <p>
		 * See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.
		 * </p>
		 */
		APPLE_MOBILE_WEB_APP_TITLE("apple-mobile-web-app-title"),

		/**
		 * <p>
		 * On iOS, you can specify a web application title for the launch icon.
		 * Use with caution.
		 * </p>
		 * <ul>
		 * <li>See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.</li>
		 * <li>See <a href="https://medium.com/%40firt/dont-use-ios-web-app-meta-tag-irresponsibly-in-your-progressive-web-apps-85d70f4438cb">Don’t use iOS meta tags irresponsibly in your Progressive Web Apps</a>.</li>
		 * </ul>
		 */
		APPLE_MOBILE_WEB_APP_CAPABLE("apple-mobile-web-app-capable"),

		/**
		 * <p>
		 * On iOS, you can specify a web application title for the launch icon.
		 * </p>
		 * <p>
		 * See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.
		 * </p>
		 */
		APPLE_MOBILE_WEB_APP_STATUS_BAR_STYLE("apple-mobile-web-app-status-bar-style");

		private final java.lang.String value;

		private Name(java.lang.String value) {
			this.value = value;
		}

		@Override
		public java.lang.String toString() {
			return value;
		}

		@Override
		public java.lang.String get(Serialization serialization, Doctype doctype) {
			return value;
		}

		public java.lang.String getValue() {
			return value;
		}
	}
}
