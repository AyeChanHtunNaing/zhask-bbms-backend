package com.bbms.dto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attachment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	 private String type;

	  @Lob
	  @Column(name="data")
	  private byte[] data;
	
//	connect when we need
//	@ManyToOne
//	private TaskDto tasks;
	
	@ManyToOne
	private TaskDto task;
	
	public AttachmentDto(String name, String type, byte[] data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }

}
