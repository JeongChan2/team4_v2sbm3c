/**********************************/
/* Table Name: 판매내역 */
/**********************************/
DROP TABLE SELL;
CREATE TABLE SELL(
		SELLNO                     		NUMBER(10)		NOT NULL    PRIMARY KEY,
		NAME                          		VARCHAR2(50)	NOT NULL,
		CNT                           		NUMBER(5)		DEFAULT 0   NOT NULL,
		PRICE                         		NUMBER(10)		NOT NULL,
		RDATE                         		DATE		    NOT NULL,
		MANAGERNO                    		NUMBER(10)	    NOT NULL,
		FOODNO                     		    NUMBER(10)		NOT NULL,
		RESNO                         		NUMBER(10)		NOT NULL,
        FOREIGN KEY (resno) REFERENCES restaurant (resno),
        FOREIGN KEY (managerno) REFERENCES manager (managerno),
        FOREIGN KEY (FOODNO) REFERENCES food (FOODNO)
);

DROP SEQUENCE SELL_SEQ;

CREATE SEQUENCE SELL_SEQ
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;

COMMENT ON TABLE SELL is '판매내역';
COMMENT ON COLUMN SELL.SELLNO is '판매번호';
COMMENT ON COLUMN SELL.NAME is '음식이름';
COMMENT ON COLUMN SELL.CNT is '개수';
COMMENT ON COLUMN SELL.PRICE is '판매액';
COMMENT ON COLUMN SELL.RDATE is '날짜';
COMMENT ON COLUMN SELL.MANAGERNO is '관리자 번호';
COMMENT ON COLUMN SELL.FOODNO is '음식번호';
COMMENT ON COLUMN SELL.RESNO is '식당번호';

1. 등록
INSERT INTO sell(sellno, name, cnt, price, rdate, managerno, foodno, resno)
VALUES (sell_seq.nextval, '음식1', 5, 30000, sysdate, 1, 1, 1);

2. 관리자별 목록
SELECT sellno, name, cnt, price, rdate, managerno, foodno, resno
FROM sell
WHERE managerno = 1
ORDER BY sellno ASC;

3. 조회
SELECT sellno, name, cnt, price, rdate, managerno, foodno, resno
FROM sell
WHERE sellno = 1;

4. 수정
UPDATE sell 
SET name = '과자', cnt = 6, price = 25000, supplierno =1, foodno = 2
WHERE sellno=1;

5. 삭제
DELETE FROM expense where expenseno = 1;

6. 식당이름, 공급업체이름 출력
SELECT expenseno, name, cnt, price, rdate, supplierno, managerno, resno
FROM expense
WHERE managerno = 1
ORDER BY expenseno ASC;

SELECT e.expenseno, e.name, e.cnt, e.price, e.rdate, s.name AS supplier_name, r.resname
FROM expense e
INNER JOIN restaurant r ON e.resno = r.resno
INNER JOIN supplier s ON e.supplierno = s.supplierno
WHERE e.managerno = 1 and e.resno = 1
ORDER BY expenseno ASC;

commit;