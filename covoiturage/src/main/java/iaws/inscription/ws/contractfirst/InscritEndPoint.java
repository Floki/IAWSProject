package iaws.inscription.ws.contractfirst;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint                                                                                
public class InscritEndPoint {
    
  private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/covoiturage";

  private XPath nomExpression;
  private XPath prenomExpression;
  private XPath mailExpression;
  private XPath adresseExpression;

  @Autowired
  public InscritEndPoint()                      
      throws JDOMException {
    Namespace namespace = Namespace.getNamespace("ups", NAMESPACE_URI);

    nomExpression = XPath.newInstance("//ups:Nom");
    nomExpression.addNamespace(namespace);

    prenomExpression = XPath.newInstance("//ups:Prenom");
    prenomExpression.addNamespace(namespace);

    mailExpression = XPath.newInstance("//ups:Mail");
    mailExpression.addNamespace(namespace);
    
    adresseExpression = XPath.newInstance("//ups:Adresse");
    adresseExpression.addNamespace(namespace);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "InscriptionRequest")                  
  public void handleInscriptionRequest(@RequestPayload Element inscriptionRequest)               
      throws Exception {
    String nom = nomExpression.valueOf(inscriptionRequest);
    String prenom = prenomExpression.valueOf(inscriptionRequest);
    String mail = mailExpression.valueOf(inscriptionRequest);
    String adresse = adresseExpression.valueOf(inscriptionRequest);
    System.out.println(nom + " " + prenom + " " + mail + " " + adresse);
  }
  
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LocalisationRequest")                  
  public void handleLocalisationRequest(@RequestPayload Element localisationRequest)               
      throws Exception {
    String nom = nomExpression.valueOf(localisationRequest);
    String prenom = prenomExpression.valueOf(localisationRequest);
    String mail = mailExpression.valueOf(localisationRequest);
    String adresse = adresseExpression.valueOf(localisationRequest);
    System.out.println(nom + " " + prenom + " " + mail + " " + adresse);
  }

}