# OOP_EX2

### Graph
Array of nodes & a corresponding array of balanced Trees
that sorts( by weight) the edges from the vertex stored in the nodes array
in the same location/index.

Allows adding and searching( by weight) an edge in logarithmic time
And to access a specific node in constant time
The class "synchronises" the states/activities of the 2 structures

### Vertices

A dynamic array to allow obtaining and deleting a node in constant time
adding a node takes a constant amortised/average time
uses streams to implement the Iterable interface

if a node has been deleted we want to remember that its place
is available. that's what the list FreeMemory is for.
that's why sometimes the length of the
array wouldn't be the real size of the structure,
so we will keep a size parameter

### Edges
A dynamic array of balanced trees



![](images/filename Ex2_uml.png)
