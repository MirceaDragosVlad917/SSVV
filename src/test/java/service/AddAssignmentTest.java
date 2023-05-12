package service;

import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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

class AddAssignmentTest {
    private Service s_test;
    private TemaXMLRepo tema_test_repo;

    public void initialize() throws IOException {
        File file = new File("fisiere/test_tema.xml");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox>\n</inbox>");
        fileWriter.close();
        StudentXMLRepo student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        NotaXMLRepo nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo, nv_test);
    }

    @org.junit.jupiter.api.Test
    void addValidAssignmentToRepo() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "descriere", 1, 1);
        s_test.addTema(t1);
        Assertions.assertEquals(t1, tema_test_repo.findOne("1"));
    }

    @org.junit.jupiter.api.Test
    void addAssignmentNrTemaNull() throws IOException {
        this.initialize();
        Tema t1 = new Tema(null, "descriere", 1, 1);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

    @org.junit.jupiter.api.Test
    void addAssignmentNrTemaEmptyString() throws IOException {
        this.initialize();
        Tema t1 = new Tema("", "descriere", 1, 1);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

    @org.junit.jupiter.api.Test
    void addAssignmentDescriptionEmptyString() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "", 1, 1);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

    @org.junit.jupiter.api.Test
    void addAssignmentDeadline0() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "descriere", 0, 1);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

    @org.junit.jupiter.api.Test
    void addAssignmentDeadline15() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "descriere", 15, 1);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

    @org.junit.jupiter.api.Test
    void addAssignmentPrimire0() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "descriere", 1, 0);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

    @org.junit.jupiter.api.Test
    void addAssignmentPrimire15() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "descriere", 1, 15);
        Assertions.assertThrows(ValidationException.class, () -> {
            s_test.addTema(t1);
        });
    }

}

