public class Simulation {

	public static double damage;
	public static int pollution;
	public static int numOfTrees;
	private int width;
	private int height;
	public static Tree[][] trees;

	public Simulation(int wi, int he, int se) {
		width = wi;
		height = he;
		pollution = 0;
		damage = 0.0;
		numOfTrees = 0;
		trees = new Tree[he][wi];
		generateTerrain(se);
		for (int y = 0; y < he; y++) {
			for (int x = 0; x < wi; x++) {
				if (trees[y][x].treeheight > 0) {
					numOfTrees++;
				}
			}
		}
	}

	public static void gridHeight() {
		System.out.print("+");
		for (int i = 0; i < 2*(Firebot.width)-1; i++) {
			System.out.print("-");
		}
		System.out.print("+\n");

		for (int y = 0; y < Firebot.height; y++) {
			System.out.print("|");
			for (int x = 0; x < Firebot.width; x++) {
				if (trees[y][x].treeheight != 0) {
					System.out.print(trees[y][x].treeheight);
				} else if (trees[y][x].burntdown) {
					System.out.print("x");
				} else {
					System.out.print(" ");
				}
				if (x != Firebot.width-1) {
					System.out.print(" ");
				}
			}
			System.out.print("|\n");
		}

		System.out.print("+");
		for (int i = 0; i < 2*(Firebot.width)-1; i++) {
			System.out.print("-");
		}
		System.out.print("+\n");
	}

	public static void gridFire() {
		System.out.print("+");
		for (int i = 0; i < 2*(Firebot.width)-1; i++) {
			System.out.print("-");
		}
		System.out.print("+\n");

		for (int y = 0; y < Firebot.height; y++) {
			System.out.print("|");
			for (int x = 0; x < Firebot.width; x++) {
				if (trees[y][x].treeheight != 0) {
					if (trees[y][x].firelel > 0 && trees[y][x].firelel <= 9) {
						System.out.print(trees[y][x].firelel);
					} else {
						System.out.print(".");
					}
				} else if (trees[y][x].burntdown) {
					System.out.print("x");
				} else if (trees[y][x].treeheight == 0) {
					System.out.print(" ");
				}
				if (x != Firebot.width-1) {
					System.out.print(" ");
				}

			}
			System.out.print("|\n");
		}

		System.out.print("+");
		for (int i = 0; i < 2*(Firebot.width)-1; i++) {
			System.out.print("-");
		}
		System.out.print("+\n");
	}

	static void nextDays (int num, String wind) {
        Firebot.day += num;
        for (int i = 0; i < num; i++) {
            switch (wind) {
                case "none": for (int y = 0; y < Firebot.height; y++) {
    			             for (int x = 0; x < Firebot.width; x++) {
    				            if (trees[y][x].firelel != 0 && !trees[y][x].burntdown) {
    					            trees[y][x].firelel ++;
                                    if (trees[y][x].firelel > 9) {
                                        trees[y][x].treeheight --;
                                        trees[y][x].firelel = 9;
                                        if (trees[y][x].treeheight <= 0) {
                                            trees[y][x].treeheight = 0;
                                            trees[y][x].burntdown = true;
                                        }
                                    }
    				            }
    			             }
    		              }
                          break;

                case "east":
                            for (int m = 0; m < Firebot.height; m++) {
                                for (int n = 0; n < Firebot.width; n++) {
                                    trees[m][n].stFir = false;
                                }
                            }
                         for (int y = 0; y < Firebot.height; y++) {
    			             for (int x = 0; x < Firebot.width; x++) {
    				            if (trees[y][x].firelel != 0 && !trees[y][x].burntdown && !trees[y][x].stFir) {
    					            trees[y][x].firelel++;
                                    if (trees[y][x].firelel > 9) {
                                        trees[y][x].treeheight --;
                                        trees[y][x].firelel = 9;
                                        if (trees[y][x].treeheight == 0) {
                                            trees[y][x].burntdown = true;
                                        }
                                    }
                                    if ((x + 1) == Firebot.width) {
                                        continue;
                                    }
                                    if (trees[y][x+1].treeheight != 0 && trees[y][x+1].firelel == 0 && !trees[y][x+1].burntdown && (x+1) < Firebot.width) {
                                        trees[y][x+1].firelel ++;
                                        trees[y][x+1].stFir = true;
                                        if (trees[y][x+1].firelel > 9) {
                                            trees[y][x+1].treeheight --;
                                            trees[y][x+1].firelel = 9;
                                            if (trees[y][x+1].treeheight == 0) {
                                                trees[y][x+1].burntdown = true;
                                            }
                                        }
                                    }
    				            }
    			             }
    		              }

                      break;

                case "south":
                            for (int m = 0; m < Firebot.height; m++) {
                                for (int n = 0; n < Firebot.width; n++) {
                                    trees[m][n].stFir = false;
                                }
                            }
                         for (int y = 0; y < Firebot.height; y++) {
    			             for (int x = 0; x < Firebot.width; x++) {
    				            if (trees[y][x].firelel != 0 && !trees[y][x].burntdown && !trees[y][x].stFir) {
    					            trees[y][x].firelel ++;
                                    if (trees[y][x].firelel > 9) {
                                        trees[y][x].treeheight --;
                                        trees[y][x].firelel = 9;
                                        if (trees[y][x].treeheight == 0) {
                                            trees[y][x].burntdown = true;
                                        }
                                    }
                                    if ((y + 1) == Firebot.height) {
                                        continue;
                                    }
                                    if (trees[y+1][x].treeheight != 0 && trees[y+1][x].firelel == 0 && !trees[y+1][x].burntdown && (y+1) < Firebot.height) {
                                        trees[y+1][x].firelel ++;
                                        trees[y+1][x].stFir = true;
                                        if (trees[y+1][x].firelel > 9) {
                                            trees[y+1][x].treeheight --;
                                            trees[y+1][x].firelel = 9;
                                            if (trees[y+1][x].treeheight == 0) {
                                                trees[y+1][x].burntdown = true;
                                            }
                                        }
                                    }
    				            }
    			             }
    		              }
                      break;

                case "west":
                            for (int m = 0; m < Firebot.height; m++) {
                                for (int n = 0; n < Firebot.width; n++) {
                                    trees[m][n].stFir = false;
                                }
                            }
                         for (int y = 0; y < Firebot.height; y++) {
    			             for (int x = 0; x < Firebot.width; x++) {
    				            if (trees[y][x].firelel != 0 && !trees[y][x].burntdown && !trees[y][x].stFir) {
    					            trees[y][x].firelel ++;
                                    if (trees[y][x].firelel > 9) {
                                        trees[y][x].treeheight --;
                                        trees[y][x].firelel = 9;
                                        if (trees[y][x].treeheight == 0) {
                                            trees[y][x].burntdown = true;
                                        }
                                    }
                                    if ((x - 1) < 0) {
                                        continue;
                                    }
                                    if (trees[y][x-1].treeheight != 0 && trees[y][x-1].firelel == 0 && !trees[y][x-1].burntdown && (x-1) >= 0) {
                                        trees[y][x-1].firelel ++;
                                        trees[y][x-1].stFir = true;
                                        if (trees[y][x-1].firelel > 9) {
                                            trees[y][x-1].treeheight --;
                                            trees[y][x-1].firelel = 9;
                                            if (trees[y][x-1].treeheight == 0) {
                                                trees[y][x-1].burntdown = true;
                                            }
                                        }
                                    }
    				            }
    			             }
    		              }

                      break;

                case "north":
                            for (int m = 0; m < Firebot.height; m++) {
                                for (int n = 0; n < Firebot.width; n++) {
                                    trees[m][n].stFir = false;
                                }
                            }
                          for (int y = 0; y < Firebot.height; y++) {
    			             for (int x = 0; x < Firebot.width; x++) {
    				            if (trees[y][x].firelel != 0 && !trees[y][x].burntdown && !trees[y][x].stFir) {
    					            trees[y][x].firelel ++;
                                    if (trees[y][x].firelel > 9) {
                                        trees[y][x].treeheight --;
                                        trees[y][x].firelel = 9;
                                        if (trees[y][x].treeheight == 0) {
                                            trees[y][x].burntdown = true;
                                        }
                                    }
                                    if ((y - 1) < 0) {
                                        continue;
                                    }
                                    if (trees[y-1][x].treeheight != 0 && trees[y-1][x].firelel == 0 && !trees[y-1][x].burntdown && (y-1) >= 0) {
                                        trees[y-1][x].firelel ++;
                                        trees[y-1][x].stFir = true;
                                        if (trees[y-1][x].firelel > 9) {
                                            trees[y-1][x].treeheight --;
                                            trees[y-1][x].firelel = 9;
                                            if (trees[y-1][x].treeheight == 0) {
                                                trees[y-1][x].burntdown = true;
                                            }
                                        }
                                    }

    				            }
    			             }
    		              }

                      break;

                case "all":
                            for (int m = 0; m < Firebot.height; m++) {
                                for (int n = 0; n < Firebot.width; n++) {
                                    trees[m][n].stFir = false;
                                }
                            }
                        for (int y = 0; y < Firebot.height; y++) {
    			             for (int x = 0; x < Firebot.width; x++) {
    				            if (trees[y][x].firelel != 0 && !trees[y][x].burntdown && !trees[y][x].stFir) {
    					            trees[y][x].firelel ++;
                                if ((x + 1) != Firebot.width) {
                                    if (trees[y][x+1].treeheight != 0 && trees[y][x+1].firelel == 0 && !trees[y][x+1].burntdown) {
                                        trees[y][x+1].firelel ++;
                                        trees[y][x+1].stFir = true;
                                        if (trees[y][x+1].firelel > 9) {
                                            trees[y][x+1].treeheight --;
                                            trees[y][x+1].firelel = 9;
                                            if (trees[y][x+1].treeheight == 0) {
                                                trees[y][x+1].burntdown = true;
                                            }
                                        }
                                    }
                                }
                                if ((y + 1) != Firebot.height) {
                                    if (trees[y+1][x].treeheight != 0 && trees[y+1][x].firelel == 0 && !trees[y+1][x].burntdown && (y+1) < Firebot.height) {
                                        trees[y+1][x].firelel ++;
                                        trees[y+1][x].stFir = true;
                                        if (trees[y+1][x].firelel > 9) {
                                            trees[y+1][x].treeheight --;
                                            trees[y+1][x].firelel = 9;
                                            if (trees[y+1][x].treeheight == 0) {
                                                trees[y+1][x].burntdown = true;
                                            }
                                        }
                                    }
                                }
                                if ((x - 1) >= 0) {
                                    if (trees[y][x-1].treeheight != 0 && trees[y][x-1].firelel == 0 && !trees[y][x-1].burntdown && (x-1) >= 0) {
                                        trees[y][x-1].firelel ++;
                                        trees[y][x-1].stFir = true;
                                        if (trees[y][x-1].firelel > 9) {
                                            trees[y][x-1].treeheight --;
                                            trees[y][x-1].firelel = 9;
                                            if (trees[y][x-1].treeheight == 0) {
                                                trees[y][x-1].burntdown = true;
                                            }
                                        }
                                    }
                                }
                                if ((y - 1) >= 0) {
                                    if (trees[y-1][x].treeheight != 0 && trees[y-1][x].firelel == 0 && !trees[y-1][x].burntdown && (y-1) >= 0) {
                                        trees[y-1][x].firelel ++;
                                        trees[y-1][x].stFir = true;
                                        if (trees[y-1][x].firelel > 9) {
                                            trees[y-1][x].treeheight --;
                                            trees[y-1][x].firelel = 9;
                                            if (trees[y-1][x].treeheight == 0) {
                                                trees[y-1][x].burntdown = true;
                                            }
                                        }
                                    }
                                }
                                    if (trees[y][x].firelel > 9) {
                                        trees[y][x].treeheight --;
                                        trees[y][x].firelel = 9;
                                        if (trees[y][x].treeheight == 0) {
                                            trees[y][x].burntdown = true;
                                        }
                                    }
    				            }
    			             }
    		              }

                      break;

            }
            Firebot.amountOfBurntdowm = 0;
            Firebot.amountOfHeight = 0;
            for (int y = 0; y < Firebot.height; y++) {
                for (int x = 0; x < Firebot.width; x++) {
                    if (trees[y][x].burntdown) {
                        Firebot.amountOfBurntdowm++;
                    } else {
                    Simulation.pollution += trees[y][x].firelel;
                    }
                    Firebot.amountOfHeight += trees[y][x].treeheight;
                }
            }
            Simulation.damage = (Firebot.amountOfBurntdowm * 100) / Simulation.numOfTrees;
            Simulation.pollution -= Firebot.amountOfHeight;
            if (Simulation.pollution < 0) {
                Simulation.pollution = 0;
            }
        }
    }

    static void status () {
        System.out.println("Day: "+Firebot.day);
        System.out.printf("Wind: %s\n\n", Firebot.wind);
    }

    static void data () {
        System.out.printf("Damage: %.2f", damage);
        System.out.println("%");
        System.out.println("Pollution: " + pollution + "\n");
    }


	private void generateTerrain(int seed) {

		// ###################################
		// ### DO NOT MODIFY THIS FUNCTION ###
		// ###################################

		Perlin perlin = new Perlin(seed);
		double scale = 10.0 / Math.min(width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double height = perlin.noise(x * scale, y * scale, 0.5);
				height = Math.max(0, height * 1.7 - 0.7) * 10;
				trees[y][x] = new Tree((int) height);
			}
		}
	}
}
