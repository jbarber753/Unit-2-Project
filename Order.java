import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Order {
    public Order(ArrayList<Cupcake> cupcakeMenu, ArrayList<Drink> drinkMenu){
        System.out.println("Hello customer. Would you like to place an order? (Y or N)");
        Scanner input = new Scanner(System.in);
        String placeOrder = input.nextLine();
        ArrayList<Object> order = new ArrayList<Object>();
        if (placeOrder.equalsIgnoreCase("y")){
            order.add(LocalDate.now());
            order.add(LocalTime.now());
            int itemNumber = 0;
            System.out.println("Here is our menu:");
            System.out.println("Cupcakes:");
            for (int i = 0; i < cupcakeMenu.size(); i++){
                itemNumber++;
                System.out.println(itemNumber);
                cupcakeMenu.get(i).type();
                System.out.println("Price: " + cupcakeMenu.get(i).getPrice());
                System.out.println();
            }
            System.out.println("Drinks:");
            for (int i = 0; i < drinkMenu.size(); i++){
                itemNumber++;
                System.out.println(itemNumber);
                cupcakeMenu.get(i).type();
                System.out.println("Price: " + cupcakeMenu.get(i).getPrice());
                System.out.println();
            }
            boolean ordering = true;
            while (ordering){
                System.out.println("So what'll it be? (Use the number next to your item of choice)");
                int orderChoice = input.nextInt();
                input.nextLine();
                if (orderChoice > itemNumber || orderChoice < 1){
                    System.out.println("Sorry, we ain't got that");
                }
                else{
                    if (orderChoice <= cupcakeMenu.size()){
                        order.add(cupcakeMenu.get(orderChoice - 1));
                    }
                    else{
                        order.add(drinkMenu.get(orderChoice - 4));
                    }
                }
                System.out.println("Anything else? Y/N");
                placeOrder = input.nextLine();
                if (!placeOrder.equalsIgnoreCase("y")){
                    ordering = false;
                }
            }
            System.out.println("Enjoy your order!");
            System.out.println(order.get(0));
            System.out.println(order.get(1));
            double subtotal = 0.0;
            for (int i = 2; i < order.size(); i++){
                if (cupcakeMenu.contains(order.get(i))){
                    Object item = order.get(i);
                    int index = cupcakeMenu.indexOf(item);
                    cupcakeMenu.get(index).type();
                    System.out.println(cupcakeMenu.get(index).getPrice());
                    subtotal += cupcakeMenu.get(index).getPrice();
                }
                else if (drinkMenu.contains(order.get(i))){
                    Object item = order.get(i);
                    int index = drinkMenu.indexOf(item);
                    drinkMenu.get(index).type();
                    System.out.println(drinkMenu.get(index).getPrice());
                    subtotal += drinkMenu.get(index).getPrice();
                }
            }
            System.out.println("Subtotal: $" + subtotal);
            new CreateFile();
            new WriteToFile(order);
        }
        else{
            System.out.println("Well then, get outta here");
        }
    }
}

class CreateFile{
    public CreateFile(){
        try{
            File salesData = new File("salesData.txt");
            if (salesData.createNewFile()){
                System.out.println("File created: " + salesData.getName());
            }
            else{
                System.out.println("File already exists");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred");
        }
    }
}

class WriteToFile{
    public WriteToFile(ArrayList<Object> order){
        try{
            FileWriter fw = new FileWriter("salesData.txt", true);
            PrintWriter salesWriter = new PrintWriter(fw);
            for (int i = 0; i < order.size(); i++){
                salesWriter.println(order.get(i));
            }
            salesWriter.close();
            System.out.println("Successfully wrote to the file");
        }
        catch(IOException e){
            System.out.println("An error occurred");
        }
    }
}
