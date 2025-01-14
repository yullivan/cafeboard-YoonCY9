package cafeboard.board;

import cafeboard.BaseEntity;
import cafeboard.post.Post;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;

    public Board() {
    }

    public Board(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedTime() {
        return super.getCreatedTime();
    }

    public LocalDateTime getUpdatedTime() {
        return super.getUpdatedTime();
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
