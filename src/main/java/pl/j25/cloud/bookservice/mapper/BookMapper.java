package pl.j25.cloud.bookservice.mapper;

import pl.j25.cloud.bookservice.model.Book;
import pl.j25.cloud.bookservice.model.dto.CreateBookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book createBookFromDto(CreateBookRequest dto);
}
