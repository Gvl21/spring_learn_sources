package com.busanit.spring.f_db.domain;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

// 멤버 클래스의 데이터 접근 객체, 관심사 : 데이터 접근
public class MemberDao {

    //JDBC template
    private JdbcTemplate jdbcTemplate;

    // 생성하면서 dataSource 의존성 주입
    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // READ
    // JDBCTemplate을 이용한 조회 쿼리
    // query(String sql, RowMapper<T>, Objects... args)
    // 첫번째 매개변수 SQL문
    // 두번째 매개변수 쿼리결과와 자바 객체으 ㅣ매핑
    // 세번째 매개변수 (필수아님) ? 에 들어갈 인자
    public Member selectByEmail(String email) {
        List<Member> resultList = jdbcTemplate.query(
                "select * from member where EMAIL = ?", (rs, rowNum) -> {
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member.setId(rs.getLong("ID"));
                    return member;

                }, email);
        // 결과문이 List에 담겨서 오기 때문에, 객체를 꺼내줘야 한다.
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    // CREATE
    public void insert(Member member) {

    }

    // UPDATE
    public void update(Member member) {

    }

    // READ ALL
    public Collection<Member> selectAll() {
        return null;
    }

}
