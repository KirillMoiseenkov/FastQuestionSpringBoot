package CreationShip.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question_id;

    @Column(name = "message", nullable = false)
    private String message;

    public Message(){};

    public Message(Question question_id, String message) {
        this.question_id = question_id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Question question_id) {
        this.question_id = question_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", question_id=" + question_id +
                ", message='" + message + '\'' +
                '}';
    }
}
