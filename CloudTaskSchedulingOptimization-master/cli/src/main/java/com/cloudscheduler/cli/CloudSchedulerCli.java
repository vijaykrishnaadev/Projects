package com.cloudscheduler.cli;
import picocli.CommandLine; import picocli.CommandLine.Command;
@Command(name="cloud-scheduler",mixinStandardHelpOptions=true,description="Cloud Task Scheduling CLI",subcommands={CommandLine.HelpCommand.class}) public class CloudSchedulerCli implements Runnable { public void run(){System.out.println("Cloud Scheduler CLI. Use --help; use the REST API at http://localhost:8080.");} public static void main(String[] a){System.exit(new CommandLine(new CloudSchedulerCli()).execute(a));} }
