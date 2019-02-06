package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.mybatis.dao.CommonDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonDaoImpl extends DaoImplBase implements CommonDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDaoImpl.class);

    @Override
    public void clear() throws DaoException {
        try(SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).deleteAll();
                getSubjectMapper(sqlSession).deleteAll();
                getTraineeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all ex:{}", ex);
                sqlSession.rollback();
                throw new DaoException("Can't delete all");
            }
            sqlSession.commit();
        }
    }
}
