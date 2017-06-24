package sr.slave.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class IndexAction extends ActionSupport implements ServletRequestAware {

    private final transient org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    private HttpServletRequest request;
    private Enumeration<String> requestHearderNames;

    @Action(results = {
            @Result(name = NONE, location = "/index.jsp", type = "redirect")
    })
    @Override
    public String execute() throws Exception {

        while (requestHearderNames.hasMoreElements()) {

            String requestHeader = requestHearderNames.nextElement();
            String headerValue = request.getHeader(requestHeader);
            logger.debug("Request Header: {} = {}", requestHeader, headerValue);
        }

        return NONE;
    }


    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {

        this.request = httpServletRequest;
        this.requestHearderNames = httpServletRequest.getHeaderNames();
    }
}
