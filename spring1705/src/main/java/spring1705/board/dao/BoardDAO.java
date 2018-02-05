package spring1705.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring1705.common.common.CommandMap;
import spring1705.common.dao.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectPagingList("board.selectBoardList", map);
	}//selectBoardList

	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("board.insertBoard", map);
	}//insetBoard

	public void updateHitCnt(Map<String, Object> map) throws Exception{
		update("board.updateHitCnt", map);
	}//updateHitCnt

	//하나의 레코드(행)만 조회한다.
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("board.selectBoardDetail", map);
	}//selectBoardDetail

	public void updateBoard(Map<String, Object> map) throws Exception{
		update("board.updateBoard", map);
	}//updateBoard

	public void deleteBoard(Map<String, Object> map) throws Exception{
		//삭제여부(DEL_BG) 필드만 업데이트 한다.
		//실제 삭제 x.
		update("board.deleteBoard", map);
	}//deleteBoard

	public void insertFile(Map<String, Object> map) throws Exception{
		insert("board.insertFile", map);
	}//insertFile

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("board.selectFileList", map);
	}//selectFileList

	public void deleteFileList(Map<String, Object> map) throws Exception{
	    update("board.deleteFileList", map);
	}//deleteFileList
	 
	public void updateFile(Map<String, Object> map) throws Exception{
	    update("board.updateFile", map);
	}//updateFile
	
}//class
