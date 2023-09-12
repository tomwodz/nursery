package pl.tomwodz.nursery.domain.information;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomwodz.nursery.domain.user.dto.SimpleUserQueryDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tinformation")
class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String title;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String content;

    @ManyToOne
    private SimpleUserQueryDto author;

    Information (LocalDateTime dateCreation, String title, String content, SimpleUserQueryDto author) {
        this.dateCreation = dateCreation;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
