package iaws.localisation.ws.contractfirst;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint                                                                                
public class LocalisationEndPoint {
    
  private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/covoiturage";

  private XPath idExpression;
  private XPath rayonExpression;

  @Autowired
  public LocalisationEndPoint()                      
      throws JDOMException {
    Namespace namespace = Namespace.getNamespace("ups", NAMESPACE_URI);

    idExpression = XPath.newInstance("//ups:Id");
    idExpression.addNamespace(namespace);

    rayonExpression = XPath.newInstance("//ups:Rayon");
    rayonExpression.addNamespace(namespace);
  }
  
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LocalisationRequest")                  
  public void handleLocalisationRequest(@RequestPayload Element localisationRequest)               
      throws Exception {
    String id = idExpression.valueOf(localisationRequest);
    String rayon = rayonExpression.valueOf(localisationRequest);
    System.out.println(id + " " + rayon);
  }

}