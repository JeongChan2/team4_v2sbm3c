DROP TABLE n_requirement;
CREATE TABLE n_requirement (
  requirementno NUMBER(10) NOT NULL     PRIMARY KEY, -- 번호, 레코드를 구분하는 컬럼 
  customerno NUMBER(10) NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼 
  calories   VARCHAR(10)    NOT NULL,    -- 칼로리 (n_requirement) kcal
  carbohydrates VARCHAR(10)    NOT NULL, -- 탄수화물 (n_requirement) g(그램)
  protein    VARCHAR(10)    NOT NULL,    -- 단백질  (n_requirement) g(그램)
  fat        VARCHAR(10)    NOT NULL,    -- 지방  (n_requirement) g(그램)
  FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
);

- 탄단지별 섭취량 계산방법
1.하루에 섭취할 칼로리를 찾는다.
(체중 감량이 목표면 기준 칼로리에서 10~20% 덜 섭취, 증량은 더 섭취)
유지 기준 20대 성인 남성 kcal는 2696kcal이다.
2. 탄단지 비율을 정한다.
5:3:2 => 평소 고강도 위주로 근력 운동을 하거나, 시간이 조금 더 걸리더라도 안정적으로 다이어트를 하고 싶은 분들에게 추천
4:4:2 => 혈당을 조절해야 하는 당뇨병 환자나 위험이 있는 분에게 도움이 될 수 있습니다. 그리고 탄수화물 섭취가 어느정도 
필요한 고강도 운동보다 유산소 위주로 운동 하시는 분에게 적합해요. 
4:3:3 => 4:4:2와 비슷하지만 위보다 조금 맛있는 식단 유산소 운동을 하시는 분에게 적합하며, 혈당 조절이 필요하신 분에게 추천해요. 
5:3:2로 계산
3. 탄단지 계산
2696 x 탄수화물(0.5) =1348
2696 x 단백질(0.3) =808.8 => 유사치인 809로 계산
2696 x 지방(0.2) = 539.2 => 539
4. 그램으로 변환
탄수화물 그램당 4kcal = 337g
단백질 그램당 4kcal = 101g
지방 그램당 9kcal = 60g

COMMENT ON TABLE N_REQUIREMENT is '권장 섭취량';
COMMENT ON COLUMN N_REQUIREMENT.REQUIREMENTNO is '번호';
COMMENT ON COLUMN N_REQUIREMENT.CUSTOMERNO is '회원번호';
COMMENT ON COLUMN N_REQUIREMENT.CALORIES is '칼로리';
COMMENT ON COLUMN N_REQUIREMENT.CARBOHYDRATES is '탄수화물';
COMMENT ON COLUMN N_REQUIREMENT.PROTEIN is '단백질';
COMMENT ON COLUMN N_REQUIREMENT.FAT is '지방';

DROP SEQUENCE n_requirement_seq;
CREATE SEQUENCE n_requirement_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

1. 등록
INSERT INTO n_requirement(requirementno, customerno, calories, carbohydrates, protein, fat)
VALUES (n_requirement_seq.nextval, 3, '2696kcal', '337g', '101g', '60g');

2. 목록
SELECT requirementno, customerno, calories, carbohydrates, protein, fat
FROM n_requirement
ORDER BY requirementno ASC, customerno ASC;

3. 조회
SELECT requirementno, customerno, calories, carbohydrates, protein, fat
FROM n_requirement
WHERE customerno = 3;

4. 수정
UPDATE n_requirement 
SET calories = 2696, carbohydrates = 337, protein = 101, fat =60
WHERE requirementno=1;

5. 영양 정보의 존재 유/무 확인
SELECT COUNT(customerno) as cnt
FROM n_requirement
WHERE customerno=3;

COMMIT;