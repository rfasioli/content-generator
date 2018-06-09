package br.com.rfasioli.ContentGenerator.service.processor;

/**
 * @author rodrigo fasioli
 *
 */
public class DocumentFragment {
	private int start;
	private int end;
	private String fragment;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public String getFragment() {
		return fragment;
	}
	public void setFragment(String fragment) {
		this.fragment = fragment;
	}
	
	public DocumentFragment() {
		super();
	}
	
	public DocumentFragment(int start, int end, String fragment) {
		super();
		this.start = start;
		this.end = end;
		this.fragment = fragment;
	}
	
}
