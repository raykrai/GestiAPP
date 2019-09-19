/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import lombok.Data;

/**
 * @author jmbargueno
 *
 */

@Data
public class Room {
	private String name;
	private int roomNum;

	private TimeZone timeZone;

	private School school;

}
