type Branch = {
  startNode: number;
  endNode: number;
};

type Customer = {
  node: number,
  numberOfCustomers: number
}

type Graph = {
  [key: number]: number[];
};

export function bfs(graph: Graph, startNode: number): number[] {
  const visited: Set<number> = new Set();
  const queue: number[] = [];

  const result: number[] = [];

  // Enqueue the starting node
  queue.push(startNode);
  visited.add(startNode);

  while (queue.length > 0) {
    const currentNode = queue.shift()!; // Non-null assertion, assuming the graph is well-formed

    // Exclude the start node from the result
    if (currentNode !== startNode) {
      result.push(currentNode);
    }

    for (const neighbor of graph[currentNode]) {
      if (!visited.has(neighbor)) {
        queue.push(neighbor);
        visited.add(neighbor);
      }
    }
  }

  return result;
}


export function append_node(state: Graph, current: Branch) {
  if (current.startNode in state) {
    return {
      ...state,
      [current.startNode]: [...state[current.startNode], current.endNode],
      [current.endNode]: state[current.endNode] ?? []
    };
  } else {
    return {
      ...state,
      [current.startNode]: [current.endNode],
      [current.endNode]: state[current.endNode] ?? []
    };
  }
}

export function make_tree(branches: Branch[]): Graph {
  return branches.reduce(append_node, {})
}

export function make_values(graph: Customer[]): { [key: number]: number } {
  return graph.reduce((state, current) => {
    return { ...state, [current.node]: current.numberOfCustomers }
  }, {});
}

export function total(values: { [key: number]: number }, nodes: number[]) {
  return nodes.map(node => values[node]).reduce((state, value) => state + value, 0);
}

export function solve(data: any, start: number): number {
  const values = make_values(data.network.customers);
  const tree = make_tree(data.network.branches);
  const coll = bfs(tree, start);

  return total(values, coll);
}
