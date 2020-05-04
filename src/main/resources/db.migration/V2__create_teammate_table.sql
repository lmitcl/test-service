CREATE TABLE TEAMMATES(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  TEAMMATE_ID                 VARCHAR (200)             NOT NULL ,
  NAME                        TEXT (100)                NOT NULL ,
  SURNAME                     TEXT (100)                NOT NULL ,
  PATRONYMIC                  TEXT (100)                NOT NULL ,
  LOGIN                       VARCHAR (100)             NOT NULL ,
  PASSWORD                    VARCHAR (200)             NOT NULL ,
  EMAIL                       VARCHAR (200)             NOT NULL ,
  PHONE                       VARCHAR (200)             NOT NULL ,
  CREATEDATE                  DATE                      NOT NULL ,
  ROLE                        VARCHAR (100)             NOT NULL
);

CREATE INDEX TEAMMATES_ID_INDEX
  ON TEAMMATES(ID);
