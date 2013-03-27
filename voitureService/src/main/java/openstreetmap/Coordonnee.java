package openstreetmap;
 
public class Coordonnee {
  
    private double lat;
    private double lng;
 
    public Coordonnee(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
 
    public double getLat() {
        return lat;
    }
 
    public void setLat(double lat) {
        this.lat = lat;
    }
 
    public double getLng() {
        return lng;
    }
 
    public void setLng(double lng) {
        this.lng = lng;
    }    
    
    public String toString() {
        return getLat() + "," + getLng();
    } 
 
    //Formule calcul des distance
    //Rayonterrestre * Cos-1(sin(lat1)sin(lat2)+cos(lat1)cos(lat2)cos(lon1-long2))
    public double distanceWith(Coordonnee coordonnee) {
          int R = 6367445;
          double lat1 = lat / 180 * Math.PI;
          double lat2 = coordonnee.getLat() / 180 * Math.PI;
          double lng1 = lng / 180 * Math.PI;
          double lng2 = coordonnee.getLng() / 180 * Math.PI;
          double distance =  
                  R * Math.acos( Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lng1 - lng2));
          return distance / 1000;
    }
    
    public static void main (String [] args) {
        Coordonnee c1 = new Coordonnee(42.970492,1.866617);
        Coordonnee c2 = new Coordonnee(43.591333,1.448021);
        System.out.println(c1.distanceWith(c2));
    }
}