package modelo.ImplDAOJDBC;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import mapper.DateMapper;
import mapper.ReservaAdapter;
import mapper.ReservaMapper;
import modelo.AbstractDAO.ReservaDAO;
import modelo.Entity.Reserva;
import modelo.acceso.AccessJdbc;

public class ReservaDAOJdbc implements ReservaDAO {

	private AccessJdbc accessJdbc;
	private ReservaMapper reservaMapper;

	public ReservaDAOJdbc(AccessJdbc accessJdbc) {
		super();
		this.accessJdbc = accessJdbc;
		this.reservaMapper = new ReservaMapper();
	}

	@Override
	public void create(Reserva entidad) {
		String sql = "INSERT INTO " + accessJdbc.getBBDD() + ".reserva (persona_id, hotel_id, fecha) values (?, ?, ?);";
		accessJdbc.update(sql, new ReservaAdapter(entidad));
	}

	@Override
	public Collection<Reserva> findAll() {
		String sql = "SELECT * FROM reserva";
		ResultSet execute = accessJdbc.execute(sql);
		Collection<Reserva> reserva = new ArrayList<>();
		try {
			while (execute.next()) {
				reserva.add(reservaMapper.map(execute));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reserva;
	}

	@Override
	public Reserva findById(Long id) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE id LIKE " + id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		return reservaMapper.map(conjuntoResultados);
	}

	@Override
	public Reserva delete(Long id) {
		Reserva byId = findById(id);
		if (byId != null) {
			String sql = "delete from " + accessJdbc.getBBDD() + ".reserva where id = " + String.valueOf(id);
			accessJdbc.executeUpdate(sql);
			return byId;
		}
		return null;
	}

	@Override
	public Collection<Reserva> findByPersonaID(Long personaID) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE persona_id LIKE " + personaID;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		Collection<Reserva> reserva = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				reserva.add(reservaMapper.map(conjuntoResultados));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserva;
	}

	@Override
	public Collection<Reserva> findByHotelID(Long hotelID) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE hotel_id LIKE " + hotelID;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		Collection<Reserva> reserva = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				reserva.add(reservaMapper.map(conjuntoResultados));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserva;
	}

	@Override
	public Collection<Reserva> findByDate(LocalDate date) {
		//Las fechas solo pueden tratarse usando el signo = en vez de 'like'
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE fecha=" + date.toString();
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		Collection<Reserva> reserva = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				reserva.add(reservaMapper.map(conjuntoResultados));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserva;
	}

}
