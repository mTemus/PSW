package hibernateOperations;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        // Creating StandardServiceRegistryBuilder
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

        // Hibernate settings which is equivalent to hibernate.cfg.xml's properties
        Map<String, String> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/psw" +
                "?useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false" +
                "&serverTimezone=UTC");
        dbSettings.put(Environment.USER, "root");
        dbSettings.put(Environment.PASS, "admin");
        dbSettings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        // Apply database settings
        registryBuilder.applySettings(dbSettings);
        // Creating registry
        StandardServiceRegistry standardServiceRegistry = registryBuilder.build();
        // Creating MetadataSources
        MetadataSources sources = new MetadataSources(standardServiceRegistry);
        // Creating Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
        // Creating SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    //Utility method to return SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
