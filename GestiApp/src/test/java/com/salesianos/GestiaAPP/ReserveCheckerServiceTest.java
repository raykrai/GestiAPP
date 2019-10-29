package com.salesianos.GestiaAPP;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.salesianostriana.dam.gestiapp.model.Reserve;
import com.salesianostriana.dam.gestiapp.model.ReservedDate;
import com.salesianostriana.dam.gestiapp.model.Room;
import com.salesianostriana.dam.gestiapp.model.RoomCategory;
import com.salesianostriana.dam.gestiapp.model.School;
import com.salesianostriana.dam.gestiapp.model.TimeZone;
import com.salesianostriana.dam.gestiapp.service.ReserveCheckerService;
import com.salesianostriana.dam.gestiapp.service.ReserveService;
import com.salesianostriana.dam.gestiapp.service.ReservedDateService;

@RunWith(JUnitPlatform.class)
public class ReserveCheckerServiceTest {

	@InjectMocks
	private ReserveCheckerService rCService;

	@Mock
	ReservedDateService reservedDateService;

	@Mock
	ReserveService reserveService;

	@Mock
	ReservedDateService reserveDateService;
	
	@Mock
	private static List<Reserve> reserveData = new ArrayList<Reserve>();
	
	@Mock
	private static List<ReservedDate> reservedDateData = new ArrayList<ReservedDate>();

	@BeforeAll
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		reserveData.add(new Reserve());
		reserveData.add(new Reserve());
		
		reservedDateData.add(new ReservedDate(1, LocalDate.of(2019, 10, 31)));
		reservedDateData.add(new ReservedDate(1, LocalDate.of(2019, 10, 28)));
		reservedDateData.add(new ReservedDate(2, LocalDate.of(2019, 10, 21)));
	}

	
	@Mock
	private static Room room = new Room(1L, "Aula", new School(), new RoomCategory());
	
	@Mock
	private static TimeZone timeZone = new TimeZone(1L, "Octava", LocalTime.of(20, 00));

	@Test
	public final void isFreeDateTest() {
		when(reserveDateService.findAll()).thenReturn(reservedDateData);
		boolean resultado = rCService.checkFreeDate(LocalDate.of(2019, 10, 29));
		assertEquals(false, resultado);
	}

	@Test
	public final void notFreeDateTest() {
		when(reserveDateService.findAll()).thenReturn(reservedDateData);
		boolean result = rCService.checkFreeDate(LocalDate.of(2019, 10, 28));
		assertEquals(false, result);
	}

	@Test
	public final void isWeekendTest() {
		when(rCService.findAll().get(0).getWeekendOn()).thenReturn(true);
		boolean result = rCService.checkWeekend(LocalDate.of(2019, 10, 26));
		assertEquals(true, result);

	}

	@Test
	public final void notWeekendTest() {
		when(rCService.findAll().get(0).getWeekendOn()).thenReturn(true);
		boolean result = rCService.checkWeekend(LocalDate.of(2019, 10, 28));
		assertEquals(false, result);

	}

	@Test
	public final void deactivateWeekendTest() {
		when(rCService.findAll().get(0).getWeekendOn()).thenReturn(false);
		boolean result = rCService.checkWeekend(LocalDate.of(2019, 10, 26));
		assertEquals(false, result);

	}

	@Test
	public final void checkReserveDateAndEmptyTest() {
		when(reserveService.findAll().isEmpty()).thenReturn(true);
		boolean result = rCService.checkReservedDate(LocalDate.of(2019, 10, 29), timeZone, room);
		assertEquals(true, result);
	}
	
	@Test
	public final void checkReserveDateAndNotReservedTest() {
		when(reserveService.findAll().isEmpty()).thenReturn(false);
		when(reserveService.findAll()).thenReturn(reserveData);
		boolean result = rCService.checkReservedDate(LocalDate.of(2019, 10, 29), timeZone, room);
		assertEquals(true, result);
	}
	
	@Test
	public final void checkReserveDateAndReservedTest() {
		when(reserveService.findAll().isEmpty()).thenReturn(false);
		when(reserveService.findAll()).thenReturn(reserveData);
		boolean result = rCService.checkReservedDate(LocalDate.of(2019, 10, 29), timeZone, room);
		assertEquals(false, result);
	}
	
	@Test
	public final void checkReserveTrueAndAllFreeTest() {
		LocalDate actualDate =  LocalDate.of(2019, 10, 29);
		LocalDate selectedDate =  LocalDate.of(2019, 10, 30);
		when(LocalDate.now()).thenReturn(selectedDate);
		when(LocalTime.now()).thenReturn(LocalTime.of(22, 00));
		when(rCService.checkFreeDate(actualDate)).thenReturn(false);
		when(rCService.checkWeekend(actualDate)).thenReturn(true);
		when(rCService.checkReservedDate(actualDate, timeZone, room)).thenReturn(true);
		boolean result = rCService.checkReserve(actualDate, timeZone, room);
		assertEquals(true, result);
	}
	
	@Test
	public final void checkReserveFalseAndNotFreeDateTest() {
		LocalDate actualDate =  LocalDate.of(2019, 10, 29);
		LocalDate selectedDate =  LocalDate.of(2019, 10, 31);
		when(LocalDate.now()).thenReturn(selectedDate);
		when(LocalTime.now()).thenReturn(LocalTime.of(22, 00));
		when(rCService.checkFreeDate(selectedDate)).thenReturn(true);
		when(rCService.checkWeekend(selectedDate)).thenReturn(true);
		when(rCService.checkReservedDate(selectedDate, timeZone, room)).thenReturn(true);
		boolean result = rCService.checkReserve(actualDate, timeZone, room);
		assertEquals(false, result);
	}

}
