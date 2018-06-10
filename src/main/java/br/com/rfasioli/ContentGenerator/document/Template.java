package br.com.rfasioli.ContentGenerator.document;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.rfasioli.ContentGenerator.dto.RuleDto;
import br.com.rfasioli.ContentGenerator.dto.TemplateBaseDto;
import br.com.rfasioli.ContentGenerator.dto.TemplateNestedDto;

/**
 * @author Rodrigo Fasioli
 */
@Document(collection = "template")
public class Template extends TemplateBaseDto {

	@Id
	private String id;
	
	private List<TemplateNestedDto> nested;
	
	public Template() { 
		super(); 
	}

	public Template(
			String description,
			RuleDto[] rules,
			String[] fragments,
			List<TemplateNestedDto> nested,
			List<String> tags) {
		super(description, rules, fragments, tags);
		this.nested = nested;
	}

	public Template(
			String description, 
			RuleDto[] rules, 
			String[] fragments,
			List<String> tags) {
		super(description, rules, fragments, tags);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<TemplateNestedDto> getNested() {
		return nested;
	}

	public void setNested(List<TemplateNestedDto> nested) {
		this.nested = nested;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", nested=" + nested + ", description=" + description + ", rules="
				+ Arrays.toString(rules) + ", fragments=" + Arrays.toString(fragments) + "]";
	}	

	
}
