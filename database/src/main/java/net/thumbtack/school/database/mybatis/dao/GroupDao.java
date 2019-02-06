package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.exception.DaoException;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.util.List;

public interface GroupDao {

    // вставляет Group в базу данных, привязывая ее к School.
    Group insert(School school, Group group) throws DaoException;//есть

    // изменяет Group  в базе данных, принадлежность к School не меняется
    void update(Group group) throws DaoException;//есть

    // получает список всех Group, независимо от School
    List<Group> getAll() throws DaoException;//есть

    // удаляет Group  в базе данных, при этом все Trainee оказываеются не принадлежащими никакой Group
    void delete(Group group) throws DaoException;//есть

    // переводит Trainee в Group. Если Trainee не принадлежал никакой Group, добавляет его в Group
    void moveTraineeToGroup(Group group, Trainee trainee) throws DaoException;//есть

    // удаляет Trainee из Group, после этого Trainee не принадлежит никакой Group
    void deleteTraineeFromGroup(Trainee trainee) throws DaoException;//есть

    // добавляет Subject к Group
    void addSubjectToGroup(Group group, Subject subject) throws DaoException;//есть
}