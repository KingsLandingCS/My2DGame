package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("/home/zubair/IdeaProjects/My2DGame/Assets/src/water.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
    }
}