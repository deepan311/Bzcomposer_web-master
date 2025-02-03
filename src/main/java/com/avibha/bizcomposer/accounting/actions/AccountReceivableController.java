package com.avibha.bizcomposer.accounting.actions;

import com.avibha.bizcomposer.accounting.dao.AccountingDetail;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.nxsol.bizcomposer.common.ConstValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author sarfrazmalik
 */
@Controller
public class AccountReceivableController {

    @RequestMapping(value = {"/AccountReceivableAR"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(InvoiceDto invoiceDto, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "";
        String action = request.getParameter("tabid");
        HttpSession sess = request.getSession();
        String companyID = (String) sess.getAttribute("CID");
        ConstValue c = new ConstValue();
        c.setCompanyId(Integer.parseInt(companyID));
        if(action.equalsIgnoreCase("AccontReceivableReport")) {
            AccountingDetail detail = new AccountingDetail();
            detail.getAccountReceivable(request, invoiceDto);
            forward = "/reports/AccountReceivableReport";
        }
        return forward;
    }
}
