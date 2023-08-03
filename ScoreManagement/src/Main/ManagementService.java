package Main;

import java.util.List;

import Main.*;
import Student.dto.*;

public interface ManagementService {
		
	// public List<학생성적Dto> list() throws ManagementException; // 학생 목록 불러오기
	
	public TeacherDto readTeacher(String id) throws ManagementException,RecordNotFoundException;  //교수 정보 읽기
	
	public ScoreDto readScore(String id) throws ManagementException,RecordNotFoundException;
	
	public List<TeacherDto> list() throws ManagementException; //test

	public List<StudentDto> listStudent() throws ManagementException; //test
	
	public ScoreDto readStudent(String id) throws ManagementException,RecordNotFoundException;

	public List<ScoreDto> listScore() throws ManagementException;



}
