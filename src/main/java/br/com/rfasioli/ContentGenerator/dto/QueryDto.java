package br.com.rfasioli.ContentGenerator.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Null;

/**
 * @author Rodrigo Fasioli
 */
public class QueryDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Null
	private String operator;
	
	private StatementDto statement;
	
	@Null
	private List<?> subQueries;
	
	public QueryDto() {
		super();
	}
	
	public QueryDto(
			String operator,
			StatementDto statement)
	{
		super();
		this.operator = operator;
		this.statement = statement;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public StatementDto getStatement() {
		return statement;
	}

	public void setStatement(StatementDto statement) {
		this.statement = statement;
	}

	public List<?> getSubQueries() {
		return subQueries;
	}

	public void setSubQuery(List<?> subQueries) {
		this.subQueries = subQueries;
	}
	
}
