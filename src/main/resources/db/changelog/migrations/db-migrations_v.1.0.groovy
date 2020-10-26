package db.changelog.migrations

databaseChangeLog {
    changeSet(id: '26_10_2020__01_add_users_table', author: 'Valentyn_Korniienko') {
        comment('Create users table.')
        preConditions(onFail: 'MARK_RAN') {
            not() {
                tableExists(tableName: 'users')
            }
        }
        createTable(tableName: 'users') {
            column(name: 'user_id', type: 'serial')
            column(name: 'user_name', type: 'text')
            column(name: 'first_name', type: 'text')
            column(name: 'last_name', type: 'text')
        }
        rollback {
            // all changes automatically rolled-back
        }
    }

    changeSet(id: '26_10_2020__02_add_constrains_on_users_table', author: 'Valentyn_Korniienko') {
        comment('Create constrains in users table.')
        preConditions(onFail: 'MARK_RAN') {
            not() {
                tableExists(tableName: 'users')
            }
        }
        addNotNullConstraint(tableName: 'users', columnName: 'user_id', columnDataType: 'serial')
        addNotNullConstraint(tableName: 'users', columnName: 'user_name', columnDataType: 'text')
        addPrimaryKey(
                tableName: 'users',
                columnNames: 'user_id',
                constraintName: 'users_pkey'
        )
    }

    changeSet(id: '26_10_2020__03_add_chats_table', author: 'Valentyn_Korniienko') {
        comment('Create chats table.')
        preConditions(onFail: 'MARK_RAN') {
            not() {
                tableExists(tableName: 'chats')
            }
        }
        createTable(tableName: 'chats') {
            column(name: 'chat_id', type: 'bigserial')
            column(name: 'chat_name', type: 'text')
        }
        rollback {
            // all changes automatically rolled-back
        }
    }

    changeSet(id: '26_10_2020__04_add_constrains_on_chats_table', author: 'Valentyn_Korniienko') {
        comment('Create constrains on chats table.')
        preConditions(onFail: 'MARK_RAN') {
            not() {
                tableExists(tableName: 'chats')
            }
        }

        addNotNullConstraint(tableName: 'chats', columnName: 'chat_id', columnDataType: 'bigserial')
        addPrimaryKey(
                tableName: 'chats',
                columnNames: 'chat_id',
                constraintName: 'chats_pkey'
        )
    }

    changeSet(id: '26_10_2020__05_add_user_reputations_table', author: 'Valentyn_Korniienko') {
        comment('Create user reputations table.')
        preConditions(onFail: 'MARK_RAN') {
            not() {
                tableExists(tableName: 'user_reputations')
            }
        }
        createTable(tableName: 'user_reputations') {
            column(name: 'user_id', type: 'serial')
            column(name: 'chat_id', type: 'bigint')
            column(name: 'reputation_value', type: 'integer')
            column(name: 'updated_date_time', type: 'timestamp')
            column(name: 'updated_from_id', type: 'integer')
        }
        rollback {
            // all changes automatically rolled-back
        }
    }

    changeSet(id: '26_10_2020__06_add_constrains_on_user_reputations_table', author: 'Valentyn_Korniienko') {
        comment('Create constrains on user_reputations table.')
        preConditions(onFail: 'MARK_RAN') {
            not() {
                tableExists(tableName: 'user_reputations')
            }
        }
        addNotNullConstraint(tableName: 'user_reputations', columnName: 'user_id', columnDataType: 'serial')
        addNotNullConstraint(tableName: 'user_reputations', columnName: 'chat_id', columnDataType: 'bigint')
        addNotNullConstraint(tableName: 'user_reputations', columnName: 'reputation_value', columnDataType: 'integer')
        addNotNullConstraint(tableName: 'user_reputations', columnName: 'updated_date_time', columnDataType: 'timestamp')
        addNotNullConstraint(tableName: 'user_reputations', columnName: 'updated_from_id', columnDataType: 'integer')
        addPrimaryKey(
                tableName: 'user_reputations',
                columnNames: 'user_id,chat_id',
                constraintName: 'user_reputations_pkey'
        )
        addForeignKeyConstraint(
                baseColumnNames: 'user_id',
                baseTableName: 'user_reputations',
                constraintName: 'user_reputations_user_id_fkey',
                referencedColumnNames: 'user_id',
                referencedTableName: 'users',
                onDelete: 'CASCADE'
        )
        addForeignKeyConstraint(
                baseColumnNames: 'chat_id',
                baseTableName: 'user_reputations',
                constraintName: 'user_reputations_chat_id_fkey',
                referencedColumnNames: 'chat_id',
                referencedTableName: 'chats',
                onDelete: 'CASCADE'
        )
    }
}


