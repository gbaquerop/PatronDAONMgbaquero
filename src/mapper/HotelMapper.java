package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Entity.Hotel;

public class HotelMapper implements Mapper<ResultSet, Hotel> {

	@Override
	public Hotel map(ResultSet s) {
		try {
			while(s.next()) {
				Long id = (Long) s.getObject(1);
				String name = (String) s.getObject(2);
				return new Hotel(id, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
