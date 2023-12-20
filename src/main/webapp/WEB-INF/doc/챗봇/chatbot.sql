/**********************************/
/* Table Name: 챗봇 */
/**********************************/

CREATE TABLE chatting(
  chattingno   NUMBER(8)    NOT NULL PRIMARY KEY,
  customerno     NUMBER(10)   NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼
  msg          VARCHAR(300) NOT NULL, -- 채팅 메시지
  rdate        DATE             NOT NULL, -- 가입일
  FOREIGN KEY (customerno) REFERENCES N_CUSTOMER (customerno)
)

CREATE SEQUENCE chatting_seq
    START WITH 1        -- 시작 번호
    INCREMENT BY 1      -- 증가값
    MAXVALUE 99999999   -- 최대값: 99999999 --> NUMBER(8) 대응
    CACHE 2             -- 2번은 메모리에서만 계산
    NOCYCLE             -- 다시 1부터 생성되는 것을 방지
    
    
INSERT INTO chatting(chattingno, customerno, msg, rdate)
VALUES(chatting_seq.nextval, 2, '안녕하세요.', sysdate)
    