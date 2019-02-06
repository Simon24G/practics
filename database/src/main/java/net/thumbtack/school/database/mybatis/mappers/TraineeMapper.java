package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TraineeMapper {
    @Insert("INSERT INTO trainee (firstName,lastName,rating) VALUES(#{firstName},#{lastName},#{rating})")
    @Options(useGeneratedKeys = true)
    Integer insert(Trainee trainee);

    @Insert("INSERT INTO trainee (firstName,lastName,rating,group_id) VALUES(#{trainee.firstName},#{trainee.lastName}," +
            "#{trainee.rating},#{groupId})")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    Integer insertWithGroup(@Param("trainee")Trainee trainee, @Param("groupId")int groupId);

    @Insert({"<script>",
            "INSERT INTO trainee (firstName,lastName,rating) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstName}, #{item.lastName}, #{item.rating})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(@Param("list") List<Trainee> traineesList);

    @Select("SELECT id, firstName, lastName, rating FROM trainee WHERE id = #{id}")
    @Results({@Result(property = "id", column = "id")})
    Trainee getById(int id);

    @Select({"<script>",
            "SELECT id, firstName, lastName, rating FROM trainee " +
            "<where>" +
            "<if test='firstName != null'> firstName like #{firstName}</if>",
            "<if test='lastName != null'> AND lastName like #{lastName}</if>",
            "<if test='rating != null'> AND rating like #{rating}</if>",
            "</where>" +
                    "</script>" })
    @Results({@Result(property = "id", column = "id")})
    List<Trainee> getByParams(@Param("firstName")String firstName,
                             @Param("lastName")String lastName,
                             @Param("rating")Integer rating);

    @Select("SELECT id, firstName, lastName, rating FROM trainee WHERE group_id = #{groupId}")
    List<Trainee> getByGroupId(int groupId);

    @Select("SELECT * FROM trainee")
    List<Trainee> getAll();

    @Update("UPDATE trainee SET firstName = #{firstName}, lastName = #{lastName}, rating = #{rating} WHERE id = #{id}")
    void update(Trainee trainee);

    @Update("UPDATE trainee SET group_id = #{groupId} WHERE id = #{id}")
    void refToGroup(@Param("id")int traineeId, @Param("groupId")Integer groupId);

    @Update("UPDATE trainee SET group_id = null WHERE id = #{id}")
    void deleteRefToGroup(int id);

    @Delete("DELETE FROM trainee WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM trainee")
    void deleteAll();
}