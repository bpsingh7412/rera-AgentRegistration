package in.gov.wf.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtility {
	private Map<String, Object> map;

	private MapUtility(Map<String, Object> map) {
		this.map = map;
	}

	private MapUtility(Object obj) {
		if (obj instanceof MapUtility) {
			this.map = ((MapUtility) obj).getMap();
		} else
			this.map = (Map<String, Object>) obj;
	}

	public String getStringValue(String key) {
		if (map != null && map.containsKey(key) && map.get(key) != null) {
			return this.map.get(key).toString();
		} else {
			return "";
		}
	}

	public Object getValue(String key) {
		return this.map.get(key);
	}

	public MapUtility getMap(String key) {
		if (this.map.containsKey(key)) {
			return new MapUtility(this.map.get(key));
		} else {
			return null;
		}
	}

	public boolean containsKey(String key) {
		return this.map.containsKey(key);
	}

	public Set<String> keySet() {
		return this.map.keySet();
	}

	public void putValue(String key, Object value) {
		this.map.put(key, value);
	}

	public void remove(String key) {
		this.map.remove(key);
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	public static MapUtility getInstance() {
		return new MapUtility(new HashMap<String, Object>());
	}

	public static MapUtility getInstance(Object map) {
		if (map instanceof Map) {
			return new MapUtility(map);
		} else if (map instanceof MapUtility) {
			return (MapUtility) map;
		}
		return null;
	}
}
