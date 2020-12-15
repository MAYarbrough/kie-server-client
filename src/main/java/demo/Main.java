package demo;

import static demo.MyAdvancedConfigurationObject.kieServicesClient;
import static demo.MyAdvancedConfigurationObject.ruleClient;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.command.runtime.SetGlobalCommand;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.FireUntilHaltCommand;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.drools.core.command.runtime.rule.HaltCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.drools.core.command.runtime.rule.InsertObjectInEntryPointCommand;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.RuleServicesClient;

import com.demos.event_planner.Guest;

import demo.MyAdvancedConfigurationObject;

public class Main {
	public static void main(String[] args) {
		rulesTuff();

//		System.out.println(new java.util.Date());
	}

	private static void rulesTuff() {
		MyAdvancedConfigurationObject.initializeKieServerClient();
		MyAdvancedConfigurationObject.initializeDroolsServiceClients();
		
//		InsertObjectCommand insertObjectCommand = new InsertObjectCommand(new Person("john", 25));
//		FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
//		FireUntilHaltCommand fireUntil = new FireUntilHaltCommand();

		List<Command<?>> commandList = new ArrayList<Command<?>>();
//		commandList.add(new InsertObjectCommand(new Guest("john", "michaels", 34, null, null, false, null, "beef")));
//		commandList.add(new AgendaGroupSetFocusCommand("set-meals"));
//		commandList.add(new SetGlobalCommand("maxTables", 6));
//		commandList.add(new FireAllRulesCommand("numberOfFiredRules"));
//		commandList.add(new GetObjectsCommand(new ClassObjectFilter(Object.class), "output"));
//		commandList.add(new FireUntilHaltCommand());
//		commandList.add(new InsertObjectInEntryPointCommand(new Guest("john", "michaels", 34, null, null, false, null, "beef"), "front-door"));
		commandList.add(new HaltCommand());
		

		KieCommands kieCommands = KieServices.Factory.get().getCommands();
		BatchExecutionCommand batch = kieCommands.newBatchExecution(commandList, "default-kie-session");
		ServiceResponse<ExecutionResults> executeResponse = ruleClient.executeCommandsWithResults("event-planner", batch);
		System.out.println("number of fired rules:" + executeResponse.getResult().getValue("numberOfFiredRules"));
	}
}
