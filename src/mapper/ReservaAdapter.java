package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Entity.Reserva;

public class ReservaAdapter implements Adapter {

	private Reserva reserva;

	public ReservaAdapter(Reserva reserva) {
		super();
		this.reserva = reserva;
	}

	@Override
	public void adapt(PreparedStatement instruccion) throws SQLException {
		instruccion.setLong(1, reserva.getIdPersona());
		instruccion.setLong(2, reserva.getIdHotel());
		instruccion.setDate(3, new DateMapper().map(reserva.getFecha()));
	}

}
