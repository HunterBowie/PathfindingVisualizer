# My Personal Project

## Pathfinding Visualizer


### What will the application do?

**Example:**

![alt text](image.png)

It will show the process of a pathfingin algorithm searching for a node on a graph. Users will be able to add walls between the start and end points and the algorithm will have to navigate around the walls to reach the end node. Once the algorithm has found the end node, it will draw out the path (sometimes the shortest path, depending on the algorithm) from start to end. 

Algorithms I could simulate:
- A* (this is the first one I will try)
- Depth-first
- Breadth-first
- Greedy Best-first

### Who will use it?

- People interested in how pathfinding algorithms work
- People looking to compare the strengths/weakness of different pathfinding algorithms

### Why is this project of interest to you?

- I like visualizations that look cool
- I like projects I could put in my portfolio

### User Stories

**Phase 1**

- As a user, I want to be able to add a Node (of type wall) to my Graph (arbitary X to Y)
- As a user, I want to be able to view the Nodes (of type wall) I have added to the graph (view Xs in Y)
- As a user, I want to be able to move the start and end positions
- As a user, I want to be able to remove all the Nodes (of type wall)

**Phase 2**

- As a user, I want to be able to save the Wall Nodes I placed on my Graph, the Graph dimensions, the start and end positions, and the type algorithm to a file with a name I provide
- As a user, I want to be prompted to load the Wall Nodes I placed on my Graph, the Graph dimensions, the start and end positions, and the type algorithm using the name of the file
- As a user, I want to be able to choose a pathfinding algorithm and watch that algorithm progress from the start to the end positions
- As a user, I want to be able to see the path the algorithmn determined after it runs


### Instructions for End User (**Phase 3**)

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by running the program and clicking "Load Graph". Then type "graph.json" into the prompt and hit the enter key. You should see a grid of squares. To add walls (black squares) to this graph, simply hold down the left mouse button over the squares you wish to change

- You can generate the second required action related to the user story "adding multiple Xs to a Y" by running the program and clicking "Load Graph". Then type "graph.json" into the prompt and hit the enter key. You should see a grid of squares. To remove walls (black squares) to from graph, simply hold down the right mouse button over the black squares you wish to change

- You can locate my visual component by loading/creating a graph. This can be done by running the program and clicking "Load Graph". Then type "graph.json" into the prompt and hit the enter key. The graph contains colored squares that indicate walls and algorithm progression.

- You can save the state of my application by clicking the "save" button on after loading/creating a graph. You will be promted to enter the name of a file. Once you enter the name (followed by the extension) and press enter (or the submit button). You will have saved your graph. This means the walls, size of the graph, start and end position, and algorithm have been saved.

- You can reload the state of my application by pressing the "load graph" button that appears when you first launch the application and the then typing the name of the file you saved your data in (with extension) and pressing enter.

