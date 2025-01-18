package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Entity.Hotel;
import modelo.Entity.Persona;
import modelo.Entity.Reserva;
import modelo.ImplDAOJDBC.DAOFactoryJDBC;
import modelo.ImplDAOJDBC.HotelDAOJdbc;
import modelo.ImplDAOJDBC.PersonaDAOJdbc;
import modelo.ImplDAOJDBC.ReservaDAOJdbc;
import modelo.acceso.AccessJdbc;

class ReservaDAOJdbcTest {
	static ReservaDAOJdbc reservaDAOJdbc;
	static HotelDAOJdbc hotelDAOJdbc;
	static PersonaDAOJdbc personaDAOJdbc;
	
	
	static AccessJdbc accessJdbc;

	static Persona persona = new Persona(1l, "Juan");
	static Hotel hotel = new Hotel(2l, "Hotel 1");
	static LocalDate date = LocalDate.of(2005, 8, 14);

	Reserva reserva = new Reserva(1l, persona.getId(), hotel.getId(), date);

	@BeforeAll
	static void setUpBeforeAll() throws Exception {
		accessJdbc = new AccessJdbc("hoteles", "root", "root");
		reservaDAOJdbc = new ReservaDAOJdbc(accessJdbc);
		hotelDAOJdbc = new HotelDAOJdbc(accessJdbc);
		personaDAOJdbc = new PersonaDAOJdbc(accessJdbc);
	}

	@BeforeEach
	void setUpBeforeEach() throws Exception {
		accessJdbc.executeUpdate("delete FROM reserva;");
		accessJdbc.executeUpdate("ALTER TABLE reserva AUTO_INCREMENT = 1;");
		
		accessJdbc.executeUpdate("delete FROM persona;");
		personaDAOJdbc.create(persona);
		accessJdbc.executeUpdate("delete FROM hotel;");
		hotelDAOJdbc.create(hotel);
	}

	@Test
	void testCreate() {
		int size = reservaDAOJdbc.findAll().size();
		reservaDAOJdbc.create(reserva);
		assertEquals(reservaDAOJdbc.findAll().size(), size + 1);
	}

	@Test
	void testFindById() {
		Reserva byId = reservaDAOJdbc.findById(reserva.getId());
		if(byId==null)
			reservaDAOJdbc.create(reserva);
			reserva.setId(reservaDAOJdbc.findById(reserva.getId()).getId());
		assertNotNull(reservaDAOJdbc.findById(reserva.getId()));
	}

	@Test
	void testDelete() {
		int size = reservaDAOJdbc.findAll().size();
		Reserva delete = reservaDAOJdbc.delete(reserva.getId());
		if (delete != null)
			assertEquals(reservaDAOJdbc.findAll().size(), size - 1);
		else
			assertEquals(reservaDAOJdbc.findAll().size(), size);
	}

	@Test
	void testFindByPersonaID() {
		Collection<Reserva> byPersonaID = reservaDAOJdbc.findByPersonaID(persona.getId());
		for (Iterator iterator = byPersonaID.iterator(); iterator.hasNext();) {
			Reserva reserva = (Reserva) iterator.next();
			
			assertEquals(reserva.getIdPersona(), persona.getId());
		}
	}

	@Test
	void testFindByHotelID() {
		Collection<Reserva> byHotelID = reservaDAOJdbc.findByHotelID(hotel.getId());
		for (Iterator iterator = byHotelID.iterator(); iterator.hasNext();) {
			Reserva reserva = (Reserva) iterator.next();
			
			assertEquals(reserva.getIdHotel(), hotel.getId());
		}
	}

	@Test
	void testFindByDate() {
		Collection<Reserva> byFecha = reservaDAOJdbc.findByDate(date);
		for (Iterator iterator = byFecha.iterator(); iterator.hasNext();) {
			Reserva reserva = (Reserva) iterator.next();
			
			assertEquals(reserva.getFecha(), date);
		}
	}
	
	@Test
	void noRepeatsColumns() {
		reservaDAOJdbc.create(reserva);
		assertEquals(reservaDAOJdbc.findAll().size(), 1);
		
		reservaDAOJdbc.create(reserva);
		assertEquals(reservaDAOJdbc.findAll().size(), 1);
		
		Reserva newReserva = new Reserva(persona.getId(), hotel.getId(), LocalDate.of(2002, 2, 2)); //Fecha diferente
		reservaDAOJdbc.create(newReserva);
		assertEquals(reservaDAOJdbc.findAll().size(), 2);
	}

}
