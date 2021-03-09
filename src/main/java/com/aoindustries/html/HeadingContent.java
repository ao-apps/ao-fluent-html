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

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/dom.html#heading-content">3.2.5.2.4 Heading content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#heading_content">Heading content</a>.</li>
 * </ul>
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface HeadingContent<__ extends HeadingContent<__>> extends
	//
	// Content models:
	//
	// Inherited: Content<__>

	//
	// Factories:
	//
	H1_factory<__>,
	H2_factory<__>,
	H3_factory<__>,
	H4_factory<__>,
	H5_factory<__>,
	H6_factory<__>,
	HGROUP_factory<__>
{
	/**
	 * Opens a new h# element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	default <
		H   extends com.aoindustries.html.H<H, __, H__, H_c>,
		H__ extends com.aoindustries.html.H__<__, H__>,
		H_c extends com.aoindustries.html.H_c<__, H_c>
	> H h(int rank) throws IOException {
		switch(rank) {
			case 1 : return (H)h1();
			case 2 : return (H)h2();
			case 3 : return (H)h3();
			case 4 : return (H)h4();
			case 5 : return (H)h5();
			case 6 : return (H)h6();
			default :
				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
		}
	}
//	default <
//		E  extends H<E, __, h__, h_c>,
//		h__ extends H__<__, h__>,
//		h_c extends H_c<__, h_c>
//	> H<E, __, h__, h_c> h(int rank) throws IOException {
//		switch(rank) {
//			case 1 : return h1();
//			case 2 : return h2();
//			case 3 : return h3();
//			case 4 : return h4();
//			case 5 : return h5();
//			case 6 : return h6();
//			default :
//				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
//		}
//	}
//	default H<
//		?,
//		__,
//		? extends H__<__, ? extends H__<__, ?>>,
//		? extends H_c<__, ? extends H_c<__, ?>>
//	> h(int rank) throws IOException {
//		switch(rank) {
//			case 1 : return h1();
//			case 2 : return h2();
//			case 3 : return h3();
//			case 4 : return h4();
//			case 5 : return h5();
//			case 6 : return h6();
//			default :
//				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
//		}
//	}
//	@SuppressWarnings("unchecked")
//	default <
//		H extends com.aoindustries.html.H<
//			H,
//			__,
//			? extends H__<__, ?>,
//			? extends H_c<__, ?>
////			? extends H__<__, ? extends H__<__, ?>>,
////			? extends H_c<__, ? extends H_c<__, ?>>
////			? extends H__<__, ? extends H__<__, ? extends H__<__, ?>>>, // TODO: This nesting could go forever without self-referential generics
////			? extends H_c<__, ? extends H_c<__, ? extends H_c<__, ?>>>  // TODO: This nesting could go forever without self-referential generics
//		>
//	> H h(int rank) throws IOException {
//		switch(rank) {
//			case 1 : return (H)h1();
//			case 2 : return (H)h2();
//			case 3 : return (H)h3();
//			case 4 : return (H)h4();
//			case 5 : return (H)h5();
//			case 6 : return (H)h6();
//			default :
//				throw new LocalizedIllegalArgumentException(Resources.PACKAGE_RESOURCES, "HeadingContent.invalidRank", rank);
//		}
//	}

	/**
	 * Creates an h# element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ h__(int rank, IORunnableE<Ex> h) throws IOException, Ex {
		return h(rank).__(h);
	}

	/**
	 * Creates an h# element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <
		H__ extends com.aoindustries.html.H__<__, H__>,
		Ex extends Throwable
	> __ h__(int rank, IOConsumerE<? super H__, Ex> h) throws IOException, Ex {
		return h(rank).__(h);
	}
//	default <Ex extends Throwable> __ h__(int rank, IOConsumerE<? super H__<__, ?>, Ex> h) throws IOException, Ex {
//		return h(rank).__(h);
//	}
//	default <Ex extends Throwable> __ h__(int rank, IOConsumerE<? super H__<__, ? extends H__<__, ?>>, Ex> h) throws IOException, Ex {
//		return h(rank).__(h);
//	}

	/**
	 * Creates an h# element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ h__(int rank, Object text) throws IOException {
		return h(rank).__(text);
	}

	/**
	 * Creates an empty h# element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ h__(int rank) throws IOException {
		return h(rank).__();
	}

	/**
	 * Creates an h# element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/sections.html#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	@SuppressWarnings("unchecked")
	default <
		H_c extends com.aoindustries.html.H_c<__, H_c>
	> H_c h_c(int rank) throws IOException {
		return (H_c)h(rank)._c();
	}
//	default H_c<__, ?> h_c(int rank) throws IOException {
//		return h(rank)._c();
//	}
//	default H_c<__, ? extends H_c<__, ?>> h_c(int rank) throws IOException {
//		return h(rank)._c();
//	}
}
