/**********************************/
/* Table Name: 식당예약 */
/**********************************/

DROP TABLE RESERVATION
CREATE TABLE RESERVATION(
		RESERV_NO                     		NUMBER(10)		     NOT NULL    PRIMARY KEY,
		RES_NAME                          	VARCHAR2(30)		 NOT NULL,
		RESERV_NAME                   		VARCHAR2(30)		 NOT NULL,
		RESERV_PHONE                  		VARCHAR2(14)		 NOT NULL ,
		CNT                           		NUMBER(3)		     NOT NULL,
		RDATE                         		VARCHAR2(50)		 NOT NULL,
		CUSTOMERNO                    		NUMBER(10)		     NULL ,
		MANAGERNO                     		NUMBER(10)		     NOT NULL ,
		RESNO                         		NUMBER(10)		     NOT NULL,
        FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO),
        FOREIGN KEY (MANAGERNO) REFERENCES MANAGER (MANAGERNO),
        FOREIGN KEY (RESNO) REFERENCES RESTAURANT (RESNO)
);

COMMENT ON TABLE RESERVATION is '식당예약';
COMMENT ON COLUMN RESERVATION.RESERV_NO is '예약번호';
COMMENT ON COLUMN RESERVATION.RES_NAME is '식당이름';
COMMENT ON COLUMN RESERVATION.RESERV_NAME is '예약자성함';
COMMENT ON COLUMN RESERVATION.RESERV_PHONE is '예약자휴대폰번호';
COMMENT ON COLUMN RESERVATION.CNT is '인원';
COMMENT ON COLUMN RESERVATION.RDATE is '날짜';
COMMENT ON COLUMN RESERVATION.CUSTOMERNO is '회원 번호';
COMMENT ON COLUMN RESERVATION.MANAGERNO is '관리자 번호';
COMMENT ON COLUMN RESERVATION.RESNO is '식당번호';

DROP SEQUENCE RESERVATION_SEQ;

CREATE SEQUENCE RESERVATION_SEQ
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;

1. 등록
INSERT INTO reservation(reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, managerno, resno, customerno)
VALUES (reservation_seq.nextval, '자유식당', '왕눈이', '000-0000-0000', 5, '2023-12-8', 1, 1, 1);

2. 비회원 등록
INSERT INTO reservation(reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, managerno, resno)
VALUES (reservation_seq.nextval, '자유식당', '왕눈이2', '000-0000-0000', 5, '2023-12-8', 1, 1);

3. 관리자별 목록
SELECT reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, managerno, resno, customerno
FROM reservation
WHERE managerno = 1
ORDER BY reserv_no ASC;

4. 회원 예약 목록
SELECT reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, managerno, resno, customerno
FROM reservation
WHERE customerno = 1
ORDER BY reserv_no ASC;

5. 조회
SELECT reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, managerno, resno, customerno
FROM reservation
WHERE reserv_no = 1;

6. 수정
UPDATE reservation 
SET reserv_name = '왕왕이', reserv_phone='010-9999-9999', cnt = 2, rdate='2023-12-10 11:00:00'
WHERE reserv_no=1;

7. 삭제
DELETE FROM reservation where reserv_no = 2;

8. 페이징
SELECT reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, r
FROM (
           SELECT reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate, rownum as r
           FROM (
                     SELECT reserv_no, res_name, reserv_name, reserv_phone, cnt, rdate
                     FROM reservation
                     WHERE managerno = 1 AND (UPPER(reserv_name) LIKE '%' || UPPER('멍멍이') || '%'
                                              OR UPPER(reserv_phone) LIKE '%' || UPPER('멍멍이') || '%')
                     ORDER BY reserv_no ASC
           )          
)
WHERE r >= 1 AND r <= 3;

9. 검색 레코드 갯수
SELECT COUNT(*) as cnt
    FROM reservation
    -- WHERE managerno=#{managerno}
    WHERE managerno = 1 AND (UPPER(reserv_name) LIKE '%' || UPPER('1111') || '%'
                            OR UPPER(reserv_phone) LIKE '%' || UPPER('1111') || '%')

commit;