package Main;

import java.sql.SQLException;
import java.util.List;

import Student.dao.AttendScoreDao;
import Main.*;

import Student.dto.AttendScoreDto;

public class AttendScoreTest {
	static AttendScoreDao attendScoreDao = new AttendScoreDaoImpl();
	public static void main(String[] args) {
//		studentList();
//		init();
		connect();
//		listAll();
//		list();
	}
	
	private static void list() {
		String id = "user01";
		try {
			AttendScoreDto dto = attendScoreDao.list(id);
			if(dto == null) throw new RecordNotFoundException();
			System.out.println("[" + id + "님의 출결점수 조회]");
			System.out.println(dto);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			System.err.println("정보를 찾을 수 없습니다.");
		}
	}

	private static void listAll() {
		try {
			List<AttendScoreDto> list = attendScoreDao.listAll();
			System.out.println("[전체 출결점수 조회]");
			for(AttendScoreDto dto : list) {
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		}
	}

	private static void init() {
		try {
			attendScoreDao.init();
			System.out.println("초기화 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		}
	}

	private static void connect() {
		try {
			attendScoreDao.connect(3);
			System.out.println("출결정보 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		}
	}

	private static void studentList() {
		try {
			attendScoreDao.studentList();
			System.out.println("학생정보 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		}
	}
}
