package in.gov.wf.util;

import java.util.Iterator;
import java.util.Map;

public class MapRenderUtility {

	private MapUtility mapObject;
	private Iterator<String> objectItr;
	private String currKey;

	private MapUtility currObject;

	public MapRenderUtility(Map<String, Object> formsMap) {
		this.mapObject = MapUtility.getInstance(formsMap);
		if (this.mapObject != null) {
			this.objectItr = this.mapObject.keySet().iterator();
		}
	}

	public MapRenderUtility(Object object) {
		this.mapObject = (MapUtility) object;
		if (this.mapObject != null) {
			this.objectItr = this.mapObject.keySet().iterator();
		} else {
			System.out.println("NULL VaLUE;;;;;;");
		}
	}

	public boolean isNext() {
		if (objectItr != null) {
			boolean flag = objectItr.hasNext();
			if (flag) {
				this.currKey = objectItr.next();

				this.currObject = this.mapObject.getMap(this.currKey);
			} else {
				this.currKey = null;
				this.currObject = null;
			}
			return flag;
		} else {
			return false;
		}
	}

	public String nextKey() {
		return this.currKey;
	}

	public MapUtility next() {
		return this.currObject;
	}

	public String getValue(String key) {
		return this.currObject.getStringValue(key);
	}

	public Object get(String key) {
		return this.currObject.getValue(key);
	}

	public MapUtility getMap(String key) {
		return MapUtility.getInstance(this.currObject.getValue(key));
	}

	public MapRenderUtility getRenderObject(String key) {
		return new MapRenderUtility(this.currObject.getMap(key));
	}

}
