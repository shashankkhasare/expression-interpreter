# Expression Interpreter
This repository contains the code for scanning, parsing and interpreting the expressions. It is based on the expression grammar. The abstract syntax tree generated using expression grammar is then interpreted using a visitor design pattern. Here is the [article](https://medium.com/@shashank.khasare/arithmetic-expression-evaluation-with-the-visitor-pattern-669600a80bcf) for a detailed explanation.

# Usage
1. Use the following command to build the application.
   ```bash
   $ mvn clean install -DskipTests
   ```
2. Use the following command to run the application.
   ```bash
   $ java -jar target/interpreters-1.0.jar
   ```

# References
1. https://en.wikipedia.org/wiki/Parsing_expression_grammar
2. https://en.wikipedia.org/wiki/Visitor_pattern
