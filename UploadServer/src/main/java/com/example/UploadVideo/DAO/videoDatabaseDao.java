package com.example.UploadVideo.DAO;

import com.example.UploadVideo.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@Profile("database")
public class videoDatabaseDao implements videoDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public videoDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Video add(Video video) {

        final String sql = "INSERT INTO video(title, descriptionn,videoName) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, video.getTitle());
            statement.setString(2, video.getDescription());
            statement.setString(3, video.getVideoPath());
            return statement;
        }, keyHolder);
        return video;
    }


}











