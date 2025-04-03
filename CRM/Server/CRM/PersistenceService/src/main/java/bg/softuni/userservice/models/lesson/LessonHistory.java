package bg.softuni.userservice.models.lesson;

import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lesson_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    @OneToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @OneToMany(mappedBy = "lessonHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;
}
