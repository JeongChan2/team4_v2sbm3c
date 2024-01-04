/**********************************/
/* Table Name: 댓글 */
/**********************************/
DROP TABLE REPLY;
CREATE TABLE REPLY(
		REPLYNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		REPLYCONTENTS                 		CLOB		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		RESCONTENTSNO                 		NUMBER(10)		 NULL ,
		CUSTOMERNO                    		NUMBER(10)		 NULL ,
        RE_REPLY                    		NUMBER(5)   DEFAULT 0   NULL ,
  FOREIGN KEY (RESCONTENTSNO) REFERENCES RESCONTENTS (RESCONTENTSNO),
  FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO)
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글번호';
COMMENT ON COLUMN REPLY.REPLYCONTENTS is '댓글';
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.RESCONTENTSNO is '식당컨텐츠 번호';
COMMENT ON COLUMN REPLY.CUSTOMERNO is '회원 번호';
COMMENT ON COLUMN REPLY.RE_REPLY is '대댓글';


DROP SEQUENCE REPLY_SEQ;

CREATE SEQUENCE REPLY_SEQ
    START WITH 1        -- 시작번호
    INCREMENT BY 1      -- 증가값
    MAXVALUE 9999999999 -- 최대값: 9999999999 --> NUMBER(10)대응
    CACHE 2             -- 2번은 메모리에서만 계산
    NOCYCLE;            -- 다시 1부터 생성되는 것을 방지




-- CREATE
INSERT INTO REPLY(REPLYNO, REPLYCONTENTS, RDATE, RESCONTENTSNO, CUSTOMERNO) 
VALUES(REPLY_SEQ.nextval, '다사랑 이거 맛있어요', sysdate, 9,11);
INSERT INTO REPLY(REPLYNO, REPLYCONTENTS, RDATE, RESCONTENTSNO, CUSTOMERNO) 
VALUES(REPLY_SEQ.nextval, '응 구라임', sysdate, 9,11);

-- READ; LIST
SELECT * FROM REPLY;

SELECT REPLYNO, REPLYCONTENTS, RDATE, RESCONTENTSNO, CUSTOMERNO FROM REPLY ORDER BY RDATE ASC;

SELECT r.REPLYNO, r.REPLYCONTENTS, r.RDATE, r.RESCONTENTSNO, r.customerno,
        c.customerno, c.cname
FROM REPLY r INNER JOIN n_customer c ON c.customerno = r.customerno
ORDER BY r.RDATE ASC;

-- DELETE about RESCONTENTSNO
DELETE FROM REPLY WHERE RESCONTENTSNO=9;

-- DELETE about customerno
DELETE FROM REPLY WHERE customerno=11;

-- DELETE about REPLYNO
DELETE FROM REPLY WHERE REPLYNO=9;

-- UPDATE
UPDATE REPLY 
SET REPLYCONTENTS = '이게 맞을까요?'
WHERE replyno = 3;


SELECT COUNT(*) as cnt
FROM REPLY
WHERE RESCONTENTSNO = 9;

SELECT r.replyno, r.replycontents, r.rdate, r.rescontentsno, r.customerno,
        c.customerno, c.cname
FROM REPLY r INNER JOIN n_customer c ON c.customerno = r.customerno
WHERE rescontentsno = 11
ORDER BY r.rdate ASC;



SELECT replyno, replycontents, rdate, rescontentsno, customerno, cname, rownum as t
        FROM (SELECT r.replyno, r.replycontents, r.rdate, r.rescontentsno,
                        c.customerno, c.cname
                FROM REPLY r INNER JOIN n_customer c ON c.customerno = r.customerno
                WHERE rescontentsno = 11
                ORDER BY r.rdate ASC)

SELECT replyno, replycontents, rdate, rescontentsno, customerno, cname, t
FROM(
    SELECT replyno, replycontents, rdate, rescontentsno, customerno, cname, rownum as t
            FROM (SELECT r.replyno, r.replycontents, r.rdate, r.rescontentsno,
                            c.customerno, c.cname
                    FROM REPLY r INNER JOIN n_customer c ON c.customerno = r.customerno
                    WHERE rescontentsno = 11
                    ORDER BY r.rdate ASC)
)
WHERE t >= 1 AND t <= 4;

SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, word, rdate,file1, file1saved, thumb1, size1, foodno
FROM(
SELECT r.rescontentsno, r.managerno, r.resno, r.title, r.rescontent, r.recom, r.cnt, r.replycnt, r.passwd, r.word, r.rdate,r.file1, r.file1saved, r.thumb1, r.size1, r.foodno
    FROM rescontents r
    WHERE r.resno = (SELECT resno FROM restaurant WHERE typenum = (SELECT typenum FROM recommend WHERE customerno = 10))
    ORDER BY NVL((SELECT AVG(NVL(score,0)) FROM score WHERE rescontentsno = r.rescontentsno),0) DESC
)
WHERE rownum<=5 
    





COMMIT;