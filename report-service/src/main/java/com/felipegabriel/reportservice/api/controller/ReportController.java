package com.felipegabriel.reportservice.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipegabriel.reportservice.api.dto.ClassificationDTO;
import com.felipegabriel.reportservice.api.proxy.FootballProxy;
import com.felipegabriel.reportservice.api.service.ReportService;
import com.itextpdf.text.DocumentException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("report-service")
@AllArgsConstructor
public class ReportController {

	private final ReportService classificationReportService;

	private final FootballProxy footballProxy;

	@GetMapping("/classification")
	public void generateClassificationReport(HttpServletResponse response, @RequestParam("season") Integer season)
			throws DocumentException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=brasileirao-" + season + ".pdf");
		
		List<ClassificationDTO> classification = footballProxy.getClassificationBySeason(season).getBody();

		classificationReportService.createReport(response, season, classification);
	}
}
