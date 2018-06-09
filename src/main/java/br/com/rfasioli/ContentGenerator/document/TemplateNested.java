package br.com.rfasioli.ContentGenerator.document;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Null;

/**
 * @author Rodrigo Fasioli
 */
public class TemplateNested extends TemplateBase {

	private String action;
	private String reference;
	@Null
	private List<?> nested;
	
	public TemplateNested() { 
		super(); 
	}

	public TemplateNested(
			String description,
			Rule[] rules,
			String[] fragments,
			String action,
			String reference,
			List<?> nested,
			List<String> tags) {
		super(description, rules, fragments, tags);
		this.action = action;
		this.reference = reference;
		this.nested = nested;
	}
	
	public TemplateNested(
			String description, 
			Rule[] rules, 
			String[] fragments,
			List<String> tags) {
		super(description, rules, fragments, tags);
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
		return "TemplateNested [action=" + action + ", reference=" + reference + ", nested=" + nested + ", description="
				+ description + ", rules=" + Arrays.toString(rules) + ", fragments=" + Arrays.toString(fragments) + "]";
	}	
	
}
