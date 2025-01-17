package cafeboard.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface postMapper {
    List<Post> findAll(Long boardId);
}



