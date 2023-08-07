package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberReopository{

    public final JdbcTemplate jdbcTemplate;

    //@Autowired  //생성자가 하나면 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(jdbcTemplate);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); //쿼리 짤 필요 없이 insert문을 만들어줌
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); //어떤 테이블에 어떤 컬럼에 대해 insert할건지 알려줌

        Map<String, Object> parameters = new HashMap<>();   //key는 컬럼명, value는 member의 필드명
        parameters.put("name", member.getName());        //member의 name을 꺼내서 parameters에 넣어줌

        Number key = jdbcInsert.executeAndReturnKey(parameters); //key를 받아옴
        member.setId(key.longValue()); //member에 id 세팅
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
       List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
         return result.stream().findAny(); //result를 스트림으로 바꿔서 하나라도 찾으면 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper()); //memberRowMapper()를 통해 member 객체로 만들어줌
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {

            Member member = new Member();
            member.setId(rs.getLong("id")); //rs에서 id를 꺼내서 member에 넣어줌
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
