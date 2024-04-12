package b2bapp.b2bappbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subcategories")
public class SubcategoryEntity {
    @Id
    @Column(name = "subcategory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
