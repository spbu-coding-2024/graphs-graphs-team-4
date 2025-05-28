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

- 
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

The application uses the **MVVM** architectural pattern. Graphs are displayed in a convenient interactive form with visualization of the algorithms' results.

## 🧪 Testing

- Unit tests for algorithms 
- Integration tests with user scenarios (SQLite saving and loading + Ford Bellman and Find Cycles)

## 📦 Technologies

- Language: Kotlin 
- GUI: Jetpack Compose 
- DataBases: SQLite, Neo4j
- Testing: `JUnit5`

## 🚀 Installation and launch

// TODO

