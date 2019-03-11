package com.santatecla.G1;


import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.santatecla.G1.author.Author;
import com.santatecla.G1.author.AuthorService;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookService;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.citation.CitationService;
import com.santatecla.G1.theme.Theme;
import com.santatecla.G1.theme.ThemeService;
import com.santatecla.G1.user.User;
import com.santatecla.G1.user.UserRepository;

@Component
@Order(1)
public class DataBaseInitializator {
	
	@Autowired
	AuthorService authorService;
	@Autowired
	ThemeService themeService;
	@Autowired
	BookService bookService;
	@Autowired 
	CitationService citationService;
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		System.out.println("Start Database initialization");
		// Author initialization		
			
		String nameBrandon="Brandon Sanderson";
		String urlImageBrandon="https://www.goodreads.com/photo/author/38550.Brandon_Sanderson";
		String bornDateBrandon="1975-12-19";
		String deathDateBrandon="";
		String bornPlaceBrandon="Lincoln, Nebraska";
		String urlMapBrandon= "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96647.87182448553!2d-96.76076790212979!3d40.80058782506782!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8796be59ca561265%3A0x633a859b1fd5deb9!2sLincoln%2C+Nebraska%2C+EE.+UU.!5e0!3m2!1ses!2ses!4v1551055373899";                  
		Author a1 = new Author(nameBrandon,urlImageBrandon,bornDateBrandon,deathDateBrandon,bornPlaceBrandon,urlMapBrandon,0);
		
		String nameEsteso ="Fernando Esteso";
		String urlImageEsteso="https://img.bekia.es/celebrities/th/2000/2576.jpg";
		String bornDateEsteso="1945-2-16";
		String deathDateEsteso="";
		String bornPlaceEsteso="Zaragoza, España";
		String urlMapEsteso= "https://www.google.es/maps/place/Zaragoza/@41.6516859,-0.9300003,13z/data=!3m1!4b1!4m5!3m4!1s0xd5914dd5e618e91:0x49df13f1158489a8!8m2!3d41.6488226!4d-0.8890853";                  
		Author a2 = new Author(nameEsteso,urlImageEsteso,bornDateEsteso,deathDateEsteso,bornPlaceEsteso,urlMapEsteso,1);
		
		String nameAlan="Alan Mathison Turing";
		String urlImageAlan="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Alan_Turing_Aged_16.jpg/440px-Alan_Turing_Aged_16.jpg";
		String bornDateAlan="1912-06-23";
		String deathDateAlan="1954-06-07";
		String bornPlaceAlan="Maida Vale, Reino Unido de Gran Bretaña e Irlanda";
		String urlMapAlan= "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75132.22175705049!2d-0.2604624272851899!3d51.53606625037453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761009a098e00b%3A0x261185c6bcdb02a2!2sMaida+Vale%2C+Londres%2C+Reino+Unido!5e0!3m2!1ses!2ses!4v1549226048515";                            
		Author a3 = new Author(nameAlan,urlImageAlan,bornDateAlan,deathDateAlan,bornPlaceAlan,urlMapAlan,2);
		
		String nameF="Federico García Lorca";
		String urlImageF="https://ep00.epimg.net/elpais/imagenes/2016/05/04/opinion/1462357086_520070_1462359062_noticia_normal.jpg";
		String bornDateF="1898-06-05";
		String deathDateF="1936-08-18";
		String bornPlaceF="Granada, España";
		String urlMapF="https://www.google.com/maps/place/Granada/@37.1851062,-3.649012,12.25z/data=!4m5!3m4!1s0xd71fce62d32c27d:0x9258f79dd3600d72!8m2!3d37.1773363!4d-3.5985571";
		Author a5= new Author(nameF,urlImageF,bornDateF,deathDateF,bornPlaceF,urlMapF,-1);
		
		String nameR="Joanne Rowling";
		String urlImageR="https://commons.wikimedia.org/wiki/File:J._K._Rowling_2010.jpg";
		String bornDateR="1965-07-31";
		String deathDateR="";
		String urlMapR="https://www.google.es/maps/place/Gloucestershire+Sur,+Reino+Unido/@51.5488153,-2.7615817,10z/data=!3m1!4b1!4m5!3m4!1s0x48719a6a7dccc6d5:0x30d8b23d499a000!8m2!3d51.5264361!4d-2.4728487";
		String bornPlaceR="Gloucestershire Sur, Reino Unido";
		Author a6= new Author(nameR,urlImageR,bornDateR,deathDateR,bornPlaceR,urlMapR,-1);
		
		String nameJK="Rafael Alberti Merello";
		String urlImageJK="https://www.elviejotopo.com/wp-content/uploads/2017/10/rafael-alberti.jpg";
		String bornDateJK="1902-12-16";
		String deathDateJK="1999-10-28";
		String urlMapJK="https://www.google.com/maps/place/El+Puerto+de+Santa+Mar%C3%ADa,+11500,+C%C3%A1diz/@36.6231038,-6.3714151,11z/data=!3m1!4b1!4m5!3m4!1s0xd0dc5568150cc8d:0x40463fd8ca03bf0!8m2!3d36.6393281!4d-6.2588369";
		String bornPlaceJK="Puerto de Santa María, Cádiz, España";
		Author a4= new Author(nameJK,urlImageJK,bornDateJK,deathDateJK,bornPlaceJK,urlMapJK,-1);
		
		String nameS="Stephen King";
		String urlImageS="https://i.blogs.es/899a7f/king/450_1000.jpeg";
		String bornDateS="1947-09-21";
		String deathDateS="";
		String urlMapS="https://www.google.com/maps/place/Portland,+Oreg%C3%B3n,+EE.+UU./data=!4m2!3m1!1s0x54950b0b7da97427:0x1c36b9e6f6d18591?ved=2ahUKEwjw0fKFvvjgAhVirHEKHYXeDR4Q8gEwAHoECAIQAQ";
		String bornPlaceS="Portland, Oregón, EE. UU.";
		Author a7= new Author(nameS,urlImageS,bornDateS,deathDateS,bornPlaceS,urlMapS,-1);

		/*for(int i=0; i<25; i++) {
			Author a = new Author(""+i,""+i,""+i,""+i,""+i,""+i,-1);
			Book b = new Book(""+i, -2);
			Theme t = new Theme(""+i);
			authorService.save(a);
			bookService.save(b);
			themeService.save(t);
		}*/

		//Books initialization
		Book b1 = new Book("Palabras Radiantes",3);
		Book b2 = new Book("Nacidos de la bruma",4);
		Book b3 = new Book("El año de los delfines",5);
		Book b4 = new Book("Refranero",-2);
		Book b5 = new Book("Romancero Gitano",-2);
		Book b6 = new Book("Harry Potter y la Piedra Filosofal",-2);
		Book b7 = new Book("IT",-2);
		Book b8= new Book("El Resplandor",-2);
		
		//Citation initialization  --> We dont need to save because they deppends of the books. (Cascade.)	
		Citation c1 = new Citation("El misterio de la vida no es un problema a resolver, sino una realidad a experimentar",b1);
		Citation c2 = new Citation("La Ilusión despierta el empeño y solamente la paciencia lo termina.",b2);
		Citation c3 = new Citation("Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo.",b3);	
		Citation c4 = new Citation("El que es bueno en la familia es también un buen ciudadano.",b4);
		Citation c5 = new Citation("Haríamos muchas más cosas si creyéramos que son muchas menos las imposibles.",b3);
		Citation c6 = new Citation("La sabiduría suprema es tener sueños bastante grandes para no perderlos de vista mientras se persiguen.",b4);
		Citation c7 = new Citation("Elige la mejor manera de vivir; la costumbre te la hará agradable.",b4);
		Citation c8 = new Citation("Los libros no se han hecho para servir de adorno: sin embargo, nada hay que embellezca tanto como ellos en el interior del hogar.",b4);
		Citation c9 = new Citation("La furia con que el mundo actual busca el placer prueba que carece de él.",b4);
		Citation c10 = new Citation("Donde hay amor no hay señor, que todo lo iguala el amor.",b4);
		Citation c11= new Citation("La buena fe es el fundamento de toda sociedad, la perfidia es la peste.",b4);
		
		Citation c12 = new Citation("¡Soledad, qué pena tienes!\n" + 
				"¡Qué pena tan lastimosa!",b5);
		Citation c13= new Citation("Nunca he encontrado ningún motivo para temer pronunciar el nombre de Voldemort.",b6);
		Citation c14= new Citation("Te pareces mucho a tu padre, pero tienes los ojos de tu madre.",b6);
		Citation c15= new Citation("¡No voy a hacerte daño! Wendy, querida, luz de mi vida, ¿de qué tienes miedo?. No me has dejado acabar la frase, dije: No voy a hacerte daño, sólo voy a aplastarte los sesos. ¡Aplastaré tus jodidos sesos!",b8);
		
		//Citation for pagination
		Citation p1= new Citation("p1", b1);
		Citation p2= new Citation("p2", b1);
		Citation p3= new Citation("p3", b1);
		Citation p4= new Citation("p4", b1);
		Citation p5= new Citation("p5", b1);
		Citation p6= new Citation("p6", b1);
		Citation p7= new Citation("p7", b1);
		Citation p8= new Citation("p8", b1);
		Citation p9= new Citation("p9", b1);
		Citation p10= new Citation("p10", b1);
		Citation p11= new Citation("p11", b1);
		Citation p12= new Citation("p12", b1);
		
		//Adding citation to books, We don't save it explicitly because they depend of the existence of a book
		b1.addCitations(c1); 
		b2.addCitations(c2);
		b3.addCitations(c3);
		b4.addCitations(c4);
		b4.addCitations(c5);
		b4.addCitations(c6);
		b4.addCitations(c7);
		b4.addCitations(c8);
		b4.addCitations(c9);
		b4.addCitations(c10);
		b4.addCitations(c11);
		b5.addCitations(c12);
		b6.addCitations(c13);
		b6.addCitations(c14);
		b8.addCitations(c15);
		
		//book for pagination citation
		b1.addCitations(p1); 
		b1.addCitations(p2); 
		b1.addCitations(p3); 
		b1.addCitations(p4); 
		b1.addCitations(p5); 
		b1.addCitations(p6); 
		b1.addCitations(p8); 
		b1.addCitations(p7); 
		b1.addCitations(p9); 
		b1.addCitations(p10); 
		b1.addCitations(p11); 
		b1.addCitations(p12); 
		

		//Set books of citations
		c1.setBook(b1);
		
		c2.setBook(b2);
		
		c3.setBook(b3);
		
		c4.setBook(b4);
		c5.setBook(b4);
		c6.setBook(b4);
		c7.setBook(b4);
		c8.setBook(b4);
		c9.setBook(b4);
		c10.setBook(b4);
		c11.setBook(b4);
		c12.setBook(b5);
		c13.setBook(b6);
		c14.setBook(b6);
		c15.setBook(b8);
		
		//Citation for pagination
		p1.setBook(b1);
		p2.setBook(b1);
		p3.setBook(b1);
		p4.setBook(b1);
		p5.setBook(b1);
		p6.setBook(b1);
		p7.setBook(b1);
		p8.setBook(b1);
		p9.setBook(b1);
		p10.setBook(b1);
		p11.setBook(b1);
		p12.setBook(b1);
		

		//Create a themes that will be relacionated with a book, the theme also depends of the existence of a book(we don't save it explicitly).
		Theme th1  = new Theme("Amor");
		Theme th2  = new Theme("Vida");
		Theme th3  = new Theme("Clasico");
		Theme th4 = new Theme("Aventura");
		Theme th5= new Theme("Miedo");
		
		
		//Add the theme to the book
		
		c1.setTheme(th1);
		c2.setTheme(th2);
		
		c4.setTheme(th3);
		c3.setTheme(th3);
		c6.setTheme(th3);
		c7.setTheme(th3);
		c8.setTheme(th3);
		c9.setTheme(th3);
		c10.setTheme(th3);
		c11.setTheme(th3);
		c12.setTheme(th1);
		c13.setTheme(th4);
		c14.setTheme(th4);
		c15.setTheme(th5);
		//Citation for pagination
		p1.setTheme(th1);
		p2.setTheme(th1);
		p3.setTheme(th1);
		p4.setTheme(th1);
		p5.setTheme(th1);
		p6.setTheme(th1);
		p7.setTheme(th1);
		p8.setTheme(th1);
		p9.setTheme(th1);
		p10.setTheme(th1);
		p11.setTheme(th1);
		p12.setTheme(th1);
		
		
		b1.setTheme(th1);
		b2.setTheme(th2);
		b3.setTheme(th3);
		b4.setTheme(th3);
		b5.setTheme(th1);
		b6.setTheme(th4);
		b7.setTheme(th5);
		b8.setTheme(th5);
	
		th1.addBook(b1);
		th2.addBook(b2);
		th3.addBook(b3);
		th3.addBook(b4);
		th1.addBook(b5);
		th4.addBook(b6);
		th5.addBook(b7);
		th5.addBook(b8);
		
		themeService.save(th1);
		themeService.save(th2);
		themeService.save(th3);
		themeService.save(th4);
		themeService.save(th5);
		
		//Add the book to the author, the existence of the book depends of the existence of the author, so we don't save the book explicitly.
		a1.addBook(b1);
		a2.addBook(b2);
		a3.addBook(b4);
		a3.addBook(b3);
		a5.addBook(b5);
		a6.addBook(b6);
		a7.addBook(b7);
		a7.addBook(b8);
		
		b1.setAuthor(a1);
		b2.setAuthor(a2);
		b3.setAuthor(a3);
		b4.setAuthor(a3);
		b5.setAuthor(a5);
		b6.setAuthor(a6);
		b7.setAuthor(a7);
		b8.setAuthor(a7);
		
		
		authorService.save(a1);
		authorService.save(a2);
		authorService.save(a3);
		authorService.save(a4);
		authorService.save(a5);
		authorService.save(a6);
		authorService.save(a7);
		
		//Save the book wich has no author.
		bookService.save(b2);
		bookService.save(b1);
		bookService.save(b4);
		bookService.save(b3);
		bookService.save(b5);
		bookService.save(b6);
		bookService.save(b7);
		bookService.save(b8);
		
		//Save the author who has write the b1
		/*citationRepository.save(c1);
		citationRepository.save(c2);
		citationRepository.save(c3);
		citationRepository.save(c4);
		citationRepository.save(c5);
		citationRepository.save(c6);
		citationRepository.save(c6);
		citationRepository.save(c7);
		citationRepository.save(c8);
		citationRepository.save(c9);
		citationRepository.save(c10);
		citationRepository.save(c11);*/

		
		
		
		
		//Initialization of the users of the application

		userRepository.save(new User("juan","4321","ROLE_STUDENT"));
		userRepository.save(new User("pedro","1234","ROLE_STUDENT"));
		userRepository.save(new User("admin","admin","ROLE_ADMIN","ROLE_USER","ROLE_STUDENT"));
		
		//Here code to test the DDBB
		List<Author> authors = authorService.findAll(); 
		System.out.println("Authors found with findAll()");
		System.out.println("----------------------------");
		for(Author a: authors) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
		List<Book> books = bookService.findAll(); 
		System.out.println("Books found with findAll()");
		System.out.println("----------------------------");
		for(Book a: books) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
		List<Theme> themes = themeService.findAll(); 
		System.out.println("Themes found with findAll()");
		System.out.println("----------------------------");
		for(Theme a: themes) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
		List<Citation> citations = citationService.findAll(); 
		System.out.println("Citations found with findAll()");
		System.out.println("----------------------------");
		for(Citation a: citations) {
			System.out.println(a);
		}
		System.out.println("----------------------------");
	}
}
