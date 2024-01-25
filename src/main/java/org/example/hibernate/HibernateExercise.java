package org.example.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.management.Query;

public class HibernateExercise {
    static SessionFactory factory;

    // Main
    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();

        // Add
        insertStudent("Antonio","Martinez","12","antonio@email.com","971112312");
        insertStudent("Diego","Martinez","12","diego@email.com","971888828");
        insertStudent("Lucas","Rodriguez","12","lucas@email.com","971822827");
        insertStudent("Manolo","Iglesias","12","manolo@email.com","971888821");
        System.out.println("Primera query hecha");

        // Update
        updateStudent(1,"Pepe","Martinez","12","antonio@email.com","971112312");
        System.out.println("Segunda query hecha");

        // Delete
        deleteStudent(2);
        System.out.println("Tercera query hecha");

        // Get one item
        System.out.println("Get name of student 1: "+getStudent(1).getName());

        // Print all students
        System.out.println("Students..............: ");
        for (Student student:listStudents()) {
            System.out.println(student.getId()+" : "+student.getName());
        }

        // Close and finish
        factory.close();
    }

    // Insert a student
    private static void insertStudent(String nombre, String lastname, String age, String email,String phone) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student(nombre,lastname,age,email,phone);
        session.save(student);
        tx.commit();
        session.close();
    }

    // Update a student
    private static void updateStudent(int id,String nombre, String lastname,String age, String email,String phone) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class, 1);
        student.setName(nombre);
        student.setLastname(lastname);
        student.setAge(age);
        student.setEmail(email);
        student.setPhone(phone);
        session.update(student);
        tx.commit();
        session.close();
    }

    // Delete a student
    private static void deleteStudent(int id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class, 1);
        session.delete(student);
        tx.commit();
        session.close();
    }

    // Get one student by id
    private static Student getStudent(int id) {
        Session session = factory.openSession();
        List<Student> students = session.createQuery("FROM Student ORDER BY name ASC").list();
        return students.get(0);
    }

    // Get all students
    private static List<Student> listStudents() {
        Session session = factory.openSession();
        List<Student> students = session.createQuery("FROM Student ORDER BY name ASC").list();
        return students;
    }
}