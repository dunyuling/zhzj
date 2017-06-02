package me.lhg;

import com.aifeng.jdbc.*;
import org.postgresql.jdbc4.Jdbc4Blob;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pro on 17-4-11.
 */
public class StudentMapper implements RowMapper<com.aifeng.jdbc.Student> {

    @Override
    public com.aifeng.jdbc.Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        com.aifeng.jdbc.Student student = new com.aifeng.jdbc.Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        return student;
    }


}
