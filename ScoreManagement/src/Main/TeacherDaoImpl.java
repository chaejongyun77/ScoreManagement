package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ScoreManagement.util.JdbcUtil;
import Student.dao.TeacherDao;
import Student.dto.StudentDto;
import Student.dto.TeacherDto;


public class TeacherDaoImpl implements TeacherDao {

	@Override
	public int count() throws SQLException {
		
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			
			String sql = "SELECT count(*) FROM TEACHER ";
			
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return count;
	}

	@Override
	public List<TeacherDto> list() throws SQLException {
		
		List<TeacherDto> result = new ArrayList<TeacherDto>();
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			
			String sql = "SELECT * FROM TEACHER order by TEACHER_ID";
			
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("TEACHER_ID");
				
				String pw = rs.getString("TEACHER_PW");
				String name = rs.getString("TEACHER_NAME");
				
				String tClass = rs.getString("TEACHER_CLASS");
				TeacherDto dto = new TeacherDto(id, pw, name, tClass);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

	@Override
	public TeacherDto findById(String id) throws SQLException {
		TeacherDto dto = null;
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM TEACHER where TEACHER_ID = ?";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {//조회결과가 있다
				
				String pw = rs.getString("TEACHER_PW");
				String name = rs.getString("TEACHER_NAME");
				String tClass = rs.getString("TEACHER_CLASS");
				dto = new TeacherDto(id, pw, name, tClass);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

	@Override
	public void add(TeacherDto dto) throws SQLException, DuplicatedIdException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO TEACHER(TEACHER_ID, TEACHER_PW, TEACHER_NAME,TEACHER_CLASS)";
			sql += "VALUES( ?, ? , ?, ? )";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setString(1, dto.getTeacherId());
			pstmt.setString(2, dto.getTeacherPw());
			pstmt.setString(3, dto.getTeacherName());
			pstmt.setString(4, dto.getTeacherClass());
			// 6. SQL 전송, 결과수신
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		
	}


}
