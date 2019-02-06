package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.Subject;

import java.util.List;

public interface SubjectDao {

    // вставляет Subject в базу данных.
    Subject insert(Subject subject) throws DaoException;//есть

    // получает Subject по его ID. Если такого ID нет, метод возвращает null
    Subject getById(int id) throws DaoException;//есть

    // получает список всех Subject. Если БД не содержит ни одного Trainee, метод возвращает пустой список
    List<Subject> getAll() throws DaoException;//есть

    // изменяет Subject  в базе данных
    void update(Subject subject) throws DaoException;//есть

    // удаляет Subject из базы данных
    void delete(Subject subject) throws DaoException;//есть

    // удаляет все Subject из базы данных
    void deleteAll() throws DaoException;//есть


}