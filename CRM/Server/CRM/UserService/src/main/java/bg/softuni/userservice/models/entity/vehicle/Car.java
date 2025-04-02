package bg.softuni.userservice.models.entity.vehicle;

import bg.softuni.userservice.models.enums.CarAvailability;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarAvailability availability;
}
