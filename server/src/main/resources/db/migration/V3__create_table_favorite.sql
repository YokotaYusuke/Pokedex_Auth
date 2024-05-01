CREATE TABLE favorite
(
    user_id uuid,
    pokemon_name varchar,
    PRIMARY KEY (user_id, pokemon_name)
)