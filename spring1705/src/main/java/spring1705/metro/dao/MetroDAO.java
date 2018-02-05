package spring1705.metro.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import spring1705.common.dao.AbstractDAO;

@Repository("metroDAO")
public class MetroDAO extends AbstractDAO{
	Logger log = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public List<String> selectMetroLines() {
		return selectList("metro.selectMetroLines");
	}//selectMetroLines()

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectStations(Map<String, Object> map) {
		//log.debug("containsKey : "+map.containsKey("LINE_NUM"));
		//log.debug("parameter : "+map.get("LINE_NUM"));
		//log.debug("Is data type String ? : " + map.get("LINE_NUM") instanceof String);
		return (List<Map<String, Object>>)selectList("metro.selectStations", map);
	}
	
}//class
