package br.com.rfasioli.ContentGenerator.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/***
 * Represent a fragment of content
 * @author Rodrigo Fasioli
 */
@Document(collection = "fragment")
public class Fragment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;
	private String content;
	private List<String> tags = new ArrayList<>(); 

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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fragment other = (Fragment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fragment [id=" + id + ", description=" + description + ", content=" + content + ", tags=" + tags + "]";
	}


}
