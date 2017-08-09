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

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Savinov
 */
public class JournalStringUtil {

	public static String shortenWithHtml(String string, int length) {
		if (string.length() <= length) {
			return string;
		}

		String shortString = StringUtil.shorten(string, length, "");

		List<String> tags = new ArrayList<>();

		Matcher matcher = _pattern.matcher(shortString);

		while (matcher.find()) {
			String tag = matcher.group(1);

			char start = tag.charAt(0);
			char end = tag.charAt(tag.length() - 1);

			if (start == CharPool.SLASH) {
				tag = tag.substring(1);

				for (String openTag : tags) {
					if (StringUtil.equalsIgnoreCase(tag, openTag)) {
						tags.remove(openTag);

						break;
					}
				}
			}
			else if (start != CharPool.EXCLAMATION && end != CharPool.SLASH) {
				int index = tag.indexOf(CharPool.SPACE);

				if (index != -1) {
					tag = tag.substring(0, index);
				}

				tags.add(tag);
			}
		}

		List<String> tokens = new ArrayList<>();

		tokens.add(shortString);

		for (String tag : tags) {
			tokens.add(StringPool.LESS_THAN);
			tokens.add(StringPool.SLASH);
			tokens.add(tag);
			tokens.add(StringPool.GREATER_THAN);
		}

		StringBundler sb = new StringBundler(tokens.size());

		for (String token : tokens) {
			sb.append(token);
		}

		String cutString = string.substring(shortString.length());

		cutString = cutString.replaceAll("<\\/[^\\s].*?>", "");

		if ((shortString.length() < string.length()) &&
			Validator.isNotNull(cutString)) {

			sb.append(StringPool.TRIPLE_PERIOD);
		}

		return sb.toString();
	}

	private static final Pattern _pattern = Pattern.compile(
		"<(\\/?[^\\s].*?)>");

}