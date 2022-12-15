package ua.chistikov.onlinelibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.chistikov.onlinelibrary.models.Library;

import java.util.List;

@Component
public class LibraryDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Library whoTook(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Library WHERE book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Library.class))
                .stream().findAny().orElse(new Library());
    }

    public List<Library> whoseBook(int person_id){
        return jdbcTemplate.query("SELECT * FROM Library WHERE person_id=?", new Object[]{person_id}, new BeanPropertyRowMapper<>(Library.class));
    }

    public void save(int person_id, int book_id) {
        jdbcTemplate.update("INSERT INTO Library(person_id, book_id) " +
                "VALUES(?, ?)", person_id, book_id);
    }

    public void deleteByBook(int id) {
        jdbcTemplate.update("DELETE FROM Library WHERE book_id=?", id);
    }

    public void deleteByPerson(int id) {
        jdbcTemplate.update("DELETE FROM Library WHERE person_id=?", id);
    }
}
