package in.gov.wf.xml.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrServicesUtiliy {

	private Element ele;
	private MapUtility serviceMap;

	public PrServicesUtiliy(MapUtility serviceMap, Element ele) {
		this.serviceMap = serviceMap;
		this.ele = ele;
	}

	public PrServicesUtiliy(Element ele) {
		;
		this.ele = ele;
	}

	public MapUtility read(MapUtility serviceMap) {
		this.serviceMap = serviceMap;
		MapUtility smap = null;
		List<Element> slist = ele.elements(ProcessConstant.PR_SERVICE);
		for (Element serv : slist) {
			String stype = serv.attributeValue(ProcessConstant.PR_SERVICE_TYPE);
			smap = MapUtility.getInstance();
			smap.putValue(ProcessConstant.PR_SERVICE_NAME, serv.element(ProcessConstant.PR_SERVICE_NAME).getText());
			smap.putValue(ProcessConstant.PR_SERVICE_DESC, serv.element(ProcessConstant.PR_SERVICE_DESC).getText());
			smap.putValue(ProcessConstant.PR_SERVICE_CATEGORY,
					serv.element(ProcessConstant.PR_SERVICE_CATEGORY).getText());
			smap.putValue(ProcessConstant.PR_SERVICE_TYPE, stype);
			if (stype.equals(ProcessConstant.PR_JAVASERVICE_TYPE)) {
				smap.putValue(ProcessConstant.PR_SERVICE_CLASS_NAME,
						serv.element(ProcessConstant.PR_SERVICE_CLASS_NAME).getText());
				smap.putValue(ProcessConstant.PR_SERVICE_METHOD_NAME,
						serv.element(ProcessConstant.PR_SERVICE_METHOD_NAME).getText());
			} else if (stype.equals(ProcessConstant.PR_DBSERVICE_TYPE)) {
				// smap.putValue(ProcessConstant.PR_SERVICE_QUERY,
				// serv.element(ProcessConstant.PR_SERVICE_QUERY).getText());

			} else {

			}

			this.serviceMap.putValue(smap.getStringValue(ProcessConstant.PR_SERVICE_NAME), smap);
		}

		return this.serviceMap;
	}

	public void generate() {
		if (this.serviceMap != null) {
			Set<String> keys = this.serviceMap.keySet();
			for (String key : keys) {
				MapUtility serv = this.serviceMap.getMap(key);
				Element e1 = ele.addElement(ProcessConstant.PR_SERVICE);
				String serviceType = serv.getStringValue(ProcessConstant.PR_SERVICE_TYPE);
				e1.addAttribute(ProcessConstant.PR_SERVICE_TYPE, serviceType);

				Element e2 = e1.addElement(ProcessConstant.PR_SERVICE_NAME);
				e2.add(DocumentHelper.createCDATA(serv.getStringValue(ProcessConstant.PR_SERVICE_NAME)));

				e2 = e1.addElement(ProcessConstant.PR_SERVICE_DESC);
				e2.add(DocumentHelper.createCDATA(serv.getStringValue(ProcessConstant.PR_SERVICE_DESC)));

				e2 = e1.addElement(ProcessConstant.PR_SERVICE_CATEGORY);
				e2.add(DocumentHelper.createCDATA(serv.getStringValue(ProcessConstant.PR_SERVICE_CATEGORY)));

				if (serviceType.equals(ProcessConstant.PR_JAVASERVICE_TYPE)) {

					e2 = e1.addElement(ProcessConstant.PR_SERVICE_CLASS_NAME);
					e2.add(DocumentHelper.createCDATA(serv.getStringValue(ProcessConstant.PR_SERVICE_CLASS_NAME)));

					e2 = e1.addElement(ProcessConstant.PR_SERVICE_METHOD_NAME);
					e2.add(DocumentHelper.createCDATA(serv.getStringValue(ProcessConstant.PR_SERVICE_METHOD_NAME)));

				} else if (serviceType.equals(ProcessConstant.PR_DBSERVICE_TYPE)) {

					// e2 = e1.addElement(ProcessConstant.PR_SERVICE_QUERY);
					// e2.add(DocumentHelper.createCDATA(serv.getStringValue(ProcessConstant.PR_SERVICE_QUERY)));

				} else {

				}

			}
		}

	}

}
