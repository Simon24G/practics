package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService extends JdbcUtils {
    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertTraineeQuery = "INSERT INTO trainee (id,firstName,lastName,rating) VALUES(?,?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtInsertTrainee = con.prepareStatement(insertTraineeQuery, Statement.RETURN_GENERATED_KEYS)) {

            stmtInsertTrainee.setNull(1,Types.INTEGER);
            stmtInsertTrainee.setString(2, trainee.getFirstName());
            stmtInsertTrainee.setString(3, trainee.getLastName());
            stmtInsertTrainee.setInt(4, trainee.getRating());
            stmtInsertTrainee.executeUpdate();
            ResultSet generatedKeys = stmtInsertTrainee.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idTrainee = generatedKeys.getInt((1));
                trainee.setId(idTrainee);
            } else {
                System.out.println("123");
                throw new SQLException("Creating Trainee failed, no ID obtained.");
            }
            //con.commit();
        }
        //rollback
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        String updateTraineeQuery = "UPDATE trainee SET firstName = ?, lastName = ?, rating = ?, group_id = ? WHERE id = ?";
        //String updateAddressQuery = "update into trainee_address values(?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtUpdateTrainee = con.prepareStatement(updateTraineeQuery)) {

            stmtUpdateTrainee.setString(1, trainee.getFirstName());
            stmtUpdateTrainee.setString(2, trainee.getLastName());
            stmtUpdateTrainee.setInt(3, trainee.getRating());
            stmtUpdateTrainee.setNull(4, Types.INTEGER);
            stmtUpdateTrainee.setInt(5, trainee.getId());
            stmtUpdateTrainee.executeUpdate();

            //con.commit();
        }
        //rollback
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        Trainee trainee = null;
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, traineeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    trainee = new Trainee();
                    trainee.setId(rs.getInt("id"));
                    trainee.setFirstName(rs.getString("firstName"));
                    trainee.setLastName(rs.getString("lastName"));
                    trainee.setRating(rs.getInt("rating"));
                }
            }
        }
        return trainee;
    }


    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        Trainee trainee = null;
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, traineeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    trainee = new Trainee();
                    trainee.setId(rs.getInt(1));
                    trainee.setFirstName(rs.getString(2));
                    trainee.setLastName(rs.getString(3));
                    trainee.setRating(rs.getInt(4));
                }
            }
        }
        return trainee;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "SELECT * FROM trainee";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(rs.getInt("id"));
                trainee.setFirstName(rs.getString("firstName"));
                trainee.setLastName(rs.getString("lastName"));
                trainee.setRating(rs.getInt("rating"));
                trainees.add(trainee);
            }
        }
        return trainees;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "SELECT * FROM trainee";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(rs.getInt(1));
                trainee.setFirstName(rs.getString(2));
                trainee.setLastName(rs.getString(3));
                trainee.setRating(rs.getInt(4));
                trainees.add(trainee);
            }
        }
        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        String traineeQuery = "DELETE FROM trainee WHERE id = ?";
        //String updateAddressQuery = "update into trainee_address values(?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtDeleteTrainee = con.prepareStatement(traineeQuery)) {
            stmtDeleteTrainee.setInt(1, trainee.getId());
            stmtDeleteTrainee.executeUpdate();
        }
        //rollback
    }

    public static void deleteTrainees() throws SQLException {
        String traineeQuery = "DELETE FROM trainee where 1=1";//TRUNCATE TABLE table_name
        //String updateAddressQuery = "update into trainee_address values(?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtDeleteTrainee = con.prepareStatement(traineeQuery)) {
            stmtDeleteTrainee.executeUpdate();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertSubjectQuery = "INSERT INTO subject (id,name) VALUES(?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtInsertSubject = con.prepareStatement(insertSubjectQuery, Statement.RETURN_GENERATED_KEYS)) {

            stmtInsertSubject.setNull(1, java.sql.Types.INTEGER);
            stmtInsertSubject.setString(2, subject.getName());
            stmtInsertSubject.executeUpdate();

            try (ResultSet generatedKeys = stmtInsertSubject.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idSubject = generatedKeys.getInt((1));
                    subject.setId(idSubject);
                } else {
                    throw new SQLException("Creating Subject failed, no ID obtained.");
                }
                //con.commit();
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        Subject subject = null;
        String selectQuery = "SELECT * FROM subject WHERE id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setId(rs.getInt(1));
                    subject.setName(rs.getString(2));
                }
            }
        }
        return subject;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        Subject subject = null;
        String selectQuery = "SELECT * FROM subject WHERE id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)){
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setId(rs.getInt("id"));
                    subject.setName(rs.getString("name"));
                }
            }
        }
        return subject;
    }
    public static void deleteSubjects() throws SQLException {
        String subjectQuery = "DELETE FROM subject where 1=1";
        Connection con = getConnection();
        try(PreparedStatement stmtDeleteSubject = con.prepareStatement(subjectQuery)) {
            stmtDeleteSubject.executeUpdate();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertSchoolQuery = "INSERT INTO school (id,name,year) VALUES(?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtInsertSchool = con.prepareStatement(insertSchoolQuery, Statement.RETURN_GENERATED_KEYS)) {

            stmtInsertSchool.setNull(1, java.sql.Types.INTEGER);
            stmtInsertSchool.setString(2, school.getName());
            stmtInsertSchool.setInt(3, school.getYear());
            stmtInsertSchool.executeUpdate();

            try (ResultSet generatedKeys = stmtInsertSchool.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idSchool = generatedKeys.getInt((1));
                    school.setId(idSchool);
                } else {
                    throw new SQLException("Creating School failed, no ID obtained.");
                }
                //con.commit();
            }
        }
    }
    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        School school = null;
        String selectQuery = "SELECT * FROM school WHERE school.id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    school.setId(rs.getInt("id"));
                    school.setName(rs.getString("name"));
                    school.setYear(rs.getInt("year"));
                }
            }
        }
        return school;
    }
    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        School school = null;
        String selectQuery = "SELECT * FROM school WHERE school.id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    school.setId(rs.getInt(1));
                    school.setName(rs.getString(2));
                    school.setYear(rs.getInt(3));
                }
            }
        }
        return school;
    }
    public static void deleteSchools() throws SQLException {
        String schoolQuery = "DELETE FROM school where 1=1";
        Connection con = getConnection();
        try(PreparedStatement stmtDeleteSchool = con.prepareStatement(schoolQuery)) {
            stmtDeleteSchool.executeUpdate();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertGroupQuery = "INSERT INTO `group` (id,name,room,school_id) VALUES(?,?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement stmtInsertGroup = con.prepareStatement(insertGroupQuery, Statement.RETURN_GENERATED_KEYS)) {

            stmtInsertGroup.setNull(1, Types.INTEGER);
            stmtInsertGroup.setString(2, group.getName());
            stmtInsertGroup.setString(3, group.getRoom());
            stmtInsertGroup.setInt(4, school.getId());
            stmtInsertGroup.executeUpdate();

            try (ResultSet generatedKeys = stmtInsertGroup.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGroup = generatedKeys.getInt((1));
                    group.setId(idGroup);
                } else {
                    throw new SQLException("Creating Group failed, no ID obtained.");
                }
                //con.commit();
            }
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        School school = null;
        String selectQuery = "SELECT school.*, `group`.* FROM school, `group` WHERE school.id = `group`.school_id and school.id = ?";
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    String schoolStr = "school.", groupStr = "group.";
                    school.setId(rs.getInt(schoolStr + "id"));
                    school.setName(rs.getString(schoolStr + "name"));
                    school.setYear(rs.getInt(schoolStr + "year"));

                    do {
                        Group group = new Group();
                        group.setId(rs.getInt(groupStr + "id"));
                        group.setName(rs.getString(groupStr + "name"));
                        group.setRoom(rs.getString(groupStr + "room"));
                        school.addGroup(group);
                    } while (rs.next());
                }
            }
        }
        return school;
    }
    public static List<School> getSchoolsWithGroups() throws SQLException {
        List<School> schools = new ArrayList<>();
        String selectQuery = "SELECT school.*, `group`.* FROM school, `group` WHERE school.id = `group`.school_id order by school.id";
        School school;
        int current, last;
        Connection con = getConnection();
        try(PreparedStatement stmt = con.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                school = new School();
                System.out.println(rs);
                school.setId(rs.getInt("school.id"));
                school.setName(rs.getString("school.name"));
                school.setYear(rs.getInt("school.year"));
                last = school.getId();
                do {
                    current = rs.getInt("school.id");
                    if (current != last) {
                        schools.add(school);

                        school = new School();
                        school.setId(current);
                        school.setName(rs.getString("school.name"));
                        school.setYear(rs.getInt("school.year"));
                        last = current;
                    }
                    Group group = new Group();
                    group.setId(rs.getInt("group.id"));
                    group.setName(rs.getString("group.name"));
                    group.setRoom(rs.getString("group.room"));
                    school.addGroup(group);
                } while (rs.next());
                schools.add(school);
            }
        }
        return schools;
    }
}
