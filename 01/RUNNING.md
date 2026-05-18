# Running the Application

## Option 1: Executable (.exe)

Requires **Java 21** installed on your machine.

1. Download and install [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
2. Run `ucs-rcp.exe`

## Option 2: Maven

Requires **JDK 21** and **Maven** installed.

**Compile and run directly (no JAR rebuild):**
```
mvn exec:java "-Dexec.mainClass=marko.App"
```

**Build fat JAR and run:**
```
mvn package -q
java -jar target/ucs-rcp.jar
```

---

## Custom Graph Input Format

When choosing the custom graph option, enter the graph as a string using the following structure:

```
[[(dest, cost), ...], [(dest, cost), ...], ...]
```

- The outer list contains one entry per **node**, in order (node 0, node 1, ...)
- Each inner list contains the **edges** leaving that node as `(destinationId, weight)` pairs
- `destinationId` is a byte (-128..127), `weight` is a short (-32768..32767)

**Examples:**

```
[[(1, 100), (2, 50), (3, 75), (5, 110)], [(0, 100), (1, 0)]]
```
Node 0 connects to nodes 1, 2, 3, 5 with respective costs. Node 1 connects to nodes 0 and 1.

```
[[(1, 100), (2, 75)], [(3, 50)], [(3, 50)]]
```
Three nodes: 0→1 (100), 0→2 (75), 1→3 (50), 2→3 (50).

A node with no outgoing edges can be omitted from the end of the list or represented as `[]`.
