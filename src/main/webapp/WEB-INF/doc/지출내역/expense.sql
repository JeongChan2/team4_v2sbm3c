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
        FOREIGN KEY (resno) REFERENCES restaurant (resno)
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
INSERT INTO food(foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat)
VALUES (food_seq.nextval, '싸이버거', '594', '60', '28', '27');

2. 목록
SELECT foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat
FROM food
ORDER BY foodno ASC;

3. 조회
SELECT foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat
FROM food
WHERE foodname = '싸이버거';

4. 수정
UPDATE food 
SET foodname='불싸이버거', f_calories = 543, f_carbohydrates = 76, f_protein = 28, f_fat =7
WHERE foodname='싸이버거';

5. 삭제
DELETE FROM food where foodno = 3;