package Student.dao;

import java.sql.SQLException;
import java.util.List;

import Main.DuplicatedIdException;
import Main.RecordNotFoundException;
import Student.dto.*;;

public interface HwLogDao {
	//등록
	public void add(HwLogDto dto) throws SQLException, DuplicatedIdException;
	//삭제
	public void delete(String id) throws SQLException, RecordNotFoundException;
	//성적을 부여해야 하는 학생수
	public int count() throws SQLException;
	//목록
	public List<HwLogDto> list() throws SQLException;
	//id검색
	public HwLogDto findById(String id) throws SQLException;
}
