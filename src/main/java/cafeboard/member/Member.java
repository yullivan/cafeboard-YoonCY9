package cafeboard.member;

import cafeboard.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    public Member() {
    }

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedTime() {
        return super.getCreatedTime();
    }

    public boolean matchPassword(String passWord) {
        int count = 0;
        for (int i = 0; i < passWord.length(); i++) {
            if (this.password.charAt(i) == passWord.charAt(i)) {
                count++;
            }
        }
        return count == this.password.length();
    }
}
