package com.codewithsudhakaran.todo.repository;
import com.codewithsudhakaran.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class TodoRepository  {
        @Autowired
        private JdbcTemplate jdbcTemplate;
        public List<Todo> insert(Todo todo) {
            jdbcTemplate.update("INSERT INTO todo(id,name,number)VALUES(?,?,?);",
                    new Object[]{todo.getId(),todo.getName(),todo.getNumber()});
            return findAll();
        }

        public List<Todo> findAll() {
            return jdbcTemplate.query("SELECT* FROM todo;",new TodoRowMapper());
        }
        public Todo findById(int id) {
            return jdbcTemplate.queryForObject("SELECT* FROM todo WHERE id=?",
                    new Object[]{id},new BeanPropertyRowMapper<>(Todo.class));
        }

        public List<Todo> update(Todo todo) {
            jdbcTemplate.update("UPDATE todo SET name=?, number=? WHERE id=?;",
                    new Object[]{todo.getName(),todo.getNumber(),todo.getId()});
            return findAll();
        }
        public List<Todo> delete(int id) {
            jdbcTemplate.update("DELETE FROM todo WHERE id=?;",
                    new Object[]{id});
            return findAll();
        }
        class TodoRowMapper implements RowMapper<Todo> {
            @Override
            public Todo mapRow(ResultSet rs,int rowNum)throws SQLException {
                return new Todo(rs.getInt("id"), rs.getString("name"), rs.getInt("number"));
            }

        }
    }
