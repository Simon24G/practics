package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {
    @Insert("INSERT INTO subject (name) VALUES(#{name})")
    @Options(useGeneratedKeys = true)
    Integer insert(Subject subject);

    @Select("SELECT * FROM subject WHERE id = #{id}")
    Subject getById(int id);

    @Select("SELECT subject.id as id, subject.name as name FROM subject, subject_group WHERE subject_group.group_id = #{groupId}" +
            " AND subject_group.subject_id = subject.id")
    List<Subject> getByGroupId(int groupId);

    @Select("SELECT * FROM subject")
    List<Subject> getAll();

    @Update("UPDATE subject SET name = #{name} WHERE id = #{id}")
    void update(Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM subject")
    void deleteAll();
}
