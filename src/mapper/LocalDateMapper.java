package mapper;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateMapper implements Mapper<Date, LocalDate> {

	@Override
	public LocalDate map(Date s) {
		return LocalDate.of(s.getYear(), s.getMonth(), s.getDay());
	}

}
