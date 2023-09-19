create table auto_post (
    id serial primary key,
    created date,
    auto_user_id int unique references auto_user(id)
);
