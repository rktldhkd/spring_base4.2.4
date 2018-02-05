package spring1705.metro.service;

import java.util.List;
import java.util.Map;

public interface MetroService{
	List<String> selectMetroLines() throws Exception;
	List<Map<String,Object>> selectStaions(Map<String,Object> map) throws Exception;
}//interface
