package cafeboard.comment;

import cafeboard.BaseEntity;
import cafeboard.post.Post;
import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    public Comment() {
    }

    public Comment(String writer, String content, Post post) {
        this.writer = writer;
        this.content = content;
        this.post = post;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Post getPost() {
        return post;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return super.getCreatedTime();
    }

    public LocalDateTime getUpdatedTime() {
        return super.getUpdatedTime();
    }

    public void setComment(String writer, String content) {
        if (writer == null && content == null) {
            throw new IllegalStateException("수정할 내용이 없습니다");
        }
        if (writer != null) {
            this.writer = writer;
        }
        if (content != null) {
            this.content = content;
        }

    }
}
