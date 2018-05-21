package com.semakula;

import java.util.ArrayList;

public class Maze {

    private int width;
    private int height;

    int arr0_width;

    public static char[][] current_position;

    public Maze(int x, int y){
        this.height = x;
        this.width = y;
    }

    public static ArrayList<Character> charset;


    private int test[][];
    public static char[][] results_arr;


    public void initMaze(String file_string){
        charset = new ArrayList<>();
        Character current;

        for(int i = 0; i<= file_string.length() - 1; i++){
            current = file_string.charAt(i);

            if(current != ' ' && current != '\n'){
                charset.add(file_string.charAt(i));
            }

        }

    }

    //Method that sets up the initial data in the maze e.g. walls and paths
    public void populateMaze(){

        test = new int[4][7];
        current_position = new char[width][height];

        int test = 0;
        //check for last item
        for(int i = 0; i<=charset.size() - 1; i++){
            for(int col = 0; col<=width - 1; col++){
                for(int row = 0; row <=height - 1; row++){
                    //refactor below
                    if(test == charset.size()){
                        break;
                    }
                    current_position[col][row] = charset.get(test);
                    test++;
                }
            }
        }

    }

    //function that traverses through the maze, checking each cell and updating
    public void traverse(){
        results_arr = new char[width][height];

        char cell;
        boolean start = false;
        char north_cell = 'c';
        char south_cell = 'c';
        char east_cell = 'c';
        char west_cell = 'c';
        //loop through all the columns in the array

        for(int col = 0; col<=width -1; col++){
            for(int row = 0; row <=current_position[1].length - 1; row++){

                if(row > 0 && row < (width -1)){
                    north_cell = current_position[row-1][row];
                    south_cell = current_position[row+1][row];

                }


                 /* if(row < (current_position[1].length -1)){
                    east_cell = current_position[col][row+1];
                }

                if(row > 0){
                    west_cell = current_position[col][row-1];
                }
*/
                //print path first
                if(col > 0 && (north_cell == '0' && south_cell == '0') && current_position[col][row] != '1'){
                    results_arr[col][row] = 'X';
                    //remove
                    //System.out.println(' ');
                    //print wall
                }else if(current_position[col][row] == '1'){
                    results_arr[col][row] = '#';
                    //remove
                    //System.out.println('#');

                    //print passage
                }else if(current_position[col][row] == '0'){
                    results_arr[col][row] = ' ';
                    //remove
                    //System.out.println('X');
                }

                System.out.println(results_arr[col][row]);
            }
        }


        //update array to populate endpoint value
        updateStartPoint();
        updateEndPoint();

    }

    private void updateStartPoint(){
        boolean start = false;

        for(int col = 0; col <= width - 1; col++){
            for(int row = 0; row<=results_arr[1].length - 1; row++){
                char current_cell = results_arr[col][row];

                if(start != true && (current_cell == 'X' || current_cell == ' ') ){
                    results_arr[col][row] = 'S';
                    start = true;
                }
            }
        }
    }

    //Method to append end location to maze
    private void updateEndPoint(){
        boolean state = false;
        for(int col = width-1; col >= 0; col--){
            for(int row = results_arr[1].length-1; row >= 0; row--){
                //get the last element in array with value 'X' 				or ' '
                if(state != true && (results_arr[col][row] == ' ' || results_arr[col][row] == 'X')){
                    results_arr[col][row] = 'E';
                    //exit loop as endpoint has been found
                    state = true;
                }

            }
        }
    }

    public char[][] getResultsArr(){
        return this.results_arr;
    }


}
