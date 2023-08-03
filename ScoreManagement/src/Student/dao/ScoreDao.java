package Student.dao;


import java.sql.SQLException;
import java.util.List;

import Main.DuplicatedIdException;
import Main.RecordNotFoundException;
import Student.dto.*;

public interface ScoreDao {
	//등록
	public void add(ScoreDto dto) throws SQLException, DuplicatedIdException;
	//수정
	public void update(ScoreDto dto) throws SQLException, RecordNotFoundException;
	//삭제
	public void delete(String id) throws SQLException, RecordNotFoundException;
	//갯수
	public int count() throws SQLException;
	//목록
	public List<ScoreDto> list() throws SQLException;
	//id검색
	public ScoreDto findById(String id) throws SQLException;
}
