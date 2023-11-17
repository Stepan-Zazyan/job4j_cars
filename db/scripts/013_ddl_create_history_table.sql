create table history (
    id serial primary key,
    startAt timestamp WITHOUT TIME ZONE DEFAULT now(),
    endAt timestamp WITHOUT TIME ZONE DEFAULT now()
)