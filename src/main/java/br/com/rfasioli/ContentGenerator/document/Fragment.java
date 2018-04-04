package br.com.rfasioli.ContentGenerator.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/***
 * Represent a fragment of content
 * 
 * @author rodrigofasioli
 *
 */
@Document(collection = "fragment")
public class Fragment {
	@Id
	private String id;

	private String description;
	private String content;


	public Fragment() {
		super();
	}

	public Fragment(String id, String description, String content) {
		super();
		this.id = id;
		this.description = description;
		this.content = content;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Fragment [id=" + id + ", description=" + description + ", content=" + content + "]";
	}

}
