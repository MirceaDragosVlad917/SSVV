package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class IntegrationTest {
    private Service s_test;
    private StudentXMLRepo student_test_repo;
    private TemaXMLRepo tema_test_repo;
    private NotaXMLRepo nota_test_repo;

    public void initialize() throws IOException {
        File file_student = new File("fisiere/test_studenti.xml");
        FileWriter fileWriter_student = new FileWriter(file_student);
        fileWriter_student.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox>\n</inbox>");
        fileWriter_student.close();
        File file_tema = new File("fisiere/test_tema.xml");
        FileWriter fileWriter_tema = new FileWriter(file_tema);
        fileWriter_tema.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox>\n</inbox>");
        fileWriter_tema.close();
        File file_nota = new File("fisiere/test_nota.xml");
        FileWriter fileWriter_nota = new FileWriter(file_nota);
        fileWriter_nota.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox>\n</inbox>");
        fileWriter_nota.close();
        student_test_repo = new StudentXMLRepo("fisiere/test_studenti.xml");
        StudentValidator sv_test = new StudentValidator();
        tema_test_repo = new TemaXMLRepo("fisiere/test_tema.xml");
        TemaValidator tv_test = new TemaValidator();
        nota_test_repo = new NotaXMLRepo("fisiere/test_nota.xml");
        NotaValidator nv_test = new NotaValidator(student_test_repo, tema_test_repo);
        s_test = new Service(student_test_repo, sv_test, tema_test_repo, tv_test, nota_test_repo, nv_test);
    }

    private Service createMockService() {
        Student s1 = new Student("1", "Mihai", 937, "email@email");
        Tema t1 = new Tema("1", "descriere", 14, 14);

        StudentXMLRepo student_mock_repo = Mockito.mock(StudentXMLRepo.class);
        TemaXMLRepo tema_mock_repo = Mockito.mock(TemaXMLRepo.class);

        when(student_mock_repo.save(any(Student.class))).thenReturn(null);
        when(student_mock_repo.findOne("1")).thenReturn(s1);
        StudentValidator sv_test = new StudentValidator();
        when(tema_mock_repo.save(any(Tema.class))).thenReturn(null);
        when(tema_mock_repo.findOne("1")).thenReturn(t1);
        TemaValidator tv_test = new TemaValidator();
        NotaValidator nv_test = new NotaValidator(student_mock_repo, tema_mock_repo);
        return new Service(student_mock_repo, sv_test, tema_mock_repo, tv_test, nota_test_repo, nv_test);
    }

    @org.junit.jupiter.api.Test
    void addValidAssignmentToRepo() throws IOException {
        this.initialize();
        Tema t1 = new Tema("1", "descriere", 1, 1);
        s_test.addTema(t1);
        Assertions.assertEquals(t1, tema_test_repo.findOne("1"));
    }

    @org.junit.jupiter.api.Test
    void addValidStudentToRepo() throws IOException {
        this.initialize();
        Student s1 = new Student("23", "asdas", 937, "asdasdfasd");
        s_test.addStudent(s1);
        Assertions.assertEquals(s1, student_test_repo.findOne("23"));
    }

    @org.junit.jupiter.api.Test
    public void addValidGradeToRepo() throws IOException {
        this.initialize();
        Student s1 = new Student("1", "Mihai", 937, "email@email");
        Tema t1 = new Tema("1", "descriere", 14, 14);

        StudentXMLRepo student_mock_repo = Mockito.mock(StudentXMLRepo.class);
        TemaXMLRepo tema_mock_repo = Mockito.mock(TemaXMLRepo.class);

        when(student_mock_repo.save(any(Student.class))).thenReturn(null);
        when(student_mock_repo.findOne("1")).thenReturn(s1);
        StudentValidator sv_test = new StudentValidator();
        when(tema_mock_repo.save(any(Tema.class))).thenReturn(null);
        when(tema_mock_repo.findOne("1")).thenReturn(t1);
        TemaValidator tv_test = new TemaValidator();
        NotaValidator nv_test = new NotaValidator(student_mock_repo, tema_mock_repo);
        Service mock_service = new Service(student_mock_repo, sv_test, tema_mock_repo, tv_test, nota_test_repo, nv_test);

        Nota n1 = new Nota("1", "1", "1", 10D, LocalDate.now());
        mock_service.addNota(n1, "Well done!");
        Assertions.assertEquals(n1, nota_test_repo.findOne("1"));
    }

    @org.junit.jupiter.api.Test
    public void addValidStudentAssignmentGradeToRepo() throws IOException {
        this.initialize();
        Student s1 = new Student("1", "Mihai", 937, "email@email");
        s_test.addStudent(s1);
        Tema t1 = new Tema("1", "descriere", 14, 14);
        s_test.addTema(t1);
        Nota n1 = new Nota("1", "1", "1", 10D, LocalDate.now());
        s_test.addNota(n1, "Well done!");
        Assertions.assertEquals(n1, nota_test_repo.findOne("1"));
    }

    @Test
    public void addValidStudent() throws IOException {
        this.initialize();
        Student s1 = new Student("1", "Mihai", 937, "email@email");
        Service mock_service = createMockService();
        Student s2 = mock_service.addStudent(s1);
        Assertions.assertNull(s2);
    }

    @Test
    public void addValidStudentValidAssignment() throws IOException {
        this.initialize();
        Student s1 = new Student("1", "Mihai", 937, "email@email");
        Service mock_service = createMockService();
        Student s2 = mock_service.addStudent(s1);
        Tema t1 = new Tema("1", "descriere", 14, 14);
        Tema t2 = mock_service.addTema(t1);
        Assertions.assertNull(t2);
    }

    @Test
    public void addValidStudentValidAssignmentValidGrade() throws IOException {
        this.initialize();
        Student s1 = new Student("1", "Mihai", 937, "email@email");
        Service mock_service = createMockService();
        Student s2 = mock_service.addStudent(s1);
        Tema t1 = new Tema("1", "descriere", 14, 14);
        Tema t2 = mock_service.addTema(t1);
        Nota n1 = new Nota("1", "1", "1", 10D, LocalDate.now());
        double n2 = mock_service.addNota(n1, "Well done!");
        Assertions.assertNull(t2);
    }

}
