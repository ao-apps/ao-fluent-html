/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020  AO Industries, Inc.
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
// TODO: Move this package to a new ao-fluent-html-utils project?
package com.aoindustries.html.util;

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInJavaScriptEncoder.encodeTextInJavaScript;
import com.aoindustries.html.Html;
import com.aoindustries.html.Link;
import com.aoindustries.lang.Strings;
import com.aoindustries.net.URIEncoder;
import java.io.IOException;

/**
 * Writes various versions of Google Analytics tracking scripts.
 *
 * @author  AO Industries, Inc.
 */
public class GoogleAnalytics {

	// Make no instances
	private GoogleAnalytics() {}

	/**
	 * Writes the modern Google Analytics <a href="https://support.google.com/analytics/answer/1008080?hl=en&amp;ref_topic=1008079#GA">Global Site Tag</a>.
	 * This is best used with {@link Doctype#HTML5}.
	 * This should be added first, or very hig	h up, in the <code>&lt;head&gt;</code>.
	 *
	 * @param trackingId  No script will be written when {@code null} or empty (after trimming)
	 */
	public static void writeGlobalSiteTag(Html html, String trackingId) throws IOException {
		trackingId = Strings.trimNullIfEmpty(trackingId);
		if(trackingId != null) {
			// See https://rehmann.co/blog/optimize-google-analytics-google-tag-manager-via-preconnect-headers/
			html.link(Link.Rel.DNS_PREFETCH).href("https://www.google-analytics.com").__().nl();
			html.link(Link.Rel.PRECONNECT).href("https://www.google-analytics.com").crossorigin(Link.Crossorigin.ANONYMOUS).__().nl();
			// + "<!-- Global site tag (gtag.js) - Google Analytics -->\n"
			// TODO: Attribute streaming src
			html.script().async(true).src("https://www.googletagmanager.com/gtag/js?id=" + URIEncoder.encodeURIComponent(trackingId)).__().nl();
			try (MediaWriter script = html.script().out__()) {
				script.write("window.dataLayer = window.dataLayer || [];\n"
					+ "function gtag(){dataLayer.push(arguments);}\n"
					+ "gtag(\"js\", new Date());\n"
					// + "\n"
					+ "gtag(\"config\", \"");
				encodeTextInJavaScript(trackingId, script);
				script.write("\");");
			}
			html.nl();
		}
	}

	/**
	 * Writes an older-style Google Analytics <a href="https://developers.google.com/analytics/devguides/collection/analyticsjs">analytics.js tracking script</a>.
	 * This is best used for compatibility with doctypes prior to {@link Doctype#HTML5}.
	 * This should be added first, or very high up, in the <code>&lt;head&gt;</code>.
	 *
	 * @param trackingId  No script will be written when {@code null} or empty (after trimming)
	 */
	// TODO: Support hitType exception? https://developers.google.com/analytics/devguides/collection/analyticsjs/field-reference
	public static void writeAnalyticsJs(Html html, String trackingId) throws IOException {
		trackingId = Strings.trimNullIfEmpty(trackingId);
		if(trackingId != null) {
			try (MediaWriter script = html.script().out__()) {
				script.write("(function(i,s,o,g,r,a,m){i[\"GoogleAnalyticsObject\"]=r;i[r]=i[r]||function(){\n"
					+ "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n"
					+ "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n"
					+ "})(window,document,\"script\",\"https://www.google-analytics.com/analytics.js\",\"ga\");\n"
					+ "ga(\"create\",\"");
				encodeTextInJavaScript(trackingId, script);
				script.write("\",\"auto\");\n"
					+ "ga(\"send\",\"pageview\");");
			}
			html.nl();
		}
	}
}
