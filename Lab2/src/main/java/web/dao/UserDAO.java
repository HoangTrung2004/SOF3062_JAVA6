package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entity.User;

public interface UserDAO extends JpaRepository<User,String> {
}
