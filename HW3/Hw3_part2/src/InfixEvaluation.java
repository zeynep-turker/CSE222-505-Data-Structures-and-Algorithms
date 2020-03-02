public class InfixEvaluation {
    private int numOfVar,numOfStr;
    private String[] str = new String[numOfStr];
    private double[] values = new double[numOfVar];
    private String[] variables = new String[numOfVar];
    public InfixEvaluation(String[] str, int numOfVar, double[] values, String[] variables){
        this.str = str;
        this.numOfVar = numOfVar;
        this.values = values;
        this.variables = variables;
        numOfStr = str.length;
    }
    public boolean IsBalanced(){ //This function decides whether the parentheses are balanced.
        boolean result = true;
        int index = 0;
        MyStack myStack = new MyStack();

        while(index < str.length && result){
            String strr = str[index];
            if(strr.equals("(") || strr.charAt(strr.length()-1) == '(') {
                myStack.push(String.valueOf(strr.charAt(strr.length()-1)));
            }
            else if(strr.equals(")") || strr.charAt(strr.length()-1) == ')'){
                String topCh = myStack.pop();
                if(topCh != null && topCh.equals("("))
                    result = true;
                else
                    result = false;
            }
            index++;
        }
        return result;
    }
    public boolean isOperator(String c) {
        if(c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^")) return true;
        return false;
    }
    public boolean isOperand(String c){
        if(!isOperator(c)) return true;
        return false;
    }
    public int operatorPrecedence(String c) {
        int result;
        switch(c)
        {
            case "+":
            case "-":
                result = 1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            default: result = -1;
        }
        return result;
    }
    public String[] infixToPosfix(){
        int j=0;
        String[] postfix = new String[20];
        MyStack myStack = new MyStack();

        for (int i = 0; i < str.length; i++) {
            String strr = new String(str[i]);

            if(strr.equals("(")){
                myStack.push("(");
            }
            else if(strr.equals(")")){
                while (!myStack.peek().data.equals("(") && !myStack.empty()) {
                    postfix[j] = myStack.pop();
                    j++;
                }
                if (!myStack.empty() && myStack.peek().data.equals("("))
                    myStack.pop();
            }
            else if(isOperator(strr))
            {
                while (!myStack.empty() && operatorPrecedence(strr) <= operatorPrecedence(myStack.peek().data)) {
                    postfix[j] = myStack.pop();
                    j++;
                }
                myStack.push(strr);
            }
            else if(isOperand(strr))
            {
                postfix[j] = strr;
                j++;
            }
        }
        while (!myStack.empty()) {
            postfix[j] = myStack.pop();
            j++;
        }
        return postfix;
    }
    public double evaluatePostfix(){
        if(IsBalanced()) {
            String[] str = infixToPosfix();
            MyStack myStack = new MyStack();
            int i = 0;

            while (str[i] != null) {
                String strr = str[i];
                if (isOperand(strr)) {
                    myStack.push(strr);
                } else {
                    String num2 = myStack.pop();
                    String num1 = myStack.pop();

                    if (strr.equals("+")) {
                        myStack.push(Double.toString(whatValue(num1) + whatValue(num2)));
                    } else if (strr.equals("-")) {
                        myStack.push(Double.toString(whatValue(num1) - whatValue(num2)));
                    } else if (strr.equals("*")) {
                        myStack.push(Double.toString(whatValue(num1) * whatValue(num2)));
                    } else {
                        if( whatValue(num2) == 0) {
                            System.out.println("Divider is not zero in division.");
                            return 0;
                        }
                        myStack.push(Double.toString(whatValue(num1) / whatValue(num2)));
                    }
                }
                i++;
            }
            return Double.parseDouble(myStack.pop());
        }
        else
            System.out.println("Unfortunately, your infix expression has a parentheses error!");
        return 0;
    }
    public double whatValue(String str){ //This function gives values of a string.
        MyMath math = new MyMath();
        for (int i=0; i<numOfVar; ++i ){
            if(str.equals(variables[i])) {
                return values[i];
            }
        }
        if(str.charAt(0) == 's' && str.charAt(1) == 'i' && str.charAt(2) == 'n'){
            for (int i=0; i<numOfVar; ++i ){
                if(str.charAt(4) == variables[i].charAt(0)) {
                    return math.sin(values[i]);
                }
            }
        }
        else if(str.charAt(0) == 'c' && str.charAt(1) == 'o' && str.charAt(2) == 's'){
            for (int i=0; i<numOfVar; ++i ){
                if(str.charAt(4) == variables[i].charAt(0)) {
                    return math.cos(values[i]);
                }
            }
        }
        else if(str.charAt(0) == 'a' && str.charAt(1) == 'b' && str.charAt(2) == 's'){
            for (int i=0; i<numOfVar; ++i ){
                if(str.charAt(4) == variables[i].charAt(0)) {
                    return math.abs(values[i]);
                }
            }
        }
        return Double.parseDouble(str);
    }
}
