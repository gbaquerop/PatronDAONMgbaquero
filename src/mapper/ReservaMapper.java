package mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import modelo.Entity.Reserva;

public class ReservaMapper implements Mapper<ResultSet, Reserva> {

	@Override
	public Reserva map(ResultSet s) {
		try {
			while (s.next()) {
				Long reserva_id = (Long) s.getObject(1);
				Date date = (Date) s.getObject(2);
				Long hotel_id = (Long) s.getObject(3);
				Long persona_id = (Long) s.getObject(4);
				
				return new Reserva(reserva_id, persona_id, hotel_id, new LocalDateMapper().map(date));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
