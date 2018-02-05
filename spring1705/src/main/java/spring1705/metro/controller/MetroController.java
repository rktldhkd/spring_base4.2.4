package spring1705.metro.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring1705.common.common.CommandMap;
import spring1705.metro.service.MetroService;

@Controller
public class MetroController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "metroService")
	private MetroService metroService;
	
	@RequestMapping(value = "/metro/openMetroView")
	public ModelAndView openMetroView() throws Exception {
		ModelAndView mv = new ModelAndView("/metro/metroView");

		List<String> resultList = metroService.selectMetroLines();
	     
	    /*Iterator it = resultList.iterator();
        Entry<String,Object> entry = null;
        while(it.hasNext())
        {
               log.debug( it.next());
               //log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
        }//while
*/
		
	    mv.addObject("list", resultList);

		return mv;
	}//openBoardList
	
	
	/**
	 * ajax, json 사용 시, @ResponseBody 애노테이션 같이 사용 요망. Unknown return value type [java.lang.Integer] 등의 에러 발생방지.
	 * @return The number of duplicated ID
	 */
	@ResponseBody
	@RequestMapping(value="/metro/getStations", method=RequestMethod.POST)
	public List<Map<String,Object>> getStations(CommandMap commandMap) throws Exception {
		List<Map<String,Object>> result = metroService.selectStaions(commandMap.getMap());

		return result;
	}//openBoardList
}//class
