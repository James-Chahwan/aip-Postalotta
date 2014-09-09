/* was user but is special name ? */
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
    FOREIGN KEY (USERID) REFERENCES Account(USERID)
);

drop table account;
drop table post;  

create view jdbcrealm_user (username, password) as
select username, password
from account;

create view jdbcrealm_group (username, groupname) as
select username, 'Users'
from account;

drop view jdbcrealm_user;
drop view jdbcrealm_group;  