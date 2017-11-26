import java.io.*;
import java.util.*;
public class MazeGame {
    static int lives,steps,gold,rows,column,cx,cy,fx,fy;
 static String board[][]=new String[1000][1000];
    public static void initialiseGame(String configFileName) throws IOException {
     if(configFileName.equals("DEFAULT")){
      lives=3;
      steps=20;
      gold=0;
      rows=4;
      column=10;
      String def[]=new String[4];
      def[0]="#@ ##  &4#";
      def[1]="##  # ## #";
      def[2]="###  3#   ";
      def[3]="#######  #";
      for(int i=0;i<rows;i++){
       for(int j=0;j<column;j++){
        board[i][j]=def[i].substring(j, j+1);
                  if(board[i][j].equals("&")){
                   cx=j;
                   cy=i;
                  }
                  if(board[i][j].equals("@")){
                   fx=j;
                   fy=i;
                  }
       }
            }
  }else{
   File file=new File(configFileName);
   if(!file.exists()){
    throw new IOException("Error: Could not load the game configuration from '"+configFileName+"'.");
   }
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(configFileName)),"UTF-8"));
            String lineTxt = null;
            lineTxt = br.readLine();
            String[] nums=lineTxt.split(" ");
            lives=Integer.parseInt(nums[0]);
            steps=Integer.parseInt(nums[1]);
            gold=Integer.parseInt(nums[2]);
            rows=Integer.parseInt(nums[3]);
            lineTxt = br.readLine();
            column=lineTxt.length();
            for(int i=0;i<column;i++){
             board[0][i]=lineTxt.substring(i, i+1);
             if(board[0][i].equals("&")){
              cx=i;
              cy=0;
             }
             if(board[0][i].equals("@")){
              fx=i;
              fy=0;
             }
            }
            for(int i=1;i<rows;i++){
             lineTxt = br.readLine();
             for(int j=0;j<column;j++){
              board[i][j]=lineTxt.substring(j, j+1);
              if(board[i][j].equals("&")){
               cx=j;
               cy=i;
              }
              if(board[i][j].equals("@")){
               fx=j;
               fy=i;
              }
             }
            }
            br.close();
  }
    }
    public static void saveGame(String toFileName) throws IOException {
  File writename = new File(toFileName);
  if(writename.exists()){
   throw new IOException("Error: Could not save the current game configuration to '"+toFileName+"'.");
  }
  writename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(lives+" "+steps+" "+gold+" "+rows+"\r\n"); // \r\n
        for(int i=0;i<rows;i++){
         for(int j=0;j<column;j++){
          out.write(board[i][j]);
         }
         out.write("\r\n");
        }
        out.flush();
        out.close();
    }
    public static int getCurrentXPosition() {
        return cx;
    }
    public static int getCurrentYPosition() {
        return cy;
    }
    public static int numberOfLives() {
        return lives;
    }
    public static int numberOfStepsRemaining() {
        return steps;
    }
    public static int amountOfGold() {
        return gold;
    }
    public static boolean isMazeCompleted() {
     if(getCurrentXPosition()==MazeGame.fx&&getCurrentYPosition()==MazeGame.fy){
      return true;
     }
        return false;
    }
    public static boolean isGameEnd() {
     if(isMazeCompleted()||numberOfStepsRemaining()==0||numberOfLives()==0){
      return true;
     }
        return false;
    }
    public static boolean isValidCoordinates(int x, int y) {
        if(y>=0&&y<MazeGame.rows&&x>=0&&x<MazeGame.column){
         return true;
        }
        return false;
    }
    public static boolean canMoveTo(int x, int y) {
     if(!isValidCoordinates(x,y)){
         return false;
        }else if(MazeGame.board[y][x].equals("#")){
         return false;
        }
        return true;
 }
    public static int moveTo(int x, int y) {
     steps--;
     if(canMoveTo(x,y)){
      if(board[y][x].equals(" ")||board[y][x].equals("@")||board[y][x].equals(".")||board[y][x].equals("&")){
          board[y][x]="&";
          System.out.println("Moved to ("+x+", "+y+").");
         }else{
          int g=Integer.parseInt(board[y][x]);
          gold=gold+g;
          board[y][x]="&";
          System.out.println("Moved to ("+x+", "+y+").");
          System.out.println("Plus "+g+" gold.");
         }
      return 1;
     }else{
      lives--;
      System.out.println("Invalid move. One life lost.");
      return 0;
     }
    }
    public static void printHelp() {
     System.out.println("Usage: You can type one of the following commands.");
     System.out.println("help         Print this help message.");
     System.out.println("board        Print the current board.");
     System.out.println("status       Print the current status.");
     System.out.println("left         Move the player 1 square to the left.");
     System.out.println("right        Move the player 1 square to the right.");
     System.out.println("up           Move the player 1 square up.");
     System.out.println("down         Move the player 1 square down.");
     System.out.println("save <file>  Save the current game configuration to the given file.");
    }
    public static void printStatus() {
  System.out.println("Number of live(s): " + lives);
  System.out.println("Number of step(s) remaining: " + steps);
  System.out.println("Amount of gold: " + gold);
    }
    public static void printBoard() {
     for(int i=0;i<rows;i++){
         for(int j=0;j<column;j++){
             System.out.print(board[i][j]);
            }
   System.out.println();
        }
    }
    public static void performAction(String command) throws IllegalArgumentException {
     String command2=command.toLowerCase();
  if (command2.equals("help")) {
   printHelp();
  }else if (command2.equals("board")) {
   printBoard();
  }else if (command2.equals("status")) {
   printStatus();
  }else if (command2.equals("left")) {
   int k=moveTo(cx-1,cy);
   if(k==1){
    board[cy][cx]=".";
    cx--;
   }
  }else if (command2.equals("right")) {
   int k=moveTo(cx+1,cy);
   if(k==1){
    board[cy][cx]=".";
    cx++;
   }
  }else if (command2.equals("up")) {
   int k=moveTo(cx,cy-1);
   if(k==1){
    board[cy][cx]=".";
    cy--;
   }
  }else if (command2.equals("down")) {
   int k=moveTo(cx,cy+1);
   if(k==1){
    board[cy][cx]=".";
    cy++;
   }
  }else if(command2.length()>4){
   if (command2.substring(0, 4).equals("save")) {
    String saving[]=command2.split(" ");
    if(saving.length!=2){
     System.out.println("Error: Could not find command '"+command2+"'.");
     System.out.println("To find the list of valid commands, please type 'help'.");
     throw new IllegalArgumentException();
    }
    try{
     saveGame(saving[1]);
    }catch(IOException e){
     System.out.println(e.getMessage());
    }
   }else{
    String command3=command.replace(" ", "");
    if(command3.length()==0){

    }else{
     System.out.println("Error: Could not find command '"+command+"'.");
     System.out.println("To find the list of valid commands, please type 'help'.");
     throw new IllegalArgumentException();
    }
   }
  }else{
   String command3=command.replace(" ", "");
   if(command3.length()==0){

   }else{
    System.out.println("Error: Could not find command '"+command+"'.");
    System.out.println("To find the list of valid commands, please type 'help'.");
    throw new IllegalArgumentException();
   }
  }
    }
    public static void main(String[] args) {
  if(args.length<1){
   System.out.println("Error: Too few arguments given. Expected 1 argument, found 0.");
   System.out.println("Usage: MazeGame [<game configuration file>|DEFAULT]");
   return;
  }else if(args.length>1){
   System.out.println("Error: Too many arguments given. Expected 1 argument, found " + args.length + ".");
   System.out.println("Usage: MazeGame [<game configuration file>|DEFAULT]");
   return;
  }
  try{
   initialiseGame(args[0]);
  }catch (IOException e) {
   System.out.println(e.getMessage());
   return;
  }
  //Command
  Scanner input = new Scanner(System.in);
  while(!isGameEnd()){
   String command=null;
   try{
    command = input.nextLine();
   }catch(Exception e) {

   }
   if(command==null){
    System.out.println("You did not complete the game.");
    break;
   }
   try{
    performAction(command);
   }catch(IllegalArgumentException e){}
  }
  if(isMazeCompleted()){
   System.out.println("Congratulations! You completed the maze!");
   System.out.println("Your final status is:");
   printStatus();
  }else if(steps==0&&lives==0){
   System.out.println("Oh no! You have no lives and no steps left.");
   System.out.println("Better luck next time!");
  }else if(steps==0){
   System.out.println("Oh no! You have no steps left.");
   System.out.println("Better luck next time!");
  }else if(lives==0){
   System.out.println("Oh no! You have no lives left.");
   System.out.println("Better luck next time!");
  }
 }
}
