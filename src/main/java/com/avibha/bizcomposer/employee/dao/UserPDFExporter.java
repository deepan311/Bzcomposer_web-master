package com.avibha.bizcomposer.employee.dao;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.employee.forms.PayrollDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class UserPDFExporter {
    private List<PayrollDto> listUsers;

    public UserPDFExporter(List<PayrollDto> listUsers) {
        this.listUsers = listUsers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("PayrollID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("EmployeeName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Month", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Year", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NetSalary", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (PayrollDto user : listUsers) {
            table.addCell(String.valueOf(user.getPayrollID()));
            table.addCell(user.getEmployeeName());
            table.addCell(user.getMonth());
            table.addCell(user.getYear());
            table.addCell(user.getNetSalary());

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Employee Payroll", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}