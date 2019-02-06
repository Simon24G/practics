package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.TraineeDao;
import net.thumbtack.school.database.mybatis.mappers.TraineeMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TraineeDaoImpl extends DaoImplBase implements TraineeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraineeDaoImpl.class);

    @Override
    public Trainee insert(Group group, Trainee trainee) throws DaoException {
        Trainee traineeRes = new Trainee();
        try (SqlSession sqlSession = getSession()) {
            try {
                if(group == null) {
                    getTraineeMapper(sqlSession).insert(trainee);
                } else {
                    getTraineeMapper(sqlSession).insertWithGroup(trainee,group.getId());
                }
                traineeRes.setId(trainee.getId());
                traineeRes.setRating(trainee.getRating());
                traineeRes.setFirstName(trainee.getFirstName());
                traineeRes.setLastName(trainee.getLastName());
            } catch (RuntimeException ex) {
                String message = "Can't insertAll trainee: " + trainee + " with group: " + (group == null ? "" : group);
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return traineeRes;
    }

    @Override
    public Trainee getById(int id) throws DaoException {
        Trainee traineeRes;
        try (SqlSession sqlSession = getSession()) {
            try {
                traineeRes = getTraineeMapper(sqlSession).getById(id);
                LOGGER.debug("Trainee = ", traineeRes);
            } catch (RuntimeException ex) {
                String message = "Can't get trainee by id " + id;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return traineeRes;
    }

    @Override
    public List<Trainee> getAll() throws DaoException {
        List<Trainee> trainees;
        try (SqlSession sqlSession = getSession()) {
            try {
                trainees = getTraineeMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                String message = "Can't get all trainees";
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return trainees;
    }

    @Override
    public void update(Trainee trainee) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).update(trainee);
            } catch (RuntimeException ex) {
                String message = "Can't update trainee: " + trainee;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public List<Trainee> getAllWithParams(String firstName, String lastName, Integer rating) throws DaoException {
        List<Trainee> trainees;
        try (SqlSession sqlSession = getSession()) {
            try {
                trainees = getTraineeMapper(sqlSession).getByParams(firstName, lastName, rating);
            } catch (RuntimeException ex) {
                String message = "Can't get trainees by " + (firstName != null ? "firstName: " + firstName : "")
                        + (lastName != null ? "lastName: " + lastName : "")
                        + (rating != null ? "rating: " + rating : "");
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
        return trainees;
    }

    @Override
    public void batchInsert(List<Trainee> trainees) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).batchInsert(trainees);
            } catch (RuntimeException ex) {
                String message = "Can't insertAll trainees: "+ trainees;
                LOGGER.debug( message + " ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException(message, ex);
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Trainee trainee) throws DaoException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).delete(trainee.getId());
            } catch (RuntimeException ex) {
                String message = "Can't delete trainee by id: " + trainee.getId();
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
                getTraineeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all trainees ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't delete all trainees");
            }
            sqlSession.commit();
        }
    }
}