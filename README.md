# GraphViz

**GraphViz** — is a desktop application with a graphical interface designed to visualize graphs, analyze their structure using modern and classical algorithms, and save the analysis results to various storages.

**Screenshots:**
![IMG_20250527_172715](https://github.com/user-attachments/assets/b814c39d-bece-489d-a311-72dbdacb182e)

![image](https://github.com/user-attachments/assets/e7e39ff7-118c-4cd0-b34b-09698ff3652c)


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

