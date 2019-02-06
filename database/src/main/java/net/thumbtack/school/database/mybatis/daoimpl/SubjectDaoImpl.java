package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.mybatis.dao.SubjectDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubjectDaoImpl extends DaoImplBase implements SubjectDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoImpl.class);

    @Override
    public Subject insert(Subject subject) throws DaoException {
        Subject subjectRes = new Subject();
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).insert(subject);
                subjectRes.setId(subject.getId());
                subjectRes.setName(subject.getName());
            } catch (RuntimeException ex) {
                String message = "Can't insertAll subject: " + subject;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return subjectRes;
    }

    @Override
    public Subject getById(int id) throws DaoException {
        Subject subjectRes;
        try (SqlSession sqlSession = getSession()) {
            try {
                subjectRes = getSubjectMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                String message = "Can't get subject by id: " + id;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return subjectRes;
    }

    @Override
    public List<Subject> getAll() throws DaoException {
        List<Subject> subjects;
        try (SqlSession sqlSession = getSession()) {
            try {
                subjects = getSubjectMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                String message = "Can't get all subjects";
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return subjects;
    }

    @Override
    public void update(Subject subject) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).update(subject);
            } catch (RuntimeException ex) {
                String message = "Can't update subject: " + subject;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Subject subject) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).delete(subject.getId());
            } catch (RuntimeException ex) {
                String message = "Can't delete subject: " + subject;
                LOGGER.debug( message + " ex:{}", ex);
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
                getSubjectMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all subjects ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't delete all subjects");
            }
            sqlSession.commit();
        }

    }
}
