package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ScoreManagement.util.JdbcUtil;
import Student.dao.StudentDao;
import Student.dao.TeacherDao;
import Student.dto.StudentDto;
import Student.dto.TeacherDto;

public class TeacherDaoExam {

	public static void main(String[] args) {
		add();
		list();
	}
	
	private static void add() {
		
		TeacherDao teacherDao = new TeacherDaoImpl();
        try {
            teacherDao.add(new TeacherDto("222", "4675", "김길똥", "A반"));
            System.out.println("등록성공");
        } catch (SQLException e) {
            System.out.println("SQL오류발생");
            e.printStackTrace();
        } catch (DuplicatedIdException e) {
            System.out.println("ID가 중복됩니다");
        }
		
	}

	private static void list() {
		   TeacherDao teacherDao = new TeacherDaoImpl();
		      try {
		           List<TeacherDto> list = teacherDao.list();
		            System.out.println("**전체 조회**");
		            for (TeacherDto dto: list) {
		            	System.out.println(dto);
		            }
		        } catch (SQLException e) {
		            System.out.println("SQL오류발생");
		            e.printStackTrace();
		        }
				
			}
}
