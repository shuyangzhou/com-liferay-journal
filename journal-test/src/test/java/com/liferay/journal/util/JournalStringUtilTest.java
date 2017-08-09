/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.journal.util;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.InputStreamReader;

import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Pavel Savinov
 */
@RunWith(PowerMockRunner.class)
public class JournalStringUtilTest {

	@Test
	public void testClosedTags() {
		String string = "<div>123456789</div>";

		Assert.assertEquals(
			"<div>12345</div>...",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "6789</div>".length()));
		Assert.assertEquals(
			string,
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "</div>".length()));
	}

	@Test
	public void testHTMLFromFile() throws Exception {
		StringBundler sb = new StringBundler();

		Class<?> clazz = getClass();

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new InputStreamReader(
						clazz.getResourceAsStream(_FILE_NAME)))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				sb.append(line);
			}
		}

		String text = sb.toString();

		Assert.assertEquals(
			text, JournalStringUtil.shortenWithHtml(text, text.length()));

		String shortenString = JournalStringUtil.shortenWithHtml(
			text, text.length() / 2);

		Source source = new Source(shortenString);

		List<StartTag> tags = source.getAllStartTags();

		for (StartTag startTag : tags) {
			if (startTag.isEndTagRequired()) {
				Element element = startTag.getElement();

				Assert.assertNotNull(element.getEndTag());
			}
		}
	}

	@Test
	public void testManyUnclosedTags() {
		String string = "<a><b><i>123456789</a></b>";

		Assert.assertEquals(
			"<a><b><i>123456</i></b></a>...",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "789</a></b>".length()));
	}

	@Test
	public void testNoModification() {
		String string = "abcdefg";

		Assert.assertSame(
			string, JournalStringUtil.shortenWithHtml(string, string.length()));
		Assert.assertSame(
			string,
			JournalStringUtil.shortenWithHtml(string, string.length() + 1));
	}

	@Test
	public void testNoOpenTag() {
		String string = "12345</p>abc";

		Assert.assertEquals(
			"12345</p>...",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "abc".length()));

		Assert.assertEquals(
			"12345...",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "</p>abc".length()));
	}

	@Test
	public void testNoTags() {
		String string = "123456789";

		Assert.assertEquals(
			"12345678...",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "9".length()));
	}

	@Test
	public void testOnlyTagsNoText() {
		String string = "<div><p><a></a></div></p>";

		Assert.assertEquals(
			"<div><p><a></a></p></div>",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "</div></p>".length()));

		string = "<a><b><c><a></a></c></b></a>";

		Assert.assertEquals(
			string,
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "</c></b></a>".length()));
	}

	@Test
	public void testUnclosedTags() {
		String string = "<div>123456789";

		Assert.assertEquals(
			"<div>12345</div>...",
			JournalStringUtil.shortenWithHtml(
				string, string.length() - "6789".length()));
	}

	private static final String _FILE_NAME =
		"dependencies/test-shorten-with-html.html";

}