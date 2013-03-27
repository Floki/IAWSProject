package openstreetmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
 
public class OpenStreetMap {
 
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";
 
    public static Coordonnee parse(final InputStream jsonStream) {
        Coordonnee coordinate = null;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final List<Object> dealData = mapper.readValue(jsonStream, List.class);
            if (dealData != null && dealData.size() == 1) {
                final Map< String, Object > locationMap = (Map< String, Object >) dealData.get(0);
                if (locationMap != null && locationMap.containsKey(LATITUDE) && locationMap.containsKey(LONGITUDE)) {
                    final double lat = Double.parseDouble(locationMap.get(LATITUDE).toString());
                    final double lng = Double.parseDouble(locationMap.get(LONGITUDE).toString());
                    coordinate = new Coordonnee(lat, lng);
                 }
             } else {
                 Logger.getLogger(OpenStreetMap.class.getName()).log(Level.SEVERE, "NO RESULTS", "NO RESULTS");
             }
         } catch (Exception ex) {
             Logger.getLogger(OpenStreetMap.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
         }
    return coordinate;
    }
 
    public static Coordonnee parse(String rawAddress) {
        InputStream is = null;
        Coordonnee coords = null;
 
        if (rawAddress != null && rawAddress.length() > 0 ) {
                String address;
                try {
                    address = URLEncoder.encode(rawAddress, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    System.err.println("Erruer à l'encodage de l'URL.");
                    return new Coordonnee(0,0);
                }
                String geocodeURL = "http://nominatim.openstreetmap.org/search?format=json&limit=1&polygon=0&addressdetails=1&countrycodes=fr&q=";
                String formattedUrl = geocodeURL + address;
                URL geocodeUrl;
                try {
                    geocodeUrl = new URL(formattedUrl);
                } catch (MalformedURLException e) {
                    System.err.println("Erruer à la formation de l'URL.");
                    return new Coordonnee(0,0);
                }
                try {
                    is = geocodeUrl.openStream();
                } catch (IOException e) {
                    System.err.println("Erruer à l'ouverture de l'URL.");
                    return new Coordonnee(0,0);
                }
                coords = parse(is);
                if( coords == null) {
                    return new Coordonnee(0,0);
                }
                return coords;
        }
        return new Coordonnee(0,0);
    }
}