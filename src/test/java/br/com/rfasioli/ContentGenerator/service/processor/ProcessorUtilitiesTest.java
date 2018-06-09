package br.com.rfasioli.ContentGenerator.service.processor;

import static org.junit.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class ProcessorUtilitiesTest {

	@Test
	public void testReplaceTags() throws JSONException {
		String source = "Texto com conteúdo para replace <%=tag.valor%> <%=tag.outrovalor%> <%=valor%>";
		JSONObject obj = new JSONObject("{\"tag\": {\"valor\": \"primeiro\", \"outrovalor\": \"segundo\"}, \"valor\": \"último\"}");
		String result = ProcessorUtilities.replaceTags(source, obj);
		assertTrue(result.equals("Texto com conteúdo para replace primeiro segundo último"));
	}

	@Test
	public void testGetValueFromJsonString() throws JSONException {
		JSONObject obj = new JSONObject("{\"tag\": {\"valor\": \"primeiro\", \"outrovalor\": \"segundo\"}, \"valor\": \"último\"}");
		String result = ProcessorUtilities.getValueFromJsonString(obj, "tag.outrovalor");
		assertTrue(result.equals("segundo"));
	}

	@Test
	public void testGetValueFromJsonString_ValueNotFound() throws JSONException {
		JSONObject obj = new JSONObject("{\"tag\": {\"valor\": \"primeiro\", \"outrovalor\": \"segundo\"}, \"valor\": \"último\"}");
		String result = ProcessorUtilities.getValueFromJsonString(obj, "tag.inexistente");
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetValueFromJsonString_TagNotFound() throws JSONException {
		JSONObject obj = new JSONObject("{\"tag\": {\"valor\": \"primeiro\", \"outrovalor\": \"segundo\"}, \"valor\": \"último\"}");
		String result = ProcessorUtilities.getValueFromJsonString(obj, "x.valor");
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetValueFromJsonString_NullPointerJSON() throws JSONException {
		String result = ProcessorUtilities.getValueFromJsonString(null, "x.inexistente");
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testGetValueFromJsonString_NullPointerTag() throws JSONException {
		JSONObject obj = new JSONObject("{\"tag\": {\"valor\": \"primeiro\", \"outrovalor\": \"segundo\"}, \"valor\": \"último\"}");
		String result = ProcessorUtilities.getValueFromJsonString(obj, null);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetJsonArrayObj() throws JSONException {
		JSONObject obj = new JSONObject("{\"lista\": [1, 2, 3, 4, 5]}");
		JSONArray array = ProcessorUtilities.getJsonArrayObj(obj, "lista");
		assertTrue(array.toString().equals("[1,2,3,4,5]"));
	}

	@Test
	public void testGetJsonArrayObj_NotFound() throws JSONException {
		JSONObject obj = new JSONObject("{\"lista\": [1, 2, 3, 4, 5]}");
		JSONArray array = ProcessorUtilities.getJsonArrayObj(obj, "lista2");
		assertTrue(array == null);
	}
}
