package com.alysson.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static List<Integer> decodeIntList(String s){
		String[] listString = s.split(",");
		List<Integer> numberList = new ArrayList<>();
		for(String numero: listString) {
			numberList.add(Integer.parseInt(numero));
		}
		return numberList;
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
