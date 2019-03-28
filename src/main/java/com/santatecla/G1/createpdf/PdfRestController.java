package com.santatecla.G1.createpdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;
import com.santatecla.G1.user.User;
import com.santatecla.G1.user.UserComponent;

@RestController
@RequestMapping("/api")
public class PdfRestController {

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private ThemeService themeService;

	@Autowired
	private PdfGenerator pdfGenerator;

	@RequestMapping("/themes/{id}/pdf")
	public void handleFileDownloadPDF(HttpServletResponse res, @PathVariable long id)
			throws FileNotFoundException, IOException {

		Theme theme = themeService.findById(id);

		if (theme == null) {
			res.sendError(404);
		} else {

			User user = userComponent.getLoggedUser();
			String name = theme.getName();

			pdfGenerator.generatePdf(user, theme);

			Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
			Path pdf = FILES_FOLDER.resolve(name);

			if (Files.exists(pdf)) {
				res.setContentType("bibliografia/pdf");
				res.setContentLength((int) pdf.toFile().length());
				FileCopyUtils.copy(Files.newInputStream(pdf), res.getOutputStream());

			} else {
				res.sendError(404);
			}
		}
	}
}