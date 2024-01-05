CREATE TABLE WithdrawalLog(
  logno                    NUMBER(10) NOT NULL PRIMARY KEY,
  customerno                NUMBER(10) NOT NULL,
  logdate                  DATE NOT NULL,
  FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
);

COMMENT ON TABLE WithdrawalLog is '로그인 내역';
COMMENT ON COLUMN WithdrawalLog.logno is '로그 번호';
COMMENT ON COLUMN WithdrawalLog.customerno is '회원 번호';
COMMENT ON COLUMN WithdrawalLog.logdate is '탈퇴 날짜';

DROP SEQUENCE log_seq;
CREATE SEQUENCE log_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 

INSERT INTO WithdrawalLog(logno, customerno, logdate)
VALUES(log_seq.nextval, 3, sysdate);

SELECT logno, customerno, logdate
from WithdrawalLog
ORDER BY logno ASC;

SELECT l.logno, l.customerno, n.id ,l.logdate
from WithdrawalLog l, n_customer n
where l.customerno = n.customerno
ORDER BY logno ASC;

DELETE FROM WithdrawalLog;
