package mapper;

import java.sql.Date;
import java.time.LocalDate;

public class DateMapper implements Mapper<LocalDate, Date> {

	@Override
	public Date map(LocalDate s) {
		Date date = new Date(s.getYear(), s.getMonthValue(), s.getDayOfMonth());
		return date;
	}

}
