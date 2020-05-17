package in.gov.wf.util;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.service.JavaService;

public class WfServiceUtil {

	public ServiceResult executeService(String serviceCode, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		ServiceResult result = new ServiceResult();
		ProcessConfiguration conf = ProcessConfiguration.getConfObj();
		MapUtility serv = conf.getProcessServices().getMap(serviceCode);
		JavaService service = getServiceObject(serv);
		service.init(req, resp);
		executeService(service, serv.getStringValue(ProcessConstant.PR_SERVICE_METHOD_NAME));

		return service.getResult();
	}

	private void executeService(JavaService service, String methodName) throws Exception {
		Method mt = service.getClass().getMethod(methodName, null);
		mt.invoke(service, null);

	}

	@Bean
	private JavaService getServiceObject(MapUtility map) throws Exception {
		JavaService serv = null;
		serv = (JavaService) Class.forName(map.getStringValue(ProcessConstant.PR_SERVICE_CLASS_NAME)).newInstance();
		return serv;
	}
}
