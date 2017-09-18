# String_Calculator
 An **expression** is an **Infix Notation**.
 <br />
 We can use an expression tree to represent an expression and how to calculate it.
 <br />
 Postfix Notation is post order traversal of an expression tree.
 <br />
 We can also use Postfix Notation to save the expression. Postfix Notation is also convenient to calculate the answer.
 
## Expression tree
1. Start with the **lowest priority operator**. It is the root.
2. Choose the lowest priority **operator** from the left hand side and right hand side as the second level **nodes**.
3. If either one of the side only remains **value**, it is **leaf**.
4. Repeat step 2 and 3, until the end of the expression.

## Priority (Precedence) 
1.  different operator
  +  () > ^ > *, / > +, -

2.  same operator
  +  ^ (Power) : Right to Left
  +  *, / : Left to Right
  +  +, - : Left to Right

## How to calculate Postfix Notation ?
1. From left to right, use a **stack** to record **value**.
2. If we meet a value, put it in the stack.
3. If we meet an operator, use it to operate with the last(top) two values in the stack.
<pre>
  The <b>last value</b> of the stack is <b>right hand side</b> and the <b>second last value</b> of the stack is <b>left hand side</b>.  </pre>
4. After operation, put the new value in the stack.
5. Repeat step 2 to 4, until the end of the postfix notation.
6. The only value in the stack is the solution.

## Convert from Infix Notation to Postfix Notation
```
Postfix Notation doesn't need parentheses.
```

1. Decide priorities of all operators, except **"()"**.
2. From left to right, use a **stack** to record **operators**.
3. If we meet a value, it is directly Postfix Notation.
4. If we meet a operator, see the stack and determine whether the last(top) operator in the stack has the higher priority.
<pre>
  + If we meet <b>"("</b>, <b>push</b> it into the stack and go to step 5.
  It is used to separate operators and don't do anything.
  + If we meet <b>")"</b>, <b>pop up</b> elements to Postfix Notation continuously until we meet <b>"("</b>, and go to step 5. 
Postfix Notation doesn't contain <b>"("</b> and <b>")"</b>.
  + If the last(top) operator in the stack has the <b>higher priority</b>, <b>Pop it up</b> to Postfix Notation and determine 
the next one.
  + If it has the <b>lower priority</b> or the <b>stack is empty</b>, <b>push the operator</b> that we met into the stack and go to 
step 5.  
  <i>Repeat checking the last(top) operator in the stack, until its priority is lower than the operator that we 
met.</i>  
</pre>
5. Repeat step 3 and 4, until the end of the Infix Notation.
6. Pop up all the elements in the stack to Postfix Notation.
