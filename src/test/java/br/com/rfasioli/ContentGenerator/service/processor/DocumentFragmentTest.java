package br.com.rfasioli.ContentGenerator.service.processor;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DocumentFragmentTest {

	DocumentFragment fragment;
	
	@Before
	public void setUp() {
		this.fragment = new DocumentFragment(1, 2, "fragmento");
	}
	
	@Test
	public void testGetStart() {
		assertTrue(this.fragment.getStart() == 1);
	}

	@Test
	public void testSetStart() {
		this.fragment.setStart(0);
		assertTrue(this.fragment.getStart() == 0);
	}

	@Test
	public void testGetEnd() {
		assertTrue(this.fragment.getEnd() == 2);
	}

	@Test
	public void testSetEnd() {
		this.fragment.setEnd(5);
		assertTrue(this.fragment.getEnd() == 5);
	}

	@Test
	public void testGetFragment() {
		assertTrue(this.fragment.getFragment().equals("fragmento"));
	}

	@Test
	public void testSetFragment() {
		this.fragment.setFragment(new String());
		assertTrue(this.fragment.getFragment().isEmpty());
	}

}
