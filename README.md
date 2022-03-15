# Advance-Program-Language

## p1:

The context-free grammar for the targeted language is as follows: \n
  \<program> ::= \<unitList>
  
  \<unitList> ::= \<unit>\<unitList> | \<unit>
  
  \<unit> ::= \<decl> | \<stmt>
  
  \<decl> ::= \<varDecl> ; | \<varDecl> = \<expr> ;
  
  \<varDecl> ::= int ident | float ident
  
  \<stmt> ::= ident = \<expr> ; | print \<expr> ;
  
  \<expr> ::= intconst | floatconst | ident | \<expr> + \<expr> | ( \<expr> )
  
The type checker to check for the following conditions:
  1) Any variable appearing in an <expr> must have a declaration in some earlier <decl>. For example, int x = 1; int y = x + w; is not allowed because w is not declared. As another example, int x = x+1; is also not allowed (the x in x+1 is not declared).
  2) Each variable can be declared only once. For example, int x; int y = 1; int x = y+1; is not allowed.
  3) In an assignment ident = <expr> ; the variable on the left-hand side of the assignment must be already declared. For example, int x; y = x+1; is not allowed.
  4) In an assignment ident = <expr> ; or a declaration with initialization <varDecl> = <expr> ; the type of the variable on the left-hand side of = must be the same as the type of the expression on the right-hand side. For example, int x; float y = 1.; x = 3.14 + y; is not allowed; neither is int x = 3.14;
  5) Both operands of + must be of the same type. For example, int x = 1; float y = 3.14; float z = y + 1.1; int w = x + z; is not allowed because of x + z.

## p2:

The context-free grammar for the targeted language is as follows: \n
  
\<program> ::= \<unitList>
  
\<unitList> ::= \<unit><unitList> | \<unit>
  
\<unit> ::= \<decl> | \<stmt>
  
\<decl> ::= \<varDecl> ; | \<varDecl> = \<expr> ; \<varDecl> ::= int ident | float ident
  
\<stmt> ::= ident = \<expr> ; | print \<expr> ;
| if ( \<condExpr> ) \<stmt>
| if ( \<condExpr> ) \<stmt> else \<stmt> | while ( \<condExpr> ) \<stmt>
| { \<unitList> }
  
\<expr> ::= intconst | floatconst | ident
| \<binaryExpr> | - \<expr> | ( \<expr> ) | readint | readfloat
  
\<binaryExpr> ::= \<expr> + \<expr> | \<expr> - \<expr> | \<expr> * \<expr> | \<expr> / \<expr> 
  
\<condExpr> ::= \<expr> \< <expr> | \<expr> <= \<expr> | \<expr> > \<expr> | \<expr> >= \<expr>
| \<expr> == \<expr> | \<expr> != \<expr> | \<condExpr> && \<condExpr> | \<condExpr> || \<condExpr> | ! \<condExpr> | ( \<condExpr> )
  
The type checker to check for the following conditions:
1) Any variable appearing in an <expr> must have a declaration in some earlier <decl>, including declarations in surrounding blocks. For example, the following code is correct:
int x = readint + 7;
float y = readfloat + 5. + readfloat;
if (x>0) { x = x + 1; int z = x + 2; { int p = z + 3; x = p + 4; } z = z + 5; { int q = x + 6; } }
However, if we replaced z + 3 with q + 3 , the code would not type check.
2) Each variable can be declared only once inside each <unitList>. So, a program of this form is not valid: int x = ...; float y = ...; int x = ... Similarly, { int z = ...; { ... } int z = ...; } is not valid. However, int z; { float z = ...; { int z = ...; } } is valid since the different declarations of z are not part of the same <unitList>.
3) The innermost surrounding declaration is used to define the type of an occurrence of a variable. For example, int z = ...; { float z = ...; { int z = ...; } { float w = z + 1.1; } } is valid because the occurrence of z in z + 1.1 is matched with the innermost surrounding float z declaration. If this declaration were deleted from the program, the top-level declaration int z would apply to z + 1.1 and the program would not type check.
4) In an assignment ident = <expr> ; the variable on the left-hand side of the assignment must have a declaration in some earlier <decl>, including declarations in surrounding blocks.
5) In an assignment ident = <expr> ; or a declaration with initialization <varDecl> = <expr> ; the type of the variable on the left-hand side of = must be the same as the type of the expression on the right-hand side.
6) Both operands of a binary arithmetic operator + - * / must be of the same type (either INT or FLOAT). The result of the operation is of that same type.
7) The operand of a unity minus operator can be either INT or FLOAT. The result of the operation is that same type.
8) The evaluation of a readint expression produced a value of type INT. The evaluation of a readfloat expression produces a value of type FLOAT.
9) Both operands of a comparison operator < <= > >= == != must be of the same type (either INT or FLOAT).

## P3:
  
  The goal of this project is to build an interpreter for the language from Project 2 (with some minor changes to the language, as defined below). The semantics to be implemented was discussed in class when we considered the definition of operational semantics. Your implementation will use the code for AST building and type checking from Project 2, with some minor changes to Interpreter.java . In your interpreter, first perform the type checking from Project 2 and exit with error code EXIT_STATIC_CHECKING_ERROR if the checking fails. If the checking succeeds, execute the program.
  
  To simplify the project, the following restriction will be imposed on all input programs: no two variables have the same name. This also applies to variables that are in different blocks: so, code such as int z = ...; { ... { int z = ...; } } is never going to be part of a valid input program. You do not have to check whether this restriction is satisfied by the input program: just assume that it is and implement your interpreter under this assumption.
  
  Details:
The semantics was described in class. A few additional details:
1) You have to catch run-time errors for “use of unintialized variable” and “division by zero” and then exit the interpreter with the corresponding error codes.
2) readint and readfloat expressions should read from UNIX stdin and produce a value of the corresponding type. If this reading cannot be performed successfully, the interpreter should exit with error code EXIT_FAILED_STDIN_READ . A simple way to implement this functionality is to use a Scanner object from java.util : e.g., at the very beginning of program execution create an object Scanner s = new Scanner(System.in) and then call s.nextLong() and s.nextDouble() whenever you need to evaluate a readint or readfloat expression. When executing the interpreter from the command line, you can put the input data in some file datafile and then do ./plan programfile < datafile
3) Type INT in our language should be implemented by a Java long type; type FLOAT in our language should be implemented by a Java double type.
you can implement the state 𝛔𝛔 with one map from variable names to values. There is no need to use a tree of maps. At the beginning of execution, the map is empty.
5) The evaluation of boolean && and || operators should use short-circuit semantics.
6) A natural way to implement the checking is to add to each expression class a method evaluate which takes as input a reference to the state, evaluates the expression, and returns the resulting value. Similarly, for each statement class you can use a method execute which takes as input a reference to the state, executes the statement, and as a result changes the state.
