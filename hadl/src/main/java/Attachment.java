/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface Attachment {
    SentRole getSentRole();

    void setSentRole(SentRole sentRole);

    ReceivedRole getReceivedRole();

    void setReceivedRole(ReceivedRole receivedRole);

    SentComponentPort getSentComponentPort();

    void setSentComponentPort(SentComponentPort sentComponentPort);

    ReceivedComponentPort getReceivedComponentPort();

    void setReceivedComponentPort(ReceivedComponentPort receivedComponentPort);
}
