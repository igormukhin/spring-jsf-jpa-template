package de.wirthedv.appname.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import de.wirthedv.bone.spring.RequestScopedComponent;

@RequestScopedComponent("indexBean")
public class IndexBean {
    
	@Autowired
	private IndexView indexView;
	
	public void submit() {
	    indexView.getSubmittedValues().add(indexView.getField());
	    
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Value submitted."));
	}
	
	public void reset() {
	    indexView.getSubmittedValues().clear();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Form reset."));
	}
	
}
