package exercise.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

// BEGIN
@Entity
@Data
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    Long id;
    String title;
    String description;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAt;
}
// END
