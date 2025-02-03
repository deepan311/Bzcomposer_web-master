package com.nxsol.bizcomposer.Action;

import com.avibha.bizcomposer.accounting.dao.AccountingDetail;
import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author sarfrazmalik
 */
@Controller
public class AccountingBudgetingController {

    @RequestMapping(value = {"/BankingAccounting"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(AccountDto accountDto, HttpServletRequest request, HttpServletResponse response,
            Model model) throws IOException, ServletException, JRException {
        String forward = "";
        model.addAttribute("accountDto", accountDto);
        model.addAttribute("emailSenderDto", new EmailSenderDto());
        String action = request.getParameter("tabid");
        HttpSession sess = request.getSession();
        String companyID = (String) sess.getAttribute("CID");
        ConstValue c = new ConstValue();
        c.setCompanyId(Integer.parseInt(companyID));

        if(action.equalsIgnoreCase("CheckDetail")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getCheckDetail(request, accountDto);
            forward = "/reports/CheckDetailReport";
        }
        if(action.equalsIgnoreCase("DepositDetail")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getDepositDetail(request, accountDto);
            forward = "/reports/DepositDetailReport";
        }
        if(action.equalsIgnoreCase("BillDetail")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getBillDetail(request, accountDto);;
            forward = "/reports/BillDetailReport";
        }
        if(action.equalsIgnoreCase("ARGraph")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getARGraph(request, accountDto);
            forward = "/reports/AccountReceivableGraphReport";
        }
        if(action.equalsIgnoreCase("IncomeExpenseGraph")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getIEGraph(request, accountDto);
            forward = "/reports/IncomeExpenseGraphReport";
        }
        if(action.equalsIgnoreCase("NetworthGraph")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getNWGraph(request, accountDto);
            forward = "/reports/NetworthGraphGraphReport";
        }
        if(action.equalsIgnoreCase("BudgetvsActualGraph")) {
            AccountingDetail ac = new AccountingDetail();
            ac.getBvAGraph(request, accountDto);
            forward = "/reports/BudgetvsActualGraph";
        }
        return forward;
    }
}
