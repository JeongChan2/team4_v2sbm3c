CREATE TABLE login(
  loginno                    NUMBER(10) NOT NULL PRIMARY KEY,
  customerno                NUMBER(10) NOT NULL,
  ip                            VARCHAR2(15) NOT NULL,
  logindate                  DATE NOT NULL,
  FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
);

COMMENT ON TABLE login is '로그인 내역';
COMMENT ON COLUMN login.loginno is '로그인 번호';
COMMENT ON COLUMN login.customerno is '회원 번호';
COMMENT ON COLUMN login.ip is '접속 IP';
COMMENT ON COLUMN login.logindate is '로그인 날짜';

