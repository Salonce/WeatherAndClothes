package Salonce.WeatherWardrobe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String userId;

    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(0)
    @Max(1000)
    private BigDecimal weight;
}