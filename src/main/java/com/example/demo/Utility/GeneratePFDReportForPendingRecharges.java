package com.example.demo.Utility;


import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.Entity.PendingRechargeEntity;
import com.example.demo.Entity.StdEntity;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;

import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class GeneratePFDReportForPendingRecharges {

    public void GeneratePfdAllPendingRecharge(HttpServletResponse response, List<PendingRechargeEntity> data, File file) {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 30, 30);

            // Prepare output streams
            PdfWriter.getInstance(document, response.getOutputStream());
            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            // Title styling
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("All Pending Recharge Users", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Add generated date
            Font metaFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Paragraph generatedDate = new Paragraph("Generated on: " + LocalDate.now().toString(), metaFont);
            generatedDate.setAlignment(Paragraph.ALIGN_RIGHT);
            generatedDate.setSpacingAfter(10);
            document.add(generatedDate);

            // Table with headers
            PdfPTable table = new PdfPTable(7); // 7 actual columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new float[]{2, 2, 3, 2, 3, 2, 2});

            // Header styling
            addTableHeader(table, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));

            // Data rows
            for (PendingRechargeEntity item : data) {
                addTableRow(table, item, FontFactory.getFont(FontFactory.HELVETICA, 11));
            }

            document.add(table);

            // Closing
            Paragraph closing = new Paragraph("Regards,\nSystem Admin", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
            closing.setSpacingBefore(20);
            document.add(closing);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table, Font font) {
        table.addCell(createHeaderCell("Pending ID", font));
        table.addCell(createHeaderCell("Std ID", font));
        table.addCell(createHeaderCell("Name", font));
        table.addCell(createHeaderCell("Amount", font));
        table.addCell(createHeaderCell("Date", font));
        table.addCell(createHeaderCell("Status", font));
        table.addCell(createHeaderCell("Table Num", font));
    }

    private PdfPCell createHeaderCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(8);
        cell.setBackgroundColor(new Color(230, 230, 250)); // Light lavender background
        return cell;
    }

    private PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }

    private void addTableRow(PdfPTable table, PendingRechargeEntity item, Font font) {
        StdEntity std = item.getStdEntityDetails();
        String formattedDate = item.getPendingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        table.addCell(createCell(String.valueOf(item.getPendingId()), font));
        table.addCell(createCell(String.valueOf(std.getStdId()), font));
        table.addCell(createCell(std.getStdName(), font));
        table.addCell(createCell(String.format("%.2f", item.getPendingAmount()), font));
        table.addCell(createCell(formattedDate, font));
        table.addCell(createCell(item.getStatus(), font));
        table.addCell(createCell(String.valueOf(std.getTblNum()), font));
    }
    
    
    
    
}
