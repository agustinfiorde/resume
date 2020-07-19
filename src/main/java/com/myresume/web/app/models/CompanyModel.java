
package com.myresume.web.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyModel extends AuditModel{

	private String name;
	private String country;
	private String province;
	
	private Double latitude;
	private Double longitude;

	private String description;
	
}
