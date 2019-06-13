package controladores;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.EmpleadoEntity;
import entities.ItemVentaEntity;
import entities.NovedadEntity;
import entities.ParamGralesEntity;
import entities.ProductoEntity;
import entities.StockEntity;
import entities.VentaEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(EmpleadoEntity.class);
             config.addAnnotatedClass(ItemVentaEntity.class);
             config.addAnnotatedClass(ProductoEntity.class);
             config.addAnnotatedClass(StockEntity.class);
             config.addAnnotatedClass(VentaEntity.class);
             config.addAnnotatedClass(ParamGralesEntity.class);
             config.addAnnotatedClass(NovedadEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {	
        return sessionFactory;
    }
}