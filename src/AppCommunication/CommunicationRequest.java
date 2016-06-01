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

    //TODO
    public String getPayload() {
        return payload;
    }

    //TODO
    public void setPayload(String payload) {
        this.payload = payload;
    }

    //TODO
    public String getUrl() {
        return url;
    }

    //TODO
    public CommunicationMessage getNetworkMessage() {
        return networkMessage;
    }

    //TODO
    public void setUrl(String url) {
        this.url = url;
    }

    //TODO
    public CommunicationRequest(CommunicationMessage networkMessage) throws UnknownFormatConversionException {
        this.networkMessage = networkMessage;

        url = networkMessage.getText().substring(0, networkMessage.getText().indexOf("/"));
        payload = networkMessage.getText().substring(networkMessage.getText().indexOf("/")+1);
    }
}
