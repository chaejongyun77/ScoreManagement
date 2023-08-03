package Main;

import java.sql.SQLException;
import java.util.List;

import Student.dao.AttendLogDao;
import Main.*;
import Student.dto.AttendLogDto;

public class AttendLogTest {
	static AttendLogDao attendLogDao = new AttendLogDaoImpl();
	public static void main(String[] args) {
//		add();
//		update();
//		find();
//		delete();
//		listAll();
//		list();
	}
	
	private static void add() {
		try {
			attendLogDao.add(new AttendLogDto(0, "user01", "유저01", null, null, null));
			System.out.println("등록 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		}
	}
	
	private static void update() {
		String hour = "08";
		String min = "59";
		String time = hour + ":" + min + ":00";
		
		try {
			attendLogDao.update(new AttendLogDto(8, null, null, null, time, null));
			System.out.println("수정 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			System.err.println("해당 정보를 찾을 수 없음");
		}
	}
	
	private static void delete() {
		try {
			attendLogDao.delete(8);
			System.out.println("삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");;
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			System.err.println("정보를 찾을 수 없습니다.");
		}
	}
	
	private static void find() {
		AttendLogDto dto = null;
		try {
			dto = attendLogDao.findBySeq(1);
			if(dto == null) throw new RecordNotFoundException();
			System.out.println(dto);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			System.err.println("해당 정보를 찾을 수 없음");
		}
	}
	
	private static void list() {
		String id = "user01";
		try {
			List<AttendLogDto> list = attendLogDao.list(id);
			if(list.size() == 0) throw new RecordNotFoundException();
			System.out.println("[" + id + "님의 출결 목록 조회]");
			for(AttendLogDto dto : list) {
				System.out.println(dto);
			}
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
			List<AttendLogDto> list = attendLogDao.listAll();
			System.out.println("[전체 출결 목록 조회]");
			for(AttendLogDto dto : list) {
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBMS 오류 발생");
		}
	}
}
