package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.mybatis.dao.GroupDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GroupDaoImpl extends DaoImplBase implements GroupDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);

    @Override
    public Group insert(School school, Group group) throws DaoException {
        Group groupRes = new Group();
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).insert(group,school.getId());
                //group.setId(id);
                groupRes.setId(group.getId());
                groupRes.setName(group.getName());
                groupRes.setRoom(group.getRoom());
                groupRes.setTrainees(group.getTrainees());
                groupRes.setSubjects(group.getSubjects());
            } catch (RuntimeException ex) {
                String message = "Can't insert group with ref school group: " + group;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return groupRes;
    }

    @Override
    public void update(Group group) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).update(group);
            } catch (RuntimeException ex) {
                String message = "Can't update group group: " + group;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public List<Group> getAll() throws DaoException {
        List<Group> groups;
        try (SqlSession sqlSession = getSession()) {
            try {
                groups = getGroupMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all groups ex: {}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't get all groups", ex);
            }
            sqlSession.commit();
        }
        return groups;
    }


    @Override
    public void delete(Group group) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).delete(group.getId());
            } catch (RuntimeException ex) {
                String message = "Can't delete group id=" +  group.getId();
                LOGGER.debug(message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public void moveTraineeToGroup(Group group, Trainee trainee) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).refToGroup(trainee.getId(), group.getId());
            } catch (RuntimeException ex) {
                String message = "Can't move trainee to group trainee=" +  trainee + " group=" + group;
                LOGGER.debug(message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    // full delete?
    @Override
    public void deleteTraineeFromGroup(Trainee trainee) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).refToGroup(trainee.getId(),null);
            } catch (RuntimeException ex) {
                String message = "Can't delete trainee to group trainee_id=" +  trainee.getId();
                LOGGER.debug(message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public void addSubjectToGroup(Group group, Subject subject) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).addSubject(subject.getId(),group.getId());
            } catch (RuntimeException ex) {
                String message = "Can't add subject to group subject=" + subject + " group=" + group;
                LOGGER.debug(message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }
}
