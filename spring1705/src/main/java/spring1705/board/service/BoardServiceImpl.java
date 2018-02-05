package spring1705.board.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring1705.board.dao.BoardDAO;
import spring1705.common.util.FileUtils;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	@Override
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		return boardDAO.selectBoardList(map);
	}

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		boardDAO.insertBoard(map);
		
		 MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		    MultipartFile multipartFile = null;
		    
		    //업로드된 파일 정보 확인 시작
		    while(iterator.hasNext()){
		        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
		        if(multipartFile.isEmpty() == false){
		            log.debug("------------- file start -------------");
		            log.debug("name : "+multipartFile.getName());
		            log.debug("filename : "+multipartFile.getOriginalFilename());
		            log.debug("size : "+multipartFile.getSize() + " byte");
		            log.debug("-------------- file end --------------\n");
		        }
		    }//while
		    //업로드된 파일 정보 확인 끝
		    
		    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
	        for(int i=0, size=list.size(); i<size; i++){
	            boardDAO.insertFile(list.get(i));
	        }
	}//insertBoard

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		boardDAO.updateHitCnt(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> boardInfMap = boardDAO.selectBoardDetail(map);
		resultMap.put("map", boardInfMap); //게시글 정보
		
		List<Map<String, Object>> list = boardDAO.selectFileList(map);
		resultMap.put("list", list); //첨부파일 목록
		
		return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		boardDAO.updateBoard(map);
		
		boardDAO.deleteFileList(map);//일단 게시글의 첨부파일 전부 삭제
		
		//request에 담겨있는 update된 첨부파일 리스트 정보.
		//parseUpdateFileInfo = request에 담겨있는 파일 정보를 list로 변환한다.
	    List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
	    Map<String,Object> tempMap = null;
	    for(int i=0, size=list.size(); i<size; i++){
	        tempMap = list.get(i);
	        if(tempMap.get("IS_NEW").equals("Y")){
	            boardDAO.insertFile(tempMap);
	        }
	        else{
	            boardDAO.updateFile(tempMap);
	        }
	    }
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		boardDAO.deleteBoard(map);
	}
	
}// class
