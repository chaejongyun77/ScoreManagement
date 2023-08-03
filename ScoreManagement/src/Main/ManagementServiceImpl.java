package Main;

import java.sql.SQLException;
import java.util.List;

import Student.dao.StudentDao;
import Student.dao.TeacherDao;
import Student.dto.ScoreDto;
import Student.dto.StudentDto;
import Student.dto.TeacherDto;
import Student.dao.*;

public class ManagementServiceImpl implements ManagementService {

	
	private StudentDao studentDao = new StudentDaoImpl(); // 
	private TeacherDao teacherDao = new TeacherDaoImpl();
	private ScoreDao scoreDao = new ScoreDaoImpl();
	
//	@Override
//	public List<StudentDto> list() throws ManagementException { // 학생 목록 불러오기
//		List<StudentDto> list = null;
//		try {
//			list = studentDao.list();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new ManagementException(e.getMessage());
//		}
//		return list;
//	}
	
	

	public List<TeacherDto> list() throws ManagementException {
		List<TeacherDto> list = null;
		try {
			list = teacherDao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e.getMessage());
		}
		return list;
	}
	public List<StudentDto> listStudent() throws ManagementException {
		List<StudentDto> list = null;
		try {
			list = studentDao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e.getMessage());
		}
		return list;
	}
	
	public List<ScoreDto> listScore() throws ManagementException {
		List<ScoreDto> list = null;
		try {
			list = scoreDao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e.getMessage());
		}
		return list;
	}

	@Override
	public TeacherDto readTeacher(String id) throws ManagementException, RecordNotFoundException {
		TeacherDto dto = null;
		try {
			dto = teacherDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e.getMessage());
		}
		return dto;
	}

	@Override
	public ScoreDto readStudent(String id) throws ManagementException, RecordNotFoundException {
		ScoreDto dto = null;
		try {
			dto = scoreDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e.getMessage());
		}
		return dto;
	}
	@Override
	public ScoreDto readScore(String id) throws ManagementException, RecordNotFoundException {
		ScoreDto dto = null;
		try {
			dto = scoreDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e.getMessage());
		}
		return dto;
	}
	

}
