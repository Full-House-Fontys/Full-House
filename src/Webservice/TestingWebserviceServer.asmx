<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://localhost/CimsService/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://localhost/CimsService/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://localhost/CimsService/">
      <s:element name="PublishEmergencyMessage">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="title" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="message" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="PublishEmergencyMessageResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="PublishEmergencyMessageResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="PublishEmergencyMessageSoapIn">
    <wsdl:part name="parameters" element="tns:PublishEmergencyMessage" />
  </wsdl:message>
  <wsdl:message name="PublishEmergencyMessageSoapOut">
    <wsdl:part name="parameters" element="tns:PublishEmergencyMessageResponse" />
  </wsdl:message>
  <wsdl:portType name="TestingWebserviceServerSoap">
    <wsdl:operation name="PublishEmergencyMessage">
      <wsdl:input message="tns:PublishEmergencyMessageSoapIn" />
      <wsdl:output message="tns:PublishEmergencyMessageSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="TestingWebserviceServerSoap" type="tns:TestingWebserviceServerSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="PublishEmergencyMessage">
      <soap:operation soapAction="http://localhost/CimsService/PublishEmergencyMessage" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="TestingWebserviceServerSoap12" type="tns:TestingWebserviceServerSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="PublishEmergencyMessage">
      <soap12:operation soapAction="http://localhost/CimsService/PublishEmergencyMessage" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="TestingWebserviceServer">
    <wsdl:port name="TestingWebserviceServerSoap" binding="tns:TestingWebserviceServerSoap">
      <soap:address location="http://localhost:19157/TestingWebserviceServer.asmx" />
    </wsdl:port>
    <wsdl:port name="TestingWebserviceServerSoap12" binding="tns:TestingWebserviceServerSoap12">
      <soap12:address location="http://localhost:19157/TestingWebserviceServer.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>