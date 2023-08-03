package Student.dao;

import java.sql.SQLException;
import java.util.List;

import Main.DuplicatedIdException;
import Student.dto.StudentDto;

public interface StudentDao {
	
	  public int count() throws SQLException;
	  //목록
	  public List<StudentDto> list() throws SQLException;
	  //검색
	  public StudentDto findById(String id) throws SQLException;
	  
	  public void add(StudentDto studentDto) throws SQLException, DuplicatedIdException;
	

}
