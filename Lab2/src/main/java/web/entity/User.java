package web.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "Users")                         // map bảng Users
public class User {
    @Id
    private String username;                           // PK

    private String password;                           // {noop}123 (lab)
    private boolean enabled;                           // BIT -> boolean

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) // lấy luôn roles khi load user
    private List<UserRole> userRoles;                  // các bản ghi nối
}