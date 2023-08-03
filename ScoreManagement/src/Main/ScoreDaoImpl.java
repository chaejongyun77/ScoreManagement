package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ScoreManagement.util.JdbcUtil;
import Main.*;
import Student.dto.*;
import Student.dao.*;


public class ScoreDaoImpl implements ScoreDao {

	//성적 입력 (교수만 가능)
	@Override
	public void add(ScoreDto dto) throws SQLException, DuplicatedIdException {
		// 3. DBMS연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			if(findById(dto.getStudentId())!=null) {
				throw new DuplicatedIdException(dto.getStudentId()+"에 해당하는 학생은 이미 있습니다.");
			}
			conn=JdbcUtil.connect();
			// 4. SQL작성
			String sql="INSERT INTO SCORE(STUDENT_ID, STUDENT_NAME, STUDENT_CLASS, MIDTERM, HW, FINALS, ATTEND, TOTAL_SCORE ) ";
			sql+="VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			// 5. Statement작성
			pstmt = conn.prepareStatement(sql);
			// 6. 데이터 설정
			pstmt.setString(1, dto.getStudentId());
			pstmt.setString(2, dto.getStudentName());
			pstmt.setString(3, dto.getStudentClass());
			pstmt.setInt(4, dto.getMidTerm());
			pstmt.setInt(5, dto.getHw());
			pstmt.setInt(6, dto.getFinals());
			pstmt.setInt(7, dto.getAttend());
			pstmt.setInt(8, dto.getTotalScore());
			// 7. SQL전송, 결과수신
			pstmt.executeUpdate(); 
		} catch (ClassNotFoundException e) {
			throw new SQLException(e); 
		}finally {
			//DBMS해제
			JdbcUtil.close(pstmt,conn);
		}
	}
	//학생의 성적 수정 (교수만 가능)
	@Override
	public void update(ScoreDto dto) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt =null;
		//JDBC연결
		try {
			if(findById(dto.getStudentId())==null) {
				throw new RecordNotFoundException(dto.getStudentId()+"에 해당하는 학생은 없습니다.");
			}
			conn = JdbcUtil.connect();
			// 4. SQL작성
			String sql="UPDATE SCORE SET STUDENT_NAME = ?, STUDENT_CLASS = ?, MIDTERM = ?, HW = ?, FINALS = ?, ATTEND = ?, TOTAL_SCORE = ?";
			sql+="WHERE STUDENT_ID = ?";
			// 5. Statement작성
			pstmt = conn.prepareStatement(sql);
			// 6. 데이터 설정
			pstmt.setString(1, dto.getStudentName());
			pstmt.setString(2, dto.getStudentClass());
			pstmt.setInt(3, dto.getMidTerm());
			pstmt.setInt(4, dto.getHw());
			pstmt.setInt(5, dto.getFinals());
			pstmt.setInt(6, dto.getAttend());
			pstmt.setInt(7, dto.getTotalScore());
			pstmt.setString(8, dto.getStudentId());
			// 7. SQL전송, 결과수신
			pstmt.executeUpdate(); 	
		}
		catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}finally {
			JdbcUtil.close(pstmt, conn);
		}
	}
	//학생의 성적 삭제 (교수만 가능)
	@Override
	public void delete(String id) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt =null;
		//JDBC연결
		try {
			if(findById(id)==null) {
				throw new RecordNotFoundException(id+"에 해당하는 학생은 없습니다");
			}
			conn = JdbcUtil.connect();
			// 4. SQL작성
			String sql="delete from SCORE ";
			sql+="WHERE STUDENT_ID = ?";
			// 5. Statement작성
			pstmt = conn.prepareStatement(sql);
			// 6. 데이터 설정
			pstmt.setString(1, id);
			// 7. SQL전송, 결과수신
			pstmt.executeUpdate(); 	
		}
		catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}finally {
			JdbcUtil.close(pstmt, conn);
		}
	}
	
	//학생의 수 반환 (교수만 가능)
	@Override
	public int count() throws SQLException {
		int count = 0;
		// DBMS 연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try { 
			conn=JdbcUtil.connect();
			// 3. SQL 작성
			String sql="SELECT count(*) FROM SCORE"; 
			// 4. Statement 생성
			pstmt = conn.prepareStatement(sql);
			// 6. SQL 전송 및 결과 수신,
			ResultSet rs=pstmt.executeQuery(); 
			rs.next();
			count=rs.getInt(1);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e); 
		}finally {
			// DBMS해제
			JdbcUtil.close(pstmt,conn);
		}
		return count;
	}
	
	//전체 학생 성적 테이블 목록 반환 (교수만 가능)
	@Override
	public List<ScoreDto> list() throws SQLException {
		List<ScoreDto> scorelist= new ArrayList<ScoreDto>();
		//DBMS 연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try { 
			conn=JdbcUtil.connect();
			// 3. SQL 작성
			String sql="SELECT STUDENT_ID, STUDENT_NAME, STUDENT_CLASS, MIDTERM, HW, FINALS, ATTEND, TOTAL_SCORE, rownum as RANK FROM (SELECT * FROM SCORE Order By TOTAL_SCORE DESC)"; 
			// 4. Statement 생성
			pstmt = conn.prepareStatement(sql);
			// 6. SQL 전송 및 결과 수신,
			ResultSet rs=pstmt.executeQuery(); 
			while(rs.next()) {
				String studentId=rs.getString("STUDENT_ID");
				String studentName=rs.getString("STUDENT_NAME");
				String studentClass=rs.getString("STUDENT_CLASS");
				int midterm=rs.getInt("MIDTERM");
				int hw=rs.getInt("HW");
				int finals=rs.getInt("FINALS");
				int attend=rs.getInt("ATTEND");
				int totalScore=rs.getInt("TOTAL_SCORE");
				int rank=rs.getInt("RANK");
				scorelist.add(new ScoreDto(studentId,studentName,studentClass,midterm,hw,  finals, attend, totalScore, rank)); 
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e); 
		}finally {
			// DBMS해제
			JdbcUtil.close(pstmt,conn);
		}
		return scorelist;
	}
	
	// 학생의 성적을 조회하는 테이블(교수 및 학생 접속 가능)
	@Override
	public ScoreDto findById(String id) throws SQLException {
		ScoreDto scoreDto=null;
		//DBMS 연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try { 
			conn=JdbcUtil.connect();
			// 3. SQL 작성
			String sql="select * FROM (SELECT STUDENT_ID, STUDENT_NAME, STUDENT_CLASS, MIDTERM, HW, FINALS, ATTEND, TOTAL_SCORE, rownum as RANK FROM SCORE Order By TOTAL_SCORE DESC) ";
			sql+=" where student_id = ?";
			// 4. Statement 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); 
			// 6. SQL 전송 및 결과 수신,
			ResultSet rs=pstmt.executeQuery(); 
			if(rs.next()) {// 조회결과가 있다.
				String studentName=rs.getString("STUDENT_NAME");
				String studentClass=rs.getString("STUDENT_CLASS");
				int midterm=rs.getInt("MIDTERM");
				int hw=rs.getInt("HW");
				int finals=rs.getInt("FINALS");
				int attend=rs.getInt("ATTEND");
				int totalScore=rs.getInt("TOTAL_SCORE");
				int rank=rs.getInt("RANK");
				scoreDto=new ScoreDto(id,studentName,studentClass,midterm,hw, finals, attend, totalScore, rank);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e); 
		}finally {
			// DBMS해제
			JdbcUtil.close(pstmt,conn);
		}
		return scoreDto;
	}

}
