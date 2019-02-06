package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.exception.DaoException;

public interface CommonDao {
    // удаляет все записи из всех таблиц, иными словами, очищает базу данных
    void clear() throws DaoException;
}
