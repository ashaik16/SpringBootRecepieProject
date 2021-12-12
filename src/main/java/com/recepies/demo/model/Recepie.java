package com.recepies.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.recepies.demo.Enum.TodoStatus;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recepie {
	 @Id
	    @GeneratedValue
	    @Column(updatable = false, nullable = false)
	    Long id;
	    @Column
	    String name;
	    @Convert(converter = StringListConverter.class)
	    private List<String> ingredients;
	    //@Convert(converter = StringListConverter.class)
	    @ElementCollection  
	    private List<String> instructions;
//	    @Column
//	    TodoStatus todoStatus;
//	    
//	    @CreationTimestamp
//	    @Column(updatable = false)
//	    Timestamp dateCreated;
//	    @UpdateTimestamp
//	    Timestamp lastModified;
}
