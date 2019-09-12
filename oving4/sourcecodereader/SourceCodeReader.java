import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SourceCodeReader {

    private File fil;
    private BufferedReader bufferedReader;
    private ArrayList<Character> stack = new ArrayList<>();
    private String currentLine;
    private int lineNumber = 1;
    private boolean ignore = false;

    public SourceCodeReader(String filepath){
        try{
            fil = new File(filepath);
            bufferedReader = new BufferedReader(new FileReader(fil));
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }

    public  void sourceCode(){
        try{
            while ((currentLine = bufferedReader.readLine()) != null){


                for (int i = 0; i < currentLine.length(); i++) {

                    if(currentLine.charAt(i) == '\'' || currentLine.charAt(i) == '\"'){
                        ignore = !ignore;
                    }

                    if(currentLine.charAt(i) == '(' || currentLine.charAt(i) == '{' || currentLine.charAt(i) == '[' && !ignore){
                        stack.add(currentLine.charAt(i));
                    } else if(currentLine.charAt(i) == ')' || currentLine.charAt(i) == '}' || currentLine.charAt(i) == ']' && !ignore){
                        switch (currentLine.charAt(i)){
                            case ')':
                                try{
                                    compareToStack('(', stack);
                                }catch (Exception e){
                                    compileError(lineNumber, i);
                                    System.exit(1);
                                }
                                break;

                            case '}':
                                try{
                                    compareToStack('{', stack);
                                }catch (Exception e){
                                    compileError(lineNumber, i);
                                    System.exit(1);
                                }
                                break;

                            case ']':
                                try{
                                    compareToStack('[', stack);
                                }catch (Exception e){
                                    compileError(lineNumber, i);
                                    System.exit(1);
                                }
                                break;
                        }
                    }
                }
                lineNumber++;
            }

        if (stack.size() > 0){
            System.out.println("Error, reached end of file while still parsing, expecting: " + stack.get(stack.size() - 1));
            System.exit(1);
        }
            bufferedReader.close();
        } catch(IOException ioe){
            System.out.println(ioe);
        }
}

    public static void main(String[] args) throws Exception {
        SourceCodeReader reader = new SourceCodeReader("test.txt");
        reader.sourceCode();
    }
    private static void compareToStack(char character, ArrayList<Character> stack) throws Exception{
        if (character == stack.get(stack.size() - 1)){
            stack.remove(stack.size() - 1);
        }else {
            throw new Exception("1");
        }
    }

    private static void compileError(int line, int character){
        System.out.println("Error at line " + line + ", character " + character);
    }
}