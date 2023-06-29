package com.springmaster.myblogtest.repository;

import com.springmaster.myblogtest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//
//@Repository
//public class PostRepository {
//    private final JdbcTemplate jdbcTemplate;
//    public PostRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//
//    }
//
//    public Post save(Post post) {
//        // DB 저장
//        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
//
//        String sql = "INSERT INTO post (username, contents) VALUES (?, ?)";
//        jdbcTemplate.update( con -> {
//                    PreparedStatement preparedStatement = con.prepareStatement(sql,
//                            Statement.RETURN_GENERATED_KEYS);
//
//                    preparedStatement.setString(1, post.getUsername());
//                    preparedStatement.setString(2, post.getContents());
//                    return preparedStatement;
//                },
//                keyHolder);
//
//        // DB Insert 후 받아온 기본키 확인
//        Long id = keyHolder.getKey().longValue();
//        post.setId(id);
//
//        return post;
//    }
//
//    public List<PostResponseDto> findAll() {
//        // DB 조회
//        String sql = "SELECT * FROM post";
//
//        return jdbcTemplate.query(sql, new RowMapper<PostResponseDto>() {
//            @Override
//            public PostResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                // SQL 의 결과로 받아온 Post 데이터들을 PostResponseDto 타입으로 변환해줄 메서드
//                Long id = rs.getLong("id");
//                String username = rs.getString("username");
//                String contents = rs.getString("contents");
//                return new PostResponseDto(id, username, contents);
//            }
//        });
//    }
//
//    public void update(Long id, PostRequestDto requestDto) {
//        String sql = "UPDATE post SET username = ?, contents = ? WHERE id = ?";
//        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);
//    }
//
//    public void delete(Long id) {
//        String sql = "DELETE FROM post WHERE id = ?";
//        jdbcTemplate.update(sql, id);
//    }
//
//    public Post findById(Long id) {
//        // DB 조회
//        String sql = "SELECT * FROM post WHERE id = ?";
//
//        return jdbcTemplate.query(sql, resultSet -> {
//            if(resultSet.next()) {
//                Post post = new Post();
//                post.setUsername(resultSet.getString("username"));
//                post.setContents(resultSet.getString("contents"));
//                return post;
//            } else {
//                return null;
//            }
//        }, id);
//    }
//}
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByCreateAtDesc();
}
