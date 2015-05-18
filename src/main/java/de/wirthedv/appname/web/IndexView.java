package de.wirthedv.appname.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.wirthedv.bone.jsf.ViewScopedComponent;

@ViewScopedComponent("indexView")
public class IndexView implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private List<String> submittedValues = new ArrayList<>();
    
	private String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

    public List<String> getSubmittedValues() {
        return submittedValues;
    }

    public void setSubmittedValues(List<String> submittedValues) {
        this.submittedValues = submittedValues;
    }
	
}
