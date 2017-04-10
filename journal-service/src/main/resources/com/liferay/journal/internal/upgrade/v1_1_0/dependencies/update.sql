create table JournalArticleLocalization (
	mvccVersion LONG default 0 not null,
	journalArticleLocalizationId LONG not null primary key,
	companyId LONG,
	journalArticlePK LONG,
	languageId VARCHAR(75) null,
	title VARCHAR(400) null,
	description STRING null
);

create unique index IX_ACF2560A on JournalArticleLocalization (articlePK, languageId[$COLUMN_LENGTH:75$]);

alter table JournalArticle add defaultLanguageId VARCHAR(75) null;

COMMIT_TRANSACTION;