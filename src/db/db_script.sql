create table if not exists users(
    user_id serial not null,
    user_name text not null,
    first_name text,
    last_name text,
    primary key (user_id)
);

create table if not exists chats(
    chat_id bigserial not null,
    chat_name text not null,
    primary key (chat_id)
);

create table if not exists user_reputations(
    user_id serial not null,
    chat_id bigint not null,
    reputation_value integer not null,
    primary key (user_id, chat_id),
    foreign key (user_id) references users(user_id),
    foreign key (chat_id) references chats(chat_id)
);