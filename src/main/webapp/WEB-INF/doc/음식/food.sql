DROP TABLE food;
CREATE TABLE food (
  foodno NUMBER(10) NOT NULL, -- 번호, 레코드를 구분하는 컬럼 
  foodname VARCHAR(20) NOT NULL,            -- 음식 이름
  f_calories   VARCHAR(10)    NOT NULL,    -- 칼로리 (food) kcal
  f_carbohydrates VARCHAR(10)    NOT NULL, -- 탄수화물 (food) g(그램)
  f_protein    VARCHAR(10)    NOT NULL,    -- 단백질  (food) g(그램)
  f_fat        VARCHAR(10)    NOT NULL,    -- 지방  (food) g(그램)
  PRIMARY KEY (foodno)
);

COMMENT ON TABLE FOOD is '음식';
COMMENT ON COLUMN FOOD.FOODNO is '번호';
COMMENT ON COLUMN FOOD.FOODNAME is '음식이름';
COMMENT ON COLUMN FOOD.F_CALORIES is '칼로리';
COMMENT ON COLUMN FOOD.F_CARBOHYDRATES is '탄수화물';
COMMENT ON COLUMN FOOD.F_PROTEIN is '단백질';
COMMENT ON COLUMN FOOD.F_FAT is '지방';

DROP SEQUENCE food_seq;
CREATE SEQUENCE food_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

1. 등록
INSERT INTO food(foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat)
VALUES (food_seq.nextval, '싸이버거', '594', '60', '28', '27');

INSERT INTO food(foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat)
VALUES (food_seq.nextval, '제육볶음', '572', '31', '40', '33');

2. 목록
SELECT foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat
FROM food
ORDER BY foodno ASC;

3. 조회
SELECT foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat
FROM food
WHERE foodname = '싸이버거';

SELECT foodno, foodname, f_calories, f_carbohydrates, f_protein, f_fat
FROM food
WHERE foodno = 1;

4. 수정
UPDATE food 
SET foodname='불싸이버거', f_calories = 543, f_carbohydrates = 76, f_protein = 28, f_fat =7
WHERE foodname='싸이버거';

5. 삭제
DELETE FROM food where foodno = 3;

COMMIT;