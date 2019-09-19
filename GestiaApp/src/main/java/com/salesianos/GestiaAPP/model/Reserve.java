/**
 * 
 */
package com.salesianos.GestiaAPP.model;

import lombok.Data;

/**
 * @author jmbargueno
 *
 */

@Data
public class Reserve {

	private Room reservedRoom;
	private User reserveUser;
	private ReservedDate date;
	private TimeZone timeZone;
}
