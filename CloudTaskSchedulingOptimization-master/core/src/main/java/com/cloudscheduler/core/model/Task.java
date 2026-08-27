package com.cloudscheduler.core.model;
import java.util.UUID;
public class Task {
  private final String id = UUID.randomUUID().toString(); private String phoneId, taskName, vmId; private int cpuCoresRequired, ramRequired, estimatedDuration, priority; private TaskStatus status = TaskStatus.PENDING;
  public String getId(){return id;} public String getPhoneId(){return phoneId;} public void setPhoneId(String v){phoneId=v;} public String getTaskName(){return taskName;} public void setTaskName(String v){taskName=v;} public String getVmId(){return vmId;} public void setVmId(String v){vmId=v;} public int getCpuCoresRequired(){return cpuCoresRequired;} public void setCpuCoresRequired(int v){cpuCoresRequired=v;} public int getRamRequired(){return ramRequired;} public void setRamRequired(int v){ramRequired=v;} public int getEstimatedDuration(){return estimatedDuration;} public void setEstimatedDuration(int v){estimatedDuration=v;} public int getPriority(){return priority;} public void setPriority(int v){priority=v;} public TaskStatus getStatus(){return status;} public void setStatus(TaskStatus v){status=v;}
}
