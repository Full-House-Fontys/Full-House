
package Webservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PublishEmergencyMessageResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "publishEmergencyMessageResult"
})
@XmlRootElement(name = "PublishEmergencyMessageResponse")
public class PublishEmergencyMessageResponse {

    @XmlElement(name = "PublishEmergencyMessageResult")
    protected String publishEmergencyMessageResult;

    /**
     * Gets the value of the publishEmergencyMessageResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPublishEmergencyMessageResult() {
        return publishEmergencyMessageResult;
    }

    /**
     * Sets the value of the publishEmergencyMessageResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPublishEmergencyMessageResult(String value) {
        this.publishEmergencyMessageResult = value;
    }

}
