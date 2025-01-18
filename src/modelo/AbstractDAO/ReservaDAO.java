package modelo.AbstractDAO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import modelo.Entity.Reserva;

public interface ReservaDAO extends GenericDAO<Reserva, Long> {
	public Collection<Reserva> findByPersonaID(Long personaID);
	public Collection<Reserva> findByHotelID(Long hotelID);
	public Collection<Reserva> findByDate(LocalDate date);
}
