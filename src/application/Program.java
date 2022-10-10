package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o diretório do arquivo .csv: ");
        String path = sc.nextLine();

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){

            List<Employee> list = new ArrayList<>();
            String line = bf.readLine();
            while(line!= null){
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = bf.readLine();


            }
            System.out.println("Salario base dos funcionários que serão selecionados: ");
            Double salario  = sc.nextDouble();

            Comparator<String> comp = (s1, s2)-> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> email = list.stream()
                    .filter(p -> p.getSalary() > salario)
                    .map(p -> p.getEmail()).sorted()
                    .collect(Collectors.toList());



            email.forEach(System.out::println);

            Double somas = list.stream()
                    .filter(p -> p.getName().charAt(0) ==('M'))
                    .map(p-> p.getSalary())
                    .reduce(0.0, (x, y) -> x+ y);

            System.out.println("Soma dos salarios das pessoas que começam com a letra 'M' no nome:" + String.format("%.2f", somas));



        }catch(IOException e){
            System.out.println("Error" + e.getMessage());

        }
        sc.close();

    }
}
