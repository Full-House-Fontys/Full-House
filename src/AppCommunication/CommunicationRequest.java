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

    public void setUrl(String url) {
        this.url = url;
    }

    public CommunicationRequest(CommunicationMessage networkMessage) throws UnknownFormatConversionException {
        this.networkMessage = networkMessage;
        // Parse the message
        Pattern p = Pattern.compile("([\\w/]+) (.+)");

        Matcher matcher = p.matcher(networkMessage.getText());

        if (matcher.matches() == false)
            throw new UnknownFormatConversionException("Network request could not be parsed!");

        url = matcher.group(0);
        payload = matcher.group(1);
    }
}
