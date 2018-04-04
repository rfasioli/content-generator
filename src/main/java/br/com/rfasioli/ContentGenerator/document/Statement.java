package br.com.rfasioli.ContentGenerator.document;

import java.util.Arrays;

public class Statement {
	private String field;
	private String operator;
	private String[] values;
	
	public Statement() {
		super();		
	}
	
	public Statement(
			String field,
			String operator,
			String[] values) {
		super();
		this.field = field;
		this.operator = operator;
		this.values = values;
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
	
	public String[] getValues() {
		return values;
	}
	
	public void setValues(String[] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Statement [field=" + field + ", operator=" + operator + ", values=" + Arrays.toString(values) + "]";
	}

}
