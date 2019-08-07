package mike.springframework.springmvcrest.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="jwtuser")
public class JwtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

}
