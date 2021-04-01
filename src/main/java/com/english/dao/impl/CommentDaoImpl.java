package com.english.dao.impl;

import com.english.dao.CommentDao;
import com.english.dao.DAOException;
import com.english.database.DatabaseDataSource;
import com.english.model.User;
import com.english.model.blog.Category;
import com.english.model.blog.Comment;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl implements CommentDao {

    private static final String POST_COMMENTS = "SELECT * FROM comments_view WHERE post_id = ?";
    private static final Logger LOGGER = Logger.getLogger(CommentDaoImpl.class);
    private final DatabaseDataSource dataSource = DatabaseDataSource.getInstance();

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void add(Category entity) throws DAOException {

    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void delete(Category entity) {

    }

    @Override
    public List<Comment> findByPostId(long postId) {
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(POST_COMMENTS);
            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                User user = new User();

                user.setId(resultSet.getLong("user_id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setImage(resultSet.getString("user_image"));
                comment.setUser(user);

                comment.setId(resultSet.getLong("comment_id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

                comments.add(comment);
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage(), e);
        }
        return comments;
    }
}
