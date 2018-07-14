package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

    //Required varaible
    MainCharacter princess;
    DungeonSurvivor game;
    SpriteBatch batch;
    OrthographicCamera camera;
    Orc orcmonster;
    BitmapFont font;
    ShapeRenderer shape;
    int counter;
    
    //Textures
    private Texture bg1;
    private Texture bg2;
    
    //Toggle booleans
    private boolean hitbox;
    private boolean fps;
    
    public GameScreen(DungeonSurvivor game) {
        this.game = game;
        princess = new MainCharacter("Chara",0,0);
        batch = game.batch;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,800);
        counter=0;
        orcmonster=new Orc(new Rectangle(0,0,64,64));
        shape=new ShapeRenderer();
        font=new BitmapFont();
        
        //Instantiating textures
        bg1=new Texture(Gdx.files.internal("Image/bg1.png"));
        bg2=new Texture(Gdx.files.internal("Image/bg2.jpg"));
        
        hitbox=false;
        fps=false;
        
    }
    
    @Override
    public void show() 
    {
        
    }
    
    @Override
    public void render(float delta) 
    {
        Gdx.gl.glClearColor(0, 0,0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //Getting the delta time
        float dealtatime=Gdx.graphics.getDeltaTime();
        int fpscount=(int)(1/dealtatime);
        
        //Updating the camera
        camera.update();
        
        batch.begin();
        
        //Drawing goes here
        
        //Drawing the background of the game
        batch.draw(bg2,0,0,800,800);
        
        //This means that the user is holding down the left button so we have to move the character to the left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            if(princess.position.x+18.5>=0)
            {
                princess.position.x-=200*dealtatime;
                counter=2;
            }
        }
        //Finally this means that the user is holding down the right button thus we have to move the chracter to the right
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            if(princess.position.x+45.5<=800)
            {
                princess.position.x+=200*dealtatime;
                counter=3;
            }
        }
        //This means that the user is holding down the up button so we have to move the character up
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            if(princess.position.y+50<=800)
            {
                princess.position.y+=200*dealtatime;
                counter=0;
            }
        }
        //This means that the user is holding down the down button so we have to move the character down
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            if(princess.position.y>=0)
            {
                princess.position.y-=200*dealtatime;
                counter=1;
            }
        }
        
        //This means that the user is holding down the left button and shift so we have to move the character to the left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&&Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
        {
             if(princess.position.x+18.5>=0)
            {
                princess.position.x-=300*dealtatime;
                counter=2;
            }
        }
        //Finally this means that the user is holding down the right button and shift thus we have to move the chracter to the right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
        {
            if(princess.position.x+45.5<=800)
            {
                princess.position.x+=300*dealtatime;
                counter=3;
            }
        }
        //This means that the user is holding down the up button and shift so we have to move the character up
        if(Gdx.input.isKeyPressed(Input.Keys.UP)&&Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
        {
            if(princess.position.y+50<=800)
            {
                princess.position.y+=300*dealtatime;
                counter=0;
            }
        }
        //This means that the user is holding down the down button and shift so we have to move the character down
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&&Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
        {
            if(princess.position.y>=0)
            {
                princess.position.y-=300*dealtatime;
                counter=1;
            }
        }
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
        {
            hitbox=!hitbox;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W))
        {
            fps=!fps;
        }
        
        if(counter==0)
        {
            batch.draw(princess.up,princess.position.x,princess.position.y);
        }
        else if(counter==1)
        {
            batch.draw(princess.down,princess.position.x,princess.position.y);
        }
        else if(counter==2)
        {
            batch.draw(princess.left,princess.position.x,princess.position.y);
        }
        else if(counter==3)
        {
            batch.draw(princess.right,princess.position.x,princess.position.y);
        }
        
        
        orcmonster.move(200/60f);
        batch.draw(orcmonster.up,orcmonster.getPosition().x,orcmonster.getPosition().y);
        
        if(princess.position.overlaps(orcmonster.getPosition()))
        {
            System.out.println("BOOPM THE PRINCESS GETS MURDERED");
        }
        
        if(fps)
        {
            font.draw(batch,""+fpscount,0,800);
        }
        batch.end();
        
        shape.begin(ShapeRenderer.ShapeType.Line);
        
        if(hitbox)
        {
            shape.rect(princess.position.x+18.5f,princess.position.y,princess.position.width,princess.position.height);
        }
        
        
        shape.end();
        
        
    }
    
    @Override
    public void resize(int width, int height) 
    {
        
    }
    
    @Override
    public void pause() 
    {
        
    }
    
    @Override
    public void resume() 
    {
        
    }
    
    @Override
    public void hide() 
    {
        
    }
    
    @Override
    public void dispose() 
    {
        
    }
}