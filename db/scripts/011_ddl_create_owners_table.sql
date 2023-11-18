drop table owners;
create table owners (
    id serial primary key,
    name text,
    user_id int references auto_user(id)
)