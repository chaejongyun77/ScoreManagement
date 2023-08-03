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

public class StudentDaoExam {
	public static void main(String[] args) {
		list();
		add();
	}

	private static void list() {
		   StudentDao studentDao = new StudentDaoImpl();
		      try {
		           List<StudentDto> list = studentDao.list();
		            System.out.println("**전체 조회**");
		            for (StudentDto dto: list) {
		            	System.out.println(dto);
		            }
		        } catch (SQLException e) {
		            System.out.println("SQL오류발생");
		            e.printStackTrace();
		        }
				
			}
	
	private static void add() {
		
		StudentDao studentDao = new StudentDaoImpl();
        try {
            studentDao.add(new StudentDto("777", "홍길똥", "4675","12121","A반"));
            System.out.println("등록성공");
        } catch (SQLException e) {
            System.out.println("SQL오류발생");
            e.printStackTrace();
        } catch (DuplicatedIdException e) {
            System.out.println("ID가 중복됩니다");
        }
		
	

	}
}
