package com.tassta.test.chat;

/**
 * Message history model.
 */
public interface MessageHistoryModel
{
    /**
     * @param user The user for which history will be returned
     * @return Message history for a user
     */
    MessageHistory getMessageHistory(User user);
}
