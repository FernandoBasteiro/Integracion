package controladores;

public class ConversorFechas {
	public static java.time.LocalDate convertJodaToJava (org.joda.time.LocalDate jodaTime) {
		if (jodaTime != null) return java.time.LocalDate.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth());
		return null;
	}
	
	public static org.joda.time.LocalDate convertJavaToJoda (java.time.LocalDate javaTime) {
		if (javaTime != null) return new org.joda.time.LocalDate(javaTime.getYear(), javaTime.getMonthValue(), javaTime.getDayOfMonth());
		return null;
	}

}
