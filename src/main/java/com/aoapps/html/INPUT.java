/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021, 2022  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.html;

import com.aoapps.encoding.Doctype;
import com.aoapps.html.any.AnyINPUT;
import java.io.IOException;
import java.io.Writer;

/**
 * @see  AnyINPUT
 *
 * @author  AO Industries, Inc.
 */
public final class INPUT {

  /** Make no instances. */
  private INPUT() {
    throw new AssertionError();
  }

  /**
   * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
   * <p>
   * This implementation has all the input attributes,
   * supporting unanticipated or more dynamic configurations.
   * </p>
   * <p>
   * This does not limit attributes by type, and would allow mismatches where
   * type-specific implementations may constrain the attributes and values.
   * Although there is less validation, doctype-specific checks are expected
   * to remain, such as only allowing type="color" in {@link Doctype#HTML5}.
   * </p>
   *
   * @param  <PC>  The parent content model this element is within
   */
  @SuppressWarnings("deprecation")
  public static final class Dynamic<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Dynamic<Document, PC, Dynamic<PC>> {

    protected Dynamic(Document document, PC pc) {
      super(document, pc);
    }

    protected Dynamic(Document document, PC pc, String type) {
      super(document, pc, type);
    }

    protected Dynamic(Document document, PC pc, Type type) {
      super(document, pc, type);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Dynamic<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_button.asp">HTML input type="button"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Button<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Button<Document, PC, Button<PC>> {

    protected Button(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Button<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_checkbox.asp">HTML input type="checkbox"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Checkbox<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Checkbox<Document, PC, Checkbox<PC>> {

    protected Checkbox(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Checkbox<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Color<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Color<Document, PC, Color<PC>> {

    protected Color(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Color<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Date<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Date<Document, PC, Date<PC>> {

    protected Date(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Date<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class DatetimeLocal<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.DatetimeLocal<Document, PC, DatetimeLocal<PC>> {

    protected DatetimeLocal(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.DatetimeLocal<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Email<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Email<Document, PC, Email<PC>> {

    protected Email(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Email<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * <ul>
   * <li>See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file">&lt;input type="file"&gt;</a>.</li>
   * </ul>
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class File<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.File<Document, PC, File<PC>> {

    protected File(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.File<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Hidden<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Hidden<Document, PC, Hidden<PC>> {

    protected Hidden(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Hidden<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * <ul>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image">&lt;input type="image"&gt;</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.</li>
   * </ul>
   *
   * @param  <PC>  The parent content model this element is within
   */
  @SuppressWarnings("deprecation")
  public static final class Image<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Image<Document, PC, Image<PC>> {

    protected Image(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Image<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_month.asp">HTML input type="month"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Month<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Month<Document, PC, Month<PC>> {

    protected Month(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Month<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Number<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Number<Document, PC, Number<PC>> {

    protected Number(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Number<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Password<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Password<Document, PC, Password<PC>> {

    protected Password(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Password<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Radio<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Radio<Document, PC, Radio<PC>> {

    Radio(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Radio<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Range<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Range<Document, PC, Range<PC>> {

    protected Range(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Range<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Reset<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Reset<Document, PC, Reset<PC>> {

    protected Reset(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Reset<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_search.asp">HTML input type="search"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Search<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Search<Document, PC, Search<PC>> {

    protected Search(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Search<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * <ul>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit">&lt;input type="submit"&gt;</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
   * </ul>
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Submit<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Submit<Document, PC, Submit<PC>> {

    protected Submit(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Submit<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_tel.asp">HTML input type="tel"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Tel<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Tel<Document, PC, Tel<PC>> {

    protected Tel(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Tel<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   */
  public static final class Text<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Text<Document, PC, Text<PC>> {

    protected Text(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Text<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Time<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Time<Document, PC, Time<PC>> {

    protected Time(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Time<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Url<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Url<Document, PC, Url<PC>> {

    protected Url(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Url<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }

  /**
   * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
   *
   * @param  <PC>  The parent content model this element is within
   *
   * @since HTML 5
   */
  public static final class Week<
    PC extends Union_Interactive_Phrasing<PC>
  > extends AnyINPUT.Week<Document, PC, Week<PC>> {

    protected Week(Document document, PC pc) {
      super(document, pc);
    }

    // Expose to this package, avoiding public to keep a clean API for optimal code assist
    @Override
    protected INPUT.Week<PC> writeOpen(Writer unsafe) throws IOException {
      return super.writeOpen(unsafe);
    }
  }
}
