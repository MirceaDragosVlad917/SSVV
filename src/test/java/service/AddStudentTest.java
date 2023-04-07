package service;

import domain.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class AddStudentTest {
    private Service s_test;
    private StudentXMLRepo student_test_repo;

    public void initialize() throws IOException {
        File file = new File("fisiere/test_studenti.xml");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox>\n</inbox>");
        fileWriter.close();
        student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        TemaXMLRepo tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        NotaXMLRepo nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo, nv_test);
    }

    @org.junit.jupiter.api.Test
    void addStudent1() throws IOException {
        this.initialize();
        Student s1 = new Student("23", "asdas", 937, "asdasdfasd");
        s_test.addStudent(s1);
        Assertions.assertEquals(s1, student_test_repo.findOne("23"));
    }

    @org.junit.jupiter.api.Test
    void addStudent2() throws IOException {
        this.initialize();
        Student s1 = new Student("23", "asdas", 937, "asdasdfasd");
        s_test.addStudent(s1);
        Assert.assertTrue(s_test.findStudent("21") != s1);
    }

    @org.junit.jupiter.api.Test
    void addStudentIDNull() throws IOException {
        this.initialize();
        Student s1 = new Student(null, "Andrei", 932, "dsafdsf");
        Assertions.assertThrows(NullPointerException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentIDEmptyString() throws IOException {
        this.initialize();
        Student s1 = new Student("", "Andrei", 932, "dsafdsf");
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentNameNull() throws IOException {
        this.initialize();
        Student s1 = new Student("124", null, 933, "sdfasfd");
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentNameEmptyString() throws IOException {
        this.initialize();
        Student s1 = new Student("124", "", 933, "sdfasfd");
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentEmailNull() throws IOException {
        this.initialize();
        Student s1 = new Student("126", "Sorin", 935, null);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentEmailEmptyString() throws IOException {
        this.initialize();
        Student s1 = new Student("126", "Sorin", 935, "");
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentGroupNegative() throws IOException {
        this.initialize();
        Student s1 = new Student("125", "Mihai", -1, "dsgfdsg");
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addStudent(s1);
        });
    }

    @org.junit.jupiter.api.Test
    void addStudentGroup0() throws IOException {
        this.initialize();
        Student s1 = new Student("125", "Mihai", 0, "dsgfdsg");
        s_test.addStudent(s1);
        Assertions.assertEquals(s1, student_test_repo.findOne("125"));
    }

    @org.junit.jupiter.api.Test
    void addStudentGroup1() throws IOException {
        this.initialize();
        Student s1 = new Student("127", "Mihai", 1, "dsgfdsg");
        s_test.addStudent(s1);
        Assertions.assertEquals(s1, student_test_repo.findOne("127"));
    }

    @org.junit.jupiter.api.Test
    void addStudentGroupMAXINTMinus1() throws IOException {
        this.initialize();
        Student s1 = new Student("128", "Mihai", Integer.MAX_VALUE-1, "dsgfdsg");
        s_test.addStudent(s1);
        Assertions.assertEquals(s1, student_test_repo.findOne("128"));
    }

    @org.junit.jupiter.api.Test
    void addStudentGroupMAXINT() throws IOException {
        this.initialize();
        Student s1 = new Student("129", "Mihai", Integer.MAX_VALUE, "dsgfdsg");
        s_test.addStudent(s1);
        Assertions.assertEquals(s1, student_test_repo.findOne("129"));
    }

}