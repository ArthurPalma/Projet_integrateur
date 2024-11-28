package main.java.org.projet.config;

// import org.projet.model.Abonnement;
// import org.projet.model.Bluray;
// import org.projet.model.CarteAbonnement;
// import org.projet.model.Film;
// import org.projet.model.Historique;
// import org.projet.model.HistoriqueLoc;
// import org.projet.model.Location;
// import org.projet.model.QRCode;
// import org.projet.model.RetourBluray;
// import org.projet.model.UtilsateurNonAbonne;
// import org.projet.model.UtilsateurAbonne;

import org.projet.hibernate.SessionFactory;
import org.projet.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.projet.hibernate.cfg.Configuration;
import org.projet.hibernate.service.ServiceRegistry;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSesstionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://DESKTOP-ORONQHJ\\MAN446:1433;databaseName=TPDAOBD\"");
                configuration.setProperty("hibernate.connection.username", "sa");
                configuration.setProperty("hibernate.connection.password", "123456");

                configuration.addAnnotatedClass(org.projet.model.Abonnement.class);
                configuration.addAnnotatedClass(org.projet.model.Bluray.class);
                configuration.addAnnotatedClass(org.projet.model.CarteAbonnement.class);
                configuration.addAnnotatedClass(org.projet.model.Film.class);
                configuration.addAnnotatedClass(org.projet.model.HistoriqueLoc.class);
                configuration.addAnnotatedClass(org.projet.model.UtilsateurAbonne.class);
                configuration.addAnnotatedClass(org.projet.model.UtilsateurNonAbonne.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
