package message;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MessageRepository {

    JdbcTemplate jdbcTemplate;

    public MessageRepository(MariaDbDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void sendMessage(User sender, User receiver, String message) {
        jdbcTemplate.update("insert into messages (sender_id, receiver_id, message) values (?, ?, ?)",
                sender.getId(), receiver.getId(), message);
    }

    public List<String> findMessagesBySenderId(long senderId) {
        return jdbcTemplate.query("select message from messages where sender_id = ?",
                (rs, rowNum) -> rs.getString("message"), senderId);
    }
}
