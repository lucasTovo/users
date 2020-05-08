package orion.user.model;


//import java.util.Set;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// import javax.persistence.CascadeType;
// import javax.persistence.FetchType;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
	private String name;
	private String email;
    



}