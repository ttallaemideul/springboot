package io.github.ttallaemideul.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilDate {
	public static final String PTN_FULL_SSS = "yyyyMMddHHmmssSSS";
	public static final SimpleDateFormat SDF = new SimpleDateFormat(PTN_FULL_SSS, Locale.KOREAN);
	
	public static String getTodayFullSSS() {
		return SDF.format(new Date());
	}
}
