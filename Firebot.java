import java.util.Scanner;

public class Firebot {

    public static int height, width, seed, day;
    public static String wind;
    private static Scanner scan;
    private static Simulation sim;
    public static String[] com;
    public static int amountOfBurntdowm = 0;
    public static int amountOfHeight = 0;


    static void command (String com) {
        switch (com) {
            case("bye"): System.out.println("bye");
                         System.exit(1);

            case("help"): System.out.println("BYE\nHELP\n\nDATA\nSTATUS\n\nNEXT <days>\nSHOW <attribute>\n\nFIRE <region>\nWIND <direction>\nEXTINGUISH <region>\n");
                          break;

            case("status"): Simulation.status();
                            break;

            case("data"): Simulation.data();
                          break;

            case("next"): Simulation.nextDays(1, wind);
                          Simulation.status();
                          break;

            default:      System.out.println("Invalid command\n");
                          break;
        }
    }

    static void command (String coma, String comb) {
        switch (coma) {
            case ("show"):  if (comb.equals("fire")) {
                                Simulation.gridFire();
                            } else if (comb.equals("height")) {
                                Simulation.gridHeight();
                            } else {
                                System.out.println("Invalid command");
                            }
                            System.out.print("\n");
                            break;

            case("wind"):   if (comb.equals("none") || comb.equals("east") || comb.equals("south") || comb.equals("west") || comb.equals("north") || comb.equals("all")) {
                              wind = comb;
                              System.out.println("Set wind to " + wind);
                            } else {
                              System.out.println("Invalid command");
                            }
                            System.out.print("\n");
                            break;

            case("fire"):   if (com.length > 3) {
                              int x1 = Integer.parseInt(comb);
                              int y1 = Integer.parseInt(com[2]);
                              int wi = Integer.parseInt(com[3]);
                              int he = Integer.parseInt(com[4]);
                              int jug = 0;
                              if (x1 < 0 || y1 < 0 || wi <= 0 || he <= 0 || x1 >= width || y1 >= height || (x1 + wi) > width || (y1 + he) > height) {
                                  System.out.println("Invalid command\n");
                                  break;
                              }
                              for (int y = 0; y < height; y++) {
                      			for (int x = 0; x < width; x++) {
                      				if (y >= y1 && y < (y1 + he) && x >= x1 && x < (x1 + wi)) {
                                        if (Simulation.trees[y][x].treeheight != 0 && !Simulation.trees[y][x].burntdown && Simulation.trees[y][x].firelel == 0) {
                                            Simulation.trees[y][x].firelel++;
                                            jug = 1;
                                        }
                      				}
                      			}
                      		  }
                              if (jug == 1) {
                                System.out.println("Started a fire");
                              } else if (jug == 0) {
                                System.out.println("No fires were started");
                              }
                            } else if (com.length == 3) {
                                int x1 = Integer.parseInt(comb);
                                int y1 = Integer.parseInt(com[2]);
                                if (x1 < 0 || y1 < 0 || x1 >= width || y1 >= height) {
                                    System.out.println("Invalid command\n");
                                    break;
                                }
                                if (Simulation.trees[y1][x1].treeheight != 0 && !Simulation.trees[y1][x1].burntdown && Simulation.trees[y1][x1].firelel == 0) {
                                    Simulation.trees[y1][x1].firelel++;
                                    System.out.println("Started a fire");
                                } else {
                                    System.out.println("No fires were started");
                                }
                            } else if (com.length == 2 && comb.equals("all")) {
                                int jug = 0;
                                for (int y = 0; y < height; y++) {
                    			 for (int x = 0; x < width; x++) {
                    				if (Simulation.trees[y][x].treeheight != 0 && !Simulation.trees[y][x].burntdown && Simulation.trees[y][x].firelel == 0) {
                                        Simulation.trees[y][x].firelel++;
                                        jug = 1;
                    				}
                    			 }
                    		  }
                              if (jug == 1) {
                                System.out.println("Started a fire");
                              } else {
                                System.out.println("No fires were started");
                              }
                            } else {
                                System.out.println("Invalid command");
                            }
                            System.out.print("\n");
                            break;

            case("extinguish"): if (com.length > 3) {
                              int x1 = Integer.parseInt(comb);
                              int y1 = Integer.parseInt(com[2]);
                              int wi = Integer.parseInt(com[3]);
                              int he = Integer.parseInt(com[4]);
                              int jug = 0;
                              if (x1 < 0 || y1 < 0 || wi <= 0 || he <= 0 || x1 >= width || y1 >= height || (x1 + wi) > width || (y1 + he) > height) {
                                  System.out.println("Invalid command\n");
                                  break;
                              }
                              for (int y = 0; y < height; y++) {
                      			for (int x = 0; x < width; x++) {
                      				if (y >= y1 && y < (y1 + he) && x >= x1 && x < (x1 + wi)) {
                                        if (Simulation.trees[y][x].treeheight != 0 && !Simulation.trees[y][x].burntdown && Simulation.trees[y][x].firelel > 0) {
                                            Simulation.trees[y][x].firelel = 0;
                                            jug = 1;
                                        }
                      				}
                      			}
                      		}
                            if (jug == 1) {
                                System.out.println("Extinguished fires");
                            } else if (jug == 0) {
                                System.out.println("No fires to extinguish");
                            }
                        } else if (com.length == 3) {
                            int x1 = Integer.parseInt(comb);
                            int y1 = Integer.parseInt(com[2]);
                            if (x1 < 0 || y1 < 0 || x1 >= width || y1 >= height) {
                                System.out.println("Invalid command\n");
                                break;
                            }
                            if (Simulation.trees[y1][x1].treeheight != 0 && !Simulation.trees[y1][x1].burntdown && Simulation.trees[y1][x1].firelel != 0) {
                                Simulation.trees[y1][x1].firelel = 0;
                                System.out.println("Extinguished fires");
                            } else {
                                System.out.println("No fires to extinguish");
                            }
                        } else if (com.length == 2 && comb.equals("all")) {
                            int jug = 0;
                            for (int y = 0; y < height; y++) {
                    			for (int x = 0; x < width; x++) {
                    				if (Simulation.trees[y][x].treeheight != 0 && !Simulation.trees[y][x].burntdown && Simulation.trees[y][x].firelel != 0) {
                                        Simulation.trees[y][x].firelel = 0;
                                        jug = 1;
                    				}
                    			}
                    		}
                            if (jug == 1) {
                                System.out.println("Extinguished fires");
                            } else {
                                System.out.println("No fires to extinguish");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                        System.out.print("\n");
                        break;

            case("next"): try {
                              int n = Integer.parseInt(comb);
                              Simulation.nextDays(n, wind);
                              Simulation.status();
                              break;
                          } catch (Exception e) {
                              System.out.println("Invalid command\n");
                              break;
                          }

            default:     System.out.println("Invalid command\n");
                         break;
        }
    }

	public static void main(String[] args) {
        if (args.length != 3) {
            System.exit(1);
        }
        scan = new Scanner(System.in);
        try {
            seed = Integer.parseInt(args[0]);
            width = Integer.parseInt(args[1]);
            height = Integer.parseInt(args[2]);
            wind = "none";
            day = 1;
            sim = new Simulation(width, height, seed);
        } catch (Exception e) {
            System.out.println("Usage: java Firebot <seed> <width> <height>");
            System.exit(1);
        }
        Simulation.status();
        System.out.print("> ");
        while (scan.hasNextLine()) {
            com = scan.nextLine().split(" ");
            try{
                if (com.length > 1) {
                    command(com[0], com[1]);
                } else {
                    command(com[0]);
                }
            } catch (Exception e) {
                System.out.println ("Invalid command\n");
            }
            System.out.print("> ");
        }
        System.out.println("\nbye");
	}
}
