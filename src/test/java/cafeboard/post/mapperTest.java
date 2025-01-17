package cafeboard.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class mapperTest {

    @Autowired
    postMapper mapper;

    @Test
    void name() {
        mapper.findAll(null);
        mapper.findAll(1L);
    }
}
