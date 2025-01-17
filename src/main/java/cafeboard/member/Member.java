package cafeboard.member;

import cafeboard.BaseEntity;
import cafeboard.SecurityUtils;
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
        String hashInputPassword = SecurityUtils.sha256EncryptHex2(passWord);
        for (int i = 0; i < hashInputPassword.length(); i++) {
            if (this.password.charAt(i) == hashInputPassword.charAt(i)) {
                count++;
            }
        }
        return count == this.password.length();
    }

    public boolean matchName(String name) {
        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            if (this.name.charAt(i) == name.charAt(i)) {
                count++;
            }
        }
        return count == this.name.length();
    }
}
