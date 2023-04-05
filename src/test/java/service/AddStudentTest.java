package service;

import domain.Student;
import org.junit.Assert;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.*;

class AddStudentTest {

    @org.junit.jupiter.api.Test
    void addStudent1() {
        Student s1 = new Student("21", "asdas", 937, "asdasdfasd");
        StudentXMLRepo student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        TemaXMLRepo tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        NotaXMLRepo nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        Service s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo,nv_test);
        s_test.addStudent(s1);
        Assert.assertTrue(s_test.findStudent("21") != s1);
    }

    @org.junit.jupiter.api.Test
    void addStudent2() {
        Student s1 = new Student("22", "asdas", 937, "asdasdfasd");
        StudentXMLRepo student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        TemaXMLRepo tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        NotaXMLRepo nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        Service s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo,nv_test);
        s_test.addStudent(s1);
        Assert.assertTrue(s_test.findStudent("21") != s1);
    }

}