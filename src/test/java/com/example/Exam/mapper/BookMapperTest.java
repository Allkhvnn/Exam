package com.example.Exam.mapper;

import com.example.Exam.dto.BookDto;
import com.example.Exam.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertEntityToDtoTest() {

        Book entityBook = new Book();
        entityBook.setId(1L);
        entityBook.setTitle("Test title");
        entityBook.setYear(2024);

        // when
        BookDto dtoBook = bookMapper.toDto(entityBook);

        Assertions.assertNotNull(dtoBook);

        Assertions.assertNotNull(dtoBook.getId());
        Assertions.assertNotNull(dtoBook.getTitle());


        Assertions.assertEquals(entityBook.getId(), dtoBook.getId());
        Assertions.assertEquals(entityBook.getTitle(), dtoBook.getTitle());
        Assertions.assertEquals(entityBook.getYear(), dtoBook.getYear());
    }

    @Test
    void convertDtoToEntityTest() {

        BookDto dtoBook = new BookDto();
        dtoBook.setId(10L);
        dtoBook.setTitle("DTO title");
        dtoBook.setYear(1999);

        Book entityBook = bookMapper.toEntity(dtoBook);

        // then
        Assertions.assertNotNull(entityBook);

        Assertions.assertNotNull(entityBook.getId());
        Assertions.assertNotNull(entityBook.getTitle());

        Assertions.assertEquals(dtoBook.getId(), entityBook.getId());
        Assertions.assertEquals(dtoBook.getTitle(), entityBook.getTitle());
        Assertions.assertEquals(dtoBook.getYear(), entityBook.getYear());
    }

    @Test
    void convertListEntityToListDtoTest() {

        List<Book> entityList = new ArrayList<>();

        Book b1 = new Book();
        b1.setId(1L);
        b1.setTitle("Book 1");
        b1.setYear(2001);

        Book b2 = new Book();
        b2.setId(2L);
        b2.setTitle("Book 2");
        b2.setYear(2002);

        Book b3 = new Book();
        b3.setId(3L);
        b3.setTitle("Book 3");
        b3.setYear(2003);

        entityList.add(b1);
        entityList.add(b2);
        entityList.add(b3);

        List<BookDto> dtoList = bookMapper.toDtoList(entityList);


        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < entityList.size(); i++) {
            Book entityBook = entityList.get(i);
            BookDto dtoBook = dtoList.get(i);

            Assertions.assertNotNull(dtoBook);
            Assertions.assertEquals(entityBook.getId(), dtoBook.getId());
            Assertions.assertEquals(entityBook.getTitle(), dtoBook.getTitle());
            Assertions.assertEquals(entityBook.getYear(), dtoBook.getYear());
        }
    }
}


