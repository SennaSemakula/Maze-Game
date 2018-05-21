package com.semakula;

import java.io.*;

public class Main {
    static Maze game1;
    static int temp_height;
    static int temp_width;

    static String file_string;

    public static void main(String[] args) throws IOException {

        File file = new File("/home/dinopc/Downloads/Gentrack maze_for_candidates/small.txt");
        readFile(file);


        game1 = new Maze(temp_height, temp_width);
        game1.initMaze(file_string);
        game1.populateMaze();
        game1.traverse();

	    printOutput();
    }

    //Method to read file
    //Includes functionality to retrieve width/height of maze
    private static void readFile(File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;

            String test_string[] = br.readLine().split(" ");

            temp_height = Integer.parseInt(test_string[1]);
            temp_width = Integer.parseInt(test_string[0]);


            //skip the first 3 lines due to storing meta data
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }


            file_string = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method that provides functionality to
    //have chosen to segregate this with the business logic
    public static void printOutput(){
        //loop through maze
        int line = 0;

        for(int col = 0; col<=game1.getResultsArr()[1].length - 1; col++){
            for(int row = 0; row<=game1.getResultsArr()[0].length -1; row++)	{
                //check if new column
                if(col > line){
                    //print new line
                    System.out.print("\n");
                    line++;
                }
                System.out.print(game1.getResultsArr()[col][row]);
            }
        }
    }


}
