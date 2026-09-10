<img src="images/cover_page.png" alt="Workshop cover" style="width:100%;max-width:960px;display:block;margin:0 auto 1.5rem auto;">

# Enterprise Agentic AI: Architecting Autonomous Java Systems for Production

**Hands-On Workshop · 90 min**

!!! tip "Quick access"
    Short URL for this guide: **[bit.ly/agents-labs](https://bit.ly/agents-labs){:target="_blank"}** — share it or bookmark it.

    [:fontawesome-solid-file-pdf: Intro slide deck](images/intro-deck.pdf){:target="_blank" .md-button }

Build intelligent, secure, and observable multi-agent applications on **Quarkus** — from a single agent to a full supervisor orchestration with human-in-the-loop, OpenTelemetry tracing, and A2A remote agents.

## Prerequisites

Confirm these before the workshop starts:

| Requirement | Check |
|-------------|-------|
| **[Java 25+](#install-java-25)** | `java -version` and `javac -version` |
| Maven 3.9+ | or use `./mvnw` in each exercise |
| Quarkus | `./mvnw quarkus:dev` |
| [OpenCode CLI](https://opencode.ai/){:target="_blank"} or any AGENTS.md-compatible AI assistant | Install and configure before the workshop |
| LLM API key | `OPENAI_API_KEY` (OpenAI, Anthropic, or compatible provider) |
| Free ports **8080**, **8888** | One process per exercise set |
| Docker or Podman | For Quarkus Dev Services (PostgreSQL, LGTM) |

### Install Java 25

Install a **JDK 25** so you have both the Java runtime and compiler. [Eclipse Temurin 25](https://adoptium.net/temurin/releases/?version=25){:target="_blank"} provides packages for different operating systems and CPU architectures; choose the latest available Java 25 patch release.

=== "macOS — Homebrew"

    With [Homebrew](https://brew.sh/){:target="_blank"} installed, install [Temurin 25](https://formulae.brew.sh/cask/temurin@25){:target="_blank"} and select it for the current terminal:

    ```bash
    brew install --cask temurin@25
    export JAVA_HOME=$(/usr/libexec/java_home -v 25)
    export PATH="$JAVA_HOME/bin:$PATH"
    ```

    Add the two `export` lines to `~/.zshrc` to keep this selection in new Zsh terminals. If you use Bash, add them to your Bash startup file instead.

=== "Linux / WSL — SDKMAN!"

    Install [SDKMAN!](https://sdkman.io/install/){:target="_blank"} in Bash or Zsh (skip the first command if it is already installed):

    ```bash
    curl -s "https://get.sdkman.io" | bash
    source "$HOME/.sdkman/bin/sdkman-init.sh"
    sdk list java
    ```

    In the list, find the latest stable **Java 25** entry under **Temurin** and copy its full **Identifier** ending in `-tem`. Press `q` to exit the list. Replace `JAVA_25_IDENTIFIER` below with that identifier, then run:

    ```bash
    sdk install java JAVA_25_IDENTIFIER
    sdk use java JAVA_25_IDENTIFIER
    sdk default java JAVA_25_IDENTIFIER
    ```

    SDKMAN! sets `JAVA_HOME` and `PATH`; `sdk use` selects the JDK in this terminal, and `sdk default` selects it for new terminals. See the [SDKMAN! usage guide](https://sdkman.io/usage/){:target="_blank"}. For WSL, install the JDK inside WSL and run the workshop commands there.

=== "Windows — installer"

    Download the **Windows JDK 25 MSI installer** from [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=25){:target="_blank"}. Run it and, on **Custom Setup**, enable both **Add to PATH** and **Set JAVA_HOME**. Finish installation, then reopen your terminal and IDE so they pick up the new environment. See the [official Windows installation guide](https://adoptium.net/installation/windows/){:target="_blank"}.

    If you use WSL for the workshop, follow the **Linux / WSL** tab instead.

Verify the selected JDK in the terminal you will use for the workshop:

```bash
java -version
javac -version
```

Both should report **25** (or a newer version). After cloning the repository below, run `./mvnw -version` from `lab/` and check that Maven also reports **Java version: 25** or newer (`.\mvnw.cmd -version` in Windows PowerShell). If Maven reports an older Java version, update `JAVA_HOME` to the JDK installation directory and put its `bin` directory first in `PATH`. Set your IDE's project JDK and Maven runner JDK to the same version.

## Get the code

```bash
git clone https://github.com/danieloh30/agentic-ai-java-workshop.git
cd agentic-ai-java-workshop
export OPENAI_API_KEY=sk-your-key-here

# Your working project — start here for Exercise 1
cd lab
./mvnw quarkus:dev
```

Open [http://localhost:8080](http://localhost:8080){:target="_blank"} — Incident Dashboard with 8 seeded incidents and status cards.
No agent behavior yet: that's Exercise 1.

<img src="images/incident-dashboard.png" alt="Incident Command Center dashboard" style="width:100%;max-width:960px;display:block;margin:1rem auto;border-radius:8px;box-shadow:0 2px 12px rgba(0,0,0,0.15);">

!!! tip "Reference solutions"
    Solutions live in `solutions/`. Each exercise guide links to its solution at the top — use them only if you get stuck.

## Exercises

| Exercise | Time | Focus |
|----------|------|-------|
| [1. Agent + tool](01-first-agent/START_HERE.md) | 15 min | `TriageAgent` + `TriageTool` |
| [2. Policy as prompt](02-maintenance-agent/START_HERE.md) | 10 min | `DiagnosticAgent` + `@SystemMessage` as policy |
| [3. Parallel agents](03-parallel-workflow/START_HERE.md) | 10 min | `@ParallelMapperAgent` + `@Output` |
| [4. Supervisor orchestration](04-supervisor/START_HERE.md) | 15 min | Full multi-agent supervisor |
| [5. AI governance](05-ai-governance/START_HERE.md) | 10 min | `AGENTS.md` + OpenCode CLI |
| [6. Human gate + tracing](06-hitl-observability/START_HERE.md) | 10 min | Human-in-the-loop + OpenTelemetry |
| [7. Remote agents (A2A)](07-a2a/START_HERE.md) | 10 min | Distributed impact assessment agent |
| [8. Quality loop (bonus)](08-quarkus-flow/START_HERE.md) | 15 min | Programmatic loop with `AgenticServices.loopBuilder()` |

Start with the **[Lab Overview](00-intro/SPEAKER_NOTES.md)** to understand the scenario, architecture, and learning path.

## Repository layout

| Path | Purpose |
|------|---------|
| `lab/` | Your hands-on Quarkus project (stub files with `// TODO`) |
| `AGENTS.md` | Project context file for AI assistants — rules, agent inventory, and conventions loaded once to avoid repeated file scans |
| `docs/` | These lab instructions (this site) |
| `solutions/` | Reference solution projects for each exercise — copy files from here if you get stuck |
| `solutions/08-quarkus-flow/lab/` | Separate starter project for the bonus exercise (not the root `lab/`) |
