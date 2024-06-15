package com.example.demo.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id //this particular thing is the id of our Run type, needed for ListCrudRepository type
        Integer id,
        @NotEmpty //comes from jakarta.validation.constraints.NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,

        @Version //to keep track if it's an existing one or a new one
        Integer version
)  {

    public Run{
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed on value must be after the started on value!");
        }

    }


}
