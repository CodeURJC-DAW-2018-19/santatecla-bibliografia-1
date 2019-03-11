package com.santatecla.G1.createpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.user.User;

@Service
public class PdfGenerator {
	
	@Autowired
	private BookService bookService;
	
	public void generatePdf(User user, Theme theme) {
		String title = theme.getName();
		Document document = new Document();
		
		List<Book> books = bookService.findByTheme(theme);

		try {
			Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
			if (!Files.exists(FILES_FOLDER))
				Files.createDirectories(FILES_FOLDER);

			PdfWriter.getInstance(document, new FileOutputStream(new File(FILES_FOLDER.toFile(), title)));

			document.open();
			for (Book b:books) {
				document.add(new Paragraph("Libro: " + b.getTitle() + " , escrito por: " + b.getAuthor().getName()));
			}

			if (books.isEmpty()) {
				document.add(new Paragraph("Error: Theme book's is Empty"));
			}

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
