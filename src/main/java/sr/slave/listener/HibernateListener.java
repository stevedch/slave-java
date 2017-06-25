package sr.slave.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sr.slave.uitl.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

    private final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {

            HibernateUtil.getSessionFactory();

        } catch (Exception e) {
            logger.error("HibernateListener: {}", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        if (null != HibernateUtil.getSessionFactory()) {
            HibernateUtil.getSessionFactory().close();
        }

        logger.debug("Hibernate session factory destroyed");
    }
}
