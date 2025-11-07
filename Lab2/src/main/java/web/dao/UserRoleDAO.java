package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entity.UserRole;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole,Integer> {

}
