package com.nxsol.bizcomposer.reportcenter.lists;

import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.avibha.common.log.Loger;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sarfrazmalik
 */
@Controller
public class ListsController {

    @RequestMapping(value = {"/ReportCenterLists"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(AccountDto accountDto, HttpServletRequest request, Model model) throws IOException, ServletException {
        Loger.log("INSIDE REPORT CENTER LISTS ACTION");
        String forward = "/accounting/accounting";
        HttpSession sess = request.getSession();
        String companyID = (String) sess.getAttribute("CID");
        ConstValue c = new ConstValue();
        c.setCompanyId(Integer.parseInt(companyID));
        String action = request.getParameter("tabid");
        model.addAttribute("accountDto", accountDto);
        model.addAttribute("emailSenderDto", new EmailSenderDto());
        if(action.equalsIgnoreCase("ChartsofCategories")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.chartOfCategories(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/chartsofCategories";
        }

        if(action.equalsIgnoreCase("TermList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.termList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/termList";
        }

        if(action.equalsIgnoreCase("SaleRepList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.salesRepList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/saleRepList";
        }

        if(action.equalsIgnoreCase("PaymentMethodList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.paymentMethodList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/paymentMethodList";
        }

        if(action.equalsIgnoreCase("ShipViaList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.shipViaList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/shipViaList";
        }

        if(action.equalsIgnoreCase("TaxTypeList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.taxTypeList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/taxTypeList";
        }

        if(action.equalsIgnoreCase("FootnoteList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.footnoteList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/foodNoteList";
        }

        if(action.equalsIgnoreCase("MessageList")) {
            String fromDate = accountDto.getFromDate();
            String toDate = accountDto.getToDate();
            String sortBy = accountDto.getSortBy();
            String datesCombo = accountDto.getDatesCombo();
            ArrayList<ListPOJO> acList = ListsDAO.messageList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
            request.setAttribute("acList", acList);
            forward = "/reports/messageList";
        }
        return forward;
    }
}
