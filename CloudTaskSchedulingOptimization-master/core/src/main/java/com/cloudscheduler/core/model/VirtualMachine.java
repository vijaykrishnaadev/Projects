package com.cloudscheduler.core.model;
import java.util.UUID;
public class VirtualMachine {
  private final String id=UUID.randomUUID().toString(); private String vmName; private int totalCpuCores,totalRamGb, usedCpuCores,usedRamGb; private double costPerHour;
  public String getId(){return id;} public String getVmName(){return vmName;} public void setVmName(String v){vmName=v;} public int getTotalCpuCores(){return totalCpuCores;} public void setTotalCpuCores(int v){totalCpuCores=v;} public int getTotalRamGb(){return totalRamGb;} public void setTotalRamGb(int v){totalRamGb=v;} public int getUsedCpuCores(){return usedCpuCores;} public int getUsedRamGb(){return usedRamGb;} public double getCostPerHour(){return costPerHour;} public void setCostPerHour(double v){costPerHour=v;} public boolean canRun(Task t){return totalCpuCores-usedCpuCores>=t.getCpuCoresRequired()&&totalRamGb-usedRamGb>=t.getRamRequired();} public void allocate(Task t){usedCpuCores+=t.getCpuCoresRequired();usedRamGb+=t.getRamRequired();} public double utilization(){return totalCpuCores==0?0:(double)usedCpuCores/totalCpuCores;}
}
