public class Map {
    public static final char WALL = '#';
    public static final char BOX = 'B';
    public static final char STICKY_BOX = 'S';
    public static final char TARGET = 'O';
    public static final char PLAYER = 'P';
    public static final char EMPTY = ' ';
    private char[][][] allMaps;
    private char[][] currentMap;
    public Map(){
        allMaps = new char[5][][];
        allMaps[0] = new char[][]{
                {'#','#','#','#','#'},
                {'#','O',' ','O','#'},
                {'#',' ','B',' ','#'},
                {'#',' ','B','P','#'},
                {'#',' ','B',' ','#'},
                {'#',' ','B',' ','#'},
                {'#','O',' ','O','#'},
                {'#','#','#','#','#'}
        };
        allMaps[1] = new char[][]{
                {'#','#','#','#','#','#'},
                {'#','#','#',' ',' ','#'},
                {'#','#','#','P',' ','#'},
                {'#',' ',' ','B','O','#'},
                {'#',' ',' ','B','O','#'},
                {'#',' ',' ','B','O','#'},
                {'#','#','#','#','#','#'}
        };
        allMaps[2] = new char[][]{
                {'#','#','#','#','#',' '},
                {'#',' ','P',' ','#',' '},
                {'#','O','O','O','#',' '},
                {'#','B','B','B','#','#'},
                {'#',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ','#'},
                {'#','#','#','#','#','#'}
        };
        allMaps[3] = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','#','S','S','S','O','#'},
                {'#',' ',' ',' ','#','#','#'},
                {'#',' ','S',' ',' ',' ','#'},
                {'#',' ','P',' ',' ',' ','#'},
                {'#','#','#','#','#','#','#'}
        };
        allMaps[4] = new char[][]{
               {'#','#','#','#','#','#','#','#','#'},
               {'#',' ',' ',' ','#','S','S','O','#'},
               {'#',' ',' ',' ','#',' ',' ','#','#'},
               {'#','P','S',' ',' ',' ',' ',' ','#'},
               {'#','#','S',' ',' ','S',' ',' ','#'},
               {'#',' ',' ',' ',' ','#','#','#','#'},
               {'#','#','#','#','#','#','#','#','#'}
        };
        currentMap = allMaps[0];
    }
    public void loadLevel(int levelNum) {
        if(levelNum >= 0 && levelNum < allMaps.length){
            currentMap = allMaps[levelNum];
        }
    }
    public char getCoordinate(int x, int y) {
        return currentMap[x][y];
    }
    public boolean isWall(int x, int y) {
        return getCoordinate(x, y) == WALL;
    }
    public char[][] getData() {
        return currentMap;
    }
}
