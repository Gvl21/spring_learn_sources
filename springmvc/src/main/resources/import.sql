--  게시글 추가
INSERT INTO ARTICLE(TITLE, CONTENT) VALUES ('인생 영화는?', '댓글 달아주세요');
INSERT INTO ARTICLE(TITLE, CONTENT) VALUES ('인생 음식은?', '댓글 달아주세요');
INSERT INTO ARTICLE(TITLE, CONTENT) VALUES ('취미는?', '댓글 달아주세요');
INSERT INTO ARTICLE(TITLE, CONTENT) VALUES ('123?', '댓글 달아주세요');

-- 댓글
INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (4, 'john', '기생충');
INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (4, 'jane', '신과 함께');
INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (4, 'tim', '쇼생크 탈출');

---- 댓글 음식
--INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (5, 'tom', '치킨');
--INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (5, 'jane', '피자');
--INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (5, 'john', '초밥');
--
---- 댓글 취미
--INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (6, 'jane', '독서');
--INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (6, 'john', '컴퓨터');
--INSERT INTO COMMENT(ARTICLE_ID, NICKNAME, BODY) VALUES (6, 'tim', '산책');