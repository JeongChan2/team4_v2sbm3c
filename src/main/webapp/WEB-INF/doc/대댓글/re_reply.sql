/**********************************/
/* Table Name: 대댓글 */
/**********************************/
CREATE TABLE REPLY_OF_REPLY(
		REPLYOFREPLYNO                		NUMBER(10)		 NOT NULL,
		REPLYCONTENTS                 		CLOB		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		CUSTOMERNO                    		NUMBER(10)		 NULL ,
		REPLYNO                       		NUMBER(10)		 NULL,
        FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO),
        FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO)
);

COMMENT ON TABLE REPLY_OF_REPLY is '대댓글';
COMMENT ON COLUMN REPLY_OF_REPLY.REPLYOFREPLYNO is '댓글번호';
COMMENT ON COLUMN REPLY_OF_REPLY.REPLYCONTENTS is '댓글';
COMMENT ON COLUMN REPLY_OF_REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY_OF_REPLY.CUSTOMERNO is '회원 번호';
COMMENT ON COLUMN REPLY_OF_REPLY.REPLYNO is '댓글번호';

DROP SEQUENCE REPLY_OF_REPLY_SEQ;

CREATE SEQUENCE REPLY_OF_REPLY_SEQ
    START WITH 1        -- 시작번호
    INCREMENT BY 1      -- 증가값
    MAXVALUE 9999999999 -- 최대값: 9999999999 --> NUMBER(10)대응
    CACHE 2             -- 2번은 메모리에서만 계산
    NOCYCLE;            -- 다시 1부터 생성되는 것을 방지
    
    
    
    
    
-- CREATE
INSERT INTO REPLY_OF_REPLY(REPLYOFREPLYNO, REPLYCONTENTS, RDATE, CUSTOMERNO, REPLYNO) 
VALUES(REPLY_OF_REPLY_SEQ.nextval, '헿133', sysdate, 10, 29);
INSERT INTO REPLY_OF_REPLY(REPLYOFREPLYNO, REPLYCONTENTS, RDATE, CUSTOMERNO, REPLYNO) 
VALUES(REPLY_OF_REPLY_SEQ.nextval, '헿222', sysdate, 14, 27);

-- READ; LIST
SELECT * FROM REPLY_OF_REPLY;

SELECT REPLYOFREPLYNO, REPLYCONTENTS, RDATE, CUSTOMERNO, REPLYNO FROM REPLY_OF_REPLY ORDER BY RDATE ASC;

-- DELETE about customerno
DELETE FROM REPLY_OF_REPLY WHERE customerno=14;

-- DELETE about REPLYNO
DELETE FROM REPLY_OF_REPLY WHERE REPLYNO=29;

-- DELETE about REPLYOFREPLYNO
DELETE FROM REPLY_OF_REPLY WHERE REPLYOFREPLYNO=3;

-- UPDATE
UPDATE REPLY_OF_REPLY 
SET REPLYCONTENTS = '이게 맞을까요?'
WHERE REPLYOFREPLYNO = 3;


SELECT COUNT(*) as cnt
FROM REPLY_OF_REPLY
WHERE REPLYNO = 29;

SELECT replyofreplyno, replycontents, rdate, customerno, cname, replyno, t
FROM(
    SELECT replyofreplyno, replycontents, rdate, customerno, cname, replyno, rownum as t
            FROM (SELECT r.replyofreplyno, r.replycontents, r.rdate, c.customerno, c.cname, r.replyno
                    FROM REPLY_OF_REPLY r INNER JOIN n_customer c ON c.customerno = r.customerno
                    WHERE REPLYNO = 29
                    ORDER BY r.rdate ASC)
)
WHERE t >= 1 AND t <= 4;



UPDATE REPLY 
SET re_reply = re_reply + 1
WHERE replyno = 29;

UPDATE REPLY 
SET re_reply = re_reply - 1
WHERE replyno = 29;

SELECT replyofreplyno, replycontents, rdate, customerno, replyno
    FROM REPLY_OF_REPLY
    WHERE replyofreplyno = 3

    
commit;
