package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SchoolMapper {
    @Insert("INSERT INTO school (name,year) VALUES (#{name},#{year})")
    @Options(useGeneratedKeys = true)
    Integer insert(School school);

    @Select("SELECT * FROM school WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchoolId",
                            fetchType = FetchType.LAZY) )})
    School getById(int id);


    @Select("SELECT school.*, `group`.*, trainee.*, subject.* FROM school, `group`, trainee, subject, subject_group" +
            " WHERE subject_group.group_id = `group`.id AND subject_group.subject_id = subject.id" +
            " AND `group`.id = trainee.group_id AND `group`.school_id = school.id AND school.id = #{id}")
    @ResultMap("SchoolMap")
    School getByIdWithJoin(int id);


    @Select("SELECT * FROM school")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchoolId",
                            fetchType = FetchType.LAZY) )})
    List<School> getAll();


    /*@Select("SELECT school.*, `group`.*, trainee.* , subject.* FROM school,"
              + " `group` LEFT JOIN trainee on trainee.group_id = `group`.id"
              + " LEFT JOIN subject_group on subject_group.group_id = `group`.id"
              + " LEFT JOIN subject on subject.id = subject_group.subject_id"
             + " WHERE`group`.school_id = school.id")
    */
    @Select("SELECT school.*, `group`.* FROM school,"
            + " `group`"
            + " WHERE`group`.school_id = school.id")
    @ResultMap("SchoolMap")
    List<School> getAllWithJoin();

    @Update("UPDATE school SET name = #{name}, year = #{year} WHERE id = #{id}")
    void update(School school);

    @Update("UPDATE trainee SET group_id = #{groupId} WHERE id = #{id}")
    void refWithGroup(@Param("id") Integer traineeId, @Param("groupId") Integer groupId);

    @Delete("DELETE FROM school WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM school")
    void deleteAll();
}
