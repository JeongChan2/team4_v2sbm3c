/**********************************/
/* Table Name: 식당 */
/**********************************/
DROP TABLE RESTAURANT;

CREATE TABLE RESTAURANT(
		RESNO                         		NUMBER(10)		   NOT NULL		 PRIMARY KEY,
		RESNAME                       		VARCHAR2(30)		 NOT NULL,
		RESADDRESS                    		VARCHAR2(50)		 NOT NULL,
		RESPHONE                      		VARCHAR2(20)		 NOT NULL,
		RESTIME                       		VARCHAR2(20)		 NOT NULL,
		RESSTAR                       		NUMBER(10)		   NOT NULL,
		SEQNO                             NUMBER(5)        DEFAULT 1   NOT NULL,
    VISIBLE                             CHAR(1)         DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE RESTAURANT is '식당';
COMMENT ON COLUMN RESTAURANT.RESNO is '식당번호';
COMMENT ON COLUMN RESTAURANT.RESNAME is '식당이름';
COMMENT ON COLUMN RESTAURANT.RESADDRESS is '식당주소';
COMMENT ON COLUMN RESTAURANT.RESPHONE is '식당전화번호';
COMMENT ON COLUMN RESTAURANT.RESTIME is '식당영업시간';
COMMENT ON COLUMN RESTAURANT.RESSTAR is '식당별점';
COMMENT ON COLUMN RESTAURANT.SEQNO is '출력순서';
COMMENT ON COLUMN RESTAURANT.VISIBLE is '출력모드';


DROP SEQUENCE RESTAURANT_SEQ;

CREATE SEQUENCE RESTAURANT_SEQ
    START WITH 1        -- 시작번호
    INCREMENT BY 1      -- 증가값
    MAXVALUE 9999999999 -- 최대값: 9999999999 --> NUMBER(10)대응
    CACHE 2             -- 2번은 메모리에서만 계산
    NOCYCLE;            -- 다시 1부터 생성되는 것을 방지
    
-- CREATE
INSERT INTO RESTAURANT(resno, resname, resaddress, resphone, restime, resstar) 
VALUES(restaurant_seq.nextval, '자유식당', '충남 아산시 신창면 순천향로 41 1층', '0507-1338-3755','10:00~21:00',0);
INSERT INTO RESTAURANT(resno, resname, resaddress, resphone, restime, resstar) 
VALUES(restaurant_seq.nextval, '돼지고기김치찌개', '충남 아산시 신창면 순천향로 41', '041-533-3292','??:??~??:??',0);
INSERT INTO RESTAURANT(resno, resname, resaddress, resphone, restime, resstar) 
VALUES(restaurant_seq.nextval, '맘스터치', '충남 아산시 신창면 순천향로 14', '041-542-4626','10:00~23:00',0);

-- READ; LIST
SELECT * FROM RESTAURANT;
SELECT resno, resname, resaddress, resphone, restime, resstar, seqno FROM RESTAURANT ORDER BY resno ASC;

-- SELECT 조회
SELECT resno, resname, resaddress, resphone, restime, resstar 
FROM RESTAURANT
WHERE resno = 1;

-- DELETE
DELETE FROM RESTAURANT WHERE resno = 3;

-- UPDATE
UPDATE RESTAURANT 
SET resname = '테스트 성공', resaddress = '테스트 성공', resphone = '테스트 성공', restime = '테스트 성공'
WHERE resno = 7;

-- 우선 순위를 높임
UPDATE RESTAURANT SET seqno = seqno-1 WHERE resno = 1;
SELECT resno, resname, resaddress, resphone, restime, resstar, seqno FROM RESTAURANT ORDER BY resno ASC;

-- 우선 순위를 낮춤
UPDATE RESTAURANT SET seqno = seqno+1 WHERE resno = 1;
SELECT resno, resname, resaddress, resphone, restime, resstar, seqno FROM RESTAURANT ORDER BY resno ASC;

-- READ: LIST
SELECT resno, resname, resaddress, resphone, restime, resstar, seqno FROM RESTAURANT ORDER BY seqno ASC;

commit;

-- 카테고리 공개 설정
UPDATE RESTAURANT SET visible = 'Y' WHERE resno = 1;
SELECT resno, resname, resaddress, resphone, restime, resstar, visible 
FROM RESTAURANT
ORDER BY resno ASC;


-- 카테고리 비공개 설정
UPDATE RESTAURANT SET visible = 'N' WHERE resno = 1;
SELECT resno, resname, resaddress, resphone, restime, resstar, visible 
FROM RESTAURANT
WHERE resno = 1
ORDER BY resno ASC;

commit;

SELECT resno, resname, resaddress, resphone, restime, resstar, visible 
FROM RESTAURANT
WHERE visible = 'Y'
ORDER BY resno ASC;


commit;

