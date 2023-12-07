/**********************************/
/* Table Name: 지출내역 */
/**********************************/
CREATE TABLE EXPENSE(
		EXPENSENO                     		NUMBER(10)		 NOT NULL       PRIMARY KEY,
		NAME                          		VARCHAR2(50)		 NOT NULL,
		CNT                           		NUMBER(5)		 DEFAULT 0		 NOT NULL,
		PRICE                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		SUPPLIERNO                    		NUMBER(10)		 NULL ,
		MANAGERNO                     		NUMBER(10)		 NULL ,
		RESNO                         		NUMBER(10)		 NULL,
        FOREIGN KEY (resno) REFERENCES restaurant (resno),
        FOREIGN KEY (managerno) REFERENCES manager (managerno),
        FOREIGN KEY (supplierno) REFERENCES supplier (supplierno)
);

DROP SEQUENCE EXPENSE_SEQ;

CREATE SEQUENCE EXPENSE_SEQ
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;

COMMENT ON TABLE EXPENSE is '지출내역';
COMMENT ON COLUMN EXPENSE.EXPENSENO is '지출번호';
COMMENT ON COLUMN EXPENSE.NAME is '이름';
COMMENT ON COLUMN EXPENSE.CNT is '개수';
COMMENT ON COLUMN EXPENSE.PRICE is '지출금액';
COMMENT ON COLUMN EXPENSE.RDATE is '지출날짜';
COMMENT ON COLUMN EXPENSE.SUPPLIERNO is '업체번호';
COMMENT ON COLUMN EXPENSE.MANAGERNO is '관리자 번호';
COMMENT ON COLUMN EXPENSE.RESNO is '식당번호';

1. 등록
INSERT INTO expense(expenseno, name, cnt, price, rdate, supplierno, managerno, resno)
VALUES (expense_seq.nextval, '빗자루', 5, 30000, sysdate, 1, 1, 1);

2. 관리자별 목록
SELECT expenseno, name, cnt, price, rdate, supplierno, managerno, resno
FROM expense
WHERE managerno = 1
ORDER BY expenseno ASC;

3. 조회 X
SELECT expenseno, name, cnt, price, rdate, supplierno, managerno, resno
FROM expense
WHERE expenseno = 1;

4. 수정
UPDATE expense 
SET name = '과자', cnt = 6, price = 25000, supplierno =1
WHERE expenseno=1;

5. 삭제
DELETE FROM expense where expenseno = 1;

commit;