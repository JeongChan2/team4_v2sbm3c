/**********************************/
/* Table Name: 평점 */
/**********************************/
DROP TABLE SCORE;
CREATE TABLE SCORE(
		SCORENO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		SCORE                         		NUMBER(1)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		RESCONTENTSNO                 		NUMBER(10)		 NULL ,
		CUSTOMERNO                    		NUMBER(10)		 NULL ,
  FOREIGN KEY (RESCONTENTSNO) REFERENCES RESCONTENTS (RESCONTENTSNO),
  FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO)
);

COMMENT ON TABLE SCORE is '평점';
COMMENT ON COLUMN SCORE.SCORENO is '평점 번호';
COMMENT ON COLUMN SCORE.SCORE is '평점';
COMMENT ON COLUMN SCORE.RDATE is '등록일';
COMMENT ON COLUMN SCORE.RESCONTENTSNO is '식당컨텐츠 번호';
COMMENT ON COLUMN SCORE.CUSTOMERNO is '회원 번호';

DROP SEQUENCE SCORE_SEQ;

CREATE SEQUENCE SCORE_SEQ
    START WITH 1        -- 시작번호
    INCREMENT BY 1      -- 증가값
    MAXVALUE 9999999999 -- 최대값: 9999999999 --> NUMBER(10)대응
    CACHE 2             -- 2번은 메모리에서만 계산
    NOCYCLE;            -- 다시 1부터 생성되는 것을 방지

-- CREATE
INSERT INTO SCORE(SCORENO, SCORE, RDATE, RESCONTENTSNO, CUSTOMERNO) 
VALUES(SCORE_SEQ.nextval, 3, sysdate, 2,2);
INSERT INTO SCORE(SCORENO, SCORE, RDATE, RESCONTENTSNO, CUSTOMERNO) 
VALUES(SCORE_SEQ.nextval, 4, sysdate, 2,3);

-- READ; LIST
SELECT * FROM SCORE;
SELECT SCORENO, SCORE, RDATE, RESCONTENTSNO, CUSTOMERNO FROM SCORE ORDER BY SCORENO ASC;

-- SELECT 조회
SELECT AVG(SCORE)
FROM SCORE
WHERE RESCONTENTSNO = 2;

-- DELETE
DELETE FROM SCORE WHERE CUSTOMERNO = 3 AND RESCONTENTSNO=2;

-- UPDATE
UPDATE SCORE 
SET SCORE = '테스트 성공', resaddress = '테스트 성공', resphone = '테스트 성공', restime = '테스트 성공'
WHERE RESCONTENTSNO = 2 and CUSTOMERNO = 2;

SELECT COUNT(*) as cnt
    FROM SCORE
    WHERE RESCONTENTSNO = 1;




COMMIT;

SELECT r.rescontentsno, r.managerno, r.resno, r.title, r.rescontent, r.recom, r.cnt, r.replycnt, r.passwd,
        r.word, r.rdate,r.file1, r.file1saved, r.thumb1, r.size1, r.foodno, 
        s.scoreno, s.score, s.rescontentsno, s.customerno
FROM rescontents r INNER JOIN score s ON r.rescontentsno = s.rescontentsno
ORDER BY s.score DESC













/**********************************/
/* Table Name: 댓글 */
/**********************************/
DROP TABLE REPLY;
CREATE TABLE REPLY(
		REPLYNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		REPLYCONTENTS                 		CLOB		 NOT NULL,
		CHU                           		NUMBER(2)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		RESCONTENTSNO                 		NUMBER(10)		 NULL ,
		CUSTOMERNO                    		NUMBER(10)		 NULL ,
  FOREIGN KEY (RESCONTENTSNO) REFERENCES RESCONTENTS (RESCONTENTSNO),
  FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO)
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글번호';
COMMENT ON COLUMN REPLY.REPLYCONTENTS is '댓글';
COMMENT ON COLUMN REPLY.CHU is '추천수';
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.RESCONTENTSNO is '식당컨텐츠 번호';
COMMENT ON COLUMN REPLY.CUSTOMERNO is '회원 번호';


DROP SEQUENCE REPLY_SEQ;

CREATE SEQUENCE REPLY_SEQ
    START WITH 1        -- 시작번호
    INCREMENT BY 1      -- 증가값
    MAXVALUE 9999999999 -- 최대값: 9999999999 --> NUMBER(10)대응
    CACHE 2             -- 2번은 메모리에서만 계산
    NOCYCLE;            -- 다시 1부터 생성되는 것을 방지

