package Main;

import java.sql.SQLException;
import java.util.List;
import Main.*;
import Student.dto.*;
import Student.dao.*;

public class HwLogDaolmplExam {
	public static HwLogDao hwLogDao = new HwLogDaoImpl();
	public static void main(String[] args) {
//		count();
		add();
//		delete();
//		find();
//		list();
	}

	private static void list() {
		try {
			List<HwLogDto> list=hwLogDao.list();
			System.out.println("** 전체 조회 **");
			for(HwLogDto dto : list) {
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} 
	}

	private static void find() {
		try {
			HwLogDto dto = hwLogDao.findById("kb2441");
			if(dto == null) {
				System.out.println("해당 학생은 없습니다.");
				return;
			}
			System.out.println(dto);
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
		}
	}

	private static void delete() {
		try {
			hwLogDao.delete("kb2443");
			System.out.println("삭제 성공");
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			System.out.println("해당ID가 없습니다.");
		}
		
	}

	private static void count() {
		try {
			int count =hwLogDao.count();
			System.out.println("등록 갯수:"+count);
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} 
	}

	private static void add() {
		try {
			hwLogDao.add(new HwLogDto("cjy205", "채종윤","과제2.txt", null));
			System.out.println("등록성공");
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} catch (DuplicatedIdException e) {
			System.out.println("해당 학생은 이미 제출하였습니다.");
		}
	}
}
