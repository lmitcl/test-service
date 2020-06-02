CREATE TABLE TEAMMATES(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  TEAMMATE_ID                 VARCHAR (200)             NOT NULL ,
  NAME                        VARCHAR (200)             NOT NULL ,
  SUR_NAME                    VARCHAR (200)             NOT NULL ,
  PATRONYMIC                  VARCHAR (200)             NULL ,
  LOGIN                       VARCHAR (100)             NOT NULL ,
  PASSWORD                    VARCHAR (200)             NOT NULL ,
  EMAIL                       VARCHAR (200)             NOT NULL ,
  PHONE_NUMBER                VARCHAR (200)             NOT NULL ,
  CREATE_DATE                 DATE                      NOT NULL ,
  ROLE                        VARCHAR (100)             NOT NULL
);

CREATE INDEX TEAMMATES_ID_INDEX
  ON TEAMMATES(ID);
