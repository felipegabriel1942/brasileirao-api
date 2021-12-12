package com.felipegabriel.reportservice.api.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.felipegabriel.reportservice.api.dto.ClassificationDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ReportService {

	public void createReport(HttpServletResponse response, Integer season, List<ClassificationDTO> classification)
			throws DocumentException, IOException {

		Document document = new Document(PageSize.A4.rotate());

		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		document.addTitle("Campeonato Brasileiro - " + season);	
		document.add(createReportHeader(season));
		document.add(createReportTable(classification));
		document.add(createReportDictionary());
		
		document.close();
	}
	
	private Paragraph createReportHeader(int season) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 15f);
		String text = "Campeonato Brasileiro - " + season;
		
		Paragraph header = new Paragraph(text, font);
		header.setSpacingAfter(40f);
		return header;
	}
	
	private Paragraph createReportDictionary() {
		Paragraph dictionary = new Paragraph("GF = Goals For, GA = Goals Against, GD = Goals Difference.");
		dictionary.setSpacingBefore(20f);
		return dictionary;
	}
		
	private PdfPTable createReportTable(List<ClassificationDTO> classification) {
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(100f);
		
		Arrays
			.asList("Position", "Team", "Points", "Played", "Victories", "Ties", "Defeats", "GF", "GA", "GD")
			.forEach(h -> table.addCell(createCell(h, BaseColor.YELLOW)));
				
		IntStream
			.range(0, classification.size())
			.forEach(i -> createTableRow(table, i, classification.get(i)));
		
		return table;
	}
	
	private void createTableRow(PdfPTable table, int index, ClassificationDTO classification) {
		BaseColor rowColor = index % 2 == 0 ? BaseColor.LIGHT_GRAY : BaseColor.WHITE;
		
		table.addCell(createCell(String.valueOf(index + 1) + "º", rowColor));
		table.addCell(createCell(classification.getTeam(), rowColor));
		table.addCell(createCell(classification.getPoints(), rowColor));
		table.addCell(createCell(classification.getTotalMatches(), rowColor));
		table.addCell(createCell(classification.getVictories(), rowColor));
		table.addCell(createCell(classification.getTies(), rowColor));
		table.addCell(createCell(classification.getDefeats(), rowColor));
		table.addCell(createCell(classification.getGoalsFor(), rowColor));
		table.addCell(createCell(classification.getGoalsAgainst(), rowColor));
		table.addCell(createCell(classification.getGoalsDifference(), rowColor));
	}
	
	private PdfPCell createCell(Object value, BaseColor color) {
		PdfPCell cell = new PdfPCell(new Paragraph(String.valueOf(value)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBackgroundColor(color);
		return cell;
	}
}
