package com.tassta.test.chat;

import java.util.Date;

/**
 * Represents a message.
 */
public interface Message
{
    /**
     * @return date when the message was sent
     */
    Date getDate();

    /**
     * @return The text of the message
     */
    String getText();

    com.tassta.test.chat.User getSender();

    com.tassta.test.chat.User getReceiver();
}
