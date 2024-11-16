import java.util.Random;

public class sudoku {
    public static Random rand = new Random();
    public static int randRange(int a, int b) {
        return rand.nextInt(b - a ) + a;
    }

    public static int[][] grilleSimple() {
        int[][] r = {{1,2,3,4,5,6,7,8,9},{4,5,6,7,8,9,1,2,3},{7,8,9,1,2,3,4,5,6},{2,3,1,5,6,4,8,9,7},{5,6,4,8,9,7,2,3,1},{8,9,7,2,3,1,5,6,4},{3,1,2,6,4,5,9,7,8},{6,4,5,9,7,8,3,1,2},{9,7,8,3,1,2,6,4,5}};
        return r;
    }

    public static void afficheGrille(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(arr[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    public static void echangeLignes(int[][] grille, int line1, int line2) {
        int[] tmp = grille[line1];
        grille[line1] = grille[line2];
        grille[line2] = tmp;
    }

    public static void echangeColonnes(int[][] grille, int row1, int row2) {
        for (int i = 0; i < 9; i++) {
            int tmp = grille[i][row1];
            grille[i][row1] = grille[i][row2];
            grille[i][row2] = tmp;
        }
    }

    public static void echangeLignesAleatoire(int[][] grille) {
        int i = randRange(0, 3);
        int j = randRange(0, 3);
        int k = randRange(0, 3);
        while (k == i) {
            k = randRange(0, 3);
        }
        echangeLignes(grille, i+3*j, k+3*j);
    }

    public static void echangeColonnesAleatoire(int[][] grille) {
        int i = randRange(0, 3);
        int j = randRange(0, 3);
        int k = randRange(0, 3);
        while (k == i) {
            k = randRange(0, 3);
        }
        echangeColonnes(grille, i+3*j, k+3*j);
    }

    public static int[][] produitGrillePleineAleatoire(int n) {
        int[][] g = grilleSimple();
        for (n=n; n > 0; n--) {
            if (randRange(0, 2) == 0) {
                echangeLignesAleatoire(g);
            } else {
                echangeColonnesAleatoire(g);
            }
        }
        return g;
    }

    public static int[][] produitGrille(int n, int m) {
        int[][] g = produitGrillePleineAleatoire(n);
        for (m=m; m > 0; m--) {
            int i = randRange(0, 9);
            int j = randRange(0, 9);
            while (g[i][j] == 0) {
                i = randRange(0, 9);
                j = randRange(0, 9);
            }
            g[i][j] = 0;
        }
        return g;
    }

    public static int[] aide(int[][] grille, int x, int y) {
        boolean[] valeursPossible = {true,true,true,true,true,true,true,true,true};
        if (grille[y][x] == 0) {
            for (int i = 0; i < valeursPossible.length; i++) {
                for (int j = 0; j < grille.length; j++) {
                    if (i + 1 == grille[j][x]) {
                        valeursPossible[i] = false;
                    }
                    if (i + 1 == grille[y][j]) {
                        valeursPossible[i] = false;
                    }
                }
                for (int j = y/3*3; j < y/3*3+3; j++) {
                    for (int j2 = x/3*3; j2 < x/3*3+3; j2++) {  
                        if (i + 1 == grille[j][j2]) {
                            valeursPossible[i] = false;
                        }
                    }
                }
            }
            int c = 0;
            for (int i = 0; i < valeursPossible.length; i++) {
                if (valeursPossible[i]) {
                    c++;
                }
            }
            int[] r = new int[c];
            c = 0;
            for (int i = 0; i < valeursPossible.length; i++) {
                if (valeursPossible[i]) {
                    r[c] = i +1;
                    c++;
                }
            }
            return r;
        }
        int[] r = {grille[y][x]};
        return r;
    }

    public static void main(String[] args) {
        int[][] g = produitGrille(10, 40);
        afficheGrille(g);
        System.out.println();
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == 0) {
                    int[] aide = aide(g, i, j);
                    for (int k = 0; k < aide.length; k++) {
                        System.out.print(aide[k]+" ");
                    }
                    System.out.println();
                }
            }
        }
    }
}