package at.tm.rmi.utils;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentParser {

	public static Options options = new Options();

	@SuppressWarnings("static-access")
	public static PIArgs parseArguments(String... args) {

		PIArgs piargs = new PIArgs();
		
		Option hostname = OptionBuilder.hasArg().isRequired().withDescription("The hostname of the Balancer you want to connect with. Argument for client and server.").withArgName("hostname").create("h");
		Option port = OptionBuilder.hasArg().withType(Number.class).isRequired().withDescription("The port of the Balancer you want to connect with. Argument for client, server and balancer.").withArgName("port").create("p");
		Option server_count = OptionBuilder.hasArg().withType(Number.class).withDescription("The number of servers you want to create. Argument for server.").withArgName("servercount").create("S");
		Option client_count = OptionBuilder.hasArg().withType(Number.class).withDescription("The number of clients you want to create. Argument for client.").withArgName("clientcount").create("C");
		Option decimal_places = OptionBuilder.hasArg().isRequired().withType(Number.class).withDescription("Amount of decimal places of PI. Argument for client.").withArgName("decimal").create("d");
		Option server_name = OptionBuilder.hasArg().isRequired().withDescription("The name of the server you want to create. Argument for server.").withArgName("servername").create("n");
		Option serverport = OptionBuilder.hasArg().withType(Number.class).isRequired().withDescription("The port the servers works with, if you start more than one server, the number gets incremented").withArgName("serverport").create("P");
		
		Option client_arg = OptionBuilder.isRequired().withDescription("Selects the client to start").withArgName("client").create("c");
		Option server_arg = OptionBuilder.isRequired().withDescription("Selects the server to start").withArgName("server").create("s");
		Option balancer_arg = OptionBuilder.isRequired().withDescription("Selects the balancer to start").withArgName("balancer").create("b");

		
		if (args[0].equals("-c"))	{
			piargs.setType('c');
			options.addOption(client_arg);
			options.addOption(hostname);
			options.addOption(port);
			options.addOption(decimal_places);
			options.addOption(client_count);
		} else if (args[0].equals("-s")) {
			piargs.setType('s');
			options.addOption(server_arg);
			options.addOption(hostname);
			options.addOption(port);
			options.addOption(serverport);
			options.addOption(server_name);
			options.addOption(server_count);

		} else if (args[0].equals("-b")) {
			piargs.setType('b');
			options.addOption(balancer_arg);
			options.addOption(port);
		}

		CommandLineParser clip = new BasicParser();
		CommandLine line = null;
		try {
			line = clip.parse(options, args);
		} catch (ParseException e) {
			System.err.println("A problem occurred while parsing the arguments, check if your arguments are valid.");
			printHelp();
		}

		if(piargs.getType()=='c'){
			piargs.setClientcount(Integer.parseInt(line.getOptionValue("C")));
			piargs.setHostname(line.getOptionValue("h"));
			piargs.setPort(Integer.parseInt(line.getOptionValue("p")));
			piargs.setDecimal_places(Integer.parseInt(line.getOptionValue("d")));
		}else if(piargs.getType()=='s'){
			piargs.setHostname(line.getOptionValue("h"));
			piargs.setPort(Integer.parseInt(line.getOptionValue("p")));
			piargs.setServer_name(line.getOptionValue("n"));
			piargs.setServercount(Integer.parseInt(line.getOptionValue("S")));
			piargs.setServerport(Integer.parseInt(line.getOptionValue("P")));
		}else if(piargs.getType()=='b'){
			piargs.setPort(Integer.parseInt(line.getOptionValue("p")));
		}
		
		return piargs;

	}

	public static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("RMI", ArgumentParser.options);
	}
}