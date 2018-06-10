package br.com.rfasioli.ContentGenerator.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Fasioli
 */
public class StatementDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String field;
	private String operator;
	private List<String> values = new ArrayList<>();
	
	public StatementDto() {
		super();		
	}
	
	public StatementDto(
			String field,
			String operator) 
	{
		super();
		this.field = field;
		this.operator = operator;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "StatementDto [field=" + field + ", operator=" + operator + ", values=" + values + "]";
	}
	
}
