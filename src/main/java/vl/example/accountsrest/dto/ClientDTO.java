package vl.example.accountsrest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import vl.example.accountsrest.entity.Status;

import java.time.LocalDateTime;

@Data
@Builder
public class ClientDTO {

    private Integer id;

    @NotEmpty(message = "Field Name should not be empty")
    @Size(min = 2, max = 50, message = "Field Name should be between 2 and 50 characters")
    private String name;

    @Email(message = "Field Email should be well-formed")
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;
}
