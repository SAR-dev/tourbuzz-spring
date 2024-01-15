CREATE TABLE admin(
    Lock char(1) not null DEFAULT 'X',
    secret text not null,
    constraint PK_T1 PRIMARY KEY (Lock),
    constraint CK_T1_Locked CHECK (Lock='X')
);

INSERT INTO admin (secret) VALUES (gen_random_uuid());