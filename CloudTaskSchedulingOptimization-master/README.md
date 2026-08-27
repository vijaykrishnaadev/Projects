# Cloud Task Scheduling System

A comprehensive cloud task scheduling platform with support for mobile device clients, intelligent task distribution, and cost optimization.

## Project Idea

This project is a cloud task scheduling system. Users submit tasks from mobile devices, and the system assigns those tasks to suitable virtual machines.

The scheduler chooses resources by considering CPU, RAM, task priority, estimated duration, VM utilization, execution cost, and expected performance. The main purpose is to use available cloud resources efficiently while keeping costs and completion time under control.

The system is designed for application-level and VM-level task management. It is not intended to describe deployment architecture, Kubernetes, cloud infrastructure, or production rollout procedures.

## How the System Works

1. A phone or client connects to the system.
2. A user submits a task with its resource and priority requirements.
3. The system checks the available virtual machines.
4. The selected scheduling strategy evaluates the available resources.
5. A suitable VM is selected for the task.
6. The task status and VM utilization are monitored.
7. Cost, performance, and completion information are made available through the API and CLI.

## Component Responsibilities

- **Core**: Owns domain concepts such as tasks, phones, VMs, data centers, and scheduling algorithms.
- **Server**: Provides the Spring Boot REST API for task, phone, VM, scheduling, health, and reporting operations.
- **CLI**: Provides terminal commands for managing the system without using the web interface.
- **Dashboard**: Intended to provide visual monitoring for tasks, VMs, costs, and system health.

## Scheduling Approach

- **Cost optimized**: Selects an affordable VM that satisfies the task requirements. Best for budget-sensitive workloads.
- **Performance optimized**: Selects a VM expected to complete the task quickly. Best for time-sensitive workloads.
- **Balanced**: Combines cost, available capacity, utilization, priority, and expected execution time. Best for mixed workloads.

## Normal Operating Instructions

1. Start the REST server.
2. Register the available virtual machines.
3. Connect the phones or other task-submission clients.
4. Submit tasks with CPU, RAM, duration, and priority information.
5. Choose a scheduling strategy.
6. Run the scheduler.
7. Check task status and VM utilization.
8. Review health, cost, and performance reports.
9. Change the scheduling strategy or VM resources when the workload changes.

## Environment Requirements

- Java 25 LTS
- Maven 3.9 or later
- PostgreSQL for normal database usage
- H2 for development or demonstration usage
- Node.js and npm only when the dashboard is enabled

## Setup Instructions

1. Install and select JDK 25.
2. Confirm that Maven uses the same JDK.
3. Restore all project modules and their Maven configuration files.
4. Configure the server database connection.
5. Build the project from the directory containing the root `pom.xml`.
6. Start the server.
7. Use the CLI or REST API to register resources and submit tasks.

## Current Workspace Status

The current workspace is incomplete. The root Maven file has been restored, but the `core` module source files and Maven configuration are missing. The CLI source is present, but its Maven configuration is also missing.

The server depends on core model classes such as `Phone`, `Task`, and `VirtualMachine`. Therefore, a complete Maven build cannot succeed until the missing core module is restored. This is a project-file issue, not a Java 25 or Maven cache issue.

## Features

### 🎯 Core Capabilities
- **Mobile Device Integration**: Connect smartphones and tablets as task submission clients
- **Intelligent Task Scheduling**: Cost-optimized and performance-optimized scheduling algorithms
- **Virtual Machine Management**: Register, monitor, and manage VM resources
- **Real-time Monitoring**: Dashboard and CLI tools for system visibility
- **Cost Analysis**: Detailed cost tracking and financial metrics

### 🛠 Components
1. **Core Module** (`core/`) - Domain models and scheduling algorithms
2. **CLI Module** (`cli/`) - Interactive terminal interface for system management
3. **Server Module** (`server/`) - REST API backend with Spring Boot
4. **Dashboard Module** (`dashboard/`) - Web UI (planned)

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│              Mobile Devices (Phones/Tablets)                │
│                   │          │          │                   │
│                   ▼          ▼          ▼                   │
├─────────────────────────────────────────────────────────────┤
│          REST API / WebSocket / Network Layer               │
├─────────────────────────────────────────────────────────────┤
│  ┌─────────────┐  ┌─────────────┐  ┌──────────────────┐   │
│  │   Task Mgmt │  │   VM Manager│  │  Phone Manager   │   │
│  └─────────────┘  └─────────────┘  └──────────────────┘   │
├─────────────────────────────────────────────────────────────┤
│          Scheduling Engine (Cost/Performance/Balanced)      │
├─────────────────────────────────────────────────────────────┤
│        ┌──────────────┐  ┌─────────────────────┐           │
│        │  Data Center │  │   Virtual Machines  │           │
│        └──────────────┘  └─────────────────────┘           │
└─────────────────────────────────────────────────────────────┘
```

## Prerequisites

- Java 25+ (JDK 25.0.1 or later)
- Maven 3.9.16+
- PostgreSQL 12+ (or H2 for development)

## Build & Setup

### 1. Build the Project

```bash
cd CloudTaskSchedulingOptimization-master
mvn clean install -DskipTests
```

### 2. Run the REST API Server

```bash
cd server
mvn spring-boot:run
```

Server will start at `http://localhost:8080`

API Documentation: `http://localhost:8080/swagger-ui.html`

### 3. Use the CLI Terminal

```bash
cd cli
mvn package
java -jar target/cloud-scheduler-cli.jar <command>
```

## CLI Commands

### Phone Management
```bash
# Connect a phone
cloud-scheduler phone connect "iPhone-1" --user alice --ip 192.168.1.100

# List connected phones
cloud-scheduler phone list --verbose

# Get phone status
cloud-scheduler phone status phone-id-123

# Disconnect a phone
cloud-scheduler phone disconnect phone-id-123
```

### Virtual Machine Management
```bash
# Register a VM
cloud-scheduler vm register "VM-Prod-1" --cores 32 --ram 128 --cost-hour 50.0

# List all VMs
cloud-scheduler vm list --sort cost

# Get VM info
cloud-scheduler vm info vm-id-123

# Update VM resources
cloud-scheduler vm update vm-id-123 --cores 64 --ram 256
```

### Task Management
```bash
# Submit a task from a phone
cloud-scheduler task submit "DataProcessing" --phone phone-1 --cpu 4 --ram 8 --duration 300

# List tasks
cloud-scheduler task list --filter running

# Get task details
cloud-scheduler task detail task-id-123

# Cancel a task
cloud-scheduler task cancel task-id-123
```

### Scheduling & Optimization
```bash
# Run the scheduler
cloud-scheduler scheduler schedule --strategy cost --limit 100

# Analyze scheduling state
cloud-scheduler scheduler analyze

# Optimize and rebalance tasks
cloud-scheduler scheduler optimize --metric balanced --simulate

# View scheduling strategies
cloud-scheduler scheduler strategy --list
```

### Data Center Operations
```bash
# Check data center status
cloud-scheduler datacenter status

# Generate reports
cloud-scheduler datacenter report --type financial --period day

# Health check
cloud-scheduler datacenter health

# View/update configuration
cloud-scheduler datacenter config --list
cloud-scheduler datacenter config --set scheduler.strategy=performance
```

## REST API Endpoints

### Tasks
```
GET    /api/v1/tasks
POST   /api/v1/tasks
GET    /api/v1/tasks/{taskId}
PUT    /api/v1/tasks/{taskId}
DELETE /api/v1/tasks/{taskId}
POST   /api/v1/tasks/{taskId}/schedule
GET    /api/v1/tasks/stats/summary
```

### Virtual Machines
```
GET    /api/v1/vms
POST   /api/v1/vms
GET    /api/v1/vms/{vmId}
PUT    /api/v1/vms/{vmId}
DELETE /api/v1/vms/{vmId}
GET    /api/v1/vms/{vmId}/utilization
GET    /api/v1/vms/stats/summary
```

### Phones
```
GET    /api/v1/phones
POST   /api/v1/phones/connect
GET    /api/v1/phones/{phoneId}
POST   /api/v1/phones/{phoneId}/disconnect
PUT    /api/v1/phones/{phoneId}/status
GET    /api/v1/phones/{phoneId}/tasks
POST   /api/v1/phones/{phoneId}/heartbeat
GET    /api/v1/phones/stats/summary
```

## Example Workflow

### 1. Start the System
```bash
# Terminal 1: Start the API server
cd server && mvn spring-boot:run

# Terminal 2: Access CLI
cd cli && java -jar target/cloud-scheduler-cli.jar
```

### 2. Register Infrastructure
```bash
# Register virtual machines
cloud-scheduler vm register "Production-VM" --cores 32 --ram 128 --cost-hour 50
cloud-scheduler vm register "Burst-VM" --cores 64 --ram 256 --cost-hour 100
```

### 3. Connect Mobile Devices
```bash
# Connect phones
cloud-scheduler phone connect "Alice-Phone" --user alice --ip 192.168.1.100
cloud-scheduler phone connect "Bob-Phone" --user bob --ip 192.168.1.101
```

### 4. Submit and Schedule Tasks
```bash
# Submit tasks from phones
cloud-scheduler task submit "DataAnalysis" --phone alice-phone-id --cpu 4 --ram 8 --duration 300
cloud-scheduler task submit "ML-Training" --phone bob-phone-id --cpu 16 --ram 32 --duration 1800

# Run the scheduler
cloud-scheduler scheduler schedule --strategy cost

# Monitor execution
cloud-scheduler task list --filter running
```

### 5. Monitor System Health
```bash
# Check data center status
cloud-scheduler datacenter status

# Analyze performance
cloud-scheduler scheduler analyze

# Generate reports
cloud-scheduler datacenter report --type performance --period day
```

## Configuration

Configuration can be managed via:

1. **CLI Commands**:
   ```bash
   cloud-scheduler datacenter config --list
   cloud-scheduler datacenter config --set key=value
   ```

2. **application.properties** (Server):
   ```
   server.port=8080
   spring.datasource.url=jdbc:postgresql://localhost/cloudscheduler
   ```

3. **Environment Variables**:
   ```bash
   export SCHEDULER_STRATEGY=cost-optimized
   export VM_MAX_UTILIZATION=80
   ```

## Scheduling Strategies

### 1. Cost Optimized (Default)
Minimizes total execution cost while meeting resource requirements.
- Considers: Task cost, VM utilization penalty, task priority
- Best for: Budget-conscious operations

### 2. Performance Optimized
Maximizes speed and responsiveness.
- Considers: Available capacity, low utilization
- Best for: Time-sensitive applications

### 3. Balanced
Balances cost and performance.
- Considers: Hybrid metrics
- Best for: Mixed workload environments

## Monitoring & Metrics

The system provides real-time metrics:

```
Data Center Level:
├── CPU Utilization: 67.5%
├── RAM Utilization: 72.3%
├── Active Tasks: 5
├── Hourly Cost: $175.00
└── Revenue per Task: $50.00

Per VM:
├── CPU: 24/32 cores (75%)
├── RAM: 95/128 GB (74%)
├── Running Tasks: 3/20
└── Cost: $50/hour

Per Phone:
├── Tasks Submitted: 7
├── Tasks Completed: 6
├── Status: Active
└── Battery: 92%
```

## API Usage Example

### cURL Examples
```bash
# List all tasks
curl -X GET http://localhost:8080/api/v1/tasks

# Submit a task
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "phoneId": "phone-1",
    "taskName": "DataProcessing",
    "cpuCoresRequired": 4,
    "ramRequired": 8,
    "estimatedDuration": 300,
    "priority": 75
  }'

# Register a VM
curl -X POST http://localhost:8080/api/v1/vms \
  -H "Content-Type: application/json" \
  -d '{
    "vmName": "Production-1",
    "totalCpuCores": 32,
    "totalRamGb": 128,
    "costPerHour": 50.0
  }'

# Connect a phone
curl -X POST http://localhost:8080/api/v1/phones/connect \
  -H "Content-Type: application/json" \
  -d '{
    "phoneName": "iPhone-User",
    "userId": "user@example.com",
    "deviceModel": "iPhone 15",
    "ipAddress": "192.168.1.100"
  }'
```

## Development

### Project Structure
```
.
├── core/                          # Core domain models and algorithms
│   └── src/main/java/com/cloudscheduler/core/
│       ├── model/                 # Domain entities
│       └── scheduler/             # Scheduling algorithms
├── cli/                           # CLI terminal interface
│   └── src/main/java/com/cloudscheduler/cli/
│       └── *Command.java          # CLI commands
├── server/                        # REST API server
│   └── src/main/java/com/cloudscheduler/server/
│       ├── controller/            # REST endpoints
│       └── service/               # Business logic
├── dashboard/                     # Web UI (planned)
└── pom.xml                        # Maven multi-module POM
```

### Adding a New Feature

1. **Core Logic** (in `core/`):
   ```java
   // Add domain model or algorithm
   public class NewFeature { ... }
   ```

2. **CLI Command** (in `cli/`):
   ```java
   @Command(name = "feature", description = "...")
   public class NewFeatureCommand implements Runnable { ... }
   ```

3. **REST Endpoint** (in `server/`):
   ```java
   @RestController
   @RequestMapping("/api/v1/features")
   public class NewFeatureController { ... }
   ```

## Troubleshooting

### Issue: Compilation errors with Java 25
**Solution**: Ensure you have JDK 25.0.1 installed:
```bash
java -version
# Should show: openjdk version "25.0.1"
```

### Issue: Maven build fails
**Solution**: Clean and rebuild:
```bash
mvn clean install -U -DskipTests
```

### Issue: Port 8080 already in use
**Solution**: Change port in `server/src/main/resources/application.properties`:
```properties
server.port=8081
```

### Issue: No VMs or phones showing
**Solution**: They are pre-loaded in memory for demonstration. To add real ones:
```bash
cloud-scheduler vm register "MyVM" --cores 16 --ram 64 --cost-hour 25
cloud-scheduler phone connect "MyPhone" --user me --ip 192.168.1.100
```

## Performance Considerations

- **Task Submission**: Designed for high throughput (1000s tasks/minute)
- **Scheduling**: Cost-optimized algorithm runs in O(n·m) where n=tasks, m=VMs
- **Memory**: Typical production setup: 2-4 GB for server, 512MB for CLI
- **Network**: Supports multiple phones over WiFi/cellular networks

## Future Enhancements

- [ ] Web Dashboard (Angular/React)
- [ ] Database persistence (PostgreSQL integration)
- [ ] Advanced scheduling (ML-based optimization)
- [ ] Real-time WebSocket updates
- [ ] Multi-region support
- [ ] Mobile app for iOS/Android
- [ ] Container orchestration (Kubernetes) support
- [ ] Advanced analytics and reporting
- [ ] SLA management

## License

MIT License - See LICENSE file

## Contributing

Contributions welcome! Please submit pull requests or issues.

## Support

For issues, questions, or suggestions, please open an issue in the repository.

---

**Built with Java 25, Spring Boot, PicoCLI**
