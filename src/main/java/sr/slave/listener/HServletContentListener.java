package sr.slave.listener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

public class HServletContentListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        SessionFactory factory = (SessionFactory) event.getServletContext().getAttribute("SessionFactory");
        factory.close();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        URL url = HServletContentListener.class.getResource("/hibernate.cfg.xml");
        Configuration configuration = new Configuration();
        configuration.configure(url);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory(registry);
        event.getServletContext().setAttribute("SessionFactory", factory);
    }
}
