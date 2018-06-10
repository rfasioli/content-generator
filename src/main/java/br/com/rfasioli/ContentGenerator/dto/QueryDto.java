package br.com.rfasioli.ContentGenerator.dto;

import java.util.List;

import javax.validation.constraints.Null;

/**
 * @author Rodrigo Fasioli
 */
public class QueryDto {
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
			StatementDto statement,
			List<?> subQueries) {
		super();
		this.operator = operator;
		this.statement = statement;
		this.subQueries = subQueries;
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
