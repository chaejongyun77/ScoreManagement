package Student.dao;

import java.sql.SQLException;
import java.util.List;

import Main.DuplicatedIdException;
import Student.dto.TeacherDto;


public interface TeacherDao {
	  //선생추가
	  public void add(TeacherDto dto) throws SQLException, DuplicatedIdException;	
	  
	  public int count() throws SQLException;
	  //목록
	  public List<TeacherDto> list() throws SQLException;
	  //검색
	  public TeacherDto findById(String id) throws SQLException;
}
