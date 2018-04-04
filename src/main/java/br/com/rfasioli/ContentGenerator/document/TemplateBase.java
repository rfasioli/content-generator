package br.com.rfasioli.ContentGenerator.document;

import java.util.Arrays;

public class TemplateBase {

	protected String description;
	protected Rule[] rules;
	protected String[] fragments;
	
	public TemplateBase() {
		super();		
	}

	public TemplateBase(
			String description,
			Rule[] rules,
			String[] fragments) {
		super();
		this.description = description;
		this.rules = rules;
		this.fragments = fragments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rule[] getRules() {
		return rules;
	}

	public void setRules(Rule[] rules) {
		this.rules = rules;
	}

	public String[] getFragments() {
		return fragments;
	}

	public void setFragments(String[] fragments) {
		this.fragments = fragments;
	}

	@Override
	public String toString() {
		return "TemplateBase [description=" + description + ", rules=" + Arrays.toString(rules) + ", fragments="
				+ Arrays.toString(fragments) + "]";
	}
	
}
