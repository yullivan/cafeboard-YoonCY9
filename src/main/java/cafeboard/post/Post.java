package cafeboard.post;

import cafeboard.BaseEntity;
import cafeboard.board.Board;
import cafeboard.comment.Comment;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String writer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


    public Post() {
    }

    public Post(String title, String content, String writer, Board board) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public Board getBoard() {
        return board;
    }

    public LocalDateTime getCreatedTime() {
        return super.getCreatedTime();
    }

    public LocalDateTime getUpdatedTime() {
        return super.getUpdatedTime();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int commentCount() {
        return this.comments.size();
    }

    public void setPost(String title, String content, String writer) {
        if (title == null && content == null && writer == null) {
            throw new IllegalStateException("수정할 내용이 없습니다");
        }
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
        if (writer != null) {
            this.writer = writer;
        }
    }

}
