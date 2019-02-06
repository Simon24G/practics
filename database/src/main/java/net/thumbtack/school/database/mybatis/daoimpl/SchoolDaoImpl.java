package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.SchoolDao;
import net.thumbtack.school.database.mybatis.mappers.GroupMapper;
import net.thumbtack.school.database.mybatis.mappers.TraineeMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SchoolDaoImpl extends DaoImplBase implements SchoolDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDaoImpl.class);

    @Override
    public School insert(School school) throws DaoException {
        School schoolRes = new School();
        try (SqlSession sqlSession = getSession()) {
            try {
                schoolRes.setId(getSchoolMapper(sqlSession).insert(school));
                schoolRes.setName(school.getName());
                schoolRes.setYear(school.getYear());
                schoolRes.setGroups(school.getGroups());
            } catch (RuntimeException ex) {
                String message = "Can't insertAll school school: " + school;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return schoolRes;
    }

    @Override
    public School getById(int id) throws DaoException {
        School schoolRes;
        try (SqlSession sqlSession = getSession()) {
            try {
                schoolRes = getSchoolMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                String message = "Can't get school by id: " + id;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return schoolRes;
    }

    @Override
    public List<School> getAllLazy() throws DaoException {
        List<School> schools;
        try (SqlSession sqlSession = getSession()) {
            try {
                schools = getSchoolMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all school lazy ex: {}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't get all school lazy", ex);
            }
            sqlSession.commit();
        }
        return schools;
    }

    @Override
    public List<School> getAllUsingJoin() throws DaoException {
        List<School> schools;
        try (SqlSession sqlSession = getSession()) {
            try {
                schools = sqlSession.selectList("net.thumbtack.school.database.mybatis.mappers.SchoolMapper.getAllUsingJoin");//getSchoolMapper(sqlSession).getAllWithJoin();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all school join ex: {}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't get all school join", ex);
            }
            sqlSession.commit();
        }
        return schools;
    }

    @Override
    public void update(School school) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).update(school);
            } catch (RuntimeException ex) {
                String message = "Can't update school: " + school;
                LOGGER.debug(message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(School school) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).delete(school.getId());
            } catch (RuntimeException ex) {
                String message = "Can't delete school: " + school;
                LOGGER.debug(message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() throws DaoException {
        try(SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all schools DeleteException {}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't delete all schools DeleteException");
            }
            sqlSession.commit();
        }
    }

    @Override
    public School insertSchoolTransactional(School school2018) throws DaoException {
        School schoolRes = new School();
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).insert(school2018);
                schoolRes.setId(school2018.getId());
                schoolRes.setYear(school2018.getYear());
                schoolRes.setName(school2018.getName());

                List<Group> groups = school2018.getGroups();
                GroupMapper groupMapper = getGroupMapper(sqlSession);
                TraineeMapper traineeMapper = getTraineeMapper(sqlSession);
                groups.forEach( (g)-> {
                    groupMapper.insert(g,schoolRes.getId());
                    g.getTrainees().forEach( (t) -> traineeMapper.insertWithGroup(t, g.getId()) );
                    g.getSubjects().forEach( (s) -> groupMapper.addSubject(s.getId(),g.getId()) );
                    // change trainees to g
                } ); // change groups to school2018
                schoolRes.setGroups(groups);

            } catch (RuntimeException ex) {
                String message = "Can't transaction insertAll school: " + school2018;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return schoolRes;

    }
}
