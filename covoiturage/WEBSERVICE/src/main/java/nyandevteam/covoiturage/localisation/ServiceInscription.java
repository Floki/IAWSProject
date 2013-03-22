package nyandevteam.covoiturage.localisation;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;
 
@Service("localisationService")
@Path("/localisation/")
public class ServiceInscription {
 
   @GET
   @Produces("text/plain")
   public String getDateTime()
   {
     return "Vous êtes sur le service de localisation.";
   }
}