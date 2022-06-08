CREATE TABLE fileboard(
unq INT unsigned NOT NULL AUTO_INCREMENT,
name VARCHAR(30),
title VARCHAR(30),
fileName VARCHAR(100),
hits INT unsigned default '0',
uploadDate DATETIME,
PRIMARY KEY(unq)
);

INSERT INTO fileboard VALUES(1, '테스트', '오늘먹은음식', 'file1', 0, sysdate());
