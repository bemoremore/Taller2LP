package src;

import parser.SyntaxChecker;
import ast.*;
import syntaxvisitor.GrapherVisitor;
import syntaxvisitor.PrinterVisitor;

import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) {
        try {
            String filename = args.length > 0 ? args[0] : "programa.cmm";
            StringBuilder cadenaAsStringBuilder = new StringBuilder();
            for (String line : Files.readAllLines(Path.of(filename))) {
                cadenaAsStringBuilder.append(line).append("\n");
            }

            System.out.println("Analizando programa: " + filename);
            SyntaxChecker parser = new SyntaxChecker(new java.io.StringReader(cadenaAsStringBuilder.toString()));
            ProgramNode ast = parser.program();

            System.out.println("Análisis sintáctico exitoso!");
            // Generar grafo del AST
            GrapherVisitor grapher = new GrapherVisitor();
            ast.accept(grapher);


            PrinterVisitor printer = new PrinterVisitor();
            ast.accept(printer);

            String outputFile = filename.replaceFirst("[.][^.]+$", "") + "_ast.dot";
            grapher.saveToFile(outputFile);
            System.out.println("Grafo del AST guardado en: " + outputFile);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

