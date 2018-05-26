# Arithmetic Evaluator

## Design

- The main idea is to parse the String to be evaluated into a tree structure.
- Ultimately, each node in the tree is either:
  - A value, i.e., an `operand` (in order to accommodate floating number, all are made double)
  - Or an `operator`, with its operands as its children; note that its child can also be an `operator`
- Upon evaluation, all nodes should return a double value:
  - In case of an `operand` node, it simply returns its own value;
  - In case of an `operator` node, it evaluates its operands with its operation. (Note: `minus` operation can have only one numerical operand, it's how negative number is allowed)
- `Parser` is an abstract class, generally it should take in a String and produce a tree of such format. An implementation `SimpleParser` is provided
- If there are unmatched brackets, an exception is raised.

## Known limitation

- Although negative number is supported, it must be wrapped in brackets;
- Divided by 0 is not supported;
- Only `+`, `-`, `*` and `/` are supported.