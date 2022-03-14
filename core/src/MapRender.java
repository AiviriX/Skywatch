
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.Map;
import com.aiv.skywatch.Units.Player;

public class MapRender {
    Player player;
    SpriteBatch batch = new SpriteBatch();
    FPSLogger logger = new FPSLogger();
    public Map map;
    OrthographicCamera cam;

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    public MapRender(){
        this.player = new Player("A");
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(false, w, h);
        this.cam.position.set(player.getX(), player.getY(), 0f);
        //this.map = new Map(cam);

    }

    public void renderMap(){
        float delta = Gdx.graphics.getDeltaTime();
        //map.render(delta);
    }
}

