package br.com.rfasioli.ContentGenerator.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Null;

import br.com.rfasioli.ContentGenerator.document.TemplateBase;

/**
 * @author Rodrigo Fasioli
 */
public class TemplateNestedDto extends TemplateBase {
	private static final long serialVersionUID = 1L;
	
	private String action;
	private String reference;

	@Null
	private List<?> nested = new ArrayList<>();
	
	public TemplateNestedDto() { 
		super(); 
	}

	public TemplateNestedDto(
			String description,
			String action,
			String reference)
	{
		super(description);
		this.action = action;
		this.reference = reference;
	}
	
	public TemplateNestedDto(String description) {
		super(description);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<?> getNested() {
		return nested;
	}

	public void setNested(List<?> nested) {
		this.nested = nested;
	}

	@Override
	public String toString() {
		return "TemplateNestedDto [action=" + action + ", reference=" + reference + ", nested=" + nested
				+ ", description=" + description + ", rules=" + rules + ", tags=" + tags + ", fragments=" + fragments
				+ "]";
	}

}
 