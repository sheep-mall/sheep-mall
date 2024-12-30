package com.sheep.mall.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sheep.mall.entity.UserEntity;

@RestController
@RequestMapping("/test")
public class TestController {

    private final NamedParameterJdbcTemplate db;

    public TestController(NamedParameterJdbcTemplate db){
        this.db = db;
    }

    @GetMapping("")
    public UserEntity testGet(){
        
        String username = "yale";
        String email = "yale@gmail.com";

        String sql  = " select * from users "
                    + " where 1=1 "
                    + " and username= ? "
                    + " and email= ? "
                    ;

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("username", username)
            .addValue("email", email);

        try {
            return db.queryForObject(sql, parameters, (rs, rowNum) -> new UserEntity(
                rs.getString("uid"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
            ));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping("")
    public UserEntity testPost(@RequestBody UserRequest userRequest){

        String sql  = " select * from users "
                    + " where 1=1 "
                    + " and username=:username "
                    + " and email=:email "
                    ;

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("username", userRequest.username())
            .addValue("email", userRequest.email());

        try {
            return db.queryForObject(sql, parameters, (rs, rowNum) -> new UserEntity(
                rs.getString("uid"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
            ));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
