package br.com.rfasioli.ContentGenerator.document;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "template")
public class Template extends TemplateBase {

	@Id
	private String id;
	
	private List<TemplateNested> nested;
//	private TemplateNested nested;
	
	public Template() { 
		super(); 
	}

	public Template(
			String description,
			Rule[] rules,
			String[] fragments,
			List<TemplateNested> nested) {
		super(description, rules, fragments);
		this.nested = nested;
	}

	public Template(
			String description, 
			Rule[] rules, 
			String[] fragments) {
		super(description, rules, fragments);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<TemplateNested> getNested() {
		return nested;
	}

	public void setNested(List<TemplateNested> nested) {
		this.nested = nested;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", nested=" + nested + ", description=" + description + ", rules="
				+ Arrays.toString(rules) + ", fragments=" + Arrays.toString(fragments) + "]";
	}	

	
}
