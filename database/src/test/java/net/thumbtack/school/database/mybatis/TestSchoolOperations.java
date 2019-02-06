package net.thumbtack.school.database.mybatis;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.School;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class TestSchoolOperations extends TestBase {

    @Test
    public void testInsertSchool() throws DaoException {
        School school2018 = insertSchool("TTSchool", 2018);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        assertEquals(school2018, schoolFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertSchoolWithNullName() throws DaoException {
        School school = new School(null, 2018);
        schoolDao.insert(school);
    }

    @Test
    public void testGetNonexistentSchool() throws DaoException {
        assertNull(schoolDao.getById(1234567));
    }

    @Test
    public void testGetSchoolsFromEmptySchoolTableLazy() throws DaoException {
        assertEquals(0, schoolDao.getAllLazy().size());
    }

    @Test
    public void testGetSchoolsFromEmptySchoolTableUsingJoin() throws DaoException {
        assertEquals(0, schoolDao.getAllUsingJoin().size());
    }

    @Test
    public void testUpdateSchool() throws DaoException {
        School school = insertSchool("TTSchool", 2018);
        School schoolFromDB = schoolDao.getById(school.getId());
        assertEquals(school, schoolFromDB);
        school.setName("Школа ТТ");
        school.setYear(2019);
        schoolDao.update(school);
        schoolFromDB = schoolDao.getById(school.getId());
        assertEquals(school, schoolFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testChangeSchoolNameToNull() throws DaoException {
        School school2018 = insertSchool("TTSchool", 2018);
        assertNotEquals(0, school2018.getId());
        School schoolFromDB = schoolDao.getById(school2018.getId());
        assertEquals(school2018, schoolFromDB);
        school2018.setName(null);
        schoolDao.update(school2018);
    }

    @Test
    public void testDeleteSchool() throws DaoException {
        School school2018 = insertSchool("TTSchool", 2018);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        assertEquals(school2018, schoolFromDB);
        schoolDao.delete(school2018);
        schoolFromDB = schoolDao.getById(school2018.getId());
        assertNull(schoolFromDB);
    }

    @Test
    public void testInsertAndDeleteTwoSchools() throws DaoException {
        School school2018 = insertSchool("TTSchool", 2018);
        School school2019 = insertSchool("TTSchool", 2019);
        List<School> schoolsFromDB = schoolDao.getAllLazy();
        assertEquals(2, schoolsFromDB.size());
        schoolsFromDB.sort(Comparator.comparingInt(School::getId));
        assertEquals(school2018, schoolsFromDB.get(0));
        assertEquals(school2019, schoolsFromDB.get(1));
        schoolDao.deleteAll();
        schoolsFromDB = schoolDao.getAllLazy();
        assertEquals(0, schoolsFromDB.size());
    }

    @Test(expected = RuntimeException.class)
    public void testInsertTwoSchoolsWithSameNameAndYear() throws DaoException {
        insertSchool("TTSchool", 2018);
        insertSchool("TTSchool", 2018);
    }


}
