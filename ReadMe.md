# QA Prism AI ⭐

## AI-Powered PRD Requirement Analysis Agent

QA Prism AI is a lightweight AI agent that helps QA teams quickly analyze Product Requirement Documents (PRDs) and generate useful QA artifacts such as Executive Summaries, Gap Analysis, Test Scenarios, Test Cases, Risk Analysis, Regression Impact, and Jira Stories.

Built using Java 17, Spring Boot, and Ollama, the application runs completely locally without requiring paid AI APIs.

---

## Features

* Upload PRD PDF documents
* Extract requirements automatically
* Generate Executive Summary
* Generate Gap Analysis
* Generate Test Scenarios
* Generate Test Cases
* Generate Risk Analysis
* Generate Regression Impact Analysis
* Generate Jira Stories
* Uses local LLMs via Ollama (No API Cost)

---

## Tech Stack

| Component   | Technology           |
| ----------- | -------------------- |
| Language    | Java 17              |
| Framework   | Spring Boot 3        |
| Frontend    | Thymeleaf, Bootstrap |
| PDF Parsing | Apache PDFBox        |
| AI Engine   | Ollama               |
| Models      | Qwen, Llama          |
| Build Tool  | Maven                |

---

## Project Structure

```text
qa-prism-ai
│
├── controller
├── service
├── model
├── util
├── templates
├── static
└── application.properties
```

---

## Setup

### 1. Clone Repository

```bash
git clone <repository-url>
cd qa-prism-ai
```

### 2. Install Ollama

Download and install Ollama.

Start Ollama:

```bash
ollama serve
```

### 3. Pull Model

```bash
ollama pull qwen3:latest
```

or

```bash
ollama pull qwen2.5:3b
```

### 4. Configure Application

Update:

```properties
ollama.url=http://localhost:11434/api/generate
ollama.model=qwen3:latest
```

### 5. Run Application

```bash
mvn clean install
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

---

## High Level Flow

```text
PRD PDF Upload
       │
       ▼
PDF Text Extraction
       │
       ▼
Prompt Generation
       │
       ▼
Ollama Local LLM
       │
       ▼
QA Artifact Generation
       │
       ▼
Display Results In UI
```

---

## Why QA Prism AI?

* No API keys required
* Runs completely locally
* Beginner-friendly architecture
* Real-world QA use case
* Easily extensible for additional AI agents
* Great portfolio project for QA Automation Engineers exploring AI

---

## Future Enhancements

* Export Reports (PDF/Excel)
* Multi-Agent Architecture
* Requirement Traceability Matrix
* Regression Coverage Dashboard
* OpenAI / Claude / Gemini Support
* Jira Integration
