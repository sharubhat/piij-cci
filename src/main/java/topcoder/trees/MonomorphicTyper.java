package topcoder.trees;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an expression, we must determine its type. Expressions are formatted as follows:
 *
 * <p><expression> ::= <constant> | <function>(<args>) <args> ::= <expression> | <expression>,<args>
 * <constant> ::= <identifier> <function> ::= <identifier> <identifier> ::= between 1 and 10 letters
 * ('a'-'z', 'A'-'Z'), inclusive Some examples of valid expressions are "x", "upper(x)",
 * "ord(upper(x))", "succ(succ)", "fst(x,x)".
 *
 * <p>To aid us in determining the type of an expression, we are given a list of type definitions.
 * Each element of the String[] definitions is formatted as follows:
 *
 * <p><definition> ::= <type_expression>:<type_name> <type_expression> ::= <constant> |
 * <function>(<type_args>) <type_args> ::= <type_name> | <type_name>,<type_args> <type_name> ::=
 * <identifier> For example:
 *
 * <p>"x:Char" means that the constant "x" has type "Char". "succ(Int):Int" means that the function
 * named "succ" that takes a parameter of type "Int" has type "Int". "upper(Char):Char" means that
 * the function named "upper" that takes a parameter of type "Char" has type "Char". "ord(Char):Int"
 * means that the function named "ord" that takes a parameter of type "Char" has type "Int".
 * "fst(Int,Int):Int" means that the function named "fst" that takes two parameters, both of type
 * "Int", has type "Int". In general, "<constant>:<type_name>" means that the constant named
 * <constant> will have type <type_name>, and "<function>(<type_args>):<type_name>" means that the
 * function named <function> will have type <type_name> if and only if its argument types match
 * <type_args> exactly (the same number of arguments, with the types in the exact order given).
 *
 * <p>Using these example definitions, we can determine the types of three of the example
 * expressions given above. "x" has type "Char", "upper(x)" has type "Char", and "ord(upper(x))" has
 * type "Int". We cannot determine the type of "succ(succ)" because all we know is that a function
 * named "succ" has type "Int" if its argument is of type "Int". In this case, we cannot determine
 * the type of its argument. We cannot determine the type of "fst(x,x)" because all we know is that
 * "fst" is of type "Int" if its two arguments are of type "Int". In this case, both arguments are
 * of type "Char".
 *
 * <p>Given a String expression and a String[] definitions, return the type of expression. If the
 * type cannot be determined using the given definitions, return an empty String ("").
 *
 * <p>Definition
 *
 * <p>Class: MonomorphicTyper Method: infer Parameters: String, String[] Returns: String Method
 * signature: String infer(String expression, String[] definitions) (be sure your method is public)
 *
 * <p>Notes - All identifiers used are case-sensitive, i.e., "a" is not the same identifier as "A".
 *
 * <p>Constraints - expression will be a valid expression (as described in the problem statement). -
 * expression will contain between 1 and 50 characters, inclusive. - definitions will contain
 * between 0 and 20 elements, inclusive. - Each element of definitions will contain between 3 and 50
 * characters, inclusive. - Each element of definitions will be a valid definition (as described in
 * the problem statement); they will contain no spaces. - definitions will contain no duplicate
 * <function>s and no duplicate <constant>s. - No <function> in definitions will have the same name
 * as a <constant> in definitions.
 *
 * <p>Examples 0)
 *
 * <p>"x" {"x:Char"} Returns: "Char" definitions tells us that a constant with the name "x" has type
 * "Char". expression is a constant named "x", so we know that its type is "Char". 1)
 *
 * <p>"upper(x)" {"x:Char","upper(Char):Char"} Returns: "Char" expression is a function named
 * "upper" that takes a constant named "x" as an argument. According to the definitions, a constant
 * named "x" has type "Char", and a function named "upper" that takes an argument of type "Char" has
 * type "Char". Therefore, we know that the type of "upper(x)" is "Char". 2)
 *
 * <p>"ord(upper(letter))" {"ord(Char):Int","upper(Char):Char","letter:Char","Letter:String"}
 * Returns: "Int" Here, the argument to the outer function "ord" is a function named "upper", which
 * takes a constant named "letter" as its argument. Using the logic from the previous example, we
 * can determine that "upper(letter)" has type "Char". This means that the function "ord" in our
 * expression is given an argument of type "Char". We can therefore conclude that the expression has
 * type "Int". Note also that "letter" and "Letter" are different constants (with different types).
 * 3)
 *
 * <p>"id(id)" {"id(Int):Int"} Returns: "" expression is a function named "id" that takes a constant
 * named "id" as an argument. definitions contains no type definition for a constant named "id", so
 * we cannot determine the type of the expression. 4)
 *
 * <p>"f(unknown)" {"f(A):B"} Returns: "" The constant passed as a parameter has no declared type.
 * 5)
 *
 * <p>"fst(zero)" {"fst(Int,Int):Int","zero:Int"} Returns: "" The definition of "fst" takes two
 * "Int" parameters, but only one is provided in the expression. 6)
 *
 * <p>"Func(Int,Char,a)" {"Int:Char", "Func(Char,Char,Int):Int", "Char:Char", "a:Int"} Returns:
 * "Int" Function and constant names are not the same as type names. The expression is well-typed
 * and has type "Int".
 */
public class MonomorphicTyper {
  public String infer(String expression, String[] definitions) {
    Map<String, String> map = new TreeMap<>();
    for (String s : definitions) {
      String[] splits = s.split(":");
      map.put(splits[0], splits[1]);
    }
    String r = map.get(expression);
    if (r != null) {
      return r;
    }
    String functionName = expression.substring(0, expression.indexOf('('));
    String args = expression.substring(expression.indexOf('('), expression.length() - 1);
//    int paren
    return null;
  }
}
