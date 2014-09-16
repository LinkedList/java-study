/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martin.models.fobjects;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author martin
 */
public class FBook {

	@NotEmpty
	private String title;
	
	private String description;	

	public FBook() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
