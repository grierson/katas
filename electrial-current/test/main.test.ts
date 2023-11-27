import { describe, expect, test } from 'vitest'
import { bfs, make_tree, append_node, make_values, total, solve } from '../src/main'

const data = {
	"network": {
		"branches": [
			{ "startNode": 10, "endNode": 20 },
			{ "startNode": 20, "endNode": 30 },
			{ "startNode": 30, "endNode": 50 },
			{ "startNode": 50, "endNode": 60 },
			{ "startNode": 50, "endNode": 90 },
			{ "startNode": 60, "endNode": 40 },
			{ "startNode": 70, "endNode": 80 }
		],
		"customers": [
			{ "node": 30, "numberOfCustomers": 8 },
			{ "node": 40, "numberOfCustomers": 3 },
			{ "node": 60, "numberOfCustomers": 2 },
			{ "node": 70, "numberOfCustomers": 1 },
			{ "node": 80, "numberOfCustomers": 3 },
			{ "node": 90, "numberOfCustomers": 5 }
		]
	},
	"selectedNode": 50
}

describe("append node", () => {
	test("add first key", () => {
		expect(append_node({}, {
			"startNode": 1,
			"endNode": 2
		})).toStrictEqual({
			1: [2],
			2: []
		})
	})

	test("add too existing key", () => {
		expect(append_node({ 1: [2], 2: [] }, {
			"startNode": 1,
			"endNode": 3
		})).toStrictEqual({
			1: [2, 3],
			2: [],
			3: []
		})
	})

	test("maintain previous children", () => {
		expect(append_node({ 1: [2], 2: [3], 3: [] }, {
			"startNode": 3,
			"endNode": 1
		})).toStrictEqual({
			1: [2],
			2: [3],
			3: [1]
		})
	})
})

test("make tree", () => {
	expect(make_tree(data.network.branches)).toStrictEqual({
		10: [20],
		20: [30],
		30: [50],
		50: [60, 90],
		90: [],
		60: [40],
		40: [],
		70: [80],
		80: []
	})
})

test('BFS to find all sub customers', () => {
	const selected = data.selectedNode;
	const tree = make_tree(data.network.branches);
	expect(bfs(tree, selected)).toStrictEqual([60, 90, 40])
})

test('map customer to values', () => {
	const values = make_values(data.network.customers)
	expect(values).toStrictEqual({
		30: 8,
		40: 3,
		60: 2,
		70: 1,
		80: 3,
		90: 5,
	})
})


test('total', () => {
	const values = make_values(data.network.customers)

	expect(total(values, [60, 90, 40])).toBe(10);
})

test('solve', () => {
	expect(solve(data, 50)).toBe(10)
})
