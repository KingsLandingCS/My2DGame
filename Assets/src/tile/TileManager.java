package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("/home/zubair/IdeaProjects/My2DGame/Assets/src/maps/world01.txt");

    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/sand.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {

            FileInputStream  fs = new FileInputStream(filePath);

            System.out.println(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));



            int col = 0;
            int row = 0;


            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();


                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {

                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("exception   ");
        }

    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX > gp.player.worldX - gp.player.screenX && worldX < gp.player.worldX + gp.player.screenX && worldY > gp.player.worldY - gp.player.screenY && worldY < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {

                worldCol = 0;
                worldRow++;
            }
        }
    }
}
