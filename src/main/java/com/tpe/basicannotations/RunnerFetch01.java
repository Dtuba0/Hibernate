
package com.tpe.basicannotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

//uygulamaya veritabanından data cekme işlemi saglar
public class RunnerFetch01 {
    public static void main(String[] args) {

        /*
        DB den data cekmek icin
                   Task id=1001 olan öğrenciyi tüm fieldlarıyla birlikte getirmek(fetch)istiyoruz
                   1)session methodu:get() en pratik ama kullanım alanı kısıtlı
                   2)SQL kodu oluşturarak DB'ce
                   3) HQL (Hibernate Query Language), Hibernate ORM tarafından sağlanan,
           nesne odaklı bir sorgulama dilidir. HQL, SQL'e benzer şekilde çalışır
           ancak doğrudan veritabanı tablolarıyla değil,
           Java sınıfları (entity'ler) ve onların özellikleriyle çalışır

         */
        Configuration config=new Configuration().configure().addAnnotatedClass(Student.class);
        SessionFactory sf= config.buildSessionFactory();
        Session session=sf.openSession();
        System.out.println("-------------GET-------------------");

        //1) session icerisinde bulunan get methodunu id ile veri cekerken kullanabiliriz
        Student student= session.get(Student.class,1001);//student classında database'de karsılık gelen tablosuna git ve 1001 id'sine sahip degeri getir
        System.out.println(student);


        //2) SQL
        System.out.println("-------------SQL-------------------");
        String sql="select * from t_student where id=1002";
        Object [] student2= (Object[]) session.createSQLQuery(sql).uniqueResult();
        //uniqueResult(): sorgunun tek satır getirecegi durumlarda kullanılır
        //geriye bir satırdan birden fazla farklı data geldigi icin data tipleri farklı oldugu icin
        //Object[] icine alınır
        System.out.println(Arrays.toString(student2));
        System.out.println("-------------HQL-------------------");

        //3) HQL Javaca
        String hql="From Student where id=1003";
        Student student3=session.createQuery(hql, Student.class).uniqueResult();
        System.out.println(student3);

        session.close();
        sf.close();





    }

}