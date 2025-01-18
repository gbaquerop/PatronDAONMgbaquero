package modelo.AbstractDAO;

import modelo.Entity.Hotel;

public interface HotelDAO extends GenericDAO<Hotel, Long> {
	public Hotel findByName(String name);
}
