package bzcomposer.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bzcomposer.configuration.module.form.templates.FormTemplatesController;

@SpringBootTest
public class BillDataPrintingFetchTests {
	@Autowired
	private FormTemplatesController controller;
	
	@Test
	public void contextLoads() throws Exception {
		 MockHttpServletRequest request = new MockHttpServletRequest();
		 MockHttpSession session =new MockHttpSession();
		 session.setAttribute("CID", "1");
		 request.setSession(session);
		 
		
		ModelAndView mview= new ModelAndView();
		mview.setViewName("formCustomization");
		Model formCustomization=(Model) mview.getModel();
		
		controller.fetchTemplatesByCompanyId(request, formCustomization);
	}
	
	public static void main(String[] args) throws Exception {
		new BillDataPrintingFetchTests().contextLoads();
	}
}
