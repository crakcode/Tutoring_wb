package tutoring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tutoring.security.User;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Review")
public class Review {
	
	@Id 
	@GeneratedValue
	@Column(name = "r_id")
	private Long rId;
	
	private String comment;
	
	private int score;
	
	@ManyToOne
	@JoinColumn(name = "l_id")
	private Lecture lecture;	

	
	@ManyToOne
	@JoinColumn(name = "u_id")
	private User user;	
}
