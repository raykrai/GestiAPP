/**
 * 
 */
package com.salesianos.GestiaAPP.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author jmbargueno
 *
 */

@Data
public class Reserve {

	private Room reservedRoom;
	private User reserveUser;
	private LocalDate date;
	private TimeZone timeZone;

}
