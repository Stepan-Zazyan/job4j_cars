create table participates
(
    id      serial PRIMARY KEY,
    post_id int not null references post (id),
    user_id int not null references users (id),
    UNIQUE (post_id, user_id)
);

