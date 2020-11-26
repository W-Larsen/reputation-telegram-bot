package com.telegram.rtb.constants;

/**
 * Sql queries.
 */
public final class SqlQueries {

    private SqlQueries() {
        throw new IllegalStateException();
    }

    //user reputation
    public static final String CREATE_USER_REPUTATION = "insert into user_reputations (user_id, chat_id, reputation_value, updated_date_time, updated_from_id) values (?,?,?,?,?)";
    public static final String FIND_BY_USER_ID_AND_CHAT_ID = "select * from user_reputations where user_id = ? and chat_id = ?";
    public static final String INCREASE_USER_REPUTATION = "update user_reputations " +
            "set reputation_value = reputation_value + 1, " +
            "updated_date_time = ?, " +
            "updated_from_id = ? " +
            "where user_id = ? and chat_id = ?";
    public static final String REDUCE_USER_REPUTATION = "update user_reputations set reputation_value = reputation_value - 1, " +
            "updated_date_time = ?, " +
            "updated_from_id = ? " +
            "where user_id = ? and chat_id = ?";
    public static final String FIND_ALL = "select * from user_reputations order by reputation_value ";
    public static final String UPDATE_REPUTATION_VALUE = "update user_reputations set reputation_value = ? " +
            "where user_id = ? and chat_id = ?";

    //user
    public static final String ADD_TELEGRAM_USER = "insert into users (user_id, user_name, first_name, last_name) values (?,?,?,?)";
    public static final String FIND_USER_BY_ID = "select * from users where user_id = ?";

    //chat
    public static final String ADD_TELEGRAM_CHAT = "insert into chats (chat_id, chat_name) values (?,?)";
    public static final String FIND_CHAT_BY_ID = "select * from chats where chat_id = ?";

}
