package com.example.StreamWebServer.DAO;

import com.example.StreamWebServer.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile("database")
public class videoDatabaseDao implements videoDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public videoDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Video> getAll() {
        final String sql = "SELECT * FROM video;";
        return jdbcTemplate.query(sql, new ToDoMapper());
    }

    @Override
    public Video findById(int id) {

        final String sql = "SELECT * "
                + "FROM video WHERE id = ?;";

        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }

    private static final class ToDoMapper implements RowMapper<Video> {

        @Override
        public Video mapRow(ResultSet rs, int index) throws SQLException {
            Video td = new Video();
            td.setId(rs.getInt("id"));
            td.setTitle(rs.getString("title"));
            td.setDescription(rs.getString("descriptionn"));
            td.setVideoPath(rs.getString("videoName"));
            return td;
        }
    }


}











