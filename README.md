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
