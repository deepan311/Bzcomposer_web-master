package com.avibha.bizcomposer.employee.dao;

import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.bizcomposer.employee.forms.PayrollDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PdfGeneration{
    private List<PayrollDto> payrollDetails;
    private ArrayList<AddEmployeeForm> employeeDetails;
    private ArrayList<TimeSheet> timeSheets;
    public PdfGeneration(List<PayrollDto> payrollDetails,ArrayList<AddEmployeeForm> employeeDetails,ArrayList<TimeSheet> timeSheets) {
        this.payrollDetails = payrollDetails;
        this.employeeDetails = employeeDetails;
        this.timeSheets = timeSheets;
    }

    public void generatePdfForPayRoll(HttpServletResponse response) {
        // Creating a PdfWriter
        char checked='\u00FE';
        char unchecked='\u00A8';

        // Creating a Document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        //handling exception
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            //PdfWriter.getInstance(document, new FileOutputStream("E:/itextExamples/MyDoc1.pdf"));
            document.open();
            Font font = new Font(BaseFont.createFont("E:\\wingding.ttf", BaseFont.IDENTITY_H, false), 12f);
            Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12f);

            PdfPTable table = new PdfPTable(3);
            PdfPCell cell1 = new PdfPCell(new Phrase("Employer",normal));
            PdfPCell cell11 = new PdfPCell(new Phrase("",normal));
            cell1.setColspan(1);
            cell11.setColspan(2);
            table.addCell(cell1);
            table.addCell(cell11);
            table.completeRow();

            PdfPCell cell2 = new PdfPCell(new Phrase("Employee's Name",normal));
            PdfPCell cellvalue = new PdfPCell(new Phrase(payrollDetails.get(0).getEmployeeName(),normal));
            cell2.setColspan(1);
            cellvalue.setColspan(2);
            table.addCell(cell2);
            table.addCell(cellvalue);
            table.completeRow();

            cell1 = new PdfPCell(new Phrase("Emp. SSN: "+employeeDetails.get(0).getSsn(),normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Hire Date: "+employeeDetails.get(0).getDoa(),normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Pay Period Ending",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);
            table.completeRow();

            cell1 = new PdfPCell(new Phrase(" ",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase(" ",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase(" ",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);
            table.completeRow();


            cell1 = new PdfPCell(new Phrase("Birthday (if under 18)",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Discharge Date",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Occupation",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);
            table.completeRow();

            cell1 = new PdfPCell(new Phrase(" ",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase(" ",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase(" ",normal));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
            table.addCell(cell1);
            table.completeRow();

            PdfPTable table1 = new PdfPTable(5);
            table1.setSpacingBefore(10f);
            table1.addCell(new PdfPCell(new Phrase("Date",normal)));
            table1.addCell(new PdfPCell(new Phrase("Start Time",normal)));
            table1.addCell(new PdfPCell(new Phrase("End Time",normal)));
            table1.addCell(new PdfPCell(new Phrase("Break",normal)));
            table1.addCell(new PdfPCell(new Phrase("Total Hours",normal)));
            table1.completeRow();

            float Totalhours = 0;
            for(int i=0; i<timeSheets.size(); i++){
                String day = timeSheets.get(i).getDay();
                String startTime = timeSheets.get(i).getStartWork();
                String endTime = timeSheets.get(i).getEndWork();
                String startTime1[] = startTime.split(" ");
                String endTime1[] = endTime.split(" ");


                table1.addCell(new PdfPCell(new Phrase(day.substring(0, 10))));
                table1.addCell(new PdfPCell(new Phrase(startTime1[1].substring(0, 5))));
                table1.addCell(new PdfPCell((new Phrase(endTime1[1].substring(0, 5)))));
                table1.addCell(new PdfPCell(new Phrase(timeSheets.get(i).getBreak())));
                table1.addCell(new PdfPCell(Phrase.getInstance(timeSheets.get(i).getWorkingHours())));
                table1.completeRow();
                Totalhours = (Totalhours +  Float.parseFloat(timeSheets.get(i).getWorkingHours()));
            }

            cell1 = new PdfPCell(new Phrase("Weekly Total",normal));
            cell1.setRowspan(2);
            table1.addCell(cell1);
            table1.addCell(new PdfPCell(new Phrase("Total Hrs.",normal)));
            table1.addCell(new PdfPCell(new Phrase("Regular Hrs.",normal)));
            table1.addCell(new PdfPCell(new Phrase("Overtime Hrs.",normal)));
            table1.completeRow();

            table1.addCell(new PdfPCell(new Phrase(Totalhours+"")));
            table1.addCell(new PdfPCell(new Phrase("   ")));
            table1.addCell(new PdfPCell(new Phrase(Totalhours+"")));
            table1.completeRow();

            cell1 = new PdfPCell(new Phrase("Salary",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getTotalSalary()+"$",normal));
            cell2.setColspan(2);
            table1.addCell(cell1);
            table1.addCell(cell2);
            table1.completeRow();


            cell1 = new PdfPCell(new Phrase("Remuneration other than cash",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase("  "));
            cell2.setColspan(2);
            table1.addCell(cell1);
            table1.addCell(cell2);
            table1.completeRow();

            cell1 = new PdfPCell(new Phrase("Gross Earnings",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getTotalSalary()+"$",normal));
            cell2.setColspan(2);
            table1.addCell(cell1);
            table1.addCell(cell2);
            table1.completeRow();

            PdfPTable table2 = new PdfPTable(5);
            table2.setSpacingBefore(10f);

            cell1 = new PdfPCell(new Phrase("% Federal Withholding Tax",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getFrederalWithholdingTax()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            cell1 = new PdfPCell(new Phrase("% Medicare Tax",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getMedicareTax()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            cell1 = new PdfPCell(new Phrase("% Social Security Tax",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getSocialSecurityTax()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            cell1 = new PdfPCell(new Phrase("% State Withholding Tax",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getStateWithholdingTax()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            cell1 = new PdfPCell(new Phrase("% State Disability Insurance",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getStateDisablitiyInsurance()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            cell1 = new PdfPCell(new Phrase("% Federal Ins. contribution act",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(  payrollDetails.get(0).getfICA()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            cell1 = new PdfPCell(new Phrase("Total Deductions",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getTotalDeduction()+"$",normal));
            cell2.setColspan(2);
            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.completeRow();

            PdfPTable table3 = new PdfPTable(5);
            table3.setSpacingBefore(10f);

            cell1 = new PdfPCell(new Phrase("Net Earnings",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase(payrollDetails.get(0).getNetSalary()+"$",normal));
            cell2.setColspan(2);
            table3.addCell(cell1);
            table3.addCell(cell2);
            table3.completeRow();

            cell1 = new PdfPCell(new Phrase("Less Remuneration other than cash",normal));
            cell1.setColspan(3);
            cell2 = new PdfPCell(new Phrase("  "));
            cell2.setColspan(2);
            table3.addCell(cell1);
            table3.addCell(cell2);
            table3.completeRow();

            cell1 = new PdfPCell();
            Chunk ch;
            Paragraph para = new Paragraph();
            String paymentMethod = payrollDetails.get(0).getPaymentMethod();
            if (paymentMethod.equalsIgnoreCase("Cash")){
                ch = new Chunk(checked);
            }else{
                ch = new Chunk(unchecked);
            }
            ch.setFont(font);
            para.add(ch);
            ch = new Chunk("By Cash");
            ch.setFont(normal);
            para.add(ch);
            cell1.addElement(para);

            cell2 = new PdfPCell();
            para= new Paragraph();
            if (paymentMethod.equalsIgnoreCase("Cheque")){
                ch = new Chunk(checked);
            }else{
                ch = new Chunk(unchecked);
            }
            ch.setFont(font);
            para.add(ch);
            ch = new Chunk("By Cheque");
            ch.setFont(normal);
            para.add(ch);
            cell2.addElement(para);

            cell2.setColspan(2);
            PdfPCell cell3 = new PdfPCell(new Phrase("Net Pay this Period",normal));
            cell3.setColspan(2);
            table3.addCell(cell1);
            table3.addCell(cell2);
            table3.addCell(cell3);
            table3.completeRow();

            cell1 = new PdfPCell();
            para = new Paragraph();
            ch = new Chunk(checked);
            ch.setFont(font);
            para.add(ch);
            ch = new Chunk("I hereby certify that the time shown above is correct");
            ch.setFont(normal);
            para.add(ch);
            cell1.addElement(para);
            cell1.setColspan(5);
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setPaddingBottom(10f);
            table3.addCell(cell1);
            table3.completeRow();

            cell1 = new PdfPCell(new Phrase("Employee's Sig:",normal));
            cell1.setColspan(3);
            cell1.setBorder(PdfPCell.NO_BORDER);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = payrollDetails.get(0).getDatePaid();
            cell2 = new PdfPCell(new Phrase("Date Paid: "+d1,normal));
            cell2.setColspan(2);
            cell2.setBorder(PdfPCell.NO_BORDER);
            table3.addCell(cell1);
            table3.addCell(cell2);
            table3.completeRow();

            document.add(table);
            document.add(table1);
            document.add(table2);
            document.add(table3);
        }catch(FileNotFoundException | DocumentException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // Closing the document
            document.close();
        }
    }
}
