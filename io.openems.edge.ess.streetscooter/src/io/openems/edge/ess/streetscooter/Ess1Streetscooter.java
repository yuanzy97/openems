package io.openems.edge.ess.streetscooter;


import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.event.EventConstants;
import org.osgi.service.metatype.annotations.Designate;

import io.openems.edge.bridge.modbus.api.BridgeModbus;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.event.EdgeEventConstants;
import io.openems.edge.ess.api.Ess;
import io.openems.edge.ess.symmetric.api.SymmetricEss;

@Designate(ocd = Config1.class, factory = true)
@Component(name = "Ess1.Streetscooter", immediate = true, configurationPolicy = ConfigurationPolicy.REQUIRE, property = EventConstants.EVENT_TOPIC
		+ "=" + EdgeEventConstants.TOPIC_CYCLE_BEFORE_CONTROLLERS)
public class Ess1Streetscooter extends AbstractEssStreetscooter implements SymmetricEss, Ess, OpenemsComponent {

	private static final int INVERTER_1_MODE_ADDRESS = 3056;
	private static final int ICU1_SET_POWER_ADRESS = 4002;
	private static final int ICU_1_ENABLED_ADDRESS = 4001;
	
	private static final int BATTERY_1_ADDRESS_OFFSET = 1000;
	private static final int INVERTER_1_ADDRESS_OFFSET = 1000;
	
	
	
	private static final int BATTERY_1_OVERLOAD_ADDRESS = 1001;
	private static final int BATTERY_1_CONNECTED_ADDRESS = 1000; // DAS STIMMT!
	private static final int INVERTER_1_CONNECTED_ADDRESS = 13000;
	private static final int ICU_1_RUNSTATE_ADDRESS = 14001;
	
	@Reference
	private ConfigurationAdmin cm;
	
	public Ess1Streetscooter() {
		super();
	}

	@Activate
	protected
	void activate(ComponentContext context, Config0 config0) {
		super.activate(context, config0.service_pid(), config0.id(), config0.enabled(), UNIT_ID, this.cm, "Modbus", config0.modbus_id());
	}
	
	@Reference(policy = ReferencePolicy.STATIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.MANDATORY)
	protected void setModbus(BridgeModbus modbus) {
		super.setModbus(modbus);
	}
	
	@Override
	protected  int getIcuSetPowerAddress() {
		return ICU1_SET_POWER_ADRESS;
	}

	@Override
	protected  int getInverterModeAddress() {
		return INVERTER_1_MODE_ADDRESS;
	}

	@Override
	protected int getIcuEnabledAddress() {
		return ICU_1_ENABLED_ADDRESS;
	}

	@Override
	protected int getAdressOffsetForBattery() {
		return BATTERY_1_ADDRESS_OFFSET;
	}

	@Override
	protected int getAdressOffsetForInverter() {
		return INVERTER_1_ADDRESS_OFFSET;
	}

	@Override
	protected int getBatteryOverloadAddress() {
		return BATTERY_1_OVERLOAD_ADDRESS;
	}

	@Override
	protected int getBatteryConnectedAddress() {
		return BATTERY_1_CONNECTED_ADDRESS;
	}

	@Override
	protected int getInverterConnectedAddress() {
		return INVERTER_1_CONNECTED_ADDRESS;
	}

	@Override
	protected int getIcuRunstateAddress() {		
		return ICU_1_RUNSTATE_ADDRESS;
	}
}
