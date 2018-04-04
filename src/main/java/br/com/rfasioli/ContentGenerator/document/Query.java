package br.com.rfasioli.ContentGenerator.document;

import java.util.List;

import javax.validation.constraints.Null;

/***
 * 
 * @author rodrigofasioli
 *
 */
public class Query {
	@Null
	private String operator;
	
	private Statement statement;
	
	@Null
	private List<?> subQueries;
	
	public Query() {
		super();
	}
	
	public Query(
			String operator,
			Statement statement,
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

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public List<?> getSubQueries() {
		return subQueries;
	}

	public void setSubQuery(List<?> subQueries) {
		this.subQueries = subQueries;
	}
	
}
