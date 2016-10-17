/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface Binding {
    ReceivedComponentPort getReceivedComponentPort();

    void setReceivedComponentPort(ReceivedComponentPort receivedComponentPort);

    SentComponentPort getSentComponentPort();

    void setSentComponentPort(SentComponentPort sentComponentPort);

    ReceivedConfigurationPort getReceivedConfigurationPort();

    void setReceivedConfigurationPort(ReceivedConfigurationPort receivedConfigurationPort);

    SentConfigurationPort getSentConfigurationPort();

    void setSentConfigurationPort(SentConfigurationPort sentConfigurationPort);
}
