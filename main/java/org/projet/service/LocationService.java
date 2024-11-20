package main.java.org.projet.service;
import main.java.org.projet.data.CarteAbonnement;
import main.java.org.projet.data.ClientAbonne;
import main.java.org.projet.data.Film;
import main.java.org.projet.data.Location;
import java.util.List;
import java.sql.Date;


public interface LocationService {
    void addLocation(Location location);
    void deleteLocation(Long idLocation);
    void updateLocation(Location location);
    List<Location> getAllLocations();
    Location getLocationById(Long idLocation);
    List<Location> getLocationByDateLocation(Date dateLocation);
    List<Location> getLocationByDateRetour(Date dateRetour);
    List<Location> getLocationByPrix(float prix);
    List<Location> getLocationByClient(ClientAbonne clientAbonne);
    List<Location> getLocationByFilm(Film film);
    List<Location> getLocationByCarteAbonnement(CarteAbonnement carteAbonnement);
}
