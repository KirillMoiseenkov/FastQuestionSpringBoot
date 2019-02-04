package CreationShip.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language
{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int language_id;

    @Column(name = "language_name",unique = true, nullable = false)
    private String language_name;

    @OneToMany(mappedBy = "language_id")
    private Set<Question>  questions;

    public Language(){}

    public Language(String language_name) {
        this.language_name = language_name;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language_name='" + language_name + '\'' +
                '}';
    }
}
