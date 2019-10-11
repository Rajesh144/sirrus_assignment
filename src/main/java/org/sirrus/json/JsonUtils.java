package org.sirrus.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public JsonUtils(int x) {

	}

	public static String writeValueAsString(Object object) throws JsonUtilException {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static byte[] writeValueAsBytes(Object object) throws JsonUtilException {
		try {
			return objectMapper.writeValueAsBytes(object);
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T readValue(String s, Class<T> clazz) throws JsonUtilException {
		try {
			if (s != null && !"".equals(s.trim())) {
				return objectMapper.readValue(s.trim(), clazz);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T readValue(byte[] data, Class<T> clazz) throws JsonUtilException {
		try {
			if (data != null && data.length > 0) {
				return objectMapper.readValue(data, clazz);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T readValue(String s, TypeReference<T> typeReference) {
		try {
			if (s != null && !"".equals(s.trim())) {
				return objectMapper.readValue(s.trim(), typeReference);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T readValue(byte[] data, TypeReference<T> typeReference) {
		try {
			if (data != null && data.length > 0) {
				return objectMapper.readValue(data, typeReference);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T convertValue(Object object, Class<T> toClazz) throws JsonUtilException {
		try {
			if (object == null) {
				return null;
			} else {
				return objectMapper.convertValue(object, toClazz);
			}
		} catch (IllegalArgumentException e) {
			throw new JsonUtilException(e);
		}
	}

	public static JsonNode readTree(JsonParser p) {
		try {
			return objectMapper.readTree(p);
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static JsonNode readTree(String s) {
		try {
			if (s != null && !"".equals(s.trim())) {
				return objectMapper.readTree(s.trim());
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new JsonUtilException(e);
		}
	}

	public static class JsonUtilException extends RuntimeException {

		private static final long serialVersionUID = -4804245225960963421L;

		public JsonUtilException(Throwable cause) {
			super(cause);
		}
	}

}
