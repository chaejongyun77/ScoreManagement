package Main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ScoreManagement.util.JdbcUtil;
import Main.*;
import Student.dao.HwLogDao;

import Student.dto.HwLogDto;
import Student.dto.*;

public class HwLogDaoImpl implements HwLogDao {
	@Override //학생이 사용
	public void add(HwLogDto dto) throws SQLException, DuplicatedIdException {
		Connection conn = null;
		PreparedStatement pstmt =null;
		//JDBC연결
		try {
			if(findById(dto.getStudentId())!=null) {
				throw new DuplicatedIdException(dto.getStudentId()+"에 해당하는 학생은 이미 제출하였습니다.");
			}
			conn = JdbcUtil.connect();
			// 4. SQL작성
			String sql="INSERT INTO HWLOG(STUDENT_ID, STUDENT_NAME, UPLOAD_DATE, FILE_NAME)";
			sql+="VALUES(?, ?, sysdate, ?)";
			// 5. Statement작성
			pstmt = conn.prepareStatement(sql);
			// 6. 데이터 설정
			pstmt.setString(1, dto.getStudentId());
			pstmt.setString(2, dto.getStudentName());
			pstmt.setString(3, dto.getFileName());
			// 7. SQL전송, 결과수신
			pstmt.executeUpdate(); 	
		}catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}finally {
			JdbcUtil.close(pstmt, conn);
		}
	}


	@Override	//학생이 사용
	public void delete(String id) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt =null;
		//JDBC연결
		try {
			if(findById(id)==null) {
				throw new RecordNotFoundException(id+"학생에 해당하는 과제는 없습니다.");
			}
			conn = JdbcUtil.connect();
			// 4. SQL작성
			String sql="delete from HWLOG ";
			sql+="WHERE STUDENT_ID = ?";
			// 5. Statement작성
			pstmt = conn.prepareStatement(sql);
			// 6. 데이터 설정
			pstmt.setString(1, id);
			// 7. SQL전송, 결과수신
			int count=pstmt.executeUpdate(); 	
		}
		catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}finally {
			JdbcUtil.close(pstmt, conn);
		}
	}

	@Override // 선생이 사용
	public int count() throws SQLException {
		int count = 0;
		// DBMS 연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try { 
			conn=JdbcUtil.connect();
			// 3. SQL 작성
			String sql="SELECT count(*) FROM HWLOG"; 
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

	@Override // 선생이 사용
	public List<HwLogDto> list() throws SQLException {
		List<HwLogDto> hwlist= new ArrayList<HwLogDto>();
		//DBMS 연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try { 
			conn=JdbcUtil.connect();
			// 3. SQL 작성
			String sql="SELECT * FROM HWLOG"; 
			// 4. Statement 생성
			pstmt = conn.prepareStatement(sql);
			// 6. SQL 전송 및 결과 수신,
			ResultSet rs=pstmt.executeQuery(); 
			while(rs.next()) {
				String studentId=rs.getString("STUDENT_ID");
				String studentName=rs.getString("STUDENT_NAME");
				Date uploadDate=rs.getDate("UPLOAD_DATE");
				String fileName=rs.getString("FILE_NAME");
				
				hwlist.add(new HwLogDto(studentId,studentName,fileName,uploadDate)); 
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e); 
		}finally {
			JdbcUtil.close(pstmt,conn);
		}
		return hwlist;
	}

	@Override // 선생이 사용
	public HwLogDto findById(String id) throws SQLException {
		HwLogDto hwDto=null;
		//DBMS 연결
		Connection conn=null;
		PreparedStatement pstmt=null;
		try { 
			conn=JdbcUtil.connect();
			// 3. SQL 작성
			String sql="SELECT * FROM HWLOG WHERE STUDENT_ID = ?"; 
			// 4. Statement 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); 
			// 6. SQL 전송 및 결과 수신,
			ResultSet rs=pstmt.executeQuery(); 
			if(rs.next()) {
				String studentName=rs.getString("STUDENT_NAME");
				Date uploadDate=rs.getDate("UPLOAD_DATE");
				String fileName=rs.getString("FILE_NAME");
				hwDto=new HwLogDto(id,studentName,fileName,uploadDate);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e); 
		}finally {
			// DBMS해제
			JdbcUtil.close(pstmt,conn);
		}
		return hwDto;
	}
}
