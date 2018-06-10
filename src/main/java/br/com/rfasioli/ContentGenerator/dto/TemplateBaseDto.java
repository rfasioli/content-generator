package br.com.rfasioli.ContentGenerator.dto;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rodrigo Fasioli
 */
public class TemplateBaseDto {

	protected String description;
	protected List<RuleDto> rules = new ArrayList<>();
	
	protected String[] fragments;
	protected List<String> tags; 
	
	public TemplateBaseDto() {
		super();		
	}

	public TemplateBaseDto(
			String description,
			RuleDto[] rules,
			String[] fragments,
			List<String> tags)
	{
		super();
		this.description = description;
		this.rules = rules;
		this.fragments = fragments;
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RuleDto[] getRules() {
		return rules;
	}

	public void setRules(RuleDto[] rules) {
		this.rules = rules;
	}

	public String[] getFragments() {
		return fragments;
	}

	public void setFragments(String[] fragments) {
		this.fragments = fragments;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TemplateBase [description=" + description + ", rules=" + Arrays.toString(rules) + ", fragments="
				+ Arrays.toString(fragments) + ", tags=" + tags + "]";
	}
	
}