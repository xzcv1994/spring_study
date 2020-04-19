package com.example.spring_study.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.spring_study.member.Member;
//import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao implements IMemberDao{
	
//	private String driver = "org.h2.Driver";
//	private String userId = "sa";
//	private String userPw = "";
//	private String url = "jdbc:h2:tcp://localhost/~/test";
	
//	private ComboPooledDataSource dataSource;
	private JdbcTemplate template;
	
	@Autowired
	public MemberDao(DataSource dataSource) {
//		dataSource = new ComboPooledDataSource();
//		try {
//			dataSource.setDriverClass(driver);
//			dataSource.setJdbcUrl(url);
//			dataSource.setUser(userId);
//			dataSource.setPassword(userPw);
//		} catch(PropertyVetoException e) {
//			e.printStackTrace();
//		}
//		
//		template = new JdbcTemplate();
//		template.setDataSource(dataSource);	
		
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int memberInsert(Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		
		final String sql = "INSERT INTO spring5fs.member (memId, memPw, memMail) values (?,?,?)";
		
		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());
		
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		// TODO Auto-generated method stub
		List<Member> members = null;
		
		final String sql = "SELECT * FROM spring5fs.member WHERE memId = ? AND memPw = ?";
		
		members = template.query(sql, 
			new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
			}, 
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Member mem = new Member();
					mem.setMemId(rs.getString("memId"));
					mem.setMemPw(rs.getString("memPw"));
					mem.setMemMail(rs.getString("memMail"));
					return mem;
				}
			});
		
		return members.get(0);
	}

	@Override
	public int memberUpdate(Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		
		final String sql = "UPDATE spring5fs.member SET memPw = ?, memMail = ? WHERE memId = ?";
		
		result = template.update(sql, member.getMemPw(), member.getMemMail(), member.getMemId());
		
		return result;
	}

	@Override
	public int memberDelete(Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		
		final String sql = "DELETE spring5fs.member WHERE memId = ? AND memPw = ?";
		
		result = template.update(sql, member.getMemId(), member.getMemPw());
		
		return result;
	}

}
