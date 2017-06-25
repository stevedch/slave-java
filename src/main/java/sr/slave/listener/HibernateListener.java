package sr.slave.listener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

    private final transient Logger logger = LoggerFactory.getLogger(this.getClass());
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {

            registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            logger.info("Configuration HibernateListener: {}", metadata);
        } catch (Exception e) {

            logger.error("Error HibernateListener: {}", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        /*SessionFactory factory = (SessionFactory) event.getServletContext().getAttribute("SessionFactory");
        factory.close();*/
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
