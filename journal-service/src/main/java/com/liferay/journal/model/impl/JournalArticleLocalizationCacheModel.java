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

package com.liferay.journal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.model.JournalArticleLocalization;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JournalArticleLocalization in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleLocalization
 * @generated
 */
@ProviderType
public class JournalArticleLocalizationCacheModel implements CacheModel<JournalArticleLocalization>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JournalArticleLocalizationCacheModel)) {
			return false;
		}

		JournalArticleLocalizationCacheModel journalArticleLocalizationCacheModel =
			(JournalArticleLocalizationCacheModel)obj;

		if ((journalArticleLocalizationId == journalArticleLocalizationCacheModel.journalArticleLocalizationId) &&
				(mvccVersion == journalArticleLocalizationCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, journalArticleLocalizationId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", journalArticleLocalizationId=");
		sb.append(journalArticleLocalizationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", journalArticlePK=");
		sb.append(journalArticlePK);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JournalArticleLocalization toEntityModel() {
		JournalArticleLocalizationImpl journalArticleLocalizationImpl = new JournalArticleLocalizationImpl();

		journalArticleLocalizationImpl.setMvccVersion(mvccVersion);
		journalArticleLocalizationImpl.setJournalArticleLocalizationId(journalArticleLocalizationId);
		journalArticleLocalizationImpl.setCompanyId(companyId);
		journalArticleLocalizationImpl.setJournalArticlePK(journalArticlePK);

		if (languageId == null) {
			journalArticleLocalizationImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			journalArticleLocalizationImpl.setLanguageId(languageId);
		}

		if (title == null) {
			journalArticleLocalizationImpl.setTitle(StringPool.BLANK);
		}
		else {
			journalArticleLocalizationImpl.setTitle(title);
		}

		if (description == null) {
			journalArticleLocalizationImpl.setDescription(StringPool.BLANK);
		}
		else {
			journalArticleLocalizationImpl.setDescription(description);
		}

		journalArticleLocalizationImpl.resetOriginalValues();

		return journalArticleLocalizationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		journalArticleLocalizationId = objectInput.readLong();

		companyId = objectInput.readLong();

		journalArticlePK = objectInput.readLong();
		languageId = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(journalArticleLocalizationId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(journalArticlePK);

		if (languageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long mvccVersion;
	public long journalArticleLocalizationId;
	public long companyId;
	public long journalArticlePK;
	public String languageId;
	public String title;
	public String description;
}