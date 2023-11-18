drop table participates;
create table participates
(
    id      serial PRIMARY KEY,
    post_id int not null references post (id),
    user_id int not null references auto_user (id),
    UNIQUE (post_id, user_id)
);

