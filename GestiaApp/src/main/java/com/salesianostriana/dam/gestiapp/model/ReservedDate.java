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
public class ReservedDate {
	
	private LocalDate date;
	private Boolean locked;

	
}
