function min (nodes) {
	if (nodes.length == 0) {
		return Number.NaN
	}
	minimum = Number.POSITIVE_INFINITY;
	currentNode = nodes.nextNode;
	while (currentNode != null) {
		minimum = Math.min(minimum, currentNode)
		currentNode = nodes.nextNode;
	}
	return minimum;
}