package Main;

import java.sql.SQLException;
import java.util.List;

import Main.*;
import Student.dto.*;
import Student.dao.*;

public class scoreDaoImplExam {
	public static ScoreDao scoreDao = new ScoreDaoImpl();
	public static void main(String[] args) {
//		count();
		add();
//		delete();
//		update();
//		find();
		//list();
	}

	private static void list() {
		try {
			List<ScoreDto> list=scoreDao.list();
			System.out.println("** 전체 조회 **");
			for(ScoreDto dto : list) {
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} 
	}

	private static void find() {
		try {
			ScoreDto dto = scoreDao.findById("kb2445");
			if(dto == null) {
				System.out.println("해당 학생은 없습니다.");
				return;
			}
			System.out.println(dto);
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		}
	}

	private static void delete() {
		try {
			scoreDao.delete("kb2443");
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
			int count =scoreDao.count();
			System.out.println("등록 갯수:"+count);
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} 
	}
	
	private static void update() {
		try {
			scoreDao.update(new ScoreDto("kb2441","고길동","5반", 80, 70, 60, 70, 250, 0));
			System.out.println("업데이트 성공");
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			System.out.println("해당ID가 없습니다.");
			e.printStackTrace();
		}		
	}

	private static void add() {
		try {
			scoreDao.add(new ScoreDto("kb2443","최길동","1반", 80, 70, 60, 50, 250, 0));
			System.out.println("등록성공");
		} catch (SQLException e) {
			System.out.println("SQL오류발생");
			e.printStackTrace();
		} catch (DuplicatedIdException e) {
			System.out.println("해당학생은 이미 존재합니다.");
		}
	}
}
