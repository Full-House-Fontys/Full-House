package GUI;

import javafx.fxml.FXML;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by fhict on 25-05-16.
 */
public class pubOnWebController {
//    @WebMethod
//    public String sayHelloWorldFrom(String from) {
//        String result = "Hello, world, from " + from;
//        System.out.println(result);
//        return result;
//    }
//
//    public static void main(String[] argv) {
//        Object implementor = new pubOnWebController();
//        String address = "http://localhost:9000/pubOnWebController?wsdl";
//        Endpoint.publish(address, implementor);
//    }

    @FXML
    private void publishTextOnWeb() {
        URL url = new URL("http://localhost:9999/");

    }
}
