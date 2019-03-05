package sample;

import java.util.Stack;

public class Calculator {
    public static String stringToRPN (String string){
        String newString = "";
        Stack<Character> stack = new Stack<>();
        int priority;

        for (int i=0; i<string.length();i++) {
            priority = getPriority(string.charAt(i));

            if (priority == 1) stack.push(string.charAt(i));
            if (priority == 0){
                newString+=string.charAt(i);
            }
            if (priority > 1){
                newString+=' ';
                if (!stack.empty()){
                    if (getPriority(stack.peek())<priority){
                        stack.push(string.charAt(i));
                    }
                    else{
                        while (getPriority(stack.peek())>=priority){
                            newString+=stack.pop();
                        }
                        stack.push(string.charAt(i));
                    }
                }
                else stack.push(string.charAt(i));
            }
            if (priority == -1){
                while (getPriority(stack.peek())!=1){
                    newString+=stack.pop();
                }
                stack.pop();
            }
        }
        while (!stack.empty()) newString+=stack.pop();
        return newString;
    }
    public static double RPNtoAnswer (String rpn){
        Stack<Double> answer = new Stack<>();
        String operand = "";

        for (int i=0; i<rpn.length(); i++){
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0){
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i))==0){
                    operand+=rpn.charAt(i);
                    i++;
                    if (i==rpn.length()) break;
                }
                answer.push(Double.valueOf(operand));
                operand = "";

            }
            if (getPriority(rpn.charAt(i))>1){
                double a = answer.pop();
                double b = answer.pop();

                if (rpn.charAt(i)=='+') answer.push(b+a);
                if (rpn.charAt(i)=='-') answer.push(b-a);
                if (rpn.charAt(i)=='*') answer.push(b*a);
                if (rpn.charAt(i)=='/') answer.push(b/a);

            }
        }
        return answer.peek();

    }
    private static int getPriority (Character character){
        if (character == '*' || character == '/') return 3;
        if (character == '+' || character == '-') return 2;
        if (character == '(') return 1;
        if (character == ')') return -1;
        else return 0;
    }
}
