package spring1705.metro.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spring1705.metro.dao.MetroDAO;

@Service("metroService")
public class MetroServiceImpl implements MetroService{
	@Resource(name = "metroDAO")
	private MetroDAO metroDAO;
	
	@Override
	public List selectMetroLines() throws Exception {
		List<String> result = metroDAO.selectMetroLines(); 
		return result;
	}//selectMetroLines()

	@Override
	public List<Map<String,Object>> selectStaions(Map<String, Object> map) throws Exception {
		return (List<Map<String,Object>>)metroDAO.selectStations(map);
	}
	
}//class
