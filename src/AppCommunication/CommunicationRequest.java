package AppCommunication;

import java.util.UnknownFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kaj Suiker on 20-4-2016.
 */
public class CommunicationRequest {
    private String url;
    private String payload;
    private CommunicationMessage networkMessage;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getUrl() {
        return url;
    }

    public CommunicationMessage getNetworkMessage() {
        return networkMessage;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CommunicationRequest(CommunicationMessage networkMessage) throws UnknownFormatConversionException {
        this.networkMessage = networkMessage;

        url = networkMessage.getText().substring(0, networkMessage.getText().indexOf("/"));
        payload = networkMessage.getText().substring(networkMessage.getText().indexOf("/")+1);
    }
}
