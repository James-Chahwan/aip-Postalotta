/* was user but is special name ? */

/* same pattern */
drop view jdbcrealm_user;
drop view jdbcrealm_group; 
/* drop tables first incase */
drop table account;
drop table post;  

 /* then make */
CREATE TABLE Account(
    USERID INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    USERNAME VARCHAR(255),
    PASSWORD VARCHAR(255)
);
 
CREATE TABLE post(
 POSTID INTEGER not null primary key
 GENERATED ALWAYS AS IDENTITY
 (START WITH 1, INCREMENT BY 1),
 TITLE VARCHAR(100),
 CONTENT VARCHAR(500),
 USERID INTEGER NOT NULL,
 LIKES INTEGER DEFAULT 0,
 FOREIGN KEY (USERID) REFERENCES Account(USERID)
);

 

create view jdbcrealm_user (username, password) as
select username, password
from account;

create view jdbcrealm_group (username, groupname) as
select username, 'Users'
from account;

