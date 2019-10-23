package pl.j25.cloud.bookservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private Long id;
    private String title;
    private String author;
    private Integer yearWritten;
    private Integer numberOfPages;
    private Integer numberOfAvailableCopies;
}
