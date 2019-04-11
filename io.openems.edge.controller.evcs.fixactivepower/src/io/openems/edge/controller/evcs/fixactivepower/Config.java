package io.openems.edge.controller.evcs.fixactivepower;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(//
		name = "Controller Electric Vehicle Charging Station: Fix Active Power", //
		description = "Defines a fixed charge/discarge power to an Electric Vehicle Charging Station.")
@interface Config {

	String id() default "ctrlEvcsFixActivePower0";

	boolean enabled() default true;

	@AttributeDefinition(name = "Evcs-id", description = "ID of Evcs device.")
	String evcs_id();

	@AttributeDefinition(name = "Charge power [W]", description = "Fix value that should be charged")
	int power();

	String webconsole_configurationFactory_nameHint() default "Controller Electric Vehicle Charging Station: Fix Active Power [{id}]";

}
