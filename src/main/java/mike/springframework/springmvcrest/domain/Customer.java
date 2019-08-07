package mike.springframework.springmvcrest.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    //POJOs
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setId(Long id){ this.id = id;}

    public Long getId(){ return this.id; }

    public String getFirstname(){ return this.firstname; }

    public String getLastname() { return this.lastname; }

}
