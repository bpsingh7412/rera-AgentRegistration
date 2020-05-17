package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrTeamUtility {

	private Element ele;
	private MapUtility teamMap;

	public PrTeamUtility(MapUtility teamMap, Element ele) {
		this.teamMap = teamMap;
		this.ele = ele;
	}

	public PrTeamUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility menuMap) {
		this.teamMap = menuMap;
		List<Element> mlist = this.ele.elements(ProcessConstant.PR_TEAM);
		if (mlist != null) {
			MapUtility team = null;
			for (Element mn : mlist) {
				team = MapUtility.getInstance();
				team.putValue(ProcessConstant.PR_TEAM_NAME, mn.element(ProcessConstant.PR_TEAM_NAME).getText());
				team.putValue(ProcessConstant.PR_TEAM_DESC, mn.element(ProcessConstant.PR_TEAM_DESC).getText());

				this.teamMap.putValue(team.getStringValue(ProcessConstant.PR_TEAM_NAME), team);
			}
		}
		return this.teamMap;
	}

	public void generate() {
		if (this.teamMap != null) {
			Set<String> keys = this.teamMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_TEAM);
				MapUtility team = this.teamMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_TEAM_NAME);
				e2.add(DocumentHelper.createCDATA(team.getStringValue(ProcessConstant.PR_TEAM_NAME)));

				e2 = e1.addElement(ProcessConstant.PR_TEAM_DESC);
				e2.add(DocumentHelper.createCDATA(team.getStringValue(ProcessConstant.PR_TEAM_DESC)));

			}

		}

	}
}
