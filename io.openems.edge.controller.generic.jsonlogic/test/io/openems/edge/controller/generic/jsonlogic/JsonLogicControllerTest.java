package io.openems.edge.controller.generic.jsonlogic;

import org.junit.Test;

import io.openems.common.OpenemsConstants;
import io.openems.common.types.ChannelAddress;
import io.openems.edge.common.sum.DummySum;
import io.openems.edge.common.sum.Sum;
import io.openems.edge.common.test.AbstractComponentTest.TestCase;
import io.openems.edge.common.test.DummyComponentManager;
import io.openems.edge.controller.test.ControllerTest;

public class JsonLogicControllerTest {

	private final static ChannelAddress ESS_SOC = new ChannelAddress(OpenemsConstants.SUM_ID,
			Sum.ChannelId.ESS_SOC.id());

	@Test
	public void test() throws Exception {
		new ControllerTest(new JsonLogicController()) //
				.addReference("componentManager", new DummyComponentManager()) //
				.addComponent(new DummySum()) //
				.activate(MyConfig.create() //
						.setRule("{" + //
								"   \"if\":[" + //
								"      {" + //
								"         \"<\": [" + //
								"            {" + //
								"               \"var\": \"" + ESS_SOC + "\"" + //
								"            }," + //
								"            50" + //
								"         ]" + //
								"      }," + //
								"      {" + //
								"         \"ignoreMe\": true," + //
								"         \"setActivePowerEquals\": 5000" + //
								"      }," + //
								"      {" + //
								"         \"ignoreMe\": true," + //
								"         \"setActivePowerEquals\": -5000" + //
								"      }" + //
								"   ]" + //
								"}") //
						.build())
				.next(new TestCase() //
						.input(ESS_SOC, 40));
	}

}
