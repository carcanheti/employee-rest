package br.com.register.employee.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserLog implements Serializable{

 
	private static final long serialVersionUID = 7228040480317402788L;

	@Column(name = "HOR_INCL", nullable = false)
	private LocalDateTime createAt;

	
	@Column(name = "COD_USUAR_INCL")
	private String createdBy;
				   
	
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

 
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}
