package com.example.vragant.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    //TODO: hacer una clase por cada elemento del juego
    //tecnicamente aqui podriamos hacer el juego, pero no seria eficiente
    @Override
    public void draw (Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {

            canvas.drawRGB(114,118, 249);

            Paint paint = new Paint();

            //Malla con cuadrados
            
            int GRID_WIDTH = 9;
            int GRID_HEIGHT = 9;
            int GRID_SIZE = 75;

            for(int i = 0; i < GRID_WIDTH; i++) {
                for(int j = 0; j < GRID_HEIGHT; j++) {
                    int left = i * (GRID_SIZE + 5);
                    int top = j * (GRID_SIZE + 5);
                    int right = left + GRID_SIZE;
                    int bottom = top + GRID_SIZE;
                    paint.setColor(Color.WHITE);
                    canvas.drawRect(new Rect(left, top, right, bottom), paint);
                }
            }

            //Malla con lineas

            /*paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);

            float startX;
            float stopX;
            float startY;
            float stopY;

            int width = canvas.getWidth();
            int height = canvas.getHeight();

            int gridSize = 10;
            int gridSpacing = Math.min(width, height) / gridSize;
            int boardSize = gridSize * gridSpacing;

            int xOffset = (width - boardSize)/2;
            int yOffset = (height - boardSize)/2;

            //Vertical Grid-lines
            for (int i = 0; i < gridSize; i++) {

                startX = xOffset + i*gridSpacing;
                startY = yOffset;

                stopX = startX;
                stopY = startY + boardSize;

                canvas.drawLine(startX, startY, stopX, stopY, paint);

            }

            //Horizontal Grid-lines
            for (int i = 0; i < gridSize; i++) {

                startX = xOffset;
                startY = yOffset + i*gridSpacing;

                stopX = startX + boardSize;
                stopY = startY;

                canvas.drawLine(startX, startY, stopX, stopY, paint);
            }*/
        }
    }

    public void update() {
        //TODO: falta esta mierda
    }
}
