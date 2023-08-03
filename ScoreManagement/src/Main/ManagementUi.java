package Main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Student.dao.TeacherDao;
import Student.dto.TeacherDto;

import Student.dto.*;
public class ManagementUi {
	private Scanner sc;
	private ManagementService mmSvc = new ManagementServiceImpl();
	
	public static void main(String[] args) {
	new ManagementUi().go();
}

	private void go() {
		init();//필요 변수 초기화
	
		while (true) {
		
			mainMenu();// 메인메뉴출력
	}
}
	private void mainMenu() {
		System.out.println("회원 종류: (1)학생 (2)교수 (3)종료");
		System.out.println("회원 선택: ");
		int menu = Integer.parseInt(sc.nextLine());
		if(menu==1) {
			goStduent();
			
		}else if(menu==2) {
			goTeacher();
		}else if(menu==3) {
			System.exit(0);
		}
		
	}
	
	private void goStduent() {
		
		System.out.println("아이디를 입력하세요: ");
		String inputId = sc.nextLine();
		
		try {
			ScoreDto dto = mmSvc.readStudent(inputId); //성적 테이블에 학생 ID까 존재한다면 
			System.out.println("작업 종류: (1)출결 (2) 성적조회 ");
			System.out.println("작업 선택");
			int menu = Integer.parseInt(sc.nextLine());
			
				//if(menu==1) { //출결
				//	출결();
				
		//	}   
				 if(menu==2) { //성적조회

					System.out.println("** 상세보기 **");
					System.out.println("학생ID: "+dto.getStudentId());
					System.out.println("이름: "+dto.getStudentName());
					System.out.println("반: "+dto.getStudentClass());
					System.out.println("중간: "+dto.getMidTerm());
					System.out.println("과제: "+dto.getHw());
					System.out.println("기말: "+dto.getFinals());
					System.out.println("출석: "+dto.getAttend());
					System.out.println("총점: "+dto.getTotalScore());			
				}
			
				 }catch (ManagementException e) {
			System.out.println("---  서버 오류입니다 ---");
		} catch (RecordNotFoundException e) {
			}

	}
	private void goTeacher() {
		System.out.println("아이디를 입력하세요: ");
		String inputId = sc.nextLine();
		
		try {
			TeacherDto dto = mmSvc.readTeacher(inputId);
//			dto.getTeacherId();
//			dto.getTeacherPw();
//			dto.getTeacherName();
//			dto.getTeacherClass();
			
			System.out.println("작업 선택: (1)학생조회 (2)성적조회 (3)성적수정 (4)출석관리");	
			int menu = Integer.parseInt(sc.nextLine());
			if(menu ==1) {
				StudentList();
			}
			else if(menu ==2){
				ScoreList();
			}
//			else if(menu ==3) {
//				성적수정();
//			}else if(menu ==4) {
//				출선관리();
//			}
			
			
		} catch (ManagementException e) {
			System.out.println("---  서버 오류입니다 ---");
		} catch (RecordNotFoundException e) {
		}
		
	}
	

	private void ScoreList() {
		System.out.println("[학생들 성적]");
		System.out.println("아이디 ---- 이름 ----- 반 ----- 중간 ----과제----기말---출석----총점");
		List<ScoreDto> list;
		try {
			list = mmSvc.listScore();
			for (ScoreDto dto : list) {
				System.out.println(  
							dto.getStudentId()+"        "+
							dto.getStudentName()+"    "+
							dto.getStudentClass()+"    "+
							dto.getMidTerm()+"    "+
							dto.getHw()+"     "+
							dto.getFinals()+"     "+
							dto.getAttend()+"      "+
							dto.getTotalScore());
			}
		} catch (ManagementException e) {
			System.out.println("*** 서버에 오류가 발생했습니다 ***");
		}
		
	}

	private void StudentList() {
		System.out.println("[학생 목록]");
		System.out.println("아이디 -------- 이름 ------- 비밀번호 ----- 작성일 -----");
		List<StudentDto> list;
		try {
			list = mmSvc.listStudent();
			for (StudentDto dto : list) {
				System.out.println(  
							dto.getStudentId()+"        "+
							dto.getStudentName()+"    "+
							dto.getStudentPw() +"    "+
							dto.getStudentTel());
							dto.getStudentClass();
			}
		} catch (ManagementException e) {
			System.out.println("*** 서버에 오류가 발생했습니다 ***");
		}
		
	}

	private void init() {
	sc = new Scanner(System.in);
	
}
}
