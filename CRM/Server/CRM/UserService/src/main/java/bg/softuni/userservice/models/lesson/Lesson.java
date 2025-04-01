package bg.softuni.userservice.models.lesson;

import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.enums.LessonStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @Column(nullable = false)
    private LocalDate date;  // Track when the lesson happens

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private int academicHours;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LessonStatus status;

    private String subject; // Optional: Topic of the lesson

    @ManyToOne
    @JoinColumn(name = "lesson_history_id")
    private LessonHistory lessonHistory;
}
