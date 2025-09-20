package com.example.library.mapper;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Entity → ResponseDTO
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "publisher.publisherName", target = "publisherName")
    @Mapping(source = "authors", target = "authors")
    BookResponseDTO toBookDTO(Book book);

    // RequestDTO → Entity
    @Mapping(source = "categoryName", target = "category.categoryName")
    @Mapping(source = "publisherName", target = "publisher.publisherName")
    @Mapping(source = "authors", target = "authors")
    Book toEntity(BookRequestDTO dto);

    // 🔹 Convert Author -> String (AuthorName)
    default List<String> mapAuthorsToStrings(List<Author> authors) {
        if (authors == null) return null;
        return authors.stream()
                .map(Author::getAuthorName)
                .toList();
    }

    // 🔹 Convert String -> Author
    default List<Author> mapStringsToAuthors(List<String> names) {
        if (names == null) return null;
        return names.stream()
                .map(name -> {
                    Author author = new Author();
                    author.setAuthorName(name);
                    return author;
                })
                .toList();
    }
}
