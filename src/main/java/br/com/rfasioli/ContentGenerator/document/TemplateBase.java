package br.com.rfasioli.ContentGenerator.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import br.com.rfasioli.ContentGenerator.dto.RuleDto;

/**
 * @author Rodrigo Fasioli
 */
public class TemplateBase implements Serializable{
	private static final long serialVersionUID = 1L;

	protected String description;
	protected List<RuleDto> rules = new ArrayList<>();
	protected List<String> tags = new ArrayList<>(); 
	
	@DBRef(lazy=true)
	protected List<Fragment> fragments = new ArrayList<>();
		
	public TemplateBase() {
		super();		
	}

	public TemplateBase(
			String description)
	{
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RuleDto> getRules() {
		return rules;
	}

	public void setRules(List<RuleDto> rules) {
		this.rules = rules;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Fragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
	}

	@Override
	public String toString() {
		return "TemplateBase [description=" + description + ", rules=" + rules + ", tags=" + tags + ", fragments="
				+ fragments + "]";
	}

}
