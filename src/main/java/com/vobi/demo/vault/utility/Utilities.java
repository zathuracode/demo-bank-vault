package com.vobi.demo.vault.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* @author Zathura Code Generator Version 23.10 http://zathuracode.org/
* 
* @generationDate 2024-09-19T17:02:34.813843
* 
*/
public class Utilities {

	private Utilities() {
    	throw new IllegalStateException("Utility class");
  	}

	public static boolean isNumeric(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("[^0-9',.\\s]");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 
	 * @param word
	 * @return Expresion regular "(\\d){1,10}\\.(\\d){1,10}" (\\d)digito
	 *         {1,10}de 1 a 10 caracteres \\. punto
	 * 
	 */
	public static boolean isDecimal(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("(\\d){1,8}\\.(\\d){0,2}");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}

	public static boolean checkWordAndCheckWithlength(String word,
			Integer length) {
		boolean ret = false;
		if (word.length() <= length) {
			ret = true;
		}
		return ret;
	}

	public static boolean isOnlyLetters(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("[^A-Za-z0-9',.\\s]");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}

	public static boolean validationsList(List<?> list) {
		return list != null && !list.isEmpty();
	}
	
	/**
	 * @author Camilo Puente
	 * @author Frank Edward Daza Gonz�lez
	 * @date Nov 1, 2017
	 * @param lblName
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public static String errorComponentLogic(String lblName, Locale locale) throws IOException{
		InputStream is = null;
		is = Utilities.class.getResourceAsStream("/i18n/messages_" + locale.getLanguage() + ".properties");
		
		String exception="";
		Properties p = new Properties();
		p.load(is);
		exception = p.getProperty(lblName);
		return exception;
	}
	
	
	/**
	 * @author Johan Steve Bejarano Fiesco
	 * @version 2018/06/05
	 * @param lblName
	 * @param locale
	 * @param params
	 * @return {@code String }
	 * @throws Exception
	 */
	public static String errorComponentLogic(String lblName, Locale locale, String... params) throws IOException{
		InputStream is = null;
		is = Utilities.class.getResourceAsStream("/i18n/messages_" + locale.getLanguage() + ".properties");
		
		String exception="";
		Properties p = new Properties();
		p.load(is);
		exception = p.getProperty(lblName);
		
		if (exception!=null && params!=null && params.length>0) {
			for(int i=0;i<params.length; i++) {
				exception = exception.replaceAll("\\$"+(i+1), params[i]);
			}
		}
		
		return exception;
	}
	
	public static String generarToken() {
		return UUID.randomUUID().toString();		
	}
	
	/**
	 * Retorna una fecha inicial con horas, minutos y segundos: 00:00:00
	 * 
	 * @author Frank Edward Daza Gonz�lez
	 * @date Nov 20, 2017
	 * @param date
	 * @return Date
	 * @throws Exception
	 */
	public static Date buildStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * Retorna una fecha final con horas, minutos y segundos: 23:59:59
	 * 
	 * @author Frank Edward Daza Gonz�lez
	 * @date Nov 20, 2017
	 * @param date
	 * @return Date
	 * @throws Exception
	 */
	public static Date buildFinalDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTime();
	}

	/**
	 * Func��n para redondear un n�mero double a los decimales que le ingresen
	 * 
	 * @author Camilo Delgado
	 * @version Mar 23, 2018
	 * @since 1.8
	 * @param numero
	 * @param decimales
	 * @return
	 *
	 */
	public static double redondear(double numero, int decimales) {
		boolean negativo = false;
		if(numero<0){
			numero *=-1;
			negativo = true;
		}
		double redondeado = Math.round(numero * Math.pow(10, decimales))
                / Math.pow(10, decimales);
        return negativo ? redondeado*-1 : redondeado;
    }

	/**
	 * Funci�n para obtener el n�mero que sea m�ltiplo de "multiploDe"
	 * y mayor que "mayorQue"
	 *
	 * @author Camilo Delgado
	 * @version Mar 24, 2018
	 * @since 1.8
	 * @param multiploDe
	 * @param mayorQue
	 * @return
	 *
	 */
	public static Integer siguienteMultiploMayorOIgual(Integer multiploDe, Integer mayorQue) {
		Double division = mayorQue.doubleValue() / multiploDe.doubleValue();
		division = (Math.ceil(division));
		return division.intValue() * multiploDe;
	}
	
}