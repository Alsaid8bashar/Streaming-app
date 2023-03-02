package com.example.StreamWebServer.DAO;


import com.example.StreamWebServer.model.Video;

import java.util.List;

public interface videoDao {

    List<Video> getAll();

    Video findById(int id);

}
