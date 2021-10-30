package com.thiagofurlan.springmongodb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QueryParamsDecoder {
	public static String decode(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
