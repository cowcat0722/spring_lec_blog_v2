insert into user_tb(username, password, email, created_at) values
                                                               ('ssar','1234','ssar@nate.com',now()),
                                                               ('cos','1234','cos@nate.com',now()),
                                                               ('love','1234','love@nate.com',now());

insert into board_tb(user_id, title, content,created_at)
values (1,'제목1','내용1',now()),
       (1,'제목2','내용2',now()),
       (2,'제목3','내용3',now()),
       (3,'제목4','내용4',now());