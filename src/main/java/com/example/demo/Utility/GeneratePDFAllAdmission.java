package com.example.demo.Utility;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.Entity.StdEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class GeneratePDFAllAdmission {

    public void generatePdfAllStudentAdmissions(HttpServletResponse response, List<StdEntity> studentList, File outputFile) {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 30, 30);

            // Prepare output streams
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            // Add Title
            addTitle(document);

            // Add metadata
            addMetaData(document);

            // Add Student Table
            addStudentTable(document, studentList);

            // Add closing message
            addClosing(document);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTitle(Document document) throws DocumentException {
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLACK);
        Paragraph title = new Paragraph("All Student Admission Report", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);
    }

    private void addMetaData(Document document) throws DocumentException {
        Font metaFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        Paragraph generatedDate = new Paragraph("Generated on: " + date, metaFont);
        generatedDate.setAlignment(Paragraph.ALIGN_RIGHT);
        generatedDate.setSpacingAfter(10);
        document.add(generatedDate);
    }

    private void addStudentTable(Document document, List<StdEntity> students) throws DocumentException {
        PdfPTable table = new PdfPTable(10); // Total columns: 10
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.setWidths(new float[]{2, 3, 3, 3, 3, 3, 2, 3, 3, 3});

        // Table Header
        addTableHeader(table);

        // Table Rows
        for (StdEntity student : students) {
            addTableRow(table, student);
        }

        document.add(table);
    }

    private void addTableHeader(PdfPTable table) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.BLACK);
        Color bgColor = new Color(220, 230, 241); // Light blue

        String[] headers = {
            "Std ID", "Name", "Phone", "Aadhar",
            "Plan Status", "Profile Status", "Table No",
            "App Date", "Start Date", "End Date"
        };

        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(bgColor);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }
    }

    private void addTableRow(PdfPTable table, StdEntity student) {
        Font rowFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

        table.addCell(createCell(String.valueOf(student.getStdId()), rowFont));
        table.addCell(createCell(student.getStdName(), rowFont));
        table.addCell(createCell(student.getPhone(), rowFont));
        table.addCell(createCell(student.getAdharNo(), rowFont));
        table.addCell(createCell(student.getPlanStatus(), rowFont));
        table.addCell(createCell(student.getProfileStatus(), rowFont));
        table.addCell(createCell(String.valueOf(student.getTblNum()), rowFont));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        table.addCell(createCell(student.getAppDate().format(formatter), rowFont));
        table.addCell(createCell(student.getPlanStartDate().format(formatter), rowFont));
        table.addCell(createCell(student.getPlanEndDate().format(formatter), rowFont));
    }

    private PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }

    private void addClosing(Document document) throws DocumentException {
        Font closingFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 11);
        Paragraph closing = new Paragraph("Regards,\nSystem Administrator", closingFont);
        closing.setSpacingBefore(20);
        document.add(closing);
    }
}
