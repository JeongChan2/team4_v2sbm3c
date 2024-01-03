/**********************************/
/* Table Name: 공급업체 */
/**********************************/
DROP TABLE SUPPLIER
CREATE TABLE SUPPLIER(
		SUPPLIERNO                    		NUMBER(10)		NOT NULL   PRIMARY KEY,
		NAME                          		VARCHAR2(50)	NOT NULL,
		RDATE                         		DATE		    NOT NULL,
		MANAGERNO                     		NUMBER(10)		NOT NULL,
        FOREIGN KEY (MANAGERNO) REFERENCES manager (MANAGERNO)
);

COMMENT ON TABLE SUPPLIER is '공급업체';
COMMENT ON COLUMN SUPPLIER.SUPPLIERNO is '업체번호';
COMMENT ON COLUMN SUPPLIER.NAME is '업체이름';
COMMENT ON COLUMN SUPPLIER.RDATE is '등록날짜';
COMMENT ON COLUMN SUPPLIER.MANAGERNO is '관리자 번호';

DROP SEQUENCE SUPPLIER_SEQ;

CREATE SEQUENCE SUPPLIER_SEQ
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;

1. 등록
INSERT INTO supplier(supplierno, name, rdate, managerno)
VALUES (SUPPLIER_SEQ.nextval, '정찬상공급', sysdate, 1);

INSERT INTO supplier(supplierno, name, rdate, managerno)
VALUES (SUPPLIER_SEQ.nextval, '성규상품', sysdate, 1);

INSERT INTO supplier(supplierno, name, rdate, managerno)
VALUES (SUPPLIER_SEQ.nextval, '규현무역', sysdate, 1);

2. 목록
SELECT supplierno, name, rdate, managerno
FROM supplier
WHERE managerno = 1
ORDER BY supplierno ASC;

3. 조회
SELECT supplierno, name, rdate, managerno
FROM supplier
WHERE supplierno = 1;

4. 삭제
DELETE FROM supplier where supplierno = 4;

5. 페이징
SELECT supplierno, name, rdate, managerno, r
FROM (
           SELECT supplierno, name, rdate, managerno, rownum as r
           FROM (
                     SELECT supplierno, name, rdate, managerno
                     FROM supplier
                     WHERE managerno=1 AND (UPPER(name) LIKE '%' || UPPER('정찬') || '%' )
                     ORDER BY supplierno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

6. 검색 레코드 갯수
SELECT COUNT(*) as cnt
    FROM supplier
        -- WHERE managerno=#{managerno}
        WHERE managerno=1 AND (UPPER(name) LIKE '%' || UPPER('정찬') || '%')

commit;