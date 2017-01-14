# String_Calculator

## Priority  
1.  different operator
  +  () > ^ > *, / > +, -

2.  same operator
  +  ^ (Power) : Right to Left
  +  *, / : Left to Right
  +  +, - : Left to Right

## How to calculate postfix notation ?
1. From left to right, use a **stack** to record value
2. If we met a value, put it in the stack.
3. If we met an operator, use it to operate with the last(top) two values in the stack.
**The last value of the stack is right hand side and the second last value of the stack is left hand side.**
4. After operation, put the new value in the stack.
5. Repeat step 2 to 4, until the end of the postfix notation.
6. The only value in the stack is the solution.