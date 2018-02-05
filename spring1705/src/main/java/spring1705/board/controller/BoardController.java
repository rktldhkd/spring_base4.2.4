package spring1705.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import spring1705.board.service.BoardService;
import spring1705.common.common.CommandMap;

@Controller
public class BoardController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/board/openBoardList")
	public ModelAndView openBoardList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardList");

		log.debug("컨트롤러 currentPageNo 값 : [" + commandMap.get("currentPageNo") + "]");
		
		Map<String,Object> resultMap = boardService.selectBoardList(commandMap.getMap());
	     
	    mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
	    mv.addObject("list", resultMap.get("result"));

		return mv;
	}//openBoardList
	
	@RequestMapping(value="/board/openBoardWrite")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/board/boardWrite");
	     
	    return mv;
	}//openBoardWrite
	
	@RequestMapping(value="/board/openBoardDetail")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		Map<String, Object> map = boardService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map")); //게시글 정보
		mv.addObject("list", map.get("list")); //첨부파일목록
		
		return mv;
	}//openBoardDetail
	
	@RequestMapping(value="/board/insertBoard")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		//forward : URL이 유지되면서 a->b로 이동된다. 넘어가는 파라메타값유지
		//redirect : URL이 변경되면서 a->b로 이동된다. 넘어가는 파라메타 초기화
	    ModelAndView mv = new ModelAndView("redirect:/board/openBoardList");
	     
	    boardService.insertBoard(commandMap.getMap(), request);
	     
	    return mv;
	}//insertBoard
	
	@RequestMapping(value="/board/openBoardUpdate")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/board/boardUpdate");
	     
	    Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap());
	    mv.addObject("map", map.get("map"));
	    mv.addObject("list", map.get("list"));
	     
	    return mv;
	}
	 
	@RequestMapping(value="/board/updateBoard")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/board/openBoardDetail");
	     
	    boardService.updateBoard(commandMap.getMap(), request);
	     
	    mv.addObject("IDX", commandMap.get("IDX"));
	    return mv;
	}
	
	@RequestMapping(value="/board/deleteBoard")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/board/openBoardList");
	     
	    boardService.deleteBoard(commandMap.getMap());
	     
	    return mv;
	}
}// class
