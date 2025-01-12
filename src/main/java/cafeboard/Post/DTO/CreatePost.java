package cafeboard.Post.DTO;

import cafeboard.board.Board;

public record CreatePost(Long boardId, String title, String content, String writer) {
}
