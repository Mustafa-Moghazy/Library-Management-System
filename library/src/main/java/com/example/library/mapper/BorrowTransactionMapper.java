package com.example.library.mapper;

import com.example.library.dto.BorrowTransactionDTO;
import com.example.library.entity.BorrowTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowTransactionMapper {
    @Mapping(source = "member.userName", target = "userName")
    @Mapping(source = "book.title", target = "bookTitle")
    BorrowTransactionDTO toDto(BorrowTransaction borrowTransaction);

}
