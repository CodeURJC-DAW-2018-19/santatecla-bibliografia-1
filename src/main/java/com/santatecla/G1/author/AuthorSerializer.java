package com.santatecla.G1.author;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.citation.Citation;
import com.santatecla.G1.user.UserComponent;

public class AuthorSerializer extends StdSerializer<Author>{
	
	@Autowired
	private UserComponent userComponent;

	public AuthorSerializer() {
		this(null);
	}
	
	public AuthorSerializer(Class<Author> t) {
		super(t);
	}
	
	@Override
	public void serialize(Author value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
		gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("itemName", value.getName());
        if(userComponent.isLoggedUser())
        	if(userComponent.getLoggedUser().getRoles().contains("STUDENT")||userComponent.getLoggedUser().getRoles().contains("ADMIN")) {
        		gen.writeStringField("urlImage",value.getUrlImage());
        		gen.writeStringField("birthDate",value.getBirthDate());
        		gen.writeStringField("deathDate",value.getDeathDate());
        		gen.writeStringField("bornPlace",value.getBornPlace());
        		gen.writeStringField("urlMap",value.getUrlMap());
                gen.writeNumberField("imgId", value.getImgId());
                
                gen.writeFieldName("books");
                gen.writeStartObject();
                gen.writeStartArray();
                for(Book b:value.getBooks()) {
                	gen.writeStartObject();
                	
                	gen.writeNumberField("id", b.getId());
                	gen.writeStringField("title", b.getTitle());
                	gen.writeStringField("publishDate", b.getPublishDate());
                	gen.writeStringField("nameEdit", b.getNameEdit());
                	gen.writeStringField("urlEdit", b.getUrlEdit());
                	gen.writeStringField("urlImgCoverPage", b.getUrlImgCoverPage());
                	gen.writeStringField("urlImgEdit", b.getUrlImgEdit());
                	gen.writeNumberField("imgId", b.getImgId());
                	
                	gen.writeFieldName("theme");
                	gen.writeStartObject();
                	gen.writeNumberField("id", b.getTheme().getId());
                	gen.writeStringField("name", b.getTheme().getName());
                	gen.writeEndObject();
                	
                	gen.writeFieldName("citation");
                	gen.writeStartObject();
                	gen.writeStartArray();
                	for(Citation c:b.getCitations()) {
                		gen.writeStartObject();
                		gen.writeNumberField("id", c.getId());
                		gen.writeStringField("text", c.getText());
                		gen.writeStringField("textAux", c.getTextAux());
                		gen.writeEndObject();
                	}
                	gen.writeEndArray();
                	gen.writeEndObject();
                	
                	gen.writeEndObject();
                }
                gen.writeEndArray();
                gen.writeEndObject();
        	}
        gen.writeEndObject();
	}
}
