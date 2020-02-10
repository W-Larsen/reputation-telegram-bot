package com.telegram.drb.constants;

/**
 * Sql queries.
 */
public final class SqlQueries {

    private SqlQueries() {
        throw new UnsupportedOperationException();
    }

    public static final String CREATE_USER_REPUTATION = "insert into user_reputations (user_id, chat_id, reputation_value) values (?,?,?)";
    public static final String FIND_BY_USER_ID_AND_CHAT_ID = "select * from user_reputations where user_id = ? and chat_id = ?";
    public static final String INCREASE_USER_REPUTATION = "update user_reputations set reputation_value = reputation_value + 1, where user_id = ? and chat_id = ?";
    public static final String REDUCE_USER_REPUTATION = "update user_reputations set reputation_value = reputation_value - 1, where user_id = ? and chat_id = ?";

}
