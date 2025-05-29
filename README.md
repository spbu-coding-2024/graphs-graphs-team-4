# GraphViz

![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue)
![Gradle](https://img.shields.io/badge/Gradle-8.8-brightgreen)
![Compose](https://img.shields.io/badge/Compose-1.8.0-orange)
![License](https://img.shields.io/badge/License-MIT-blue)
![build](https://img.shields.io/badge/build-passing-brightgreen)


**GraphViz** — is a desktop application with a graphical interface designed to visualize graphs, analyze their structure using modern and classical algorithms, and save the analysis results to various storages.

**Screenshots:**

**Dark theme - 10k vertices and 20k edges:**

![IMG_20250527_172715](https://github.com/user-attachments/assets/b814c39d-bece-489d-a311-72dbdacb182e)

**White theme:**

![image_2025-05-28_22-35-59](https://github.com/user-attachments/assets/7b37f661-eaa5-4f7c-a033-a92536e626ef)

**Video demonstration on YouTube:**

[![Video demonstration](https://img.youtube.com/vi/cyapdboouAI/0.jpg)](https://www.youtube.com/watch?v=cyapdboouAI)

## 🔧 Main functionality

- Graph analysis:
  - Algorithm for graph layout on the plane
  - Search for communities using the Louvain algorithm
  - Classical algorithms:
      - Extraction of strongly connected components
      - Search for cycles from a given vertex
      - Construction of a minimum spanning tree (MST)
      - Search for the shortest path - Ford Bellman
- Saving and loading graphs:
  - Storing graphs in SQLite
  - Integration with Neo4j

## 🖼 Interface

The application uses the **MVVM** architectural pattern. Graphs are displayed in a convenient interactive form with visualization of the algorithms' results

## 🧪 Testing

- Unit tests for algorithms 
- Integration tests with user scenarios (SQLite saving and loading + Ford Bellman and Find Cycles)

## 📦 Technologies

- Language: Kotlin 
- GUI: Jetpack Compose 
- DataBases: SQLite, Neo4j
- Testing: `JUnit5`

## 🚀 Get started

* To download project:
```
    git clone git@github.com:spbu-coding-2024/trees-trees-team-4.git
```
* To build project:
```
    ./gradlew build
```

* To run application:
```
    ./gradlew run
```
* To run a local instance of Neo4j, make sure Docker is installed on your system.
Then, start a Neo4j session using the following command:
```
 docker run -d --name neo4j -p 7474:7474 -p 7687:7687 -e NEO4J_AUTH=neo4j/Test1234554321 -v /home/vkinzin/data/neo4j/data neo4j
```
This will run Neo4j locally at http://localhost:7474 with default credentials:
```
Username: neo4j
Password: test
```

---

## Model
Our model supports multiple graph types:
* [Directed Graph](https://en.wikipedia.org/wiki/Directed_graph)
* [Undirected Graph](https://en.wikipedia.org/wiki/Graph_(discrete_mathematics))
* [Directed Weighted Graph](https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)#Weighted_graph)
* [Undirected Weighted Graph](https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)#Weighted_graph)

## Algorithms

The application provides a collection of classical graph algorithms, implemented in the `algorithms` package and available through the user interface:

* [MST algorithm](https://en.wikipedia.org/wiki/Minimum_spanning_tree) — Kraskal finds a minimum spanning forest of an undirected edge-weighted graph. If the graph is connected, it finds a minimum spanning tree
* [Ford-Bellman algorithm](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm) — finds shortest paths in graphs that may include negative edge weights
* [Find Cycle algorithm](https://en.wikipedia.org/wiki/Cycle_%28graph_theory%29#Algorithm) — determines whether a cycle exists involving a specified vertex
* [Strongly connected - Kosaraju's algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm) — identifies and extracts strongly connected components in directed graphs

In addition to classical algorithms, the application features built-in layout algorithms for automatic graph visualization:

* [Find Communities - Louvain Algoritms](https://github.com/JetBrains-Research/louvain) — The Louvain method for community detection is a greedy optimization method intended to extract non-overlapping communities from large networks
* [Graph Layout](https://github.com/gephi/gephi/wiki/Force-Atlas-2) - ForceAtlas2 is a force-directed layout algorithm based on physical simulation principles like gravity, repulsion, attraction

All algorithms are modularly implemented, making them reusable outside the user interface context

## Supported languages:

  - English
  - Russian
  - Chinese


## 📄 License

This project is licensed under the [**MIT License**](LICENSE).

## 📌 Authors
- **Vladimir Pugovkin**'
- **Rafael**
- **Tatyana Gromova**

© 2025
