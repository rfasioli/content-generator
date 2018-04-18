package br.com.rfasioli.ContentGenerator.document;

import java.util.List;

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
	private List<String> tags; 

	public Fragment() {
		super();
	}

	public Fragment(String id, String description, String content, List<String> tags) {
		super();
		this.id = id;
		this.description = description;
		this.content = content;
		this.tags = tags;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Fragment [id=" + id + ", description=" + description + ", content=" + content + ", tags=" + tags + "]";
	}

}
