package cafeboard.comment;

import cafeboard.post.Post;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;

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
        return createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setComment(String writer, String content) {
        if (writer != null) {
            this.writer = writer;
        }
        if (content != null) {
            this.content = content;
        }
    }
}
