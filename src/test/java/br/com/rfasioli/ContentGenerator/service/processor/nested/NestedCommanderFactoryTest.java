package br.com.rfasioli.ContentGenerator.service.processor.nested;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rfasioli.ContentGenerator.service.processor.nested.impl.AddAfterNestedCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.nested.impl.AppendNestedCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.nested.impl.ReplaceNestedCommanderImpl;

public class NestedCommanderFactoryTest {

	@Test
	public void testGetNestedCommander_Append() {
		NestedCommander nestedCommander = NestedCommanderFactory.getNestedCommander("APPEND");
		assertTrue(nestedCommander instanceof AppendNestedCommanderImpl);
	}

	@Test
	public void testGetNestedCommander_EmptyAsAppend() {
		NestedCommander nestedCommander = NestedCommanderFactory.getNestedCommander("");
		assertTrue(nestedCommander instanceof AppendNestedCommanderImpl);
	}

	@Test
	public void testGetNestedCommander_nullAsAppend() {
		NestedCommander nestedCommander = NestedCommanderFactory.getNestedCommander(null);
		assertTrue(nestedCommander instanceof AppendNestedCommanderImpl);
	}

	@Test
	public void testGetNestedCommander_Add() {
		NestedCommander nestedCommander = NestedCommanderFactory.getNestedCommander("ADD");
		assertTrue(nestedCommander instanceof AddAfterNestedCommanderImpl);
	}

	@Test
	public void testGetNestedCommander_Replace() {
		NestedCommander nestedCommander = NestedCommanderFactory.getNestedCommander("REPLACE");
		assertTrue(nestedCommander instanceof ReplaceNestedCommanderImpl);
	}
	
}
