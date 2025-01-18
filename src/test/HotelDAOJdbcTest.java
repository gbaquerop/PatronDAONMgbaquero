package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Entity.Hotel;
import modelo.ImplDAOJDBC.HotelDAOJdbc;
import modelo.ImplDAOJDBC.PersonaDAOJdbc;
import modelo.acceso.AccessJdbc;

class HotelDAOJdbcTest {
	
	static HotelDAOJdbc hotelDAOJdbc;
	static AccessJdbc accessJdbc;
	Hotel entidad = new Hotel(1l, "Hotel 1");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		accessJdbc = new AccessJdbc("hoteles", "root", "root");
		hotelDAOJdbc = new HotelDAOJdbc(accessJdbc);
		accessJdbc.executeUpdate("delete FROM hotel;");
	}
	
	@AfterEach
	void tearDown() {
		accessJdbc.executeUpdate("delete FROM hotel;");
	}

	@Test
	void testCreate() {
		int size = hotelDAOJdbc.findAll().size();
		hotelDAOJdbc.create(entidad);
		assertEquals(size + 1, hotelDAOJdbc.findAll().size());
	}

	@Test
	void testFindById() {
		Hotel byId = hotelDAOJdbc.findById(entidad.getId());
		if (byId==null) {
			hotelDAOJdbc.create(entidad);
		}
		assertNotNull(hotelDAOJdbc.findById(entidad.getId()));
		
	}

	@Test
	void testDelete() {
		hotelDAOJdbc.create(entidad);
		int sizeBefore = hotelDAOJdbc.findAll().size();
		hotelDAOJdbc.delete(entidad.getId());
		int sizeAfter = hotelDAOJdbc.findAll().size();
		assertNotEquals(sizeBefore, sizeAfter);
		assertEquals(sizeAfter, 0);
	}

	@Test
	void testFindByName() {
		hotelDAOJdbc.create(entidad);
		Hotel hotelbbdd = hotelDAOJdbc.findByName(entidad.getNombre());
		assertEquals(hotelbbdd.getNombre(), entidad.getNombre());
	}

}
