package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/*
*   @Delete("DELETE FROM subject_group")
    void deleteAll(); //TODO: is necessary?

* */
public interface GroupMapper {
    @Insert("INSERT INTO `group` (name, room, school_id) VALUES (#{group.name}, #{group.room}, #{schoolId})")
    @Options(useGeneratedKeys = true, keyProperty="group.id")
    Integer insert(@Param("group")Group group, @Param("schoolId")int schoolId);

    @Insert("INSERT INTO subject_group (subject_id,group_id) VALUES (#{subjectId},#{groupId})")
    @Options(useGeneratedKeys = true)
    Integer addSubject(@Param("subjectId")int subjectId, @Param("groupId")int groupId);

    @Select("SELECT id, name, room FROM `group` WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroupId",
                            fetchType = FetchType.LAZY) ),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroupId",
                            fetchType = FetchType.LAZY) ) })
    Group getById(int id);

    /*
    "SELECT `group`.id, `group`.name, `group`.room, trainee.firstName, trainee.lastName," +
            "trainee.rating, trainee.id, subject.name, subject.id FROM `group`, trainee, subject WHERE" +
            "`group`.id = subject.group_id AND `group`.id = trainee.group_id AND id = #{id}")

    * */
    @Select("SELECT `group`.*, trainee.*, subject.* FROM `group`, trainee, subject, subject_group WHERE" +
            "subject_group.group_id = `group`.id AND subject_group.group_id = subject.id" +
            "AND `group`.id = trainee.group_id AND id = #{id}")
    @ResultMap("GroupMap")
    Group getByIdWithJoin(int id);

    @Select("SELECT * FROM `group` WHERE school_id = #{schoolId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroupId",
                            fetchType = FetchType.LAZY) ),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroupId",
                            fetchType = FetchType.LAZY) ) })
    List<Group> getBySchoolId(int schoolId);

    @Select("SELECT * FROM `group`")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroupId",
                            fetchType = FetchType.LAZY) ),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroupId",
                            fetchType = FetchType.LAZY) ) })
    List<Group> getAll();

    @Select("SELECT `group`.*, trainee.*, subject.* FROM `group`, trainee, subject, subject_group WHERE" +
            "subject_group.group_id = `group`.id AND subject_group.group_id = subject.id" +
            "AND `group`.id = trainee.group_id")
    @ResultMap("GroupMap")
    List<Group> getAllWithJoin();

    @Update("UPDATE `group` SET name = #{name}, room = #{room} WHERE id = #{id}")
    void update(Group group);

    @Delete("DELETE FROM `group` WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM `group`")
    void deleteAll();
}