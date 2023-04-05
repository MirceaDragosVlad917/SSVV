package service;

import domain.Tema;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

class AddAssignmentTest {
    @org.junit.jupiter.api.Test
    void addAssignment1(){
        Tema tema = new Tema("1", "desc", 10, 8);
        StudentXMLRepo student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        TemaXMLRepo tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        NotaXMLRepo nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        Service s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo,nv_test);
        s_test.addTema(tema);
        Assertions.assertSame(tema.getDeadline(), 10);
    }

    @org.junit.jupiter.api.Test
    void addAssignment2(){
        Tema tema = new Tema("1", "desc", 10, 8);
        StudentXMLRepo student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        TemaXMLRepo tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        NotaXMLRepo nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        Service s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo,nv_test);
        s_test.addTema(tema);
        Assertions.assertNotSame(s_test.findTema("1"), tema);
    }
}
