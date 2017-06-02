package me.lhg;

import com.aifeng.jdbc.*;
import com.aifeng.jdbc.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-11.
 */
public class StudentJDBCTemplate implements com.aifeng.jdbc.StudentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private SimpleJdbcCall jdbcCall;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getRecord");
    }
    public void create(String name, Integer age) {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update( SQL, name, age);
        System.out.println("Created Record Name = " + name + " Age = " + age);
        return;
    }
    //get student basic way
    /*public Student getStudent(Integer id) {
        String SQL = "select * from Student where id = ?";
        Student student = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StudentMapper());

        return student;
    }*/


    public com.aifeng.jdbc.Student getStudent(Integer id) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("param0", id);
        Map<String, Object> out = jdbcCall.execute(in);

        com.aifeng.jdbc.Student student = new com.aifeng.jdbc.Student();
        student.setId(id);
//        student.setName((String) out.get("param1"));
        student.setAge((Integer) out.get("param1"));
        return student;
    }

    public List<com.aifeng.jdbc.Student> listStudents() {
        String SQL = "select * from Student";
        List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
        return students;
    }
    public void delete(Integer id) {
        String SQL = "delete from Student where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
        return;
    }
    public void update(Integer id, Integer age){
        String SQL = "update Student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id );
        return;
    }
}


    /*DELIMITER $$
    DROP PROCEDURE IF EXISTS `test`.`getRecord` $$
        CREATE PROCEDURE `test`.`getRecord` (
        IN param0 INTEGER,
        OUT param1 VARCHAR(20),
        OUT param2  INTEGER)
        BEGIN
        SELECT name, age
        INTO param1, param2
        FROM Student where id = param0;
        END $$
        DELIMITER ;
*/



    /*DELIMITER $$
    DROP PROCEDURE IF EXISTS `test`.`getRecord` $$
        CREATE PROCEDURE `test`.`getRecord` (
        IN param0 INT,
        OUT param1  INT)
        BEGIN
        SELECT age
        INTO param1
        FROM Student where id = param0;
        END $$
        DELIMITER ;*/



    //select * from mysql.proc where db = 'test' and type = 'procedure';

