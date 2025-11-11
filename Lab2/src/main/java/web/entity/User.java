package web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    String username;
    String password;
    boolean enabled;
    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
    List<UserRole> userRoles;
}
