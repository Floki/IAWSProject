package nyandevteam.covoiturage.inscription;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;
 
@Service("inscriptionService")
@Path("/inscription/")
public class ServiceInscription {
 
   @GET
   @Produces("text/plain")
   public String getDateTime()
   {
     return "Vous êtes sur le service d'incription.";
   }
}