create table if not exists user_reputations(
    user_id serial not null,
    chat_id bigint not null,
    reputation_value integer not null,
    primary key (user_id, chat_id)
)