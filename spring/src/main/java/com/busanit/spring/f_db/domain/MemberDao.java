package com.busanit.spring.f_db.domain;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    // 두번째 매개변수 쿼리결과와 자바 객체의 매핑
    // 세번째 매개변수 (필수아님) ? 에 들어갈 인자
    public Member selectByEmail(String email) {
       List<Member> result = jdbcTemplate.query(
                "select * from member where EMAIL = ?", new RowMapper<Member>() {
                   @Override
                   public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                       Member member = new Member(
                               rs.getString("EMAIL"),
                               rs.getString("PASSWORD"),
                               rs.getString("NAME"),
                               rs.getTimestamp("REGDATE").toLocalDateTime()
                       );
                       member.setId(rs.getLong("ID"));
                       return member;

                   }
               }, email);
        // 결과문이 List에 담겨서 오기 때문에, 객체를 꺼내줘야 한다.
        return result.isEmpty() ? null : result.get(0);
    }

    // PreparedStatementCreator 를 이용한 방식
    // CREATE
    public void insert(Member member) {
        // ID 필드 자동생성을 위한 KeyHolder
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
                    // 전달받은 커넥션을 이용해 prepareStatement 생 성
                    PreparedStatement pstmt = con.prepareStatement("""
                    INSERT INTO 
                    MEMBER(EMAIL, PASSWORD, NAME, REGDATE) 
                    VALUE (?,?,?,?)
                    """, new String[]{"ID"});
            // 각각 인덱스, 파라미터 값 설정
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            // 생성한 객체 리턴
            return pstmt;
        }, keyHolder);
        Number key = keyHolder.getKey();
        member.setId(key.longValue());

    }
    // INSERT, UPDATE, DELETE 쿼리는
    // jdbcTemplate.update()를 활용한다.
    // UPDATE
    public void update(Member member) {
        // MEMBER 테이블에서 email 기준으로 이름, 비밀번호, 변경
        jdbcTemplate.update("UPDATE MEMBER SET NAME = ?, PASSWORD = ? WHERE EMAIL = ?",
                member.getName(),
                member.getPassword(),
                member.getEmail());
    }

    // READ ALL
    public Collection<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("SELECT * from MEMBER", (rs, rowNum) -> {
            Member member = new Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime()
            );
            // ID를 결과문에서 ID 컬럼에서 가져와 별도로 세팅
            member.setId(rs.getLong("ID"));
            return member;
        });
        return results;
    }

}
