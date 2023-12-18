DROP TABLE rescontents CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
CREATE TABLE rescontents(
        rescontentsno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        managerno                              NUMBER(10)     NOT NULL , -- FK
        resno                                NUMBER(10)         NOT NULL , -- FK
        title                                 VARCHAR2(50)         NOT NULL,
        rescontent                               CLOB                  NOT NULL,
        recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
        cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
        replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
        passwd                                VARCHAR2(15)         NOT NULL,
        word                                  VARCHAR2(100)         NULL ,
        rdate                                 DATE               NOT NULL,
        file1                                   VARCHAR(50)          NULL,  -- 원본 파일명 image
        file1saved                            VARCHAR(50)          NULL,  -- 저장된 파일명, image
        thumb1                              VARCHAR(50)          NULL,   -- preview image
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
        price                                 NUMBER(10)      DEFAULT 0 NULL,  
        dc                                    NUMBER(10)      DEFAULT 0 NULL,  
        saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
        point                                 NUMBER(10)      DEFAULT 0 NULL,  
        salecnt                               NUMBER(10)      DEFAULT 0 NULL,
        map                                   VARCHAR2(1000)            NULL,
        youtube                               VARCHAR2(1000)            NULL,
        FOREIGN KEY (managerno) REFERENCES manager (managerno),
        FOREIGN KEY (resno) REFERENCES restaurant (resno)
);

COMMIT;

COMMENT ON TABLE rescontents is '컨텐츠 - 영양관리';
COMMENT ON COLUMN rescontents.rescontentsno is '식당컨텐츠 번호';
COMMENT ON COLUMN rescontents.managerno is '관리자 번호';
COMMENT ON COLUMN rescontents.resno is '식당 번호';
COMMENT ON COLUMN rescontents.title is '제목';
COMMENT ON COLUMN rescontents.rescontent is '내용';
COMMENT ON COLUMN rescontents.recom is '추천수';
COMMENT ON COLUMN rescontents.cnt is '조회수';
COMMENT ON COLUMN rescontents.replycnt is '댓글수';
COMMENT ON COLUMN rescontents.passwd is '패스워드';
COMMENT ON COLUMN rescontents.word is '검색어';
COMMENT ON COLUMN rescontents.rdate is '등록일';
COMMENT ON COLUMN rescontents.file1 is '메인 이미지';
COMMENT ON COLUMN rescontents.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN rescontents.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN rescontents.size1 is '메인 이미지 크기';
COMMENT ON COLUMN rescontents.price is '정가';
COMMENT ON COLUMN rescontents.dc is '할인률';
COMMENT ON COLUMN rescontents.saleprice is '판매가';
COMMENT ON COLUMN rescontents.point is '포인트';
COMMENT ON COLUMN rescontents.salecnt is '수량';
COMMENT ON COLUMN rescontents.map is '지도';
COMMENT ON COLUMN rescontents.youtube is 'Youtube 영상';

-- rescontents 테이블에 food의 foodno를 컬럼으로 추가하기
ALTER TABLE rescontents ADD FOREIGN KEY(FOODNO) REFERENCES FOOD;

DROP SEQUENCE rescontents_seq;

CREATE SEQUENCE rescontents_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO rescontents(rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(rescontents_seq.nextval, 1, 1, '매운갈비찜', '맛있다', 0, 0, 0, '123',
       '자연', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

-- 유형 1 전체 목록
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1
FROM rescontents
ORDER BY rescontentsno DESC;

-- food와 rescontents 조인
SELECT r.rescontentsno, r.managerno, r.resno, r.title, r.rescontent, r.recom, r.cnt, r.replycnt, r.passwd,
        r.word, r.rdate,r.file1, r.file1saved, r.thumb1, r.size1, r.foodno, 
        f.f_calories, f.f_carbohydrates, f.f_protein, f.f_fat
FROM rescontents r JOIN food f ON r.foodno = f.foodno
ORDER BY rescontentsno DESC;

-- food와 rescontents 조인 + 남은 영양소보다 적은 음식 추천
SELECT r.rescontentsno, r.managerno, r.resno, r.title, r.rescontent, r.recom, r.cnt, r.replycnt, r.passwd,
        r.word, r.rdate,r.file1, r.file1saved, r.thumb1, r.size1, r.foodno, 
        f.f_calories, f.f_carbohydrates, f.f_protein, f.f_fat
FROM rescontents r JOIN food f ON r.foodno = f.foodno
ORDER BY rescontentsno DESC;

-- 유형 2 카테고리별 목록
INSERT INTO rescontents(rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(rescontents_seq.nextval, 1, 2, '김치찌개', '고기가 많다', 0, 0, 0, '123',
       '드라마,K드라마,넷플릭스', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);
            
INSERT INTO rescontents(rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(rescontents_seq.nextval, 1, 2, '제육볶음', '맛있다', 0, 0, 0, '123',
       '드라마,K드라마,넷플릭스', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

INSERT INTO rescontents(rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(rescontents_seq.nextval, 1, 2, '계란말이', '부드럽다', 0, 0, 0, '123',
       '드라마,K드라마,넷플릭스', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

COMMIT;

-- 1번 resno 만 출력
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=1
ORDER BY rescontentsno DESC;

-- 2번 resno 만 출력
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=2
ORDER BY rescontentsno DESC;

-- 3번 resno 만 출력
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=3
ORDER BY rescontentsno DESC;

-- 모든 레코드 삭제
DELETE FROM rescontents;
commit;

-- 삭제
DELETE FROM rescontents
WHERE rescontentsno = 25;
commit;

DELETE FROM rescontents
WHERE resno=12 AND rescontentsno <= 41;

commit;


-- ----------------------------------------------------------------------------------------------------
-- 검색, resno별 검색 목록
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, word, rdate,
       file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
ORDER BY rescontentsno ASC;

-- 카테고리별 목록
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, word, rdate,
       file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=2
ORDER BY rescontentsno ASC;

-- 1) 검색
-- ① resno별 검색 목록
-- word 컬럼의 존재 이유: 검색 정확도를 높이기 위하여 중요 단어를 명시
-- 글에 'swiss'라는 단어만 등장하면 한글로 '스위스'는 검색 안됨.
-- 이런 문제를 방지하기위해 'swiss,스위스,스의스,수의스,유럽' 검색어가 들어간 word 컬럼을 추가함.
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=1 AND word LIKE '%오지%'
ORDER BY rescontentsno DESC;

-- title, content, word column search
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=1 AND (title LIKE '%오지%' OR rescontent LIKE '%오지%' OR word LIKE '%오지%')
ORDER BY rescontentsno DESC;

-- ② 검색 레코드 갯수
-- 전체 레코드 갯수, 집계 함수
SELECT COUNT(*)
FROM rescontents
WHERE resno=1;

  COUNT(*)  <- 컬럼명
----------
         5
         
SELECT COUNT(*) as cnt -- 함수 사용시는 컬럼 별명을 선언하는 것을 권장
FROM rescontents
WHERE resno=1;

       CNT <- 컬럼명
----------
         5

-- resno 별 검색된 레코드 갯수
SELECT COUNT(*) as cnt
FROM rescontents
WHERE resno=1 AND word LIKE '%오지%';

SELECT COUNT(*) as cnt
FROM rescontents
WHERE resno=1 AND (title LIKE '%오지%' OR rescontent LIKE '%오지%' OR word LIKE '%오지%');

-- SUBSTR(컬럼명, 시작 index(1부터 시작), 길이), 부분 문자열 추출
SELECT rescontentsno, SUBSTR(title, 1, 4) as title
FROM rescontents
WHERE resno=1 AND (rescontent LIKE '%오지%');

-- SQL은 대소문자를 구분하지 않으나 WHERE문에 명시하는 값은 대소문자를 구분하여 검색
SELECT rescontentsno, title, word
FROM rescontents
WHERE resno=1 AND (word LIKE '%FOOD%');

SELECT rescontentsno, title, word
FROM rescontents
WHERE resno=1 AND (word LIKE '%food%'); 

SELECT rescontentsno, title, word
FROM rescontents
WHERE resno=1 AND (LOWER(word) LIKE '%food%'); -- 대소문자를 일치 시켜서 검색

SELECT rescontentsno, title, word
FROM rescontents
WHERE resno=1 AND (UPPER(word) LIKE '%' || UPPER('FOOD') || '%'); -- 대소문자를 일치 시켜서 검색 ★

SELECT rescontentsno, title, word
FROM rescontents
WHERE resno=1 AND (LOWER(word) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색

SELECT rescontentsno || '. ' || title || ' 태그: ' || word as title -- 컬럼의 결합, ||
FROM rescontents
WHERE resno=1 AND (LOWER(word) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색


SELECT UPPER('한글') FROM dual; -- dual: 오라클에서 SQL 형식을 맞추기위한 시스템 테이블

-- ----------------------------------------------------------------------------------------------------
-- 검색 + 페이징 + 메인 이미지
-- ----------------------------------------------------------------------------------------------------
-- step 1
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE resno=1 AND (title LIKE '%food%' OR rescontent LIKE '%food%' OR word LIKE '%food%')
ORDER BY rescontentsno DESC;

-- step 2
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, rownum as r
FROM (
          SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
                     file1, file1saved, thumb1, size1, map, youtube
          FROM rescontents
          WHERE resno=1 AND (title LIKE '%오지%' OR rescontent LIKE '%오지%' OR word LIKE '%오지%')
          ORDER BY rescontentsno DESC
);

-- step 3, 1 page
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
          SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
                     file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
                               file1, file1saved, thumb1, size1, map, youtube
                     FROM rescontents
                     WHERE resno=1 AND (title LIKE '%오지%' OR rescontent LIKE '%오지%' OR word LIKE '%오지%')
                     ORDER BY rescontentsno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

-- step 3, 2 page
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM rescontents
                     WHERE resno=1 AND (title LIKE '%food%' OR rescontent LIKE '%food%' OR word LIKE '%food%')
                     ORDER BY rescontentsno DESC
           )          
)
WHERE r >= 4 AND r <= 6;

-- 대소문자를 처리하는 페이징 쿼리
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM contents
                     WHERE cateno=1 AND (UPPER(title) LIKE '%' || UPPER('단풍') || '%' 
                                         OR UPPER(content) LIKE '%' || UPPER('단풍') || '%' 
                                         OR UPPER(word) LIKE '%' || UPPER('단풍') || '%')
                     ORDER BY contentsno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

-- ----------------------------------------------------------------------------
-- 조회
-- ----------------------------------------------------------------------------
SELECT rescontentsno, managerno, resno, title, rescontent, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM rescontents
WHERE rescontentsno = 1;

-- ----------------------------------------------------------------------------
-- 다음 지도, MAP, 먼저 레코드가 등록되어 있어야함.
-- map                                   VARCHAR2(1000)         NULL ,
-- ----------------------------------------------------------------------------
-- MAP 등록/수정
UPDATE contents SET map='카페산 지도 스크립트' WHERE contentsno=1;

-- MAP 삭제
UPDATE contents SET map='' WHERE contentsno=1;

commit;

-- ----------------------------------------------------------------------------
-- Youtube, 먼저 레코드가 등록되어 있어야함.
-- youtube                                   VARCHAR2(1000)         NULL ,
-- ----------------------------------------------------------------------------
-- youtube 등록/수정
UPDATE contents SET youtube='Youtube 스크립트' WHERE contentsno=1;

-- youtube 삭제
UPDATE contents SET youtube='' WHERE contentsno=1;

commit;

-- 패스워드 검사, id="password_check"
SELECT COUNT(*) as cnt 
FROM rescontents
WHERE rescontentsno=1 AND passwd='123';

-- 텍스트 수정: 예외 컬럼: 추천수, 조회수, 댓글 수
UPDATE rescontents
SET title='기차를 타고', rescontent='계획없이 여행 출발',  word='나,기차,생각' 
WHERE rescontentsno = 2;

-- ERROR, " 사용 에러
UPDATE rescontents
SET title='기차를 타고', rescontent="계획없이 '여행' 출발",  word='나,기차,생각'
WHERE rescontentsno = 2;

-- ERROR, \' 에러
UPDATE rescontents
SET title='기차를 타고', rescontent='계획없이 \'여행\' 출발',  word='나,기차,생각'
WHERE rescontentsno = 1;

-- SUCCESS, '' 한번 ' 출력됨.
UPDATE rescontents
SET title='기차를 타고', rescontent='계획없이 ''여행'' 출발',  word='나,기차,생각'
WHERE rescontentsno = 1;

-- SUCCESS
UPDATE contents
SET title='기차를 타고', content='계획없이 "여행" 출발',  word='나,기차,생각'
WHERE contentsno = 1;

commit;

-- 파일 수정
UPDATE rescontents
SET file1='beer01.jpg', file1saved='beer01.jpg', thumb1='beer01_t.jpg', size1=5000
WHERE rescontentsno = 26;

-- 삭제
DELETE FROM contents
WHERE contentsno = 42;

commit;

DELETE FROM contents
WHERE contentsno >= 7;

commit;

-- 추천
UPDATE contents
SET recom = recom + 1
WHERE contentsno = 1;

-- cateno FK 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM contents 
WHERE cateno=1;

-- adminno FK 특정 관리자에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM contents 
WHERE adminno=1;

-- cateno FK 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM contents
WHERE cateno=1;

-- adminno FK 특정 관리자에 속한 레코드 모두 삭제
DELETE FROM contents
WHERE adminno=1;

commit;

-- 다수의 카테고리에 속한 레코드 갯수 산출: IN
SELECT COUNT(*) as cnt
FROM contents
WHERE cateno IN(1,2,3);

-- 다수의 카테고리에 속한 레코드 모두 삭제: IN
SELECT contentsno, adminno, cateno, title
FROM contents
WHERE cateno IN(1,2,3);

CONTENTSNO    ADMINNO     CATENO TITLE                                                                                                                                                                                                                                                                                                       
---------- ---------- ---------- ------------------------
         3             1                   1           인터스텔라                                                                                                                                                                                                                                                                                                  
         4             1                   2           드라마                                                                                                                                                                                                                                                                                                      
         5             1                   3           컨저링                                                                                                                                                                                                                                                                                                      
         6             1                   1           마션       
         
SELECT contentsno, adminno, cateno, title
FROM contents
WHERE cateno IN('1','2','3');

CONTENTSNO    ADMINNO     CATENO TITLE                                                                                                                                                                                                                                                                                                       
---------- ---------- ---------- ------------------------
         3             1                   1           인터스텔라                                                                                                                                                                                                                                                                                                  
         4             1                   2           드라마                                                                                                                                                                                                                                                                                                      
         5             1                   3           컨저링                                                                                                                                                                                                                                                                                                      
         6             1                   1           마션       

-- ----------------------------------------------------------------------------------------------------
-- cate + contents INNER JOIN
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT c.name,
       t.contentsno, t.adminno, t.cateno, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube
FROM cate c, contents t
WHERE c.cateno = t.cateno
ORDER BY t.contentsno DESC;

-- contents, admin INNER JOIN
SELECT t.contentsno, t.adminno, t.cateno, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube,
       a.mname
FROM admin a INNER JOIN contents t ON a.adminno = t.adminno
ORDER BY t.contentsno DESC;

-- ----------------------------------------------------------------------------------------------------
-- View + paging
-- ----------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW vcontents
AS
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, word, rdate,
        file1, file1saved, thumb1, size1, map, youtube
FROM contents
ORDER BY contentsno DESC;
                     
-- 1 page
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
       file1, file1saved, thumb1, size1, map, youtube, r
FROM (
     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
            file1, file1saved, thumb1, size1, map, youtube, rownum as r
     FROM vcontents -- View
     WHERE cateno=14 AND (title LIKE '%야경%' OR content LIKE '%야경%' OR word LIKE '%야경%')
)
WHERE r >= 1 AND r <= 3;

-- 2 page
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
       file1, file1saved, thumb1, size1, map, youtube, r
FROM (
     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
            file1, file1saved, thumb1, size1, map, youtube, rownum as r
     FROM vcontents -- View
     WHERE cateno=14 AND (title LIKE '%야경%' OR content LIKE '%야경%' OR word LIKE '%야경%')
)
WHERE r >= 4 AND r <= 6;


-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 좋아요(recom) 기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT contentsno, adminno, cateno, title, thumb1, r
FROM (
           SELECT contentsno, adminno, cateno, title, thumb1, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, thumb1
                     FROM contents
                     WHERE cateno=1
                     ORDER BY recom DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 평점(score) 기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM contents
                     WHERE cateno=1
                     ORDER BY score DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 최신 상품 기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM contents
                     WHERE cateno=1
                     ORDER BY rdate DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 조회수 높은 상품기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM contents
                     WHERE cateno=1
                     ORDER BY cnt DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 낮은 가격 상품 추천, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM contents
                     WHERE cateno=1
                     ORDER BY price ASC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 높은 가격 상품 추천, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT contentsno, adminno, cateno, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM contents
                     WHERE cateno=1
                     ORDER BY price DESC
           )          
)
WHERE r >= 1 AND r <= 7;

