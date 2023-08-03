package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import ScoreManagement.util.JdbcUtil;
import Student.dao.StudentDao;
import Student.dto.StudentDto;
import Student.dto.TeacherDto;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int count() throws SQLException {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			
			String sql = "SELECT count(*) FROM STUDENT ";
			
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
	public List<StudentDto> list() throws SQLException {
		List<StudentDto> result = new ArrayList<StudentDto>();
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			
			String sql = "SELECT * FROM STUDENT order by STUDENT_ID";
			
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("STUDENT_ID");
				String name = rs.getString("STUDENT_NAME");
				String pw = rs.getString("STUDENT_PW");
				String tel = rs.getString("STUDENT_TEL");
				String sClass = rs.getString("STUDENT_CLASS");
				StudentDto dto = new StudentDto(id, name, pw, tel, sClass);
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
	public void add(StudentDto dto) throws SQLException, DuplicatedIdException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO STUDENT(STUDENT_ID, STUDENT_NAME, STUDENT_PW,STUDENT_TEL,STUDENT_CLASS)";
			sql += "VALUES( ?, ? , ?, ?,? )";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setString(1, dto.getStudentId());
			pstmt.setString(2, dto.getStudentName());
			pstmt.setString(3, dto.getStudentPw());
			pstmt.setString(4, dto.getStudentTel());
			pstmt.setString(5, dto.getStudentClass());
			// 6. SQL 전송, 결과수신
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		
	}

	@Override
	public StudentDto findById(String id) throws SQLException {
		StudentDto dto = null;
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM STUDENT where STUDENT_ID = ?";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {//조회결과가 있다
				String name = rs.getString("STUDENT_NAME");
				String pw = rs.getString("STUDENT_PW");
				String tel = rs.getString("STUDENT_TEL"); 
				String sClass = rs.getString("STUDENT_CLASS");
				dto = new StudentDto(id, name, pw, tel, sClass);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}
	}


