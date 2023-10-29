CREATE TABLE post(
                     id SERIAL PRIMARY KEY,
                     name text,
                     link text,
                     text text,
                     created TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);
