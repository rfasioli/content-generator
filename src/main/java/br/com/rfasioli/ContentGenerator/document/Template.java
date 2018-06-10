package br.com.rfasioli.ContentGenerator.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.rfasioli.ContentGenerator.dto.TemplateNestedDto;

/**
 * @author Rodrigo Fasioli
 */
@Document(collection = "template")
public class Template extends TemplateBase {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private List<TemplateNestedDto> nested = new ArrayList<>();
	
	public Template() { 
		super(); 
	}

	public Template(String description) {
		super(description);
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", nested=" + nested + ", description=" + description + ", rules=" + rules
				+ ", tags=" + tags + ", fragments=" + fragments + "]";
	}
	
}
