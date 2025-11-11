package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entity.Role;

public interface RoleDAO extends JpaRepository<Role,String> {
}
