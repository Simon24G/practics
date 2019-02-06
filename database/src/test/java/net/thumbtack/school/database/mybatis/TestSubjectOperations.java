package net.thumbtack.school.database.mybatis;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.Subject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSubjectOperations extends TestBase {

    @Test
    public void testInsertSubject() throws DaoException {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
    }

    @Test
    public void testGetNonexistentSubject() throws DaoException {
        assertNull(subjectDao.getById(1234567));
    }

    @Test
    public void testGetSubjectsFromEmptySubjectsTable() throws DaoException {
        assertEquals(0, subjectDao.getAll().size());
    }

    @Test(expected = RuntimeException.class)
    public void testInsertSubjectWithNullName() throws DaoException {
        Subject subject = new Subject();
        subjectDao.insert(subject);
    }

    @Test
    public void testUpdateSubject() throws DaoException {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
        subject.setName("Windows");
        subjectDao.update(subject);
        subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateSubjectSetNullName() throws DaoException {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
        subject.setName(null);
        subjectDao.update(subject);
    }

    @Test
    public void testDeleteSubject() throws DaoException {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
        subjectDao.delete(subject);
        subjectFromDB = subjectDao.getById(subject.getId());
        assertNull(subjectFromDB);
    }

    @Test
    public void testInsertAndDeleteTwoSubjects() throws DaoException {
        Subject subjectLinux = insertSubject("Linux");
        Subject subjectMySQL = insertSubject("MySQL");
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subjectLinux);
        subjects.add(subjectMySQL);
        List<Subject> subjectsFromDB = subjectDao.getAll();
        assertEquals(subjects, subjectsFromDB);
        subjectDao.deleteAll();
        subjectsFromDB = subjectDao.getAll();
        assertEquals(0, subjectsFromDB.size());
    }

}
